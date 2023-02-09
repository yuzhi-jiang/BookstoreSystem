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
public class UAddress implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String aName;

    private String province;

    private String city;

    private String area;

    private String street;

    private Integer housenumber;

    private Integer plainId;

    private Integer uId;

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
    
    public String getaName() {
        return aName;
    }

      public void setaName(String aName) {
          this.aName = aName;
      }
    
    public String getProvince() {
        return province;
    }

      public void setProvince(String province) {
          this.province = province;
      }
    
    public String getCity() {
        return city;
    }

      public void setCity(String city) {
          this.city = city;
      }
    
    public String getArea() {
        return area;
    }

      public void setArea(String area) {
          this.area = area;
      }
    
    public String getStreet() {
        return street;
    }

      public void setStreet(String street) {
          this.street = street;
      }
    
    public Integer getHousenumber() {
        return housenumber;
    }

      public void setHousenumber(Integer housenumber) {
          this.housenumber = housenumber;
      }
    
    public Integer getPlainId() {
        return plainId;
    }

      public void setPlainId(Integer plainId) {
          this.plainId = plainId;
      }
    
    public Integer getuId() {
        return uId;
    }

      public void setuId(Integer uId) {
          this.uId = uId;
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
        return "UAddress{" +
              "id=" + id +
                  ", aName=" + aName +
                  ", province=" + province +
                  ", city=" + city +
                  ", area=" + area +
                  ", street=" + street +
                  ", housenumber=" + housenumber +
                  ", plainId=" + plainId +
                  ", uId=" + uId +
                  ", createtime=" + createtime +
                  ", createby=" + createby +
                  ", updatetime=" + updatetime +
                  ", updateby=" + updateby +
                  ", description=" + description +
              "}";
    }
}
