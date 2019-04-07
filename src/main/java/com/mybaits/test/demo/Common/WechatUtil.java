package com.mybaits.test.demo.Common;
import com.mybaits.test.demo.bean.WxUser;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;
public class WechatUtil {
    public static void main(String[] args) {
//        \/oepq533bw1gqt2qQ+OQJA==
        String result = decryptData(
                "4yqTd5ROoSI42cDr+PRiq2T/umH9xpSm6FTv1uM1UJZ/mTeH+QbBkbKOdRjrAfNW2ElVchz/ql83B3osdsU6Mxdiq3BBPakicnQtF0iVzVejCKkfzI2JfVn/ZosaydjSjVataDwlgYJmR3pIcQwOsQqGhixxFQ3ohzsqyXOwC3JiZ/DQ9znd0oJzreH1wpk44ooFgASvw+TR8QgZDH1cYIW5OhFoY5tmrxNfMFomHQiMnB3ES54K/Gv5DMXbuWJMnce1QT6dSs3Sk2UWPKFKICtsxSvt/iuEVywJBipvh4RONLCo0vWyOC2Z4xRL0BPdjkhLCISD1CgCdw/5R65IsOtkF7Ps49gSHujIiV8eBOlZtDIZBPb81gkz8BVYkVMimwAfY1jl+KtmWVlvamcMMBGBQ8PNOmo7JiJP9JYFLxKHS/j3glAIaOA8yC+h2l1mvpqCe4EnVof6Y6zovrSiOdKia3qnD2zkXZiT+ytpZDI=",
                 "4XD9yJL+iZokv22TuCRv6w==",
                "Wk7nEhqNntTaGk4cemikpA=="
        );
        WxUser wxUser=JsonUtils.jsonToPojo(result,WxUser.class);
        System.out.println(wxUser);
    }

    public static String decryptData(String encryptDataB64, String sessionKeyB64, String ivB64) {
        return new String(
                decryptOfDiyIV(
                        Base64.decode(encryptDataB64),
                        Base64.decode(sessionKeyB64),
                        Base64.decode(ivB64)
                )
        );
    }

    private static final String KEY_ALGORITHM = "AES";
    private static final String ALGORITHM_STR = "AES/CBC/PKCS7Padding";
    private static Key key;
    private static Cipher cipher;

    private static void init(byte[] keyBytes) {
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(ALGORITHM_STR, "BC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @param keyBytes      解密密钥
     * @param ivs           自定义对称解密算法初始向量 iv
     * @return 解密后的字节数组
     */
    private static byte[] decryptOfDiyIV(byte[] encryptedData, byte[] keyBytes, byte[] ivs) {
        byte[] encryptedText = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedText;
    }
}
