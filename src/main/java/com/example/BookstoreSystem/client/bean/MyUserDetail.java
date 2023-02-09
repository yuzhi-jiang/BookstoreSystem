package com.example.BookstoreSystem.client.bean;

import com.alibaba.fastjson.JSONObject;
import com.example.BookstoreSystem.client.entity.Permission;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

@Accessors(chain = true)//支持链式调用
@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)//返回的json不返回空值
public class MyUserDetail implements Serializable {
    Integer id;

    String username;
    String password;
    String icon;
    String phone;
    String mail;
    Integer status;
    Integer score=0;
    String lastTime;
    String createTime;
    Integer  gender;
    Boolean isEnable;
    public Set<BRole> roles;
    public Set<Permission> permissions;
    public void setData(String username,String icon,String phone,String mail,int status,int score,String lastTime,String createTime,int gender,boolean isEnable){
        this.icon=icon;
        this.username=username;
        this.phone=phone;
        this.mail=mail;
        this.status=status;
        this.score=score;
        this.lastTime=lastTime;
        this.createTime=createTime;
        this.gender=gender;
        this.isEnable=isEnable;
    }

    public String[] getArrayRoles(){
        if (roles==null){
            return null;
        }
        BRole[] bRoles = this.roles
                .toArray(new BRole[this.roles.size()]);


        String[] roles = new String[bRoles.length];
        for (int i = 0; i < bRoles.length; i++) {
            roles[i] = bRoles[i].getName();
            //判断用户是否有角色 ROLE_ADMIN_SUPER 包含 ROLE_ADMIN  ROLE_USER 包含 ROLE_USER
        }
        return roles;
    }
//
//    public Set<Permission> getPermissions() {
//        return permissions;
//    }
//
//    public void setPermissions(Set<Permission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public void setScore(Integer score) {
//        this.score = score;
//    }

    //    public MyUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//        this.username=username;
//        this.password=password;
//    }
//
//    public MyUserDetail(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//        this.username=username;
//        this.password=password;
//        this.isEnable=enabled;
//    }



//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getIcon() {
//        return icon;
//    }
//
//    public void setIcon(String icon) {
//        this.icon = icon;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getMail() {
//        return mail;
//    }
//
//    public void setMail(String mail) {
//        this.mail = mail;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public int getScore() {
//        return score;
//    }
//
//    public void setScore(int score) {
//        this.score = score;
//    }
//
//    public String getLastTime() {
//        return lastTime;
//    }
//
//    public void setLastTime(String lastTime) {
//        this.lastTime = lastTime;
//    }
//
//    public String getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(String createTime) {
//        this.createTime = createTime;
//    }
//
//    public int getGender() {
//        return gender;
//    }
//
//    public void setGender(int gender) {
//        this.gender = gender;
//    }
//
//    public boolean isEnable() {
//        return isEnable;
//    }
//
//    public void setEnable(boolean enable) {
//        isEnable = enable;
//    }
//
    public Set<BRole> getRoles() {
        return roles;
    }
//
//    public void setRoles(Set<BRole> roles) {
//        this.roles = roles;
//    }

    @Override
    public String toString() {
        String res= "MyUserDetail{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", icon='" + icon + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", status=" + status +
                ", score=" + score +
                ", lastTime='" + lastTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", gender=" + gender +
                ", isEnable=" + isEnable +
                ", roles=" + roles +
                ", permissions=" + permissions +
                '}';

        return JSONObject.toJSONString(this);
    }


//    @Override
//    public String toString() {
//        String res = "{";
//            if (id != null) res += "\"id\":" + id ;
//            if (username != null) res += ",\"username\":" + username;
//            if (password != null) res += ",\"password\":" + password;
//            if (icon != null) res += ",\"icon\":" + icon ;
//            if (phone != null) res += ",\"phone\":" + phone;
//            if (mail != null) res += ",\"mail\":" + mail ;
//            if (status != null) res += ",\"status\":" + status;
//            if (score != null) res += ",\"score\":" + score ;
//            if (lastTime != null) res += ",\"lastTime\":" + lastTime;
//            if (createTime != null) res += ",\"createTime\":" + createTime;
//            if(gender!=null) res+=",\"gender\":"+gender;
//            if (roles!=null)res+=",\"roles\":"+roles;
//            if (permissions!=null)res+=",\"permissions\":"+permissions;
//
//        res += "}";
////        return res;
//
////        String  res= "{" +
////                "id=" + id +
////                ", username='" + username + '\'' +
////                ", password='" + password + '\'' +
////                ", icon='" + icon + '\'' +
////                ", phone='" + phone + '\'' +
////                ", mail='" + mail + '\'' +
////                ", status=" + status +
////                ", score=" + score +
////                ", lastTime='" + lastTime + '\'' +
////                ", createTime='" + createTime + '\'' +
////                ", gender=" + gender +
////                ", isEnable=" + isEnable +
////                ", roles=" + roles +
////                '}';
//      return res;
//    }

    public static void main(String[] args) {
        MyUserDetail userDetail = new MyUserDetail().setPassword("").setPhone("").setScore(0).setStatus(0).setUsername("").setIcon("").setGender(0).setRoles(null);
        System.out.println(JSONObject.toJSON(userDetail));
        System.out.println(userDetail);
    }
}
