package org.example;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

// 客户类
public class Customer extends User {
    private String level;
    private String registrationTime;
    private double totalConsumption;
    Admin admin=new Admin();
    Scanner reader=new Scanner(System.in);
    Random random=new Random();
    List<Customer> customers=new LinkedList<>();
    ShoppingCart cart=new ShoppingCart();

    public Customer(){
    }
    public Customer(String id,String username,String password,String level,String registrationTime,double totalConsumption,String phone,String email){
        this.id=id;
        this.username=username;
        this.password=password;
        this.level=level;
        this.registrationTime=registrationTime;
        this.totalConsumption=totalConsumption;
        this.phone=phone;
        this.email=email;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public String getRegistrationTime() {
        return registrationTime;
    }
    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }
    public double getTotalConsumption() {
        return totalConsumption;
    }
    public void setTotalConsumption(double totalConsumption) {
        this.totalConsumption = totalConsumption;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Double.compare(totalConsumption, customer.totalConsumption) == 0 && Objects.equals(level, customer.level) && Objects.equals(registrationTime, customer.registrationTime) && Objects.equals(admin, customer.admin) && Objects.equals(reader, customer.reader) && Objects.equals(random, customer.random) && Objects.equals(customers, customer.customers) && Objects.equals(cart, customer.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, registrationTime, totalConsumption, admin, reader, random, customers, cart);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", level='" + level + '\'' +
                ", registrationTime='" + registrationTime + '\'' +
                ", totalConsumption=" + totalConsumption +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    //----------------------------------------------------------------------------------------------------------------------
    public boolean register() {
        int cnt=0;
        int count=0;
        System.out.println("请输入用户名：");
        username=reader.nextLine();
        System.out.println("请输入密码：");
        password=reader.nextLine();
        System.out.println("请输入邮箱：");
        email=reader.nextLine();
        System.out.println("请输入手机号：");
        phone=reader.nextLine();

        String usernamePattern = "^.{5,}$";
        if(Pattern.matches(usernamePattern, username)){
            System.out.println("用户名有效。");
            count++;
        } else {
            System.out.println("用户名无效，长度必须不少于5个字符。");
        }
        String passwordPattern = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+}{:;<>,.?/~-]).{8,}$";
        if(Pattern.matches(passwordPattern, password)){
            System.out.println("密码有效。");
            count++;
        } else {
            System.out.println("密码无效，长度必须大于8个字符，且必须包含大小写字母、数字和标点符号。");
        }
        for(int i=0;i<phone.length();i++){
            char c=phone.charAt(i);
            if(!(c>='0'&&c<='9')){
                cnt++;
            }
        }
        if(phone.length()==11&&phone.startsWith("1")&&cnt==0){
            System.out.println("手机号有效。");
            count++;
        } else {
            System.out.println("手机号无效，长度必须大于11个字符，且必须以1开头，只能是数字。");
        }
        if(count==3){
            super.username=username;
            super.password=password;
            super.email=email;
            super.phone=phone;
            return true;
        }
        else{
            System.out.println("注册失败，请重新注册！");
            return false;
        }
    }

    @Override
    public boolean login() {
        customers.add(new Customer("111","张三","Abc@123","金牌","2020",1000,"12345","zhangsan@111"));
        customers.add(new Customer("222","李四","Abc@456","银牌","2021",2000,"23456","lisi@222"));
        customers.add(new Customer("333","王五","Abc@789","铜牌","2022",3000,"34567","wangwu@333"));

        int i=0;
        System.out.println("新用户登录请选择1，老用户登录请选择2：");
        int choice=reader.nextInt();
        switch(choice){
            case 1:
                for(i=1;i<=5;i++){
                    System.out.println("请输入用户名：");
                    username=reader.next();
                    System.out.println("请输入密码：");
                    password=reader.next();
                    if(username.equals(this.username) && password.equals(this.password)){
                        return true;
                    }
                    else{
                        System.out.println("登录失败！");
                        System.out.println("您还剩"+(5-i)+"次机会！");
                    }
                }
                break;
            case 2:
                for(i=1;i<=5;i++) {
                    int count=0;
                    System.out.println("请输入用户名：");
                    String username1=reader.next();
                    System.out.println("请输入密码：");
                    String password1=reader.next();
                    for(Customer customer:customers){
                        if(username1.equals(customer.getUsername()) && password1.equals(customer.getPassword())){
                            count++;
                        }
                    }
                    if(count==0){
                        System.out.println("登录失败！");
                        System.out.println("您还剩"+(5-i)+"次机会！");
                    }
                    else{
                        return true;
                    }
                }
                break;
            default:
                System.out.println("无效的选项，请重新选择。");
        }
        if(i==6){
            System.out.println("您的账户已锁定！");
            while(true){
                //空循环体，程序将卡在这里
            }
        }
        return false;
    }
    //----------------------------------------------------------------------------------------------------------------------
    public boolean changePassword() {
        System.out.println("新用户修改密码请选择1，老用户修改密码请选择2：");
        int choice=reader.nextInt();
        switch(choice){
            case 1:
                System.out.println("请输入新密码：");
                String newPassword=reader.next();
                String passwordPattern = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+}{:;<>,.?/~-]).{8,}$";
                if(Pattern.matches(passwordPattern, newPassword)){
                    System.out.println("密码有效，已成功修改！");
                    this.password=newPassword;
                    System.out.println("请重新登录！");
                    if(login()){
                        System.out.println("登录成功，欢迎客户！");
                    }
                    return true;
                } else {
                    System.out.println("密码无效，长度必须大于8个字符，且必须包含大小写字母、数字和标点符号。");
                    return false;
                }
            case 2:
                ListIterator<Customer> it=customers.listIterator();
                System.out.println("请输入原有密码：");
                String originPassword=reader.next();
                System.out.println("请输入新密码：");
                String newPassword1=reader.next();
                String passwordPattern1 = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+}{:;<>,.?/~-]).{8,}$";
                int count=0;
                while(it.hasNext()){
                    Customer cus=it.next();
                    if(Pattern.matches(passwordPattern1, newPassword1)&&originPassword.equals(cus.getPassword())){
                        cus.setPassword(newPassword1);
                        System.out.println("密码有效，已成功修改！");
                        System.out.println("请重新登录！");
                        if(login()){
                            System.out.println("登录成功，欢迎客户！");
                        }
                        count++;
                        break;
                    }
                }
                if(count==0){
                    System.out.println("原密码错误或新密码无效。新密码长度必须大于8个字符，且必须包含大小写字母、数字和标点符号。");
                    return false;
                }
                return true;
            default:
                System.out.println("无效的选项，请重新选择。");
        }
        return false;
    }

    public boolean resetPassword() {
        customers.add(new Customer("111","张三","Abc@123","金牌","2020",1000,"12345","zhangsan@111"));
        customers.add(new Customer("222","李四","Abc@456","银牌","2021",2000,"23456","lisi@222"));
        customers.add(new Customer("333","王五","Abc@789","铜牌","2022",3000,"34567","wangwu@333"));

        String newPassword=null;
        while(true){
            int count=0;
            System.out.println("请输入用户名：");
            username=reader.next();
            System.out.println("请输入注册邮箱地址：");
            email=reader.next();
            for(Customer customer:customers){
                if(username.equals(customer.getUsername())&&email.equals(customer.getEmail())){
                    System.out.println("新密码已发送到您的邮箱，请查收并使用新密码登录。");
                    newPassword=generateRandomPassword();
                    customer.setPassword(newPassword);
                    count++;
                    break;
                }
            }
            if(count==1){
                break;
            }else{
                System.out.println("用户名或邮箱地址不正确，请检查后重试。");
            }
        }
        while(true){
            int count=0;
            System.out.println("请再次输入邮箱：");
            email=reader.next();
            for(Customer customer:customers){
                if(email.equals(customer.getEmail())){
                    System.out.println("邮箱正确，您可以使用这个密码登录：");
                    System.out.println(newPassword);
                    count++;
                    break;
                }
            }
            if(count==1){
                return true;
            }else{
                System.out.println("邮箱地址不正确，请检查后重试。");
            }
        }
    }

    public String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        int passwordLength = 8;
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < passwordLength; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }
    //----------------------------------------------------------------------------------------------------------------------
    public void addToCart() {
        cart.addToCart();
    }
    public void removeFromCart() {
        cart.removeFromCart();
    }
    public void modifyCartItem() {
        cart.modifyCartItem();
    }
    public void checkout() {
        cart.checkout();
    }
    public void viewShoppingHistory() {
        cart.viewShoppingHistory();
    }
    @Override
    public void logout(){
        System.out.println("已退出客户系统。");
    }
}
