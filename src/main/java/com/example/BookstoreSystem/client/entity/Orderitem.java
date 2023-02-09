package com.example.BookstoreSystem.client.entity;

import java.math.BigDecimal;
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
public class Orderitem implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer oId;

    private Integer sId;

    private Integer bId;

    private Integer amount;

    private BigDecimal price;

    private BigDecimal generous;

    private BigDecimal promotionReduction;

    private Integer status;

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
    
    public Integer getoId() {
        return oId;
    }

      public void setoId(Integer oId) {
          this.oId = oId;
      }
    
    public Integer getsId() {
        return sId;
    }

      public void setsId(Integer sId) {
          this.sId = sId;
      }
    
    public Integer getbId() {
        return bId;
    }

      public void setbId(Integer bId) {
          this.bId = bId;
      }
    
    public Integer getAmount() {
        return amount;
    }

      public void setAmount(Integer amount) {
          this.amount = amount;
      }
    
    public BigDecimal getPrice() {
        return price;
    }

      public void setPrice(BigDecimal price) {
          this.price = price;
      }
    
    public BigDecimal getGenerous() {
        return generous;
    }

      public void setGenerous(BigDecimal generous) {
          this.generous = generous;
      }
    
    public BigDecimal getPromotionReduction() {
        return promotionReduction;
    }

      public void setPromotionReduction(BigDecimal promotionReduction) {
          this.promotionReduction = promotionReduction;
      }
    
    public Integer getStatus() {
        return status;
    }

      public void setStatus(Integer status) {
          this.status = status;
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
        return "Orderitem{" +
              "id=" + id +
                  ", oId=" + oId +
                  ", sId=" + sId +
                  ", bId=" + bId +
                  ", amount=" + amount +
                  ", price=" + price +
                  ", generous=" + generous +
                  ", promotionReduction=" + promotionReduction +
                  ", status=" + status +
                  ", createtime=" + createtime +
                  ", createby=" + createby +
                  ", updatetime=" + updatetime +
                  ", updateby=" + updateby +
                  ", description=" + description +
              "}";
    }
}
