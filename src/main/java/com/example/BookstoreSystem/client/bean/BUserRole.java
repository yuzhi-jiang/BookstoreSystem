package com.example.BookstoreSystem.client.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName BUserRole.java
 * @Description TODO
 * @createTime 2022年05月01日 16:06:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BUserRole implements Serializable {
    private Integer id;
    private Integer uId;

    @NotBlank(message = "角色名称不能为空")
    private Integer rId;
    private String updateTime;
    private int status;




}
