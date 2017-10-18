package com.lol.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by zhuwei on 14-12-24.
 */

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AES {

    private IvParameterSpec ivSpec;

    private SecretKeySpec keySpec;

    public static String AESKEY = "jjsdj#$827gfgh38";

    public static String AesPublicKey = "akljzmknm.ahkjkl";

    public AES(String key, String ivkey) {
        try {
            this.keySpec = new SecretKeySpec(key.getBytes(), "AES");
            this.ivSpec = new IvParameterSpec(ivkey.getBytes());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] encrypt(byte[] origData) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, this.keySpec, this.ivSpec);
            return cipher.doFinal(origData);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String encryptNoEncode(String origData) {
        try {
            AES aes = new AES(AESKEY, AesPublicKey);
            byte[] crypted = aes.encrypt(origData.getBytes());
           // System.out.println(new String(crypted));
            String result = base64Encode(crypted);
            return result;
        } catch(EnumConstantNotPresentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String encryptString(String origData) {
        try {
            AES aes = new AES(AESKEY, AesPublicKey);
            byte[] crypted = aes.encrypt(origData.getBytes());
           // System.out.println(new String(crypted));
            String result1 = base64Encode(crypted);
           // System.out.println(result1);
            String result = URLEncoder.encode(result1, "utf-8");
            return result;
        } catch(EnumConstantNotPresentException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decrypt(String encryptedData) throws Exception {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, this.keySpec, this.ivSpec);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    // public byte[] decrypt(byte[] crypted) {
    // try {
    // Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    // cipher.init(Cipher.DECRYPT_MODE, this.keySpec, this.ivSpec);
    // return cipher.doFinal(crypted);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return null;
    // }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // uEPo/Ho/xkKT+C3AaHjrTg==
        AES aes = new AES(AESKEY, AesPublicKey);
//        String data = "123456";
//        byte[] crypted = aes.encrypt(data.getBytes());
         String base=aes.decrypt("fvxWjvf+swvRVktHGZVLVg==");
         System.out.println(base);

      //  System.out.println(base64Encode(crypted));
    }

    public static String base64Encode(byte[] data) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public static String base64Decode(String data) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(data);
            data = new String(b, "utf-8");
        } catch(Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
