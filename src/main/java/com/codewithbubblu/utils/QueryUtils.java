package com.codewithbubblu.utils;

public class QueryUtils {
    public static final String INSERT_NEWUSER = "INSERT INTO credentials (name,email,password) values (?,?,?);";
    public static final String SELECT_USER = "SELECT id,name,email,password FROM credentials WHERE email=? and password=?";
    public static final String SELECT_ALL_CATEGORIES = "SELECT id,name FROM categories";
    public static final String SELECT_ALL_PRODUCTS = "SELECT * from products";
    public static final String SELECT_ALL_CARTS = "SELECT * FROM carts where userId=?;";
    public static final String CHECK_CARTS = "SELECT * FROM carts where productId=? and userId=?;";
    public static final String SELECT_PARTICULAR_CART = "SELECT * FROM products where id=?;";
    public static final String ADD_TO_CART = "insert into carts (userId,productId,counts) values (?,?,?);";
    public static final String UPDATE_CART = "UPDATE carts set counts=? where productId=? and userId=?";
    public static final String CHECKOUT = "INSERT into orders (userId) values (?);";
    public static final String CHANGE_CART_STATUS = "UPDATE carts set isOrdered=1 and date=current_timestamp where userId=?";
}
