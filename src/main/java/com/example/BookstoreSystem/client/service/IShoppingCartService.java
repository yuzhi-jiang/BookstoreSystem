package com.example.BookstoreSystem.client.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.BookstoreSystem.client.entity.Shoppingcart;

import java.util.List;

/**
 * 
 *  服务类
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface IShoppingCartService extends IService<Shoppingcart> {

    Shoppingcart getShoppingCartById(Long id);

    List<Shoppingcart> getShoppingCartByUserId(Long userId);

    Boolean addShoppingCart(Shoppingcart shoppingCart);

    Integer addShoppingCartByList(List<Shoppingcart> shoppingCartList);

    Boolean deleteShoppingCart(Long id);

    Boolean updateShoppingCart(Shoppingcart shoppingCart);

    Boolean deleteAllShoppingCart(Long userId);
}
