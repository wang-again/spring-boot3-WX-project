package com.wangagain.wx.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

public class CaptchaUtil {
    // 验证码字符集
    private static final String CODE_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    // 验证码长度
    private static final int CODE_LENGTH = 4;
    // 图片宽度
    private static final int IMG_WIDTH = 120;
    // 图片高度
    private static final int IMG_HEIGHT = 40;
    // 字体大小
    private static final int FONT_SIZE = 20;
    // 干扰线数量
    private static final int LINE_COUNT = 5;

    /**
     * 生成验证码
     * @return 验证码信息，包含验证码文本和Base64编码的图片
     */
    public static CaptchaInfo generateCaptcha() {
        // 创建图片
        BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
        // 获取画笔
        Graphics2D g = image.createGraphics();
        // 设置背景色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);
        // 设置字体
        g.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        // 生成验证码
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < CODE_LENGTH; i++) {
            char c = CODE_CHAR.charAt(random.nextInt(CODE_CHAR.length()));
            code.append(c);
            // 绘制验证码字符
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawString(String.valueOf(c), 20 + i * 25, 25 + random.nextInt(10));
        }
        // 绘制干扰线
        for (int i = 0; i < LINE_COUNT; i++) {
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawLine(random.nextInt(IMG_WIDTH), random.nextInt(IMG_HEIGHT), random.nextInt(IMG_WIDTH), random.nextInt(IMG_HEIGHT));
        }
        // 释放资源
        g.dispose();
        // 将图片转换为Base64
        String base64Image = null;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(image, "png", outputStream);
            byte[] imageBytes = outputStream.toByteArray();
            base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CaptchaInfo(code.toString(), base64Image);
    }

    /**
     * 验证码信息类
     */
    public static class CaptchaInfo {
        private String code;
        private String image;

        public CaptchaInfo(String code, String image) {
            this.code = code;
            this.image = image;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}