package utils;

import java.util.Random;

public class OTP {

    public static String getOTP() {
        Random rd = new Random();

        String number_1 = rd.nextInt(10) + "";
        String number_2 = rd.nextInt(10) + "";
        String number_3 = rd.nextInt(10) + "";
        String number_4 = rd.nextInt(10) + "";
        String number_5 = rd.nextInt(10) + "";
        String number_6 = rd.nextInt(10) + "";

        String otp = number_1+number_2+number_3+number_4+number_5+number_6;
        
        return otp;
    }
}
