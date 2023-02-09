package com.example.BookstoreSystem.client.bean;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName BUser.java
 * @Description TODO
 * @createTime 2022年04月06日 10:58:00
 */
@Data
public class BUser implements Serializable {
    private Long id; // 用户ID
    // @NotBlank(message = "账号不能为空")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$" ,message = "账号应字母开头，允许5-16字节，允许字母数字下划线")
    private String name;
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}$",message = "密码必须包含大小写字母和数字的组合，可以使用特殊字符，长度在8-15之间")
    private String password;

    @NotBlank(message = "电话不能为空")
//    @Pattern(regexp = "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)",message = "电话号码支持:手机号码，3-4位区号，7-8位直播号码，1－4位分机号")
    @Pattern(regexp = "^1[34578]\\d{9}$",message = "电话号码不合法")
    private String phone;




    @NotNull(message = "性别不能为空")
    private boolean gender;

//    @NotNull(message = "address不能为空")
    private String address;

    @NotBlank(message = "邮箱不可为空")
    @Email(message = "邮箱格式不正确")
    private String mail;

    public BUser() {
    }

    public BUser(String name, String password, String phone, boolean gender, String address, String mail) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.address = address;
        this.mail = mail;
    }

    private Integer rid;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "TUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", rid=" + rid +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isGender() {
        return gender;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
