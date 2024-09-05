package org.example;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// 主类
public class Main_1_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin=new Admin();
        Customer customer=new Customer();
        List<Customer> customers=new LinkedList<>();
        List<Product> products=new LinkedList<>();

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

        while(true){
            System.out.println("您好！管理员请选择1，客户请选择2，关闭系统请选择3：");
            int choice1 = scanner.nextInt();
            switch (choice1){
                case 1:
                    if(admin.login()){
                        System.out.println("登录成功，欢迎管理员！");

                        outerLoop:
                        while(true){
                            System.out.println("请选择接下来的操作：");
                            System.out.println("1. 密码管理");
                            System.out.println("2. 客户管理");
                            System.out.println("3. 商品管理");
                            System.out.println("4. 退出登录");
                            System.out.println("5. 将所有信息存放在SQLite数据库");
                            System.out.println("6. 从SQLite数据库中查询所有信息");
                            int choice2 = scanner.nextInt();
                            switch (choice2){
                                case 1:
                                    System.out.println("请选择操作：");
                                    System.out.println("1. 修改自身密码");
                                    System.out.println("2. 重置指定用户密码");
                                    int subChoice1 = scanner.nextInt();
                                    scanner.nextLine(); // 处理换行符

                                    switch (subChoice1) {
                                        case 1:
                                            while(true){
                                                if(admin.changePassword()){
                                                    break;
                                                }
                                            }
                                            break outerLoop;
                                        case 2:
                                            admin.resetPassword(customers);
                                            break;
                                        default:
                                            System.out.println("无效的选项，请重新选择。");
                                    }
                                    break;
                                case 2:
                                    System.out.println("请选择操作：");
                                    System.out.println("1. 列出所有客户信息");
                                    System.out.println("2. 删除客户信息");
                                    System.out.println("3. 查询客户信息");
                                    int subChoice2 = scanner.nextInt();
                                    scanner.nextLine(); // 处理换行符

                                    switch (subChoice2) {
                                        case 1:
                                            admin.listAllCustomers(customers);
                                            break;
                                        case 2:
                                            admin.deleteCustomer(customers);
                                            break;
                                        case 3:
                                            admin.searchCustomer(customers);
                                            break;
                                        default:
                                            System.out.println("无效的选项，请重新选择。");
                                    }
                                    break;
                                case 3:
                                    System.out.println("请选择操作：");
                                    System.out.println("1. 列出所有商品的信息");
                                    System.out.println("2. 添加商品的信息");
                                    System.out.println("3. 修改商品的信息");
                                    System.out.println("4. 删除商品的信息");
                                    System.out.println("5. 查询商品的信息");
                                    int subChoice3 = scanner.nextInt();
                                    scanner.nextLine(); // 处理换行符

                                    switch (subChoice3) {
                                        case 1:
                                            admin.listAllProducts(products);
                                            break;
                                        case 2:
                                            admin.addProduct();
                                            System.out.println("请输入您要添加的商品编号：");
                                            String id=scanner.next();
                                            System.out.println("请输入您要添加的商品名称：");
                                            String name=scanner.next();
                                            System.out.println("请输入您要添加的生产厂家：");
                                            String manufacturer=scanner.next();
                                            System.out.println("请输入您要添加的生产日期：");
                                            String productionDate=scanner.next();
                                            System.out.println("请输入您要添加的型号：");
                                            String model=scanner.next();
                                            System.out.println("请输入您要添加的进货价：");
                                            double purchasePrice=scanner.nextDouble();
                                            System.out.println("请输入您要添加的零售价格：");
                                            double retailPrice=scanner.nextDouble();
                                            System.out.println("请输入您要添加的数量：");
                                            int quantity=scanner.nextInt();

                                            products.add(new Product(id,name,manufacturer,productionDate,model,purchasePrice,retailPrice,quantity));
                                            System.out.println("已成功添加！以下是添加过后的的商品信息：");
                                            System.out.println(products);
                                            break;
                                        case 3:
                                            admin.modifyProduct();
                                            int count=0;
                                            System.out.println("请输入您要修改的商品编号：");
                                            String id1=scanner.next();
                                            System.out.println("请输入修改后的商品名称：");
                                            String name1=scanner.next();
                                            System.out.println("请输入修改后的生产厂家：");
                                            String manufacturer1=scanner.next();
                                            System.out.println("请输入修改后的生产日期：");
                                            String productionDate1=scanner.next();
                                            System.out.println("请输入修改后的型号：");
                                            String model1=scanner.next();
                                            System.out.println("请输入修改后的进货价：");
                                            double purchasePrice1=scanner.nextDouble();
                                            System.out.println("请输入修改后的零售价格：");
                                            double retailPrice1=scanner.nextDouble();
                                            System.out.println("请输入修改后的数量：");
                                            int quantity1=scanner.nextInt();

                                            for(Product product:products){
                                                if(product.getId().equals(id1)){
                                                    product.setName(name1);
                                                    product.setManufacturer(manufacturer1);
                                                    product.setProductionDate(productionDate1);
                                                    product.setModel(model1);
                                                    product.setPurchasePrice(purchasePrice1);
                                                    product.setRetailPrice(retailPrice1);
                                                    product.setQuantity(quantity1);
                                                    count++;
                                                    break;
                                                }
                                            }
                                            if(count==1){
                                                System.out.println("已成功修改！以下是修改过后的的商品信息：");
                                                System.out.println(products);
                                            }
                                            else{
                                                System.out.println("该商品不存在！");
                                            }
                                            break;
                                        case 4:
                                            admin.deleteProduct(products);
                                            break;
                                        case 5:
                                            admin.searchProduct(products);
                                            break;
                                        default:
                                            System.out.println("无效的选项，请重新选择。");
                                    }
                                    break;
                                case 4:
                                    admin.logout();
                                    customers.clear();
                                    products.clear();
                                    break outerLoop;
                                case 5:
                                    // 创建用户表、商品表和购物历史表
                                    SQLite.createTables();

                                    //此处仅列出三位顾客
                                    SQLite.insertCustomer("111","张三","金牌","2020",1000,"12345","zhangsan@111");
                                    SQLite.insertCustomer("222","李四","银牌","2021",2000,"23456","lisi@222");
                                    SQLite.insertCustomer("333","王五","铜牌","2022",3000,"34567","wangwu@333");
                                    //此处仅列出六件商品
                                    SQLite.insertProduct("1","洗发水","宝洁公司","2021","小",20,40,100);
                                    SQLite.insertProduct("2","沐浴露","安利公司","2022","中",40,80,200);
                                    SQLite.insertProduct("3","洗手液","舒肤佳公司","2023","大",30,60,300);
                                    SQLite.insertProduct("4","香皂","多芬公司","2024","大",10,20,400);
                                    SQLite.insertProduct("5","牙膏","黑人公司","2022","中",15,30,150);
                                    SQLite.insertProduct("6","洗面奶","欧莱雅公司","2020","小",35,70,350);
                                    break;
                                case 6:
                                    SQLite.queryCustomers();
                                    SQLite.queryProducts();
                                    SQLite.queryShoppingHistory();
                                    break;
                                default:
                                    System.out.println("无效的选项，请重新选择。");
                            }
                        }
                    }else{
                        System.out.println("登录失败，请重新登录！");
                    }
                    break;
                case 2:
                    System.out.println("您好！新用户注册请选择1，登录请选择2，忘记密码请选择3：");
                    int choice2 = scanner.nextInt();
                    switch (choice2){
                        case 1:
                            while(true){
                                if(customer.register()){
                                    System.out.println("注册成功,请重新登录！");
                                    break;
                                }
                            }
                        case 2:
                            if(customer.login()){
                                System.out.println("登录成功，欢迎客户！");

                                outerLoop:
                                while(true){
                                    System.out.println("请选择接下来的操作：");
                                    System.out.println("1. 修改自身密码");
                                    System.out.println("2. 购物");
                                    System.out.println("3. 退出登录");
                                    int choice = scanner.nextInt();
                                    switch(choice){
                                        case 1:
                                            while(true){
                                                if(customer.changePassword()){
                                                    break;
                                                }
                                            }
                                            break;
                                        case 2:
                                            System.out.println("请选择操作：");
                                            System.out.println("1. 将商品加入购物车");
                                            System.out.println("2. 将商品从购物车中移除");
                                            System.out.println("3. 修改购物车中的商品");
                                            System.out.println("4. 结账");
                                            System.out.println("5. 查看购物历史");

                                            int choice3=scanner.nextInt();
                                            switch(choice3){
                                                case 1:
                                                    customer.addToCart();
                                                    break;
                                                case 2:
                                                    customer.removeFromCart();
                                                    break;
                                                case 3:
                                                    customer.modifyCartItem();
                                                    break;
                                                case 4:
                                                    customer.checkout();
                                                    break;
                                                case 5:
                                                    customer.viewShoppingHistory();
                                                    break;
                                                default:
                                                    System.out.println("无效的选项，请重新选择。");
                                            }
                                            break;
                                        case 3:
                                            customer.logout();
                                            customers.clear();
                                            products.clear();
                                            break outerLoop;
                                        default:
                                            System.out.println("无效的选项，请重新选择。");
                                    }
                                }
                            }
                            break;
                        case 3:
                            if(customer.resetPassword()){
                                break;
                            }
                        default:
                            System.out.println("无效的选项，请重新选择。");
                    }
                    break;
                case 3:
                    scanner.close();
                    return;
                default:
                    System.out.println("无效的选项，请重新选择。");
                    break;
            }
        }
    }
}
