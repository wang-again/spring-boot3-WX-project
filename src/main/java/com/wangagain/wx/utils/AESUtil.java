package com.wangagain.wx.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtil {

    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128; // 认证标签长度
    private static final int IV_LENGTH_BYTE = 12;  // GCM 推荐 IV 长度为 12 字节

    /**
     * 加密方法
     * @param plainText 明文（如微信 Token）
     * @param secretKey 16字节(128位)或32字节(256位)的密钥
     */
    public static String encrypt(String plainText, String secretKey) throws Exception {
        // 1. 生成随机 IV（初始向量），每次加密都必须不同
        byte[] iv = new byte[IV_LENGTH_BYTE];
        new SecureRandom().nextBytes(iv);

        // 2. 初始化 Cipher
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);

        // 3. 执行加密
        byte[] cipherText = cipher.doFinal(plainText.getBytes());

        // 4. 将 IV 和 密文 拼接在一起，方便解密时读取
        ByteBuffer bb = ByteBuffer.allocate(iv.length + cipherText.length);
        bb.put(iv);
        bb.put(cipherText);

        return Base64.getEncoder().encodeToString(bb.array());
    }
}