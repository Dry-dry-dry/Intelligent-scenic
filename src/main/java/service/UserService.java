package service;

import com.cetc52.kjyq.common.PageResult;
import com.cetc52.kjyq.dao.entity.UserEntity;
import com.cetc52.kjyq.dao.entity.UserQueryReq;
import com.cetc52.kjyq.systemmanage.domain.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangyao
 * createTime 2019-7-29
 */
public interface UserService {
    void insert(UserInsertReq req);

    void update(UserUpdateReq req);

    void delete(ListIdReq req);

    UserEntity getById(Integer id);

    UserEntity getByAccountAndPwd(String account, String password);

    UserEntity getByAccount(String account);

    PageResult<UserEntity> list(UserQueryReq page);

    String getUserAccount(ListIdReq req);

     List<GunBulletUserInfo> listPeopleByRole(RoleInfoReq req);

    List<UserEntity> listPeopleByMaster(@Param("master") String master);

}
