package com.codewithbubblu.controller;

import com.codewithbubblu.controller.implementation.IProductController;
import com.codewithbubblu.database.DBConnection;
import com.codewithbubblu.utils.AppException;
import com.codewithbubblu.utils.QueryUtils;
import com.codewithbubblu.utils.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import static com.codewithbubblu.database.DBConnection.getDBConnection;
import static com.codewithbubblu.utils.AppInput.enterInt;
import static com.codewithbubblu.utils.FileUtil.*;
import static com.codewithbubblu.utils.Utils.print;
import static com.codewithbubblu.utils.Utils.println;
import static java.lang.Integer.parseInt;

public class ProductController implements IProductController {
    HomeController homeController;
    CartController cartController;
    private final DBConnection dbConnection;
    private static final Connection con= getDBConnection();
    public ProductController(HomeController homeController){
        this.homeController=homeController;
        cartController=new CartController(homeController);
        dbConnection=new DBConnection();
    }
    //db done
    public void showProducts() {
        try{
            PreparedStatement ps=con.prepareStatement(QueryUtils.SELECT_ALL_PRODUCTS);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt("id")+". "+rs.getString("name"));
            }
            println("99.Back");
            int categoryChoice=enterInt(StringUtil.ADD_TO_CART);
            if(categoryChoice==99){
                homeController.printMenu();
            }
            else{
                cartController.addToCart(categoryChoice);
                println(StringUtil.CART_SUCCESSFULL);
                showProducts();
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void adminProductFunctions() {
        println(StringUtil.ENTER_ADMIN_PRODUCTS_CHOICES);
        try {
            int adminProductChoices=enterInt(StringUtil.ENTER_ADMIN_PRODUCTS_CHOICES);
            if(adminProductChoices==99){
                adminProductFunctions();
            }
            else {
                if (adminProductChoices==1){
                    showProducts();
                } else if (adminProductChoices==2) {
                    addProducts();
                } else if (adminProductChoices==3) {
                    int choice=enterInt(StringUtil.ENTER_CHOICE);
                    deleteProducts(choice);
                } else if (adminProductChoices==4){
                    editProducts();
                }
                else {
                    homeController.invalidChoice(new AppException(StringUtil.INVALID_CHOICE));
                }
            }
        } catch (AppException e) {
            throw new RuntimeException(e);
        }

    }

    private void editProducts() {
    }

    private void deleteProducts(int productDeleteChoice) {
        try {
            Scanner scanner = new Scanner(getProductsFile());
            while (scanner.hasNext()) {
                String value = scanner.next().trim();
                String[] productArray = value.split(",");
                if(parseInt(productArray[0])==productDeleteChoice){
                    replaceCartFile(value,"");
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addProducts() {

    }

    public void showSelectedProducts(int id){
        try{
            Scanner scanner=new Scanner(getProductsFile());
            while (scanner.hasNext()) {
                String value = scanner.next().trim();
                String[] productsArray = value.split(",");
                if(parseInt(productsArray[5])==id){
                    System.out.println(productsArray[0]+"."+productsArray[1]+"=>"+productsArray[3]);
                }
                else {}
            }
            scanner.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
