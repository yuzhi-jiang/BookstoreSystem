package com.example.BookstoreSystem.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.BookstoreSystem.client.entity.Shelves;
import com.example.BookstoreSystem.client.mapper.ShelvesMapper;
import com.example.BookstoreSystem.client.service.IShelvesService;
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
public class ShelvesService extends ServiceImpl<ShelvesMapper, Shelves> implements IShelvesService {

    @Resource
    ShelvesMapper shelvesMapper;

    @Override
    public List<Shelves> getAllShelves() {
        return shelvesMapper.queryAllShelves();
    }

    @Override
    public List<Shelves> getShelfByPage(Integer start, Integer size) {
        return shelvesMapper.queryShelfByPage(start, size);
    }

    @Override
    public Shelves getShelvesById(Integer id) {
        return shelvesMapper.queryShelvesById(id);
    }

    @Override
    public Shelves getShelvesByName(String name) {
        return shelvesMapper.queryShelvesByName(name);
    }

    //get the count of shelves
    @Override
    public Integer getCount() {
        return shelvesMapper.queryShelvesCount();
    }

    @Override
    public Boolean updateShelves(Shelves shelves) {
        return shelvesMapper.updateShelves(shelves);
    }

    @Override
    public Boolean addShelves(Shelves shelves) {
        return shelvesMapper.addShelves(shelves);
    }

    @Override
    public Boolean deleteShelves(Integer id) {
        return shelvesMapper.deleteShelves(id);
    }
}
