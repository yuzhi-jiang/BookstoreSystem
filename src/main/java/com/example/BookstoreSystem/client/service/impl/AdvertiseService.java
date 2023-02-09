package com.example.BookstoreSystem.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.BookstoreSystem.client.entity.Advertise;
import com.example.BookstoreSystem.client.mapper.AdvertiseMapper;
import com.example.BookstoreSystem.client.service.IAdvertiseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class AdvertiseService extends ServiceImpl<AdvertiseMapper, Advertise> implements IAdvertiseService {



    @Resource
    AdvertiseMapper mapper;
    @Override
    public List<Advertise> getAllAdvertise() {
        return mapper.queryAllAdvertise();
    }

    @Override
    public Advertise getAdvertiseById(int id) {
        return mapper.queryAdvertiseById(id);
    }

    @Override
    public Integer addAdvertise(Advertise advertise) {
        return mapper.insertAdvertise(advertise);
    }

    @Override
    public boolean updateAdvertise(Advertise advertise) {
        return mapper.updateAdvertise(advertise);
    }

    @Override
    public boolean deleteAdvertiseById(int id) {
        return mapper.deleteAdvertise(id);
    }

    @Override
    public List<Advertise> getAdvertiseByPage(int currPage, int pageSize) {
        Integer start=(currPage-1)*pageSize;
        return mapper.queryAdvertiseByPage(start,pageSize);
    }


    @Override
    public int getAdvertiseCount() {
        return mapper.queryAdvertiseCount();
    }
}
