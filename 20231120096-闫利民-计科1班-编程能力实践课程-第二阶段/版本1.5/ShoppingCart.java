package org.example;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ShoppingCart {
    List<Product> products=new LinkedList<>();
    Map<String,Integer> cart=new LinkedHashMap<>();
    List<String> shoppingHistory = new ArrayList<>();
    Scanner reader=new Scanner(System.in);

    public ShoppingCart(){
    }
    public ShoppingCart(String input){
    }

    public void addToCart(){
        products.add(new Product("1","洗发水","宝洁公司","2021","小",20,40,100));
        products.add(new Product("2","沐浴露","安利公司","2022","中",40,80,200));
        products.add(new Product("3","洗手液","舒肤佳公司","2023","大",30,60,300));
        products.add(new Product("4","香皂","多芬公司","2024","大",10,20,400));
        products.add(new Product("5","牙膏","黑人公司","2022","中",15,30,150));
        products.add(new Product("6","洗面奶","欧莱雅公司","2020","小",35,70,350));

        System.out.println("请输入要添加的商品编号：");
        String id=reader.next();
        System.out.println("请输入要添加的商品数量：");
        int quantity=reader.nextInt();
        int count=0;
        for(Product product:products){
            if(id.equals(product.getId())){
                int currentQuantity = cart.getOrDefault(product.getName(),0);
                if(currentQuantity+quantity<=product.getQuantity()){
                    cart.put(product.getName(),currentQuantity+quantity);
                    System.out.println("商品" +product.getName()+"已添加到购物车，数量："+quantity);
                }else{
                    System.out.println("该商品内存不足！");
                }
                count++;
                break;
            }
        }
        if(count==0){
            System.out.println("该商品不存在!");
        }
        System.out.println("购物车内已有的商品："+cart);
    }
    public static ShoppingCart fromString(String input){
        return new ShoppingCart(input);
    }

    public void removeFromCart(){
        System.out.println("请输入您想要移除的商品名称：");
        String name=reader.next();
        System.out.println("您确定要移除吗？移除后无法恢复!");
        System.out.println("如果确定，请输入1;如果想要退出，请输入0");
        int i=reader.nextInt();
        switch(i){
            case 1:
                int count=0;
                Set<String> keys=cart.keySet();
                for(String key:keys){
                    if(name.equals(key)){
                        cart.remove(key);
                        count++;
                        break;
                    }
                }
                if(count==1){
                    System.out.println("已成功移除！以下是购物车剩下的商品：");
                    System.out.println(cart);
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

    public void modifyCartItem(){
        System.out.println("请输入您想要修改数量的商品名称：");
        String name=reader.next();
        int quantity;
        int currentQuantity;
        int count=0;
        Set<String> keys=cart.keySet();
        for(String key:keys){
            if(name.equals(key)){
                while(true){
                    System.out.println("增加商品请输入1，减少商品请输入2：");
                    int choice=reader.nextInt();
                    int cnt=0;
                    switch(choice){
                        case 1:
                            System.out.println("请输入您想要增加的商品数量：");
                            quantity=reader.nextInt();
                            currentQuantity = cart.getOrDefault(key,0);
                            int cnt1=0;
                            for(Product product:products){
                                if(name.equals(product.getName())&&currentQuantity+quantity<=product.getQuantity()){
                                    cart.put(key,currentQuantity+quantity);
                                    cnt1++;
                                }
                            }
                            if(cnt1==0){
                                System.out.println("很抱歉，您要添加的商品数量超过上限，您是否想要将剩下的商品库存添加到购物车？");
                                System.out.println("如果是请输入1，如果不是请输入2：");
                                int num=reader.nextInt();
                                switch(num){
                                    case 1:
                                        for(Product product:products){
                                            if(name.equals(product.getName())){
                                                cart.put(key,product.getQuantity());
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println("很抱歉，您要添加的商品数量超过上限，添加失败！");
                                        break;
                                    default:
                                        System.out.println("无效的选项，请重新选择。");
                                }

                            }
                            count++;
                            cnt++;
                            break;
                        case 2:
                            System.out.println("请输入您想要减少的商品数量：");
                            quantity=reader.nextInt();
                            currentQuantity = cart.getOrDefault(key,0);
                            if(currentQuantity-quantity>0){
                                cart.put(key,currentQuantity-quantity);
                            }else if(currentQuantity-quantity<=0){
                                cart.remove(key);
                            }
                            count++;
                            cnt++;
                            break;
                        default:
                            System.out.println("无效的选项，请重新选择。");
                    }
                    if(cnt==1){
                        break;
                    }
                }
                break;
            }
        }
        if(count==1){
            System.out.println("以下是购物车剩下的商品：");
            System.out.println(cart);
        }else{
            System.out.println("该商品不存在！");
        }
    }

    public void checkout() {
        int count=0;
        System.out.println("请输入您想要购买的商品名称：");
        String name=reader.next();
        System.out.println("请输入您想要购买的商品数量：");
        int quantity=reader.nextInt();
        double retailPrice=0;
        double totalPrice;
        for(Product product:products){
            if(name.equals(product.getName())){
                retailPrice=product.getRetailPrice();
            }
        }
        for (Map.Entry<String, Integer> entry:cart.entrySet()) {
            if(name.equals(entry.getKey())){
                int currentQuantity = cart.getOrDefault(name,0);
                if(currentQuantity-quantity>0){
                    totalPrice=retailPrice*quantity;
                    cart.put(name,currentQuantity-quantity);
                    shoppingHistory.add("购买时间："+new Date()+", 商品名称："+name+", 总价："+totalPrice+", 数量："+quantity);
                    SQLite.insertShoppingHistory(name,totalPrice,quantity);
                }else if(currentQuantity-quantity==0){
                    cart.remove(name);
                    totalPrice=retailPrice*quantity;
                    shoppingHistory.add("购买时间："+new Date()+", 商品名称："+name+", 总价："+totalPrice+", 数量："+quantity);
                    SQLite.insertShoppingHistory(name,totalPrice,quantity);
                }else if(currentQuantity-quantity<0){
                    totalPrice=retailPrice*currentQuantity;
                    System.out.println("该商品内存不足，仅剩"+currentQuantity+"件");
                    while(true){
                        System.out.println("若想将剩余的商品购买，请输入1；退出请输入0：");
                        int choice=reader.nextInt();
                        int cnt=0;
                        switch(choice){
                            case 1:
                                cart.remove(name);
                                shoppingHistory.add("购买时间："+new Date()+", 商品名称："+name+", 总价："+totalPrice+", 数量："+currentQuantity);
                                SQLite.insertShoppingHistory(name,totalPrice,quantity);
                                cnt++;
                                break;
                            case 0:
                                System.out.println("已退出！");
                                count++;
                                cnt++;
                                break;
                            default:
                                System.out.println("无效的选项，请重新选择。");
                        }
                        if(cnt==1){
                            break;
                        }
                    }
                }
                count++;
                break;
            }
        }
        switch (count) {
            case 1:
                while(true){
                    System.out.println("请选择支付方式：1.支付宝  2.微信  3.银行卡");
                    int choice=reader.nextInt();
                    int cnt=0;
                    switch(choice){
                        case 1:
                            System.out.println("模拟支付宝支付成功！以下是购物车剩下的商品：");
                            cnt++;
                            break;
                        case 2:
                            System.out.println("模拟微信支付成功！以下是购物车剩下的商品：");
                            cnt++;
                            break;
                        case 3:
                            System.out.println("模拟银行卡支付成功！以下是购物车剩下的商品：");
                            cnt++;
                            break;
                        default:
                            System.out.println("无效的选项，请重新选择。");
                    }
                    if(cnt==1){
                        break;
                    }
                }   System.out.println(cart);
                break;
            case 0:
                System.out.println("该商品不存在！");
                break;
            default:
                System.out.println("该商品内存不足！");
                break;
        }
    }

    public void viewShoppingHistory() {
        if(shoppingHistory.isEmpty()) {
            System.out.println("购物历史为空！");
        }else{
            System.out.println("购物历史：");
            for(String history:shoppingHistory) {
                System.out.println(history);
            }
        }
    }
}
