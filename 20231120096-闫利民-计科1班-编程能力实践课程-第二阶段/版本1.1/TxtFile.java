package org.example;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TxtFile{
    Scanner scanner = new Scanner(System.in);
    List<Customer> customers=new LinkedList<>();
    List<Product> products=new LinkedList<>();
    List<Product> products1=new LinkedList<>();
    List<ShoppingCart> shoppingHistorys=new ArrayList<>();
    String fileName1,fileName2,fileName3,fileName4;


    public void saveToFile() {
        try {
            System.out.println("请输入您要存储用户信息的文本文件名：");
            fileName1=scanner.next();
            System.out.println("请输入您要存储商品信息的文本文件名：");
            fileName2=scanner.next();
            System.out.println("请输入您要存储添加商品信息的文本文件名：");
            fileName3=scanner.next();
            System.out.println("请输入您要存储购物历史信息的文本文件名：");
            fileName4=scanner.next();
            saveCustomersToFile(fileName1);
            saveProductsToFile(fileName2);
            saveAddProductsToFile(fileName3);
            savePurchaseHistoriesToFile(fileName4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCustomersToFile(String fileName) throws IOException {
        //此处仅列出三位顾客
        customers.add(new Customer("111","张三","金牌","2020",1000,"12345","zhangsan@111"));
        customers.add(new Customer("222","李四","银牌","2021",2000,"23456","lisi@222"));
        customers.add(new Customer("333","王五","铜牌","2022",3000,"34567","wangwu@333"));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Customer customer : customers) {
                writer.write(customer.toString());
                writer.newLine();
            }
        }
    }

    private void saveProductsToFile(String fileName) throws IOException {
        //此处仅列出三件商品
        products.add(new Product("1","洗发水","宝洁公司","2021","小",20,40,100));
        products.add(new Product("2","沐浴露","安利公司","2022","中",40,80,200));
        products.add(new Product("3","洗手液","舒肤佳公司","2023","大",30,60,300));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : products) {
                writer.write(product.toString());
                writer.newLine();
            }
        }
    }

    private void saveAddProductsToFile(String fileName) throws IOException {
        //此处仅列出要添加的三件商品
        products1.add(new Product("4","香皂","多芬公司","2024","大",10,20,400));
        products1.add(new Product("5","牙膏","黑人公司","2022","中",15,30,150));
        products1.add(new Product("6","洗面奶","欧莱雅公司","2020","小",35,70,350));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : products1) {
                writer.write(product.toString());
                writer.newLine();
            }
        }
    }

    private void savePurchaseHistoriesToFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (ShoppingCart shoppingHistory : shoppingHistorys) {
                writer.write(shoppingHistory.toString());
                writer.newLine();
            }
        }
    }
    //------------------------------------------------------------------------------------------------------------------
    public void loadFromFile() {
        try {
            loadCustomersFromFile();
            loadProductsFromFile();
            loadAddProductsFromFile();
            loadPurchaseHistoriesFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomersFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Customer customer = Customer.fromString(line);
                customers.add(customer);
            }
        }
    }

    private void loadProductsFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName2))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Product product = Product.fromString(line);
                products.add(product);
            }
        }
    }

    private void loadAddProductsFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName3))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Product product = Product.fromString(line);
                products1.add(product);
            }
        }
    }

    private void loadPurchaseHistoriesFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName4))) {
            String line;
            while ((line = reader.readLine()) != null) {
                ShoppingCart history = ShoppingCart.fromString(line);
                shoppingHistorys.add(history);
            }
        }
    }
}