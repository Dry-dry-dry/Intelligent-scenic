package entity;

import lombok.Data;

@Data
public class User {
 private int id;
 private String username;
 private String pwd;
 private int roleid;
 private int cardid;
 private String email;
 private String area;
 private String tourist;
 private String company;
 private String guide;
}
