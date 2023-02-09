package com.example.BookstoreSystem.util;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName AutoGeneratorUtil.java
 * @Description TODO
 * @createTime 2022年04月06日 09:19:00
 */


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.List;

public class AutoGeneratorUtil {
    /**
     * 启动生成代码
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("------开始---------");
        doGenerator();
        System.out.println("------结束---------");
    }
//"administer","administe_role","advertise",
//        "advertise_type","book","book_type",
//        "myorder","orderitem","role",
//        "role_permission", "user","user_role",
//        "permission","shoppingcart","shoppingcart_item"
//        ,"bookhot","booktypehot","shelves","store","u_address"
    /**
     * 基础配置
     */
    private static String outputDir = System.getProperty("user.dir") + "/src/main/java";
    private static String author = "yefeng";
    /**
     * 数据库配置
     */
    private static DbType dbType = DbType.MYSQL;
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String userName = "root";
    private static String password = "jiangshao888";
    private static String url = "jdbc:mysql://127.0.0.1:3306/ssm?serverTimezone=UTC";
    private static String[] tables = {
        "administer", "administer_role", "advertise",
            "advertistype", "book", "bookhot",
            "booktype","booktypehot", "myorder",
            "orderitem","permission", "role",
            "role_permission", "roletype", "shelves",
            "shoppingcart", "store", "u_address",
            "user", "user_role","user_permission",
    };
    /**
     * 生成包路径
     */
    private static String packageParent = "com.example.BookstoreSystem";
    private static String entity = "client1.entity";
//    private static String mapper = "client1.mapper";
//    private static String mapperXml = "client1.mapper.mappers";
//    private static String service = "client1.service";
//    private static String serviceImpl = "client1.service.impl";
//    private static String controller = "client1.controller";

    public static void doGenerator() {
        AutoGenerator autoGenerator = new AutoGenerator();
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //代码生成存放位置
        gc.setOutputDir(outputDir);
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);
        gc.setOpen(true);
        gc.setAuthor(author);
//        gc.setMapperName("%sMapper");
//        gc.setXmlName("%sMapper");
//        gc.setServiceImplName("%sService");
//        gc.setServiceName("I%sService");
//        gc.setControllerName("%sController");
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(dbType);
        dsc.setDriverName(driverName);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        dsc.setUrl(url);
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(tables);
        strategy.setSuperEntityColumns(new String[]{});
        //strategy.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        List<TableFill> tableFillList = CollUtil.newArrayList();
        TableFill fill = new TableFill("updatetime", FieldFill.INSERT_UPDATE);
        tableFillList.add(fill);
        fill = new TableFill("createtime", FieldFill.INSERT);
        tableFillList.add(fill);
        strategy.setTableFillList(tableFillList);
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packageParent);
        // 代码生成包路径
        pc.setEntity(entity);
//        pc.setMapper(mapper);
//        pc.setXml(mapperXml);
//        pc.setService(service);
//        pc.setServiceImpl(serviceImpl);
//        pc.setController(controller);
        mpg.setPackageInfo(pc);
        // 注入自定义配置，可以在 VM 中使用 ${cfg.packageMy} 设置值
        // InjectionConfig cfg = new InjectionConfig() {
        //     public void initMap() {
        //         Map<String, Object> map = new HashMap<String, Object>();
        //         map.put("packageMy", packageBase);
        //         this.setMap(map);
        //     }
        // };

        // mpg.setCfg(cfg);

        // TemplateConfig tc = new TemplateConfig();
        // tc.setEntity("templates/entity.java.vm");
        // tc.setMapper("templates/mapper.java.vm");
        // tc.setXml("templates/mapper.xml.vm");
        // tc.setServiceImpl("templates/serviceImpl.java.vm");
        // tc.setService("templates/service.java.vm");
        // tc.setController("templates/controller.java.vm");
        // mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();
    }
}