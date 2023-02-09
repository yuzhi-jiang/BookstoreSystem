package com.example.BookstoreSystem.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.BookstoreSystem.client.entity.Store;
import com.example.BookstoreSystem.client.mapper.StoreMapper;
import com.example.BookstoreSystem.client.service.IStoreService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 *  服务实现类
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
@Service
public class StoreService extends ServiceImpl<StoreMapper, Store> implements IStoreService {

    @Override
    public List<Store> getAllStore() {
        return null;
    }

    @Override
    public Boolean addStore(Store store) {
        return null;
    }

    @Override
    public Boolean updateStore(Store store) {
        return null;
    }

    @Override
    public Boolean deleteStore(int id) {
        return null;
    }

    @Override
    public Store getStoreById(Integer id) {
        return null;
    }

    @Override
    public Store getStoreByName(String name) {
        return null;
    }

    @Override
    public Boolean deleteStoreById(int id) {
        return null;
    }
}
