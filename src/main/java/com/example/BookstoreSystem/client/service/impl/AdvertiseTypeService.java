package com.example.BookstoreSystem.client.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.BookstoreSystem.client.entity.AdvertiseType;
import com.example.BookstoreSystem.client.mapper.AdvertiseTypeMapper;
import com.example.BookstoreSystem.client.service.IAdvertiseTypeService;
import com.example.BookstoreSystem.util.LogUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName AdvertiseTypeService.java
 * @Description TODO
 * @createTime 2022年05月13日 14:30:00
 */
@Service
public class AdvertiseTypeService extends ServiceImpl<AdvertiseTypeMapper, AdvertiseType> implements IAdvertiseTypeService {


    @Resource
    AdvertiseTypeMapper advertiseTypeMapper;

    @Override
    public List<AdvertiseType> queryAllAdvertiseType() {
        return advertiseTypeMapper.queryAllAdvertiseType();
    }

    @Override
    public List<AdvertiseType> getAdvertiseTypeByPage(int currPage, int pageSize) {
        int start = (currPage - 1) * pageSize;
        return advertiseTypeMapper.queryAdvertiseTypeByPage(start, pageSize);
    }

    @Override
    public Integer getAdvertiseTypeCount() {
        return advertiseTypeMapper.quertAdvertiseTYpeCount();

    }

    @Override
    public boolean addAdvertiseType(AdvertiseType advertiseType) {
        return advertiseTypeMapper.addAdvertiseType(advertiseType)>0;
    }

    @Override
    public boolean deleteAdvertiseType(Integer id) {
        return advertiseTypeMapper.deleteAdTypeById(id);
    }

    @Override
    public boolean updateAdvertiseType(AdvertiseType advertiseType) {
        boolean falg= advertiseTypeMapper.updateAdvertiseType(advertiseType);
        LogUtil.info("falg:"+falg);

        return falg;

    }
}
