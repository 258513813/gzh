package com.zhuermu.gzh.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignUtil {
    /**
     * ��ӿ�������Ϣ�е� token Ҫһ�£����︳��ʲôֵ���ڽӿ�������Ϣ�е�Token��Ҫ��дʲôֵ��
     * ���߱���һ�¼��ɣ���������Ŀ���ơ���˾������д�ȣ����������õ�����Ŀ����weixinface
     */
    private static String token = "ermutoken";
     
    /**
     * ��֤ǩ��
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce){
        String[] arr = new String[]{token, timestamp, nonce};
        // �� token, timestamp, nonce �������������ֵ�����
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
         
        try {
            md = MessageDigest.getInstance("SHA-1");
            // �����������ַ���ƴ�ӳ�һ���ַ������� shal ����
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        content = null;
        // ��sha1���ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()): false;
    }
     
    /**
     * ���ֽ�����ת��Ϊʮ�������ַ���
     * @param digest
     * @return
     */
    private static String byteToStr(byte[] digest) {
        // TODO Auto-generated method stub
        String strDigest = "";
        for(int i = 0; i < digest.length; i++){
            strDigest += byteToHexStr(digest[i]);
        }
        return strDigest;
    }
     
    /**
     * ���ֽ�ת��Ϊʮ�������ַ���
     * @param b
     * @return
     */
    private static String byteToHexStr(byte b) {
        // TODO Auto-generated method stub
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(b >>> 4) & 0X0F];
        tempArr[1] = Digit[b & 0X0F];
         
        String s = new String(tempArr);
        return s;
    }
}