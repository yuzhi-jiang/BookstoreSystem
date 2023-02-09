package com.example.BookstoreSystem.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.BookstoreSystem.client.entity.Shoppingcart;

import java.util.List;

/**
 * 
 *  Mapper 接口
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface ShoppingcartMapper extends BaseMapper<Shoppingcart> {

    Boolean updateShoppingCart(Shoppingcart shoppingCart);

    Boolean deleteByUserId(Long userId);

    List<Shoppingcart> queryShoppingCartByUserId(Long userId);

    Shoppingcart queryShoppingCartById(Long id);
}
