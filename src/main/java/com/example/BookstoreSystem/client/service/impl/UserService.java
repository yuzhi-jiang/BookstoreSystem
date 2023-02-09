package com.example.BookstoreSystem.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.BookstoreSystem.bean.ResCode;
import com.example.BookstoreSystem.client.bean.BUser;
import com.example.BookstoreSystem.client.bean.MyUserDetail;
import com.example.BookstoreSystem.client.entity.User;
import com.example.BookstoreSystem.client.mapper.UserMapper;
import com.example.BookstoreSystem.client.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.example.BookstoreSystem.util.Constan2.*;

/**
 * 
 *  服务实现类
 * 
 *
 * @author yefeng
 * @since 2022-04-06
 */
@Service

public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    UserMapper mapper;


    @Override
    public MyUserDetail getUserDetail(String username) {
        return mapper.getUserDetail(username);
    }





    @Override
    public ResCode register(BUser user) {
        if (user == null) {
            return ResCode.PARAM_ERROR;
        }
        ResCode canRegister = canRegisterByUserName(user.getName(), user.getPhone(), user.getMail());
        if (canRegister != ResCode.USER_NOT_EXIST) return canRegister;

        return RegisterTx(user);
    }

    @Transactional
    protected ResCode RegisterTx(BUser user) {
        Integer registerStatu = registerByUser(user);
        if (registerStatu > 0) {
            Integer code = bindUserRole(user.getId(), ROLE_USER);
            if (code > 0) {
                Integer bindPerminssCode = bindUserPermission(user.getId(), ROLE_USER, Permission_User_ALl);
                if (bindPerminssCode < 1) {
                    return ResCode.USER_PERMISSION_BIND_FAIL;
                }
            } else {
                return ResCode.USER_ROLE_BIND_FAIL;
            }
        } else {
            return ResCode.USER_REGISTER_FAIL;
        }
        return ResCode.USER_REGISTER_SUCCESS;
    }
    @Override
    public ResCode canRegisterByUserName(String name, String phone, String mail) {
        User user = mapper.canRegisterUserByUserName(name, phone, mail);
        if (user == null) {
            return ResCode.USER_NOT_EXIST;
        }


        if (user.getPhone().equals(phone) || user.getPhone() == phone) {
            return ResCode.USER_PHONE_EXIT;
        }

        if (user.getMail().equals(mail) || user.getMail() == mail) {
            return ResCode.USER_MAIL_EXIT;
        }
//        if(user.getName().equals(name))return Code_Register_UserMailHad;

        return ResCode.USER_EXIST;
    }
//    @Override
//    public Integer canRegisterByUserName(String name, String phone, String mail) {
//        User user = mapper.canRegisterUserByUserName(name, phone, mail);
//        System.out.println(user);
//        if (user == null) {
//            return ResCode.User_CanRegister.getErrCode();
//        }
//
//
//        if (user.getPhone().equals(phone) || user.getPhone() == phone) {
//            return Code_Register_UserPhoneHad;
//        }
//
//        if (user.getMail().equals(mail) || user.getMail() == mail) {
//            return Code_Register_UserMailHad;
//        }
////        if(user.getName().equals(name))return Code_Register_UserMailHad;
//
//        return Code_Register_UserNameHad;
//    }

    @Override
    public List<MyUserDetail> getUserByPage(Integer page, Integer limit) {
        //根据当前页和每页显示数量计算出起始位置
        Integer start = (page - 1) * limit;

        System.out.println("start:" + start);
        System.out.println("limit:" + limit);
        return mapper.getUserByPage(start, limit);
    }

    @Override
    public Long getUserCount() {
        return mapper.getUserCount();
    }

    @Transactional
    @Override
    public Integer deleteUser(Long usrId) {
        Integer statu1 = deleteUserByUid(usrId);
        Integer statu2 =  deleteUserRole(usrId);
        if (statu1 > 0 && statu2 > 0) {
            return Code_Delete_Success;
        }
        return Code_Delete_Fail;
    }

    private Integer deleteUserByUid(Long usrId) {
        return mapper.deleteUserByUid(usrId);
    }

    //删除用户角色
    public Integer deleteUserRole(Long usrId) {
        return mapper.deleteUserRole(usrId);
    }


    @Override
    public List<MyUserDetail> getAllUser() {
        return mapper.getAllUser();
    }

    @Override
    public Integer updateUserPwd(Long uid,String password) {
        return mapper.updatePassword(uid, password);
    }

    @Override
    public Integer updateUserPwd(String userName, String password) {
        return mapper.updatePassword(userName, password);
    }

    @Override
    public Integer updateUser(MyUserDetail user) {
        return mapper.updateUser(user);
    }

    @Override
    public ResCode checkUser(String userName) {
        String user = mapper.checkUser(userName);
        System.out.println(user);
        if (user == null) {
            return ResCode.SUCCESS;
        }
        return ResCode.USER_EXIST;
    }


    @Override
    public ResCode checkPhone(String phone) {
        String res = mapper.checkPhone(phone);
        if (res==null){
            return ResCode.SUCCESS;
        }
        return ResCode.USER_PHONE_EXIT;
    }

    @Override
    public ResCode checkMail(String email) {
        String res = mapper.checkMail(email);
        if (res==null){
            return ResCode.SUCCESS;
        }
        return ResCode.USER_MAIL_EXIT;
    }

    private Integer bindUserPermission(Long uid, Long rid, Long pid) {
        return mapper.bindUserPermission(uid, rid, pid);
//        return -1;
    }

    private Integer bindUserRole(Long uid, Long utid) {
        return mapper.bindUserRole(uid, utid);
    }

    private Integer registerByUser(BUser user) {
        return mapper.registerUser(user);
    }

}
