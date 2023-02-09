package com.example.BookstoreSystem.client.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author yefeng
 * @since 2022-05-05
 */
@Data
@Setter
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Accessors(chain = true)//支持链式调用
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 书的类型ID
     */
    private Integer typeid;


    /**
     * 状态
     */
    private Integer status;

    /**
     * 书名字
     */
    private String bookName;


    /**
     * 书的ISBN码
     */
    private String isbn;

    /**
     * 出版时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date publishedtime;

    /**
     * 作者
     */
    private String author;


    /**
     * 封面url
     */
    private String coverUrl;

    @TableField(exist = false)//数据库中不存在
    private String hotNum;


    /**
     * 书类型名称
     */
    @TableField(exist = false)
    private String BookTypeName;
    private String BookTypeLabel;
    /**
     * 书本单价
     */
    private BigDecimal price;

    /**
     * 书的编号,可做唯一索引
     */
    private Integer bookid;

    /**
     * 装帧
     */
    private String binding;

    private Integer foliox;

    private Integer folioy;

    /**
     * 开多少页，开本
     */
    private Integer foliosize;

    /**
     * 库存
     */
    private Integer kucun;

    /**
     * 店铺ID
     */
    private Integer sId;

    /**
     * 目录
     */
    private String catalogue;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 书介绍长图地址
     */
    @TableField("longPic")
    private String longpic;

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


}
