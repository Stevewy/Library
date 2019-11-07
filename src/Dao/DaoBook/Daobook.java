package Dao.DaoBook;

import Entity.Book.Book;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function
 */
public class Daobook {

    public static void lookAllBook(){                          //查看图书馆所有书籍
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            for(int i = 0;i < b.size();i++){
                System.out.print(b.get(i)+"  ");
                if((i+1) % 3 == 0) System.out.println();// 打印5本书后换行
            }
            in.close();
        }
        catch (Exception e){                            //出现问题,关闭程序
            System.out.println("书库出问题,请尽快联系管理员修复问题,图书馆即将关闭");
            System.exit(0);
        }
    }

    public static Book searchBookByName(String name){       //用书名得到书
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            for(int i = 0;i < b.size();i++){             //遍历查找书名相同
                if(name.equals(b.get(i).getName())){
                    return b.get(i);
                }
            }
            return null;
        }catch (Exception e){
            System.out.println("书库出问题,请尽快联系管理员修复问题,图书馆即将关闭");
            System.exit(0);
            return null;
        }
    }

    public static Book searchBookByNumber(String number){      //用书号得到书
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            for(int i = 0;i < b.size();i++){
                if(number.equals(b.get(i).getNumber())){      //遍历查找书号相同的
                    return b.get(i);
                }
            }
            return null;
        }catch (Exception e){
            System.out.println("书库出问题,请尽快联系管理员修复问题,图书馆即将关闭");
            System.exit(0);
            return null;
        }
    }

//    public static int setBook(Book book){                     //向书库中加书
//        try{
//            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
//            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
//            for(int i = 0;i < b.size();i++){
//                if(book.equals(b.get(i))){      //遍历查找书相同的
//                    System.out.println("该书已经在书库中,不予添加");
//                    return -1;
//                }
//            }
//            b.add(book);                                       //增加图书
//            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("book.txt"));  //写入book.txt
//            out.writeObject(b);
//            in.close();
//            out.close();
//            return 0;
//        }catch (Exception e){
//            System.out.println("书库出问题,请尽快联系管理员修复问题,图书馆即将关闭");
//            System.exit(0);
//            return -1;
//        }
//    }

    public static void addBook(Book book,int number){
        try{
            int i;
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            for(i = 0;i < b.size();i++){                   //遍历书库,如果有直接加数量,没有就结束,此时i=b.size()
                if(book.equals(b.get(i))){
                    b.get(i).addNumber(number);
                    break;
                }
            }
            if(i == b.size()){                        //书库没有,直接添加
                book.setNumber(number);
                b.add(book);
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("book.txt"));  //写入book.txt
            out.writeObject(b);
            in.close();
            out.close();
            System.out.println("已经为"+b.get(i).getName()+"增加了"+number+"本");
        }catch (Exception e){
            System.out.println("书库出问题,请尽快联系管理员修复问题,图书馆即将关闭");
            System.exit(0);
        }
    }
}


