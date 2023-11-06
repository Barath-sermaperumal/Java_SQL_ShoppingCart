package com.codewithbubblu.view;

import com.codewithbubblu.utils.StringUtil;

import static com.codewithbubblu.utils.Utils.println;

public class LoginPage {
    public void invalidCredentials(){
        try {
            println("#================#");
            println("Invalid Credentials!");
            println("#================#");
            Thread.sleep(1000);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
