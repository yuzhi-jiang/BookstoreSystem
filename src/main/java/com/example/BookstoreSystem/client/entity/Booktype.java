package com.example.BookstoreSystem.client.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
@Data
@Setter
@Getter
public class Booktype implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 名称
     */
      private String key;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createtime;

      /**
     * 更新时间
     */
        @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updatetime;

      /**
     * 更新人
     */
      private Integer updateby;

      /**
     * 显示文本
     */
      @TableField("label")
      private String label;

      /**
     * 书的父类别,0为根类别
     */
      @TableField("parentId")
    private Integer parentId;

      /**
     * 状态1可用，0不可用
     */
      private Integer status;

      /**
     * 创建者
     */
      @TableField("createBy")
    private Integer createby;

}
