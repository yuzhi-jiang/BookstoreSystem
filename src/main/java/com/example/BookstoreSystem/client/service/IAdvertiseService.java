package com.example.BookstoreSystem.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.BookstoreSystem.client.entity.Advertise;

import java.util.List;

/**
 * 
 *  服务类
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface IAdvertiseService extends IService<Advertise> {

    List<Advertise> getAllAdvertise();

    Advertise getAdvertiseById(int id);

    Integer addAdvertise(Advertise advertise);

    boolean updateAdvertise(Advertise advertise);

    boolean deleteAdvertiseById(int id);

    List<Advertise> getAdvertiseByPage(int currPage, int pageSize);

    int getAdvertiseCount();
}
