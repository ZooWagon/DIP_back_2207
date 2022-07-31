package com.zzw.dig_back_220721.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdGenerator {

    public static String GenerateId(String tag) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String tmp1 = sdf.format(date);
        String tmp2 = tmp1.substring(2);
        int mod=(int)Math.pow(10,3);
        String frontZero="%0"+(3)+"d";
        String tmp3 = String.format(frontZero, date.getTime() % mod);
        String pid1 = tag + tmp2 + tmp3;
        // System.out.println("Generator ID: pid="+pid1);
        return pid1;
    }

    public static String GenerateImgId() {
         return GenerateId("IMG_");
    }

    public static String GenerateSid(){
        return GenerateId("90");
    }
}
