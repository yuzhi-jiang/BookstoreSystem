package com.example.BookstoreSystem.util;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.apache.shiro.SecurityUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.example.BookstoreSystem.publicController.apiController.VerificationCode;

/**
 * @author yefeng
 * @version 1.0.0
 * @ClassName verificationCodeUtil.java
 * @Description TODO
 * @createTime 2022年04月20日 10:34:00
 */
public class VerificationCodeUtil {


    public static String getVerificationCodeCode(){
        return (String) SecurityUtils.getSubject().getSession().getAttribute(VerificationCode);
    }
    //校验验证码
    public static boolean checkVerificationCode(String youCode, String verificationCode) {
        return verificationCode.equalsIgnoreCase(youCode);
    }
    //校验验证码
    public static boolean checkVerificationCode(String youCode) {
        String verificationCodeCode = getVerificationCodeCode();
        if (verificationCodeCode == null) {
            return false;
        }
        return verificationCodeCode.equalsIgnoreCase(youCode);
    }
    public static LineCaptcha getCaptcha(int length){
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100, 4, 100);
        return lineCaptcha;
    }
    //生成验证码
    public static String getVerificationCode(int length) {
        return getCaptcha(length).getCode();
    }
    //返回验证码base64图片
    public static Image getVerificationCodeImageByCode(String code) {
        Image image = CaptchaUtil.createLineCaptcha(116, 36, code.length(), 10).createImage(code);
        return image;
    }
    //返回验证码base64图片
    public static String getVerificationCodeBase64Image(int length) {
        //生成线干扰的验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116,36,length,10);
        return "data:image/png;base64,"+lineCaptcha.getImageBase64();
    }
    public static BufferedImage getVerificationCodeImage(int len) {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116,36,len,10);
        BufferedImage codeImg = lineCaptcha.getImage();
        return codeImg;
    }
    public static void main(String[] args) {
        //生成验证码
        String code = getVerificationCode(4);
        LogUtil.info(code);

        //返回验证码base64图片
        String codeImg = getVerificationCodeBase64Image(5);
        LogUtil.info(codeImg);


        boolean sss = checkVerificationCode("1234", "1234");
        LogUtil.info(sss);

//        //返回验证码图片
//        BufferedImage codeImg1 = getVerificationCodeImage(code);
//        System.out.println(codeImg1);
    }


}
