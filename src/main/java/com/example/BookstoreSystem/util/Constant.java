package com.example.BookstoreSystem.util;

public class Constant {
    public static final Integer CODE_OK = 1;
    public static final Integer CODE_USER_ERROR = 0;//用户未登录
    public static final Integer Code_USER_EXIST = 2;//用户已经存在
    public static final Integer Code_LOGIN_FAIL = 3;//用户登录失败
    public static final Integer USER_ROLE_ADD_FALSE = -2;//用户权限添加失败


    public static final Integer Code_PorductDetail_FAIL = -3;//用户登录失败
    public static final Integer CODE_PRODUCT_QUERY_FAIL = -4;//获取商品失败
    public static final Integer CODE_PRODUCTSHOPPING_CAR_ADD_FAIL = -5;//添加购物车商品失败
    public static final Integer CODE_PRODUCT_REMOVE_FAIL = -6;//商城购物车商品失败


    public static final Integer CODE_PRODUCT_BUY_FALSE = -7;//添加购物车商品失败
    public static final Integer CODE_PRODUCT_AMOUNT_LOW = -8;//添加购物车商品失败

    public static final Integer CODE_UPDATE_ORDER_ITEM_FAIL = -9;//修改订单项状态失败

    //下单
    public static final Integer ORDER_STATUS_PAYED = 1;//订单已支付


    //上传文件
    public static final Integer FILE_SAVE_FAIL = -10;
    public static final Integer IMAGE_SAVE_FAIL = -11;

    public static final String ERROR_CODE = "ERROR_CODE";
    public static final String ERROR_MSSAGE = "ERROR_MSSAGE";


    //添加广告
    public static final Integer AD_ADD_FAIL=-12;
    public static final Integer AD_ADD_TYPE_FAIL=-13;

    private static String CURR = "";

    static {
        CURR = PathUtil.getCurrProjectPath();
    }

    public static final String IMAGE_SAVE_PATH = CURR + "\\" + "target\\classes\\static\\img\\";
//    public static final String IMAGE_SAVE_PATH =  "/img/";
    public static final Integer CODE_STORE_IMG_FILE_FAIL = -10;
    public static final Integer CODE_ADD_PRODUCT_TX_FAIL = -11;
    public static final Integer CODE_UPDATE_PRODUCT_TX_FAIL = -11;

    public static final String ROLE_BUYER = "BUYER";
    public static final String ROLE_SELLER = "SELLER";
    public static final String ROLE_ADMIN = "ADMIN";

}
