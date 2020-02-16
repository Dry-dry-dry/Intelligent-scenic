package service.impl;

import com.cetc52.kjyq.common.DESUtil;
import com.cetc52.kjyq.common.PageResult;
import com.cetc52.kjyq.dao.entity.RoleEntity;
import com.cetc52.kjyq.dao.entity.UserEntity;
import com.cetc52.kjyq.dao.entity.UserQueryReq;
import com.cetc52.kjyq.dao.entity.UserRoleRelationEntity;
import com.cetc52.kjyq.dao.mapper.RoleMapper;
import com.cetc52.kjyq.dao.mapper.UserMapper;
import com.cetc52.kjyq.systemmanage.domain.dto.*;
import com.cetc52.kjyq.systemmanage.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import static com.cetc52.kjyq.common.ShiroPermission.ALGORITHM_NAME;
import static com.cetc52.kjyq.shirocas.utils.DESUtil.generateKey;

/**
 * @author zhangyao
 * createTime 2019-7-29
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void insert(UserInsertReq req) {
        try {
            UserEntity entity = new UserEntity();
            //提高效率的给实体类赋值
            BeanUtils.copyProperties(req, entity);
            String pass1 = new SimpleHash(ALGORITHM_NAME, entity.getPassword()).toHex();
            Key key = generateKey("goodgoodstudy,daydayup");
            String password = DESUtil.encrypt(key.toString(), pass1);
            entity.setPassword(password);
            userMapper.insert(entity);

            addRelation(req.getRoleIds(), entity.getId());
        }
        catch (Exception e){

        }

    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void update(UserUpdateReq req) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(req, entity);
        userMapper.update(entity);

        userMapper.deleteRelation(req.getId());
        addRelation(req.getRoleIds(), req.getId());
    }

    private void addRelation(List<Integer> roleIds, Integer userId){
        for (Integer roleId : roleIds) {
            UserRoleRelationEntity relation = new UserRoleRelationEntity(userId, roleId);
            userMapper.addRelation(relation);
        }
    }
     //删除操作的时候增加事务
    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void delete(ListIdReq req) {
        userMapper.delete(req.getIds());
        userMapper.deleteRelationByUserIds(req.getIds());
    }

    @Override
    public UserEntity getById(Integer id) {
        UserEntity user = userMapper.getUserById(id);
        return user;
    }

    @Override
    public PageResult<UserEntity> list(UserQueryReq req) {
        PageHelper.startPage(req.getPageNo(), req.getPageSize());
        List<UserEntity> list = userMapper.list(req);
        PageInfo<UserEntity> pageInfo = new PageInfo<>(list);
        for (UserEntity entity : pageInfo.getList()) {
            List<Integer> roleIds = userMapper.getRolesByUserId(entity.getId());
            String roleNames = "";
            for (int i=0; i<roleIds.size(); i++) {
                RoleEntity role = roleMapper.getById(roleIds.get(i));
                String roleName = role.getRoleName();
                roleNames += roleName;
                if (i < roleIds.size()-1){
                    roleNames += ",";
                }
            }
            entity.setRolesName(roleNames);
            entity.setRoleIds(roleIds);
        }
        return new PageResult<>(pageInfo);
    }

    @Override
    public String getUserAccount(ListIdReq req) {
        List<String> accounts = userMapper.getAccountById(req.getIds());
        String result = "";
        for (int i=0; i < accounts.size(); i++) {
            result += accounts.get(0);
            if (i< accounts.size()-1){
                result += ",";
            }
        }
        return result;
    }

    @Override
    public UserEntity getByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }

    @Override
    public UserEntity getByAccountAndPwd(String account, String password) {

        return userMapper.getUserByUserNameAndPassword(account, password);
    }
    @Override
    public  List<GunBulletUserInfo> listPeopleByRole(RoleInfoReq req) {
        List<GunBulletUserInfo> list = new ArrayList<>();
        RoleEntity roleEntitie = roleMapper.roleInfoByRoleName(req.getRoleName());
        List<UserEntity> userEntities = roleMapper.userListByUserId(roleEntitie.getId());
        for (UserEntity userEntity: userEntities
             ) {
            GunBulletUserInfo gunBulletUserInfo = new GunBulletUserInfo();
            gunBulletUserInfo.setDepartment(userEntity.getDepartment());
            gunBulletUserInfo.setId(userEntity.getId());
            gunBulletUserInfo.setMasterRange(userEntity.getMasterRange());
            gunBulletUserInfo.setPhone(userEntity.getPhone());
            gunBulletUserInfo.setUserName(userEntity.getUserName());
            gunBulletUserInfo.setRemark(userEntity.getRemark());
            gunBulletUserInfo.setRoleName(roleEntitie.getRoleName());
            gunBulletUserInfo.setPosition(userEntity.getPosition());
            list.add(gunBulletUserInfo);
        }


        return  list;
    }

    @Override
    public List<UserEntity> listPeopleByMaster(String master) {
        List<String> masterRange = new ArrayList<>();
        List<UserEntity> userEntities = userMapper.allUser();
        for (UserEntity userEntity:userEntities
             ) {
            String[] masters = userEntity.getMasterRange().split(",");
            for (String mas: masters
                 ) {
            if (master.equals(mas)){
                masterRange.add(userEntity.getMasterRange());
            }

            }
        }
        List<UserEntity> userEntities1 = userMapper.listPeopleByMaster(masterRange);
        return userEntities1;
    }
}
