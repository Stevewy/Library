package Entity.Admini;

import Dao.DaoBook.StudentDaoBook;
import Dao.DaoUser.DaoAdministrator;
import Dao.DaoUser.DaoStundent;
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
public class Admini implements Serializable{
    private String account;
    private String password;
   // private static int studentReturn = 1;

    public Admini(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Admini() {
    }

    /**
     * 创建新的学生账户
     * @param account
     * @param password
     * @return
     */
    public static boolean createAccount(String account,String password){
           if(!DaoAdministrator.studentExist(account)) {
               Student student = new Student(account, password);
               if(DaoAdministrator.creatStudent(student))
                   return true;
               else
                   System.out.println("创建失败");
                   return false;
           }
           else
               System.out.println("错误,账户已存在");
               return false;
    }

    /**
     * 删除学生账户(并将学生借的书归还）
     * @param account
     * @return
     */
    public static boolean deleteStudent(String account) {
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

    /**
     * 找具体的一名学生
     * @param account
     * @return
     */
    public static Student searchStudent(String account){
        Student student= DaoAdministrator.searchStudent(account);
        if(student!=null){
       return student;
    }
        else
           return  null;
    }

    /**
     * 在书库中加书，并更新文件
     * @param book
     * @param number
     * @return
     */
    public static boolean addBook(Book book, int number){
        book.setNowAmount(book.getNowAmount()+number);
        book.setTotalAmount(book.getTotalAmount()+number);
        StudentDaoBook sdb=new StudentDaoBook();
       if(sdb.updateBook(book,false))
            return true;
       else return false;
    }

    /**
     * 删除总书库中的书，并更新文件
     * @param book
     * @param number
     * @return
     */
    public static boolean deleteBook(Book book,int number){//book是删除的书，number是数量
         if(book.getNowAmount()>=number){                        //删除的书数量不嫩超过现有的数量
            book.setTotalAmount(book.getTotalAmount()-number);
            book.setNowAmount(book.getNowAmount()-number);       //总数量和现有数量要同时减去
             StudentDaoBook sdb=new StudentDaoBook();
             sdb.updateBook(book,false);
             return true;
         }
         return false;
    }


    /**
     * 管理员查看所有学生的账号信息
     */
    public static void viewAllStudentInfo(){
        ArrayList<Student>student=DaoAdministrator.viewAllStudentInfo();
        if(student!=null){
            for(Student stu:student){
              stu.studentToString();
            }
        }
}

    /**
     * 管理员查看文件下学生目录
     */
    public static void viewAllStudentFile(){
       ArrayList<Student>student=DaoAdministrator.viewAllStudentInfo();
        if(student!=null){
            for(Student stu:student){
                System.out.println(stu.getAccount());
            }
        }
    }

    /**
     * 用于管理员修改密码并返回一个bool值
     * @param admini
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public static boolean changeAdminPassword(Admini admini,String oldPassword,String newPassword){
       if(isLegal(newPassword)){
        if(admini.getPassword().equals(oldPassword)){
            admini.setPassword(newPassword);
            DaoAdministrator.updateAdmini(admini);
            return true;
          }
       }
        return false;
    }

    /**
     * 用于学生修改密码并返回一个bool值
     * @param student
     * @param newPassword
     * @return
     */
    public static boolean changeStudentPassword(Student student,String newPassword) {
        if(isLegal(newPassword)){
        student.setPassword(newPassword);
        DaoStundent.updateStudent(student);
        return true;
        }
        return false;
    }

    /**
     * 判断密码是否合法（大小写，数字均包含，且长度在6-16位）
     * @param password
     * @return
     */
    private static boolean isLegal(String password){
        char[] temp=password.toCharArray();
        int Upper=0;
        int Lower=0;
        int Digit=0;
        for(int j=0;j<temp.length;j++){
            if(temp[j] < '0' || (temp[j] > '9' && temp[j] < 'A') || (temp[j] > 'Z' && temp[j] < 'a') || temp[j] > 'z'){
                System.out.println("密码含有非法字符");
                return false;
            }
        }
        for(int i=0;i<temp.length;i++){
            if(Character.isUpperCase(temp[i]))
                Upper++;
            if(Character.isLowerCase(temp[i]))
                Lower++;
            if(Character.isDigit(temp[i]))
                Digit++;
        }
        if(Upper>0&&Lower>0&&Digit>0&&(temp.length>=6&&temp.length<=16)){
            return true;
        }
        else {
        System.out.println("密码长度在6-16位且必须同时包含大小写字母和数字");
        return false;
        }
    }

    /**
     * 管理员向书库增加图书
     * @param
     */
    public static boolean addBook(Book book){
        StudentDaoBook stu = new StudentDaoBook();
        if(stu.updateBook(book,false))
             return true ;
       else return false ;
    }
//
//    /**
//     * 确认学生还书
//     * @param
//     */
//    public static void ensure(Student student, Book book, int numeber){
//        DaoAdministrator.studentReturnBooks(studentReturn);
//    }
//
//    /**
//     * 给管理员的有同学还书的提示
//     */
//    public static void tips(){
//        int returnPeople = DaoAdministrator.getReturn();
//        if(returnPeople == 1){
//            System.out.println("");
//        }
//    }

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

    @Override
    public String toString() {
        return "Admini" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
