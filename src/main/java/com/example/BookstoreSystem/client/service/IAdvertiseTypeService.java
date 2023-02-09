package com.example.BookstoreSystem.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.BookstoreSystem.client.entity.AdvertiseType;

import java.util.List;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName IAdvertiseTypeService.java
 * @Description TODO
 * @createTime 2022年05月13日 14:28:00
 */
public interface IAdvertiseTypeService extends IService<AdvertiseType> {
    List<AdvertiseType> queryAllAdvertiseType();

    List<AdvertiseType> getAdvertiseTypeByPage(int currPage, int pageSize);

    Integer getAdvertiseTypeCount();

    boolean addAdvertiseType(AdvertiseType advertiseType);

    boolean deleteAdvertiseType(Integer id);

    boolean updateAdvertiseType(AdvertiseType advertiseType);
}
