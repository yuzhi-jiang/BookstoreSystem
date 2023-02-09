package com.example.BookstoreSystem.client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.BookstoreSystem.client.bean.BRole;
import com.example.BookstoreSystem.client.bean.BUser;
import com.example.BookstoreSystem.client.bean.MyUserDetail;
import com.example.BookstoreSystem.client.entity.Permission;
import com.example.BookstoreSystem.client.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

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
public interface UserMapper extends BaseMapper<User> {
    @Select("select ur.id as 'id',ur.u_id as 'uid',ut.name as 'name' from user_role as ur left join role as ut\n" +
            "on ur.role_id=ut.id\n" +
            "where ur.u_id=#{uid}")
    @Results({
            @Result(column = "id",property ="id",jdbcType = JdbcType.INTEGER,id = true),
            @Result(column = "name",property ="name"),
            //是否使用角色绑定权限 是则打开下面注释
//            @Result(column = "uid",property = "permissions",
//                    many = @Many(select = "com.example.BookstoreSystem.client.mapper.PermissionMapper.queryPermissionByUserId",
//                            fetchType= FetchType.EAGER
//                    )
//            )
    })
    public List<BRole> findRolesByUid(@Param("uid") Long uid);


//    @Select("select pid as id,p.name,p.status,p.createtime,p.createBy,p.updatetime,p.updateBy,p.description from role_permission as r\n" +
//            "left join permission p on r.pid=p.id\n" +
//            "where rid in (select role_id as id from user_role where u_id =#{uid});")

    @Select("select p.id,p.name as 'name',u.createtime,u.createBy,u.updateBy,u.updatetime from user_permission as u left join  permission as p on u.pid=p.id where u.uid=#{uid}")
    List<Permission> findPermissionsByUid(@Param("uid") Long uid);


    @Select("select p.id,p.name as 'name',u.createtime,u.createBy,u.updateBy,u.updatetime from user_permission as u left join  permission as p on u.pid=p.id where u.uid=#{uid}")
    List<Permission> findPermissionsByRoleName(@Param("rname") String  rname);


    @Select("select p.id,p.name as 'name',u.createtime,u.createBy,u.updateBy,u.updatetime from user_permission as u left join  permission as p on u.pid=p.id where u.uid=#{uid}")
    List<Permission> findPermissionsByRoleId(@Param("rid") Long rid);

    @Select("select * from user where u_name=#{name} limit 1")
    @Results({
            @Result(column = "id",property = "id",jdbcType = JdbcType.INTEGER,id = true),
            @Result(column = "u_name",property = "username"),
            @Result(column = "u_password",property = "password"),
            @Result(column = "u_icon",property = "icon"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "mail",property = "mail"),
            @Result(column = "status",property = "status",jdbcType = JdbcType.INTEGER),
            @Result(column = "lastTime",property = "lastTime"),
            @Result(column = "createTime",property = "createTime",jdbcType = JdbcType.DATE),
            @Result(column = "isEnable",property = "isEnable"),
            @Result(column = "gender",property = "gender" ),
            @Result(column = "u_score",property = "score" ),
            @Result(column = "id",property = "roles",
                    many = @Many(select = "com.example.BookstoreSystem.client.mapper.UserMapper.findRolesByUid",
                            fetchType= FetchType.EAGER
                    )
            ),
            //是否使用角色绑定权限而非用户绑定权限，不是则注释掉下面
            @Result(column = "id",property = "permissions",
                    many = @Many(select = "com.example.BookstoreSystem.client.mapper.UserMapper.findPermissionsByUid",
                            fetchType= FetchType.EAGER
                    )
            )
    })
    MyUserDetail getUserDetail(@Param("name") String username);


    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    @Insert("insert into user(u_name,u_password,phone,mail,status,gender,isEnable) values(#{name},#{password},#{phone},#{mail},1,#{gender},1)")
    Integer registerUser(BUser user);



    @Insert("insert into user_role(u_id,role_id) values(#{uid},#{utid})")
    Integer bindUserRole(@Param("uid") Long uid,@Param("utid") Long utid);


    @Insert("insert into user_permission(uid,rid,pid) values(#{uid},#{rid},#{pid})")
    Integer bindUserPermission(@Param("uid") Long uid,@Param("rid") Long rid,@Param("pid") Long pid);



    @Select("select u_name as name,phone,mail from user where u_name=#{username} or phone=#{phone} or mail=#{mail} limit 1")
    User canRegisterUserByUserName(@Param("username") String username,@Param("phone") String phone,@Param("mail") String mail);



    @Delete("delete from user where id=#{uid}")
    Integer deleteUserByUid(@Param("uid") Long usrId);

    @Delete("delete from user_role where u_id=#{uid}")
    Integer deleteUserRole(@Param("uid") Long uid);

    //获取所有用户
    @Select("select * from user")
    @Results({
            @Result(column = "id",property = "id",jdbcType = JdbcType.INTEGER,id = true),
            @Result(column = "u_name",property = "username"),
            @Result(column = "u_password",property = "password"),
            @Result(column = "u_icon",property = "icon"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "mail",property = "mail"),
            @Result(column = "status",property = "status",jdbcType = JdbcType.INTEGER),
            @Result(column = "lastTime",property = "lastTime"),
            @Result(column = "createTime",property = "createTime",jdbcType = JdbcType.DATE),
            @Result(column = "isEnable",property = "isEnable"),
            @Result(column = "gender",property = "gender" ),
            @Result(column = "u_score",property = "score" ),
            @Result(column = "id",property = "roles",
                    many = @Many(select = "com.example.BookstoreSystem.client.mapper.UserMapper.findRolesByUid",
                            fetchType= FetchType.EAGER
                    )
            )

    })
    List<MyUserDetail> getAllUser();

    //更新密码
    @Update("update user set u_password=#{password} where id=#{uid}")
    Integer updatePassword(@Param("uid") Long uid,@Param("password") String password);

    //更新密码
    @Update("update user set u_password=#{password} where id=#{uid}")
    Integer updatePassword(@Param("userName") String userName,@Param("password") String password);
    //更新用户信息
    @Update("update user set u_name=#{username},u_icon=#{icon},phone=#{phone},mail=#{mail},gender=#{gender},isEnable=#{isEnable},u_score=#{score},updatetime=current_timestamp where id=#{id}"
    )
    Integer updateUser(MyUserDetail user);

    @Select("select u_name from user where u_name=#{username}")
    String checkUser(@Param("username") String userName);

    @Select("select mail from user where mail=#{email}")
    String checkMail(@Param("email")String email);

    @Select("select phone from user where phone=#{phone}")
    String checkPhone(@Param("phone") String phone);

    @Select("select count(*) from user")
    Long getUserCount();

    @Select("select id,u_name as username,u_score as score,createtime as createTime,description,status,isEnable,mail,phone,gender from user limit #{start},#{size}")
    List<MyUserDetail> getUserByPage(@Param("start") Integer start,@Param("size") Integer size);
}
