package Entity.Admini;

import Dao.DaoUser.DaoAdministrator;
import Entity.Book.Book;
import Entity.Student.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Zengfanyu
 * @date 2019/11/4
 * @function
 */
public class Admini   {
    private String account;
    private String password;

    public boolean createAccount(String account,String password){
           if(!DaoAdministrator.studentExist(account)) {
               Student student = new Student(account, password);
               if(DaoAdministrator.creatStudent(student))
                   return true;
               else
                   return false;
           }
           else
               return false;
    }

    public boolean deleteAccount(String account) {
        if(DaoAdministrator.studentExist(account)){
            if( DaoAdministrator.deleteStudent(account)){
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    public static void searchStudent(String account){
        Student student= DaoAdministrator.searchStudent(account);
        if(student!=null){
        System.out.println("账户信息如下");
        System.out.println(student.getAccount()+" "+" "+student.getPassword());
        System.out.println(student.getBooks()+" ");
        System.out.println(student.getNumber()+" ");
    }
        else
            System.out.println("该账户不存在");
    }

    public void addBook(Book book, int number){}

    public void deleteBook(Book book,int number)throws FileNotFoundException,IOException{

    }
    
}
