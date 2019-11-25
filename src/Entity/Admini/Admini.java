package Entity.Admini;

import Dao.DaoBook.StudentDaoBook;
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

    public static boolean addBook(Book book, int number){
        book.setNowAmount(book.getNowAmount()+number);
        book.setTotalAmount(book.getTotalAmount()+number);
        return true;
    }

    public static boolean deleteBook(Book book,int number){//book是删除的书，number是数量
         if(book.getNowAmount()>=number){                        //删除的书数量不嫩超过现有的数量
            book.setTotalAmount(book.getTotalAmount()-number);
            book.setNowAmount(book.getNowAmount()-number);       //总数量和现有数量要同时减去
             StudentDaoBook sdb=new StudentDaoBook();
             sdb.updateBook(book);
             return true;
         }
         return false;
    }


   // public static boolean returnBook(){}

   // public static boolean deleteStudent(){}

   // public static boolean viewAllStudent(){}

    public static boolean changeAdminPassword(Admini admini,String oldPassword,String newPassword){
       if(isLegal(newPassword)){
        if(admini.getAccount().equals(oldPassword)){
            admini.setAccount(newPassword);
            return true;
          }
       }
        return false;
    }

    public static boolean changeStudentPassword(Student student,String newPassword) {
        if(isLegal(newPassword)){
        student.setPassword(newPassword);
        return true;
        }
        return false;
    }

    private static boolean isLegal(String password){
        char[] temp=password.toCharArray();
        int Upper=0;
        int Lower=0;
        int Digit=0;
        for(int j=0;j<temp.length;j++){
            if(temp[j] < '0' || (temp[j] > '9' && temp[j] < 'A') || (temp[j] > 'Z' && temp[j] < 'a') || temp[j] > 'z')
                System.out.println("密码含有非法字符");
                return false;
        }
        for(int i=0;i<temp.length;i++){
            if(Character.isUpperCase(temp[i]))
                Upper++;
            if(Character.isLowerCase(temp[i]))
                Lower++;
            if(Character.isDigit(temp[i]))
                Digit++;
        }
        if(Upper>0&&Lower>0&&Digit>0){
            return true;
        }
        else {
        System.out.println("密码必须同时包含大小写字母和数字");
        return false;
        }
    }





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
