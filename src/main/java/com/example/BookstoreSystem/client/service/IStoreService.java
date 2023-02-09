package com.example.BookstoreSystem.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.BookstoreSystem.client.entity.Store;

import java.util.List;

/**
 * 
 *  服务类
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface IStoreService extends IService<Store> {

    List<Store> getAllStore();

    Boolean addStore(Store store);

    Boolean updateStore(Store store);

    Boolean deleteStore(int id);

    Store getStoreById(Integer id);

    Store getStoreByName(String name);

    Boolean deleteStoreById(int id);
}
