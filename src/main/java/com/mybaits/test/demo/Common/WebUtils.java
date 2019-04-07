package com.mybaits.test.demo.Common;

import java.util.Random;

public class WebUtils {
    public  static  String randomNum(int len){
        String s="";
        for ( int i=0;i<len;i++){
            Random random=new Random();
            s+=random.nextInt(10);
        }
        return  s;
    }
}
