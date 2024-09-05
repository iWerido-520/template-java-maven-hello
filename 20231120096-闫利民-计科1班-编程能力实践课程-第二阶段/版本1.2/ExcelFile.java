package org.example;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelFile {
    List<Customer> customers=new LinkedList<>();
    List<Product> products=new LinkedList<>();
    List<ShoppingCart> shoppingHistorys=new ArrayList<>();

    public void readFromExcel() {
        try {
            FileInputStream fis = new FileInputStream(new File("shopping_data.xlsx"));
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet customerSheet = workbook.getSheet("customers");
            Sheet productSheet = workbook.getSheet("Products");
            Sheet shoppingHistorySheet = workbook.getSheet("ShoppingHistory");

            for (Row row : customerSheet) {
                if (row.getRowNum() == 0) continue;
                String id = row.getCell(0).getStringCellValue();
                String username = row.getCell(1).getStringCellValue();
                String level=row.getCell(2).getStringCellValue();
                String registrationTime=row.getCell(3).getStringCellValue();
                double totalConsumption=row.getCell(4).getNumericCellValue();
                String phone=row.getCell(5).getStringCellValue();
                String email=row.getCell(6).getStringCellValue();
                customers.add(new Customer(id,username,level,registrationTime,totalConsumption,phone,email));
            }

            for (Row row : productSheet) {
                if (row.getRowNum() == 0) continue;
                String id = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                String manufacturer = row.getCell(2).getStringCellValue();
                String productionDate = row.getCell(3).getStringCellValue();
                String model =row.getCell(4).getStringCellValue();
                double purchasePrice = row.getCell(5).getNumericCellValue();
                double retailPrice = row.getCell(6).getNumericCellValue();
                int quantity = (int)row.getCell(7).getNumericCellValue();
                products.add(new Product(id,name,manufacturer,productionDate,model,purchasePrice,retailPrice,quantity));
            }

            for (Row row : shoppingHistorySheet) {
                if (row.getRowNum() == 0) continue;
                String name = row.getCell(1).getStringCellValue();
                double totalPrice = row.getCell(2).getNumericCellValue();
                int quantity = (int) row.getCell(3).getNumericCellValue();
                shoppingHistorys.add(new ShoppingCart(name,totalPrice,quantity));
            }

            workbook.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToExcel() {
        //此处仅列出三位顾客
        customers.add(new Customer("111","张三","金牌","2020",1000,"12345","zhangsan@111"));
        customers.add(new Customer("222","李四","银牌","2021",2000,"23456","lisi@222"));
        customers.add(new Customer("333","王五","铜牌","2022",3000,"34567","wangwu@333"));
        //此处仅列出六件商品
        products.add(new Product("1","洗发水","宝洁公司","2021","小",20,40,100));
        products.add(new Product("2","沐浴露","安利公司","2022","中",40,80,200));
        products.add(new Product("3","洗手液","舒肤佳公司","2023","大",30,60,300));
        products.add(new Product("4","香皂","多芬公司","2024","大",10,20,400));
        products.add(new Product("5","牙膏","黑人公司","2022","中",15,30,150));
        products.add(new Product("6","洗面奶","欧莱雅公司","2020","小",35,70,350));

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet customerSheet = workbook.createSheet("Customers");
            Sheet productSheet = workbook.createSheet("Products");
            Sheet shoppingHistorySheet = workbook.createSheet("ShoppingHistory");

            // Write customer data
            Row customerHeaderRow = customerSheet.createRow(0);
            customerHeaderRow.createCell(0).setCellValue("Id");
            customerHeaderRow.createCell(1).setCellValue("Username");
            customerHeaderRow.createCell(2).setCellValue("Level");
            customerHeaderRow.createCell(3).setCellValue("RegistrationTime");
            customerHeaderRow.createCell(4).setCellValue("TotalConsumption");
            customerHeaderRow.createCell(5).setCellValue("Phone");
            customerHeaderRow.createCell(6).setCellValue("Email");
            for (Customer customer : customers) {
                Row row = customerSheet.createRow(customerSheet.getLastRowNum() + 1);
                row.createCell(0).setCellValue(customer.getId());
                row.createCell(1).setCellValue(customer.getUsername());
                row.createCell(2).setCellValue(customer.getLevel());
                row.createCell(3).setCellValue(customer.getRegistrationTime());
                row.createCell(4).setCellValue(customer.getTotalConsumption());
                row.createCell(5).setCellValue(customer.getPhone());
                row.createCell(6).setCellValue(customer.getEmail());
            }

            // Write product data
            Row productHeaderRow = productSheet.createRow(0);
            productHeaderRow.createCell(0).setCellValue("Id");
            productHeaderRow.createCell(1).setCellValue("Name");
            productHeaderRow.createCell(2).setCellValue("Manufacturer");
            productHeaderRow.createCell(3).setCellValue("ProductionDate");
            productHeaderRow.createCell(4).setCellValue("Model");
            productHeaderRow.createCell(5).setCellValue("PurchasePrice");
            productHeaderRow.createCell(6).setCellValue("RetailPrice");
            productHeaderRow.createCell(7).setCellValue("Quantity");
            for (Product product : products) {
                Row row = productSheet.createRow(productSheet.getLastRowNum() + 1);
                row.createCell(0).setCellValue(product.getId());
                row.createCell(1).setCellValue(product.getName());
                row.createCell(2).setCellValue(product.getManufacturer());
                row.createCell(3).setCellValue(product.getProductionDate());
                row.createCell(4).setCellValue(product.getModel());
                row.createCell(5).setCellValue(product.getPurchasePrice());
                row.createCell(6).setCellValue(product.getRetailPrice());
                row.createCell(7).setCellValue(product.getQuantity());
            }

            // Write shoppingHistory data
            Row shoppingHistoryHeaderRow = shoppingHistorySheet.createRow(0);
            shoppingHistoryHeaderRow.createCell(0).setCellValue("Product Name");
            shoppingHistoryHeaderRow.createCell(1).setCellValue("TotalPrice");
            shoppingHistoryHeaderRow.createCell(2).setCellValue("Quantity");
            for (ShoppingCart history : shoppingHistorys) {
                Row row = shoppingHistorySheet.createRow(shoppingHistorySheet.getLastRowNum() + 1);
                row.createCell(0).setCellValue(history.getName());
                row.createCell(1).setCellValue(history.getTotalPrice());
                row.createCell(2).setCellValue(history.getQuantity());
            }

            FileOutputStream fos = new FileOutputStream(new File("shopping_data.xlsx"));
            workbook.write(fos);
            workbook.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}