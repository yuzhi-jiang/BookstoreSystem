package com.example.BookstoreSystem.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName AdvertiseType.java
 * @Description TODO
 * @createTime 2022年05月08日 14:12:00
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@ToString
public class AdvertiseType implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String key;
    private String label;
    private Integer status;
    private String createTime;
    private String updateTime;
    @TableField("createBy")
    private Integer createUserId;
    @TableField("updateBy")
    private Integer updateUserId;

    @TableField(exist = false)
    private Integer createUserName;
    @TableField(exist = false)
    private Integer updateUserName;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(Integer createUserName) {
        this.createUserName = createUserName;
    }

    public Integer getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(Integer updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
