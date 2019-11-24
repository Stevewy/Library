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

  /*  public boolean deleteBook(Book book,int number)throws FileNotFoundException,IOException{  //book是删除的书，number是数量
             if(book.getNowAmount()>=number){      //删除的书数量不嫩超过现有的数量
            book.setTotalAmount(book.getTotalAmount()-number);   //但是删书是从总数量里面减少 （总数量不会小于现有数量）
              ArrayList books=DaoAdministrator.deleteBook(book,number);
              if(books!=null){

              }
             }
    }*/


    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }
}
