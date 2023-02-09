package com.example.BookstoreSystem.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.BookstoreSystem.client.entity.Shelves;

import java.util.List;

/**
 * 
 *  服务类
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface IShelvesService extends IService<Shelves> {

    List<Shelves> getAllShelves();

    List<Shelves> getShelfByPage(Integer start, Integer size);

    Shelves getShelvesById(Integer id);

    Shelves getShelvesByName(String name);

    //get the count of shelves
    Integer getCount();

    Boolean updateShelves(Shelves shelves);

    Boolean addShelves(Shelves shelves);

    Boolean deleteShelves(Integer id);
}
