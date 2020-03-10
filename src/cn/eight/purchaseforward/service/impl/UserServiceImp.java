package cn.eight.purchaseforward.service.impl;

import cn.eight.purchaseforward.dao.UserDao;
import cn.eight.purchaseforward.pojo.User;
import cn.eight.purchaseforward.service.UserService;
import cn.eight.purchaseforward.util.Tools;

public class UserServiceImp implements UserService {
    private UserDao userDao=new UserDao();

    @Override
    public boolean registerUser(User user) {
        //对密码进行加密
        user.setPassword(Tools.md5(user.getPassword()));
        return userDao.insertUser(user);
    }

    @Override
    public boolean checkUser(String username) {
        return userDao.queryUserByUsername(username);
    }
}
