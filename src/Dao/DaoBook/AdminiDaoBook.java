package Dao.DaoBook;

import Entity.Book.Book;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author WangYao
 * @date 2019/11/13
 * @function
 */
public class AdminiDaoBook extends StudentDaoBook {
    public static boolean addBook(Book book, int number){        //为图书馆加书
        try{
            int i;                                         //注意,i要定义在外面,后面需要根据i来操作
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            for(i = 0;i < b.size();i++){                   //遍历书库,如果有直接加数量,没有就结束,此时i=b.size()
                if(book.equals(b.get(i))){
                    b.get(i).addBook(number);
                    break;
                }
            }
            if(i == b.size()){                                         //书库没有,直接添加
                b.add(book);
                book.addBook(number);
            }
            StorageBook(new ObjectOutputStream(new FileOutputStream("book.txt")),b);
            StorageBook(new ObjectOutputStream(new FileOutputStream("copyBook.txt")),b);
            return true;
        }catch (Exception e){
            System.out.println("书库出问题,图书馆即将关闭");
            System.exit(0);
            return false;
        }
    }

    public static boolean deleteBook(Book book,int number){
        try{
            int i;                                                                               //注意,i要定义在外面,后面需要根据i来操作
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            for(i = 0;i < b.size();i++){                                //遍历书库,如果有直接减少数量,数量不够就报错,没有就结束,此时i=b.size()
                if(book.equals(b.get(i))){
                    if(b.get(i).getNowAmount() < number){
                        System.out.println("书库中已有"+book.getNowAmount()+"本,数量小于你要删除的数量,请重新输入");
                        return false;
                    }
                    b.get(i).addBook(-1 * number);
                    break;
                }
            }
            if(i == b.size())                                                                                     //书库没有,直接报错
                return false;

            StorageBook(new ObjectOutputStream(new FileOutputStream("book.txt")),b);
            StorageBook(new ObjectOutputStream(new FileOutputStream("copyBook.txt")),b);
            return true;
        }catch (Exception e){
            System.out.println("书库出问题,图书馆即将关闭");
            System.exit(0);
            return true;
        }
    }

    public static boolean copyBook(){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("copyBook.txt"));
            out.writeObject(b);
            out.close();
            return true;
        }
        catch (Exception e){                                                         //出现问题,返回false
            return false;
        }
    }
}
