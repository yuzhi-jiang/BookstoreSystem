package com.example.BookstoreSystem.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.BookstoreSystem.client.entity.Shoppingcart;
import com.example.BookstoreSystem.client.mapper.ShoppingcartMapper;
import com.example.BookstoreSystem.client.service.IShoppingCartService;
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
public class ShoppingCartService extends ServiceImpl<ShoppingcartMapper, Shoppingcart> implements IShoppingCartService {

    @Resource
    ShoppingcartMapper mapper;

    @Override
    public Shoppingcart getShoppingCartById(Long id) {
        return mapper.queryShoppingCartById(id);
    }

    @Override
    public List<Shoppingcart> getShoppingCartByUserId(Long userId) {
        return mapper.queryShoppingCartByUserId(userId);
    }

    @Override
    public Boolean addShoppingCart(Shoppingcart shoppingCart) {
        return mapper.insert(shoppingCart)>0;
    }

    @Override
    public Integer addShoppingCartByList(List<Shoppingcart> shoppingCartList) {
        return null;//mapper.insertList(shoppingCartList);// TODO: 2022/5/11 暂且不用
    }

    @Override
    public Boolean deleteShoppingCart(Long id) {
        return mapper.deleteById(id)>0;
    }

    @Override
    public Boolean updateShoppingCart(Shoppingcart shoppingCart) {
        return mapper.updateShoppingCart(shoppingCart);
    }

    @Override
    public Boolean deleteAllShoppingCart(Long userId) {
       return mapper.deleteByUserId(userId);
    }
}
