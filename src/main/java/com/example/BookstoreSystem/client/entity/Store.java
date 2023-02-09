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
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 店名
     */
      private String name;

      /**
     * 企业名
     */
      private String company;

    private Integer registerId;

      /**
     * 法人
     */
      private String legalPenson;

      /**
     * 执照所在地
     */
      private String licenseLocation;

      /**
     * 注册资金,单位W
     */
      private BigDecimal registerCapital;

      /**
     * 有效期
     */
      private LocalDateTime termValidity;

      /**
     * 经营范围
     */
      private String nature;

      /**
     * 店铺在本商城网址
     */
      private String website;

      /**
     * 营业执照图片链接
     */
      private String licensePic;

      /**
     * 联系地址
     */
      private String storeAdd;

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
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }
    
    public String getCompany() {
        return company;
    }

      public void setCompany(String company) {
          this.company = company;
      }
    
    public Integer getRegisterId() {
        return registerId;
    }

      public void setRegisterId(Integer registerId) {
          this.registerId = registerId;
      }
    
    public String getLegalPenson() {
        return legalPenson;
    }

      public void setLegalPenson(String legalPenson) {
          this.legalPenson = legalPenson;
      }
    
    public String getLicenseLocation() {
        return licenseLocation;
    }

      public void setLicenseLocation(String licenseLocation) {
          this.licenseLocation = licenseLocation;
      }
    
    public BigDecimal getRegisterCapital() {
        return registerCapital;
    }

      public void setRegisterCapital(BigDecimal registerCapital) {
          this.registerCapital = registerCapital;
      }
    
    public LocalDateTime getTermValidity() {
        return termValidity;
    }

      public void setTermValidity(LocalDateTime termValidity) {
          this.termValidity = termValidity;
      }
    
    public String getNature() {
        return nature;
    }

      public void setNature(String nature) {
          this.nature = nature;
      }
    
    public String getWebsite() {
        return website;
    }

      public void setWebsite(String website) {
          this.website = website;
      }
    
    public String getLicensePic() {
        return licensePic;
    }

      public void setLicensePic(String licensePic) {
          this.licensePic = licensePic;
      }
    
    public String getStoreAdd() {
        return storeAdd;
    }

      public void setStoreAdd(String storeAdd) {
          this.storeAdd = storeAdd;
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
        return "Store{" +
              "id=" + id +
                  ", name=" + name +
                  ", company=" + company +
                  ", registerId=" + registerId +
                  ", legalPenson=" + legalPenson +
                  ", licenseLocation=" + licenseLocation +
                  ", registerCapital=" + registerCapital +
                  ", termValidity=" + termValidity +
                  ", nature=" + nature +
                  ", website=" + website +
                  ", licensePic=" + licensePic +
                  ", storeAdd=" + storeAdd +
                  ", createtime=" + createtime +
                  ", createby=" + createby +
                  ", updatetime=" + updatetime +
                  ", updateby=" + updateby +
                  ", description=" + description +
              "}";
    }
}
