package com.codewithbubblu.utils;

import java.io.*;
import java.util.Scanner;

public class FileUtil {
    private static File credentialFile;
    private static File categoriesFile;
    private static File productsFile;
    private  static File cartFile;
    public static File getCredentialFile(){
        if(credentialFile==null){
            credentialFile=new File("src/main/java/com/codewithbubblu/assets/credentials.csv");
        }
        return credentialFile;
    }

    public static File getCategoriesFile(){
            categoriesFile=new File("src/main/java/com/codewithbubblu/assets/categories.csv");
        return categoriesFile;
    }

    public static File getProductsFile(){
            productsFile=new File("src/main/java/com/codewithbubblu/assets/products.csv");
        return productsFile;
    }

    public static File getCartsFile(){
            cartFile=new File("src/main/java/com/codewithbubblu/assets/carts.csv");
        return cartFile;
    }

    public static void appendStrTocartFile(String fileName, String str)
    {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(str);
            out.newLine();
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }

    public static void appendStrToProductFile(String fileName, String str)
    {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(str);
            out.newLine();
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }

    public static void appendStrToCategoriesFile(String fileName, String str)
    {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(str);
            out.newLine();
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }

    public static void appendStrToOrdersFile(String fileName, String str)
    {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
            out.write(str);
            out.newLine();
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }

    public static void replaceCartFile(String oldString, String newString) throws IOException {
        String filePath = "src/main/java/com/codewithbubblu/assets/carts.csv";
        Scanner sc = new Scanner(new File(filePath));
        StringBuffer buffer = new StringBuffer();
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine()+System.lineSeparator());
        }
        String fileContents = buffer.toString();
        System.out.println("Contents of the file: "+fileContents);
        sc.close();
        fileContents = fileContents.replaceAll(oldString, newString);
        FileWriter writer = new FileWriter(filePath);
        System.out.println("new data: "+fileContents);
        writer.append(fileContents);
        writer.flush();
    }

    public static void fileWritter(File s){
        Scanner sc = null;
        try {
            sc = new Scanner(s);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuffer buffer = new StringBuffer();
        while (sc.hasNextLine()) {
            buffer.append(sc.nextLine()+System.lineSeparator());
        }
        String fileContents = buffer.toString();
        System.out.println("Myfile: "+fileContents);
        sc.close();
    }
}
