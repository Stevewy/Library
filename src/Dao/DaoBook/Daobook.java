package Dao.DaoBook;

import Entity.Book.Book;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
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

    public static Book searchBookByName(String name){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            for(int i = 0;i < b.size();i++){
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

    public static Book searchBookByNumber(String number){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            for(int i = 0;i < b.size();i++){
                if(number.equals(b.get(i).getNumber())){
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

}


