package Dao.DaoUser;

import Entity.Student.Student;

import javax.print.DocFlavor;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Zengfanyu
 * @date 2019/11/4
 * @function
 */
public class DaoStundent {
    public static boolean updateStudent(Student student){
        try{
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(student.getAccount()+".txt"));
        oos.writeObject(student);
        return true;
        }
        catch (FileNotFoundException fe){
            System.out.println("学生文件查找失败");
            return false;
        }
        catch (IOException ioe ){
            System.out.println("文件读取失败");
            return false;
        }
    }
}


