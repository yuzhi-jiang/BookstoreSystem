package com.example.BookstoreSystem.client.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yefeng
 * @since 2022-05-05
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String uName;

    private String uPassword;

      /**
     * 头像地址
     */
      private String uIcon;

    private String phone;

    private String mail;

    private Integer status;

      /**
     * 用户打分
     */
      private Integer uScore;

    @TableField("lastTime")
    private LocalDateTime lasttime;

    private Boolean gender;

    @TableField("isEnable")
    private Boolean isenable;

      /**
     * 创建时间
     */
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createtime;

      /**
     * 创建者
     */
      @TableField("createBy")
    private Integer createby;

      /**
     * 更新时间
     */
        @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updatetime;

      /**
     * 更新者
     */
      @TableField("updateBy")
    private Integer updateby;

      /**
     * 描述
     */
      private String description;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getuName() {
        return uName;
    }

      public void setuName(String uName) {
          this.uName = uName;
      }
    
    public String getuPassword() {
        return uPassword;
    }

      public void setuPassword(String uPassword) {
          this.uPassword = uPassword;
      }
    
    public String getuIcon() {
        return uIcon;
    }

      public void setuIcon(String uIcon) {
          this.uIcon = uIcon;
      }
    
    public String getPhone() {
        return phone;
    }

      public void setPhone(String phone) {
          this.phone = phone;
      }
    
    public String getMail() {
        return mail;
    }

      public void setMail(String mail) {
          this.mail = mail;
      }
    
    public Integer getStatus() {
        return status;
    }

      public void setStatus(Integer status) {
          this.status = status;
      }
    
    public Integer getuScore() {
        return uScore;
    }

      public void setuScore(Integer uScore) {
          this.uScore = uScore;
      }
    
    public LocalDateTime getLasttime() {
        return lasttime;
    }

      public void setLasttime(LocalDateTime lasttime) {
          this.lasttime = lasttime;
      }
    
    public Boolean getGender() {
        return gender;
    }

      public void setGender(Boolean gender) {
          this.gender = gender;
      }
    
    public Boolean getIsenable() {
        return isenable;
    }

      public void setIsenable(Boolean isenable) {
          this.isenable = isenable;
      }
    
    public LocalDateTime getCreatetime() {
        return createtime;
    }

      public void setCreatetime(LocalDateTime createtime) {
          this.createtime = createtime;
      }
    
    public Integer getCreateby() {
        return createby;
    }

      public void setCreateby(Integer createby) {
          this.createby = createby;
      }
    
    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

      public void setUpdatetime(LocalDateTime updatetime) {
          this.updatetime = updatetime;
      }
    
    public Integer getUpdateby() {
        return updateby;
    }

      public void setUpdateby(Integer updateby) {
          this.updateby = updateby;
      }
    
    public String getDescription() {
        return description;
    }

      public void setDescription(String description) {
          this.description = description;
      }

    @Override
    public String toString() {
        return "User{" +
              "id=" + id +
                  ", uName=" + uName +
                  ", uPassword=" + uPassword +
                  ", uIcon=" + uIcon +
                  ", phone=" + phone +
                  ", mail=" + mail +
                  ", status=" + status +
                  ", uScore=" + uScore +
                  ", lasttime=" + lasttime +
                  ", gender=" + gender +
                  ", isenable=" + isenable +
                  ", createtime=" + createtime +
                  ", createby=" + createby +
                  ", updatetime=" + updatetime +
                  ", updateby=" + updateby +
                  ", description=" + description +
              "}";
    }
}
