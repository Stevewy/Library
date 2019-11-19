package Entity.Admini;

import Dao.DaoUser.DaoAdministrator;
import Entity.Book.Book;
import Entity.Student.Student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Zengfanyu
 * @date 2019/11/4
 * @function
 */
public class Admini  implements AdminiInterface {
    static Scanner in=new Scanner(System.in);

    public void createAccount(){
        System.out.println("请输入新账号:\n");
        String account =in.next();
        try {
            if(DaoAdministrator.createStudent(account)){
                System.out.println("请输入密码:\n");
                String password=in.next();
                Student student=new Student(account,password);
                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("Students/"+account+".txt") );
                oos.writeObject(student);
                System.out.println("创建成功\n");
                oos.close();
            }
            else {
                System.out.println("Error!!!");
            }
        }catch (Exception e){
        e.printStackTrace();
        }
    }


    public static void searchStudent(){
        System.out.println("请输入查找的账号:\n");
        String account=in.next();
        Student student= DaoAdministrator.searchStudent(account);
        if(student!=null){
        System.out.println("账户信息如下");
        System.out.println(student.getAccount()+" "+" "+student.getPassword());
        System.out.println(student.getBooks());
        }
        else
            System.out.println("该账户不存在");
    }


    public void deleteAccount() {
        System.out.println("请输入要删除的账号");
        String account =in.next();
        Student student =DaoAdministrator.deleteStudent(account);

    }

    public void increaseBook(Book book, int number){

    }
    public void deleteBook(Book book){}
    
}
