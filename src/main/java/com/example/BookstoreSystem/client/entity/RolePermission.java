package com.example.BookstoreSystem.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 角色与权限绑定
 * </p>
 *
 * @author yefeng
 * @since 2022-05-05
 */
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 角色id
     */
      private Integer rid;

      /**
     * 权限id
     */
      private Integer pid;

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
     * 状态
     */
      private Integer status;

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
    
    public Integer getRid() {
        return rid;
    }

      public void setRid(Integer rid) {
          this.rid = rid;
      }
    
    public Integer getPid() {
        return pid;
    }

      public void setPid(Integer pid) {
          this.pid = pid;
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
    
    public Integer getStatus() {
        return status;
    }

      public void setStatus(Integer status) {
          this.status = status;
      }
    
    public String getDescription() {
        return description;
    }

      public void setDescription(String description) {
          this.description = description;
      }

    @Override
    public String toString() {
        return "RolePermission{" +
              "id=" + id +
                  ", rid=" + rid +
                  ", pid=" + pid +
                  ", createtime=" + createtime +
                  ", createby=" + createby +
                  ", updatetime=" + updatetime +
                  ", updateby=" + updateby +
                  ", status=" + status +
                  ", description=" + description +
              "}";
    }
}
