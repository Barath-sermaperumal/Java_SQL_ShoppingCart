package com.codewithbubblu.controller;

import com.codewithbubblu.App;
import com.codewithbubblu.controller.implementation.IAuthController;
import com.codewithbubblu.database.DBConnection;
import com.codewithbubblu.models.Role;
import com.codewithbubblu.models.User;
import com.codewithbubblu.utils.AppException;
import com.codewithbubblu.utils.QueryUtils;
import com.codewithbubblu.utils.StringUtil;
import com.codewithbubblu.view.LoginPage;
import com.codewithbubblu.view.RegisterPage;
import com.mysql.cj.jdbc.JdbcConnection;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.codewithbubblu.utils.AppInput.enterInt;
import static com.codewithbubblu.utils.AppInput.enterString;
import static com.codewithbubblu.utils.FileUtil.getCredentialFile;
import static com.codewithbubblu.utils.Utils.println;
import static java.lang.Integer.parseInt;

public class AuthController implements IAuthController {
    private final HomeController homeController;
    private final AppController appController;
    private final LoginPage loginPage;
    private final RegisterPage registerPage;
    private final DBConnection dbConnection;
    private static final Connection con= DBConnection.getDBConnection();
    public static int loggedInUserId;
    public AuthController(AppController appController) {
        this.appController = appController;
        homeController = new HomeController(this);
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        dbConnection = new DBConnection();
    }

    @Override
    public void login() {
        String email, password;
        email = enterString(StringUtil.ENTER_EMAIL);
        password = enterString(StringUtil.ENTER_PASSWORD);

        User user = validateUser(email, password);
        if (user != null) {
            if (user.getRole() == Role.ADMIN) {
                homeController.printAdminMenu();
            } else {
                homeController.printMenu();
            }
        } else {
            loginPage.invalidCredentials();
            authMenu();
        }
    }

    private User validateUser(String email, String password) {
        User user = new User();
        try {
            PreparedStatement ps=con.prepareStatement(QueryUtils.SELECT_USER);
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                user.setId(parseInt(rs.getString("id")));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                loggedInUserId = rs.getInt("id");
            }
            if (user.getEmail().equals("admin@gmail.com"))
                user.setRole(Role.ADMIN);
            else
                user.setRole(Role.USER);
            return user;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void logout() {
        loggedInUserId = 0;
        authMenu();
    }
    //db done
    @Override
    public void register() {
        String name, email, password, c_password;
        name = enterString(StringUtil.ENTER_NAME);
        email = enterString(StringUtil.ENTER_EMAIL);
        password = enterString(StringUtil.ENTER_PASSWORD);
        c_password = enterString(StringUtil.ENTER_PASSWORD_AGAIN);

        if (password.equals(c_password)) {
            try {
                PreparedStatement ps=con.prepareStatement(QueryUtils.INSERT_NEWUSER);
                ps.setString(1,name);
                ps.setString(2,email);
                ps.setString(3,password);
                ps.executeUpdate();
                registerPage.printRegistrationSuccessful();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            registerPage.passwordMisMatch();
        }
        authMenu();
    }

    @Override
    public void authMenu() {
        appController.printAuthMenu();
        int choice;
        try {
            choice = enterInt(StringUtil.ENTER_CHOICE);
            if (choice == 1) {
                login();
            } else if (choice == 2) {
                register();
            } else {
                invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
            }
        } catch (AppException appException) {
            invalidChoice(appException);
        }
    }

    private void invalidChoice(AppException e) {
        println(e.toString());
        authMenu();
    }
}
