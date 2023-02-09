package com.example.BookstoreSystem.client.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

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
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Accessors(chain = true)//支持链式调用
@Data
public class Advertise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer typeid;
    @TableField(exist = false)
    private String typeName;
    @TableField(exist = false)
    private String typeText;


    //广告名称/标题
    private String adName;

    private Integer sizex;

    private Integer sizey;

    @TableField(exist = false)
    private String size;

    private String link;

    private String picpath;

    private LocalDateTime time;

    private Integer status;


    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSize() {
        return sizex + "*" + sizey;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public void setSize(String size) {
        this.size = size;
    }

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

//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getTypeid() {
//        return typeid;
//    }
//
//    public void setTypeid(Integer typeid) {
//        this.typeid = typeid;
//    }
//
//    public Integer getSizex() {
//        return sizex;
//    }
//
//    public void setSizex(Integer sizex) {
//        this.sizex = sizex;
//    }
//
//    public Integer getSizey() {
//        return sizey;
//    }
//
//    public void setSizey(Integer sizey) {
//        this.sizey = sizey;
//    }
//
//    public String getLink() {
//        return link;
//    }
//
//    public void setLink(String link) {
//        this.link = link;
//    }
//
//    public String getPicpath() {
//        return picpath;
//    }
//
//    public void setPicpath(String picpath) {
//        this.picpath = picpath;
//    }
//
//    public LocalDateTime getTime() {
//        return time;
//    }
//
//    public void setTime(LocalDateTime time) {
//        this.time = time;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public LocalDateTime getCreatetime() {
//        return createtime;
//    }
//
//    public void setCreatetime(LocalDateTime createtime) {
//        this.createtime = createtime;
//    }
//
//    public Integer getCreateby() {
//        return createby;
//    }
//
//    public void setCreateby(Integer createby) {
//        this.createby = createby;
//    }
//
//    public LocalDateTime getUpdatetime() {
//        return updatetime;
//    }
//
//    public void setUpdatetime(LocalDateTime updatetime) {
//        this.updatetime = updatetime;
//    }
//
//    public Integer getUpdateby() {
//        return updateby;
//    }
//
//    public void setUpdateby(Integer updateby) {
//        this.updateby = updateby;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    @Override
//    public String toString() {
//        return "Advertise{" +
//                "id=" + id +
//                ", typeid=" + typeid +
//                ", typeName='" + typeName + '\'' +
//                ", typeText='" + typeText + '\'' +
//                ", adName='" + adName + '\'' +
//                ", sizex=" + sizex +
//                ", sizey=" + sizey +
//                ", size='" + size + '\'' +
//                ", link='" + link + '\'' +
//                ", picpath='" + picpath + '\'' +
//                ", time=" + time +
//                ", status=" + status +
//                ", createtime=" + createtime +
//                ", createby=" + createby +
//                ", updatetime=" + updatetime +
//                ", updateby=" + updateby +
//                ", description='" + description + '\'' +
//                '}';
//    }
}
