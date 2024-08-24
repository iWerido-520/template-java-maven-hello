package org.example;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

// 主类
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin=new Admin();
        Customer customer=new Customer();
        List<Customer> customers=new LinkedList<>();
        List<Product> products=new LinkedList<>();
        List<Product> products1=new LinkedList<>();

        //此处仅列出三位顾客
        customers.add(new Customer("111","张三","Abc@123","金牌","2020",1000,"12345","zhangsan@111"));
        customers.add(new Customer("222","李四","Abc@456","银牌","2021",2000,"23456","lisi@222"));
        customers.add(new Customer("333","王五","Abc@789","铜牌","2022",3000,"34567","wangwu@333"));
        //此处仅列出三件商品
        products.add(new Product("1","洗发水","宝洁公司","2021","小",20,40,100));
        products.add(new Product("2","沐浴露","安利公司","2022","中",40,80,200));
        products.add(new Product("3","洗手液","舒肤佳公司","2023","大",30,60,300));
        //此处仅列出要添加的三件商品
        products1.add(new Product("4","香皂","多芬公司","2024","大",10,20,400));
        products1.add(new Product("5","牙膏","黑人公司","2022","中",15,30,150));
        products1.add(new Product("6","洗面奶","欧莱雅公司","2020","小",35,70,350));

        while(true){
            System.out.println("您好！管理员请选择1，客户请选择2，关闭系统请选择3：");
            int choice1 = scanner.nextInt();
            switch (choice1){
                case 1:
                    System.out.println("请输入管理员账户和密码：");
                    if(admin.login()){
                        System.out.println("登录成功，欢迎管理员！");

                        outerLoop:
                        while(true){
                            System.out.println("请选择接下来的操作：");
                            System.out.println("1. 密码管理");
                            System.out.println("2. 客户管理");
                            System.out.println("3. 商品管理");
                            System.out.println("4. 退出登录");
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
                                            admin.changePassword();
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
                                            int count=0;
                                            ListIterator<Product> it=products1.listIterator();
                                            System.out.println("请输入您想要添加的商品名称：");
                                            String name=scanner.next();
                                            while(it.hasNext()){
                                                Product pro=it.next();
                                                if(name.equals(pro.name)){
                                                    products.add(pro);
                                                    count++;
                                                }
                                            }
                                            if(count==1){
                                                System.out.println("已成功添加！以下是添加过后的的商品信息：");
                                                System.out.println(products);
                                            }else{
                                                System.out.println("该商品不存在！");
                                            }
                                            break;
                                        case 3:
                                            admin.modifyProduct();
                                            int count1=0;
                                            System.out.println("请输入您要修改的商品编号：");
                                            String id=scanner.next();
                                            System.out.println("请输入修改后的商品名称：");
                                            String name1=scanner.next();
                                            System.out.println("请输入修改后的生产厂家：");
                                            String manufacturer=scanner.next();
                                            System.out.println("请输入修改后的生产日期：");
                                            String productionDate=scanner.next();
                                            System.out.println("请输入修改后的型号：");
                                            String model=scanner.next();
                                            System.out.println("请输入修改后的进货价：");
                                            double purchasePrice=scanner.nextDouble();
                                            System.out.println("请输入修改后的零售价格：");
                                            double retailPrice=scanner.nextDouble();
                                            System.out.println("请输入修改后的数量：");
                                            int quantity=scanner.nextInt();

                                            for(Product product:products){
                                                if(product.getId().equals(id)){
                                                    product.setName(name1);
                                                    product.setManufacturer(manufacturer);
                                                    product.setProductionDate(productionDate);
                                                    product.setModel(model);
                                                    product.setPurchasePrice(purchasePrice);
                                                    product.setRetailPrice(retailPrice);
                                                    product.setQuantity(quantity);
                                                    count1++;
                                                    break;
                                                }
                                            }
                                            if(count1==1){
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
                                    break outerLoop;
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
