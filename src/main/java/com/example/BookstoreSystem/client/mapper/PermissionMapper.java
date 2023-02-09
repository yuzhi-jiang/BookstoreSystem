package com.example.BookstoreSystem.client.mapper;

import com.example.BookstoreSystem.client.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 *  Mapper 接口
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
public interface PermissionMapper extends BaseMapper<Permission> {


//    #根据角色名称查询对应权限
//    select * from role_permission as r
//    left join permission p on r.pid=p.id
//    where rid =(select id from role where name ='ROLE_USER');

    @Select("select * from role_permission as r\n" +
            "left join permission p on r.pid=p.id\n" +
            "where rid =(select id from role where name =#{roleName});")
    List<Permission> queryPermissionByRoleName(@Param("roleName") String roleName);

//#根据角色id查询权限
//    select * from role_permission as r
//    left join permission p on r.pid=p.id
//    where rid =2
    @Select("select * from role_permission as r\n" +
            "left join permission p on r.pid=p.id\n" +
            "where rid =#{rid}")
    List<Permission> queryPermissionByRoleId(@Param("rid") Long rid);

//#根据用户id查询到角色ids 再查询权限\n" +
    @Select("select pid as id,p.name,p.status,p.createtime,p.createBy,p.updatetime,p.updateBy,p.description from role_permission as r\n" +
            "left join permission p on r.pid=p.id\n" +
            "where rid in (select role_id as id from user_role where u_id =#{uid});")
    List<Permission> queryPermissionByUserId(@Param("uid") Long uid);

/*
    select * from role where id = 1;

    select * from role where name='ROLE_USER';

    select * from role where name in ('ROLE_USER','ROLE_SELLER');

    select id from role where name ='ROLE_USER';
    select id from role where name in ('ROLE_USER','ROLE_SELLER');

    select * from role_permission where rid in(select id from role where name in ('ROLE_USER','ROLE_SELLER'))
*/
}
