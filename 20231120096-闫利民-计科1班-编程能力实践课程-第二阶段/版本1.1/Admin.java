package org.example;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Pattern;

// 管理员类
public class Admin extends User {
    private static final String ADMIN_USERNAME = "admin";
    private String ADMIN_PASSWORD = "ynuinfo#777";
    Scanner reader=new Scanner(System.in);

    public Admin(){
    }
    @Override
    public boolean login() {
        System.out.println("请输入管理员账户名：");
        username=reader.next();
        System.out.println("请输入管理员密码：");
        password=reader.next();
        return username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD);
    }

    public boolean changePassword() {
        System.out.println("请输入原有密码：");
        String originPassword=reader.next();
        System.out.println("请输入新密码：");
        String newPassword=reader.next();
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+}{:;<>,.?/~-]).{8,}$";
        if(Pattern.matches(passwordPattern,newPassword)&&originPassword.equals(ADMIN_PASSWORD)){
            ADMIN_PASSWORD=newPassword;
            System.out.println("密码有效，已成功修改！");
            System.out.println("请重新登录！");
        }else{
            System.out.println("原密码错误或新密码无效。新密码长度必须大于8个字符，且必须包含大小写字母、数字和标点符号。");
            return false;
        }
        return true;
    }

    public void resetPassword(List<Customer> customers) {
        int count=0;
        ListIterator<Customer> it=customers.listIterator();
        System.out.println("请输入要重置密码的用户名：");
        username=reader.next();
        while(it.hasNext()){
            Customer cus=it.next();
            if(username.equals(cus.getUsername())){
                cus.setPassword("Abc@12345");
                count++;
            }
        }
        if(count==1){
            System.out.println("密码已重置成功！");
            System.out.println("请告诉用户可以使用这个密码登录：Abc@12345");
        }else{
            System.out.println("该用户不存在！");
        }
    }
    //----------------------------------------------------------------------------------------------------------------------
    public void listAllCustomers(List<Customer> customers) {
        for (Customer customer:customers) {
            System.out.println(customer);
        }
    }

    public void deleteCustomer(List<Customer> customers) {
        ListIterator<Customer> it=customers.listIterator();
        System.out.println("请输入您想要删除的客户ID：");
        id=reader.next();
        System.out.println("您确定要删除吗？");
        System.out.println("如果确定，请输入1；如果想要退出，请输入0");
        int i=reader.nextInt();
        switch(i){
            case 1:
                int count=0;
                while(it.hasNext()){
                    Customer cus=it.next();
                    if(id.equals(cus.id)){
                        it.remove();
                        count++;
                    }
                }
                if(count==1){
                    System.out.println("已成功删除！以下是剩下的客户信息：");
                    System.out.println(customers);
                }else{
                    System.out.println("该用户不存在！");
                }
                break;
            case 0:
                System.out.println("已退出！");
                break;
            default:
                System.out.println("输入的不是有效数字，请重新输入！");
        }
    }

    public void searchCustomer(List<Customer> customers) {
        System.out.println("请选择你要查询的方式：");
        System.out.println("1代表根据客户ID进行查询，2代表根据客户的用户名查询，3代表一次查询所有客户的信息");
        int i=reader.nextInt();
        switch(i){
            case 1:
                int count=0;
                System.out.println("请输入您想要查询的客户ID：");
                id=reader.next();
                for(Customer customer:customers){
                    if(customer.getId().equals(id)){
                        System.out.println(customer);
                        count++;
                    }
                }
                if(count==0){
                    System.out.println("该用户不存在！");
                }
                break;
            case 2:
                int cnt=0;
                System.out.println("请输入您想要查询的客户的用户名：");
                username=reader.next();
                for(Customer customer:customers){
                    if(customer.getUsername().equals(username)){
                        System.out.println(customer);
                        cnt++;
                    }
                }
                if(cnt==0){
                    System.out.println("该用户不存在！");
                }
                break;
            case 3:
                for (Customer customer:customers) {
                    System.out.println(customer);
                }
                break;
            default:
                System.out.println("输入的不是有效数字，请重新输入！");
        }
    }
    //----------------------------------------------------------------------------------------------------------------------
    public void listAllProducts(List<Product> products) {
        for (Product product:products) {
            System.out.println(product);
        }
    }

    public void addProduct() {
        System.out.println("功能移到主函数。。。");
    }

    public void modifyProduct() {
        System.out.println("功能移到主函数。。。");
    }

    public void deleteProduct(List<Product> products) {
        ListIterator<Product> it=products.listIterator();
        System.out.println("请输入您想要删除的商品名称：");
        String name=reader.next();
        System.out.println("您确定要删除吗？删除后无法恢复!");
        System.out.println("如果确定，请输入1;如果想要退出，请输入0");
        int i=reader.nextInt();
        switch(i){
            case 1:
                int count=0;
                while(it.hasNext()){
                    Product pro=it.next();
                    if(name.equals(pro.name)){
                        it.remove();
                        count++;
                    }
                }
                if(count==1){
                    System.out.println("已成功删除！以下是剩下的商品信息：");
                    System.out.println(products);
                }else{
                    System.out.println("该商品不存在！");
                }
                break;
            case 0:
                System.out.println("已退出！");
                break;
            default:
                System.out.println("输入的不是有效数字，请重新输入！");
        }
    }

    public void searchProduct(List<Product> products) {
        System.out.println("请选择你要查询的方式：");
        System.out.println("1代表根据商品名称进行查询，2代表根据生产厂家进行查询，3代表根据零售价格进行查询，4代表组合查询");
        int i=reader.nextInt();
        switch(i){
            case 1:
                int count1=0;
                System.out.println("请输入您想要查询的商品名称：");
                String name=reader.next();
                for(Product product:products){
                    if(product.getName().equals(name)){
                        System.out.println(product);
                        count1++;
                    }
                }
                if(count1==0){
                    System.out.println("该商品不存在！");
                }
                break;
            case 2:
                int count2=0;
                System.out.println("请输入您想要查询的商品的生产厂家：");
                String manufacturer=reader.next();
                for(Product product:products){
                    if(product.getManufacturer().equals(manufacturer)){
                        System.out.println(product);
                        count2++;
                    }
                }
                if(count2==0){
                    System.out.println("该商品不存在！");
                }
                break;
            case 3:
                int count3=0;
                System.out.println("请输入您想要查询的商品的零售价格：");
                double retailPrice=reader.nextDouble();
                for(Product product:products){
                    if(product.getRetailPrice()==retailPrice){
                        System.out.println(product);
                        count3++;
                    }
                }
                if(count3==0){
                    System.out.println("该商品不存在！");
                }
                break;
            case 4:
                int count4=0;
                System.out.println("请根据商品名称、生产厂家、零售价格进行组合查询：");
                String name1=reader.next();
                String manufacturer1=reader.next();
                double retailPrice1=reader.nextDouble();
                for(Product product:products){
                    if(product.getName().equals(name1)&&product.getManufacturer().equals(manufacturer1)&&product.getRetailPrice()==retailPrice1){
                        System.out.println(product);
                        count4++;
                    }
                }
                if(count4==0){
                    System.out.println("该商品不存在！");
                }
                break;
            default:
                System.out.println("输入的不是有效数字，请重新输入！");
        }
    }
    @Override
    public void logout(){
        System.out.println("已退出管理员管理系统。");
    }
}
