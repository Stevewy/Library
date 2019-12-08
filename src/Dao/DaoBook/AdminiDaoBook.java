package Dao.DaoBook;
import Entity.Book.Book;

import java.io.*;
import java.util.ArrayList;

/**
 * @author WangYao
 * @date 2019/11/13
 * @function
 */
public class AdminiDaoBook extends StudentDaoBook {

    /**
     * 管理员手动备份书库(由于自动备份,该方法是个摆设)
     * @return 复制成功则ture
     */
    public boolean copyBook(){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Book\\copyBook.txt"));
            out.writeObject(books);
            out.close();
            return true;
        }
        catch (Exception e){                                                         //出现问题,返回false
            return false;
        }
    }

    public boolean revese(){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Book\\copyBook.txt"));
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Book\\book.txt"));
            out.writeObject(in.readObject());
            in.close();
            out.close();
            return true;
        }
        catch (IOException e){                                                         //出现问题,返回false
            System.out.println("由于文件读取错误,复原失败");
            return false;
        }
        catch (ClassNotFoundException e){
            System.out.println("由于备份损坏,复原失败");
            return false;
        }
    }
}
