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
public class Shoppingcart implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 用户Id
     */
      private Integer buyerId;

      /**
     * 书id
     */
      private Integer bId;

      /**
     * 店铺id
     */
      private Integer sId;

      /**
     * 数量
     */
      private Integer amount;

      /**
     * 是否勾选
     */
      private Integer check;

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
    
    public Integer getBuyerId() {
        return buyerId;
    }

      public void setBuyerId(Integer buyerId) {
          this.buyerId = buyerId;
      }
    
    public Integer getbId() {
        return bId;
    }

      public void setbId(Integer bId) {
          this.bId = bId;
      }
    
    public Integer getsId() {
        return sId;
    }

      public void setsId(Integer sId) {
          this.sId = sId;
      }
    
    public Integer getAmount() {
        return amount;
    }

      public void setAmount(Integer amount) {
          this.amount = amount;
      }
    
    public Integer getCheck() {
        return check;
    }

      public void setCheck(Integer check) {
          this.check = check;
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
        return "Shoppingcart{" +
              "id=" + id +
                  ", buyerId=" + buyerId +
                  ", bId=" + bId +
                  ", sId=" + sId +
                  ", amount=" + amount +
                  ", check=" + check +
                  ", createtime=" + createtime +
                  ", createby=" + createby +
                  ", updatetime=" + updatetime +
                  ", updateby=" + updateby +
                  ", description=" + description +
              "}";
    }
}
