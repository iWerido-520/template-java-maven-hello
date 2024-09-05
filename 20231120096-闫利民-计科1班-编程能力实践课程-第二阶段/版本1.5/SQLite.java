package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {
    private static final String DB_URL = "jdbc:sqlite:shopping.db";

    // 创建用户表、商品表和购物历史表
    public static void createTables() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String createCustomerTable = "CREATE TABLE IF NOT EXISTS customers (" +
                    "id TEXT," +
                    "username TEXT," +
                    "level TEXT," +
                    "registrationTime TEXT," +
                    "totalConsumption REAL," +
                    "phone TEXT," +
                    "email TEXT)";
            stmt.execute(createCustomerTable);

            String createProductTable = "CREATE TABLE IF NOT EXISTS products (" +
                    "id TEXT," +
                    "name TEXT," +
                    "manufacturer TEXT," +
                    "productionDate TEXT," +
                    "model TEXT," +
                    "purchasePrice REAL," +
                    "retailPrice REAL," +
                    "quantity INTEGER)";
            stmt.execute(createProductTable);

            String createShoppingHistoryTable = "CREATE TABLE IF NOT EXISTS purchase_history (" +
                    "name TEXT," +
                    "totalPrice REAL," +
                    "quantity INTEGER)";
            stmt.execute(createShoppingHistoryTable);
        } catch (SQLException e) {
            System.out.println("创建表时出现错误：" + e.getMessage());
        }
    }

    // 插入用户信息
    public static void insertCustomer(String id,String username,String level,String registrationTime,double totalConsumption,String phone,String email) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO customers(id,username,level,registrationTime,totalConsumption,phone,email) VALUES(?,?,?,?,?,?,?)")) {
            pstmt.setString(1,id);
            pstmt.setString(2,username);
            pstmt.setString(3,level);
            pstmt.setString(4,registrationTime);
            pstmt.setDouble(5,totalConsumption);
            pstmt.setString(6,phone);
            pstmt.setString(7,email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("插入用户信息时出现错误：" + e.getMessage());
        }
    }

    // 插入商品信息
    public static void insertProduct(String id, String name, String manufacturer, String productionDate, String model, double purchasePrice, double retailPrice, int quantity) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO products(id,name,manufacturer,productionDate,model,purchasePrice,retailPrice,quantity) VALUES(?,?,?,?,?,?,?,?)")) {
            pstmt.setString(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3,manufacturer);
            pstmt.setString(4,productionDate);
            pstmt.setString(5,model);
            pstmt.setDouble(6,purchasePrice);
            pstmt.setDouble(7,retailPrice);
            pstmt.setInt(8,quantity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("插入商品信息时出现错误：" + e.getMessage());
        }
    }

    // 插入购物历史信息
    public static void insertShoppingHistory(String name,double totalPrice,int quantity) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO shoppingHistorys(name,totalPrice,quantity) VALUES(?,?,?)")) {
            pstmt.setString(1,name);
            pstmt.setDouble(2,totalPrice);
            pstmt.setInt(3,quantity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("插入购物历史信息时出现错误：" + e.getMessage());
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    // 查询用户信息
    public static void queryCustomers() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM customers")) {
            while (rs.next()) {
                System.out.println("用户ID: " + rs.getString("id") +
                        ", 用户名: " + rs.getString("username") +
                        ", 用户级别: " + rs.getString("level")+
                        ", 用户注册时间: " + rs.getString("registrationTime")+
                        ", 客户累计消费总金额: " + rs.getDouble("totalConsumption")+
                        ", 用户手机号: " + rs.getString("phone")+
                        ", 用户邮箱: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("查询用户信息时出现错误：" + e.getMessage());
        }
    }

    // 查询商品信息
    public static void queryProducts() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {
            while (rs.next()) {
                System.out.println("商品编号: " + rs.getString("id") +
                        ", 商品名称: " + rs.getString("name") +
                        ", 生产厂家: " + rs.getString("manufacturer") +
                        ", 生产日期: " + rs.getString("productionDate") +
                        ", 型号: " + rs.getString("model") +
                        ", 进货价: " + rs.getDouble("purchasePrice") +
                        ", 零售价格: " + rs.getDouble("retailPrice") +
                        ", 数量: " + rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.out.println("查询商品信息时出现错误：" + e.getMessage());
        }
    }

    // 查询购物历史信息
    public static void queryShoppingHistory() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM shoppingHistorys")) {
            while (rs.next()) {
                System.out.println("商品名称: " + rs.getString("name") +
                        ", 总价: " + rs.getDouble("totalPrice") +
                        ", 数量: " + rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.out.println("查询购物历史信息时出现错误：" + e.getMessage());
        }
    }
}
