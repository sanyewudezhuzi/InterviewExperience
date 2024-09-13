package com.zhuzi.guan_cha_zhe;

/**
 * @Description RegisterEmailObserver
 * @Author zhuzi
 * @Date 2024/09/11
 */
public class RegisterEmailObserver implements UserRegistObserver {
    @Override
    public void handleRegisterSuccess(String userId) {
        String emailMessage = "恭喜你注册成功, 已发放100元代金券, 请前往个人中心领取";
        return;
    }
}
