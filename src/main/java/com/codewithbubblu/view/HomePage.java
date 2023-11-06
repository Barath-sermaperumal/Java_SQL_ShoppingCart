package com.codewithbubblu.view;

import com.codewithbubblu.utils.StringUtil;

import static com.codewithbubblu.utils.Utils.println;

public class HomePage {
    public void printMenu() {
        println(StringUtil.USERMENU);
    }
    public void printWelcome(){
        println(StringUtil.WELCOME_TO_LOGINPAGE);
    }
    public void printAdminMenu() {
        println(StringUtil.ADMINMENU);
    }
}
