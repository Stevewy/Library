package Dao.DaoBook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author WangYao
 * @date 2019/11/28
 * @function
 */
public class SuperDaoBook {
    public static void formatBook(){

        try {
            FileOutputStream out = new FileOutputStream("Book\\book.txt");
            FileOutputStream cout = new FileOutputStream("Book\\coopBook.txt");
        }
        catch (IOException e){
            System.out.println("格式化书库失败");
            return;
        }

        System.out.println("格式化完成,系统即将关闭");
        System.exit(0);
    }
}
