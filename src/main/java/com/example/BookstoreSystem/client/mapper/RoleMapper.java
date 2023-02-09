package com.example.BookstoreSystem.client.mapper;

import com.example.BookstoreSystem.client.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 
 *  Mapper 接口
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * @title queryAllRoles
     * @description 查找所有角色
     * @author yefeng
     * @Param
     * @updateTime 2022/5/1 15:25
     */
    @Select("select * from role")
    List<Role> queryAllRoles();

    /**
     * @title updateRole
     * @description 更新角色
     * @author yefeng
     * @Param
     * @updateTime 2022/5/1 15:25
     */
    @Update("update role set name=#{roleName},status=#{status} where id=#{id}")
    Boolean updateRole(Role role);


    /**
     * @title queryRoleById
     * @description 根据id查找角色
     * @author yefeng
     * @Param
     * @updateTime 2022/5/1 15:25
     */
    @Insert("insert into role(name,status) values(#{roleName},#{status})")
    Boolean AddRole(Role role);

//    @Select("select * from role where id in {id}")
//    List<Role> queryRolesByIds(List<Integer> id);
}
