package com.zhuzi.guan_cha_zhe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description BaseService
 * @Author zhuzi
 * @Date 2024/09/11
 */
@Service
public class BaseService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private VoucherDao voucherDao;

    @Autowired
    private SendEmailService sendEmailService;

    private static final List<UserRegistObserver> USER_REGIST_OBSERVERS = new ArrayList<>();

    static {
        // 注册观察者
        USER_REGIST_OBSERVERS.add(new RegisterVoucherObserver());
        USER_REGIST_OBSERVERS.add(new RegisterEmailObserver());
    }

    // 注册功能
    public Boolean register(String username, String password, String email) {
        // 1. 校验用户名是否重复
        if (checkUserNameExist(username)) {
            throw new RuntimeException("用户名已存在");
        }

        // 2. 新增一条用户数据
        String userId = userDao.addUser(username, password);

        // 3. 注册成功
        /* if (userId != null && userId.length() > 0) {
            // 3.1 送100元代金券
            voucherDao.addVoucher(userId, "100");

            // 3.2 发送邮件
            String emailMessage = "恭喜你注册成功, 已发放100元代金券, 请前往个人中心领取";
            sendEmailService.sendEmail(email, emailMessage);

            // 3.3 其它操作
        } */
        for (UserRegistObserver observer : USER_REGIST_OBSERVERS) {
            observer.handleRegisterSuccess(userId);
        }

        return true;
    }

    // 校验用户名是否重复
    public Boolean checkUserNameExist(String username) {
        return false;
    }

}
