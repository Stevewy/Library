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

    /**
     * 管理员手动备份书库(由于自动备份,该方法是个摆设)
     * @return
     */
    public boolean copyBook(){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("copyBook.txt"));
            out.writeObject(books);
            out.close();
            return true;
        }
        catch (Exception e){                                                         //出现问题,返回false
            return false;
        }
    }

}
