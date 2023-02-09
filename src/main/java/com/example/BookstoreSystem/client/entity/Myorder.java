package com.example.BookstoreSystem.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yefeng
 * @since 2022-05-05
 */
public class Myorder implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    @TableField("byerId")
    private Integer byerid;

    private Integer addId;

    private Integer status;

    private String aUpdateName;

    private String aUpdatePhone;

    private LocalDateTime aUpdateAddress;

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
    
    public Integer getByerid() {
        return byerid;
    }

      public void setByerid(Integer byerid) {
          this.byerid = byerid;
      }
    
    public Integer getAddId() {
        return addId;
    }

      public void setAddId(Integer addId) {
          this.addId = addId;
      }
    
    public Integer getStatus() {
        return status;
    }

      public void setStatus(Integer status) {
          this.status = status;
      }
    
    public String getaUpdateName() {
        return aUpdateName;
    }

      public void setaUpdateName(String aUpdateName) {
          this.aUpdateName = aUpdateName;
      }
    
    public String getaUpdatePhone() {
        return aUpdatePhone;
    }

      public void setaUpdatePhone(String aUpdatePhone) {
          this.aUpdatePhone = aUpdatePhone;
      }
    
    public LocalDateTime getaUpdateAddress() {
        return aUpdateAddress;
    }

      public void setaUpdateAddress(LocalDateTime aUpdateAddress) {
          this.aUpdateAddress = aUpdateAddress;
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
        return "Myorder{" +
              "id=" + id +
                  ", byerid=" + byerid +
                  ", addId=" + addId +
                  ", status=" + status +
                  ", aUpdateName=" + aUpdateName +
                  ", aUpdatePhone=" + aUpdatePhone +
                  ", aUpdateAddress=" + aUpdateAddress +
                  ", createtime=" + createtime +
                  ", createby=" + createby +
                  ", updatetime=" + updatetime +
                  ", updateby=" + updateby +
                  ", description=" + description +
              "}";
    }
}
