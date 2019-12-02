import Dao.DaoUser.DaoAdministrator;
import Dao.DaoUser.DaoStundent;
import Dao.DaoUser.DaoSuper;
import Entity.Admini.Admini;
import Entity.Book.Book;
import Entity.Student.Student;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * @author Zengfanyu
 * @date 2019/12/1
 * @function
 */
public class NewMain {
    public static void main(String[] args) {
        System.out.println("1.查看账户列表");
        System.out.println("2.查看所有账户信息");
        System.out.println("3.查找学生");
        System.out.println("4.创建新账户");
        System.out.println("5.修改学生账户密码");
        System.out.println("6.删除账户");
        System.out.println("7.添加图书");
        System.out.println("8.删除图书");
        System.out.println("9.管理员修改密码");

        Scanner in = new Scanner(System.in);
        String account ;
        String password ;
        String oldPassword;
        String newPassword;
        Student stu ;
        Admini admini;
        Book book;
        int choice = in.nextInt();
        switch (choice){
            case 1:
                System.out.println("学生列表如下");
                Admini.viewAllStudentFile();
                break;

            case 2:
                System.out.println("所有学生情况如下");
                Admini.viewAllStudentInfo();
                break;

            case 3:
                System.out.println("请输入学生账号");
                account = in.next();
                Student student = Admini.searchStudent(account);
                if(student!=null)
                    student.studentToString();
                else
                    System.out.println("未找到该学生");
                break;

            case 4:
                System.out.println("请输入账号");
                account = in.next();
                //缺少一个判断账号是否正确的方法
                System.out.println("请输入初始密码");
                password = in.next();
                if(Admini.createAccount(account,password))
                    System.out.println("创建成功");
                break;

            case 5:
                System.out.println("请输入修改的学生账号");
                account = in.next();
                stu = DaoAdministrator.searchStudent(account);
                while (stu == null) {
                    System.out.println("输入账号有误，请确认后重新输入");
                    account = in.next();
                    stu = DaoAdministrator.searchStudent(account);
                }
                System.out.println("请输入新密码");
                password = in.next();
               if( Admini.changeStudentPassword(stu,password))
                   System.out.println("修改成功");
               break;

            case 6:
                System.out.println("请输入删除的账号");
                account = in.next();
                stu = DaoAdministrator.searchStudent(account);
                while (stu == null){
                    System.out.println("输入账号有误，请确认后重新输入");
                    account = in.next();
                    stu = DaoAdministrator.searchStudent(account);
                }
                if(Admini.deleteStudent(account))
                    System.out.println("删除成功");
               else
                   System.out.println("删除失败");
               break;

//            case 7:
//                System.out.println("请列入书籍信息");
//                System.out.println("书名      ISBN号       出版社     总数量     类别      价格");
//                book = new Book(in.next(),in.next(),in.next(),in.nextInt(),in.next(),in.nextInt());
//                if(Admini.addBook(book))

//            case 8:

             case 9:
                 System.out.println("请输入账号");
                 account = in.next();
                 admini = DaoSuper.searchAdministrator(account);
                 while (admini == null){
                     System.out.println("该用户不存在,请重新输入");
                     account = in.next();
                     admini = DaoSuper.searchAdministrator(account);
                    }
                     System.out.println("请输入旧密码");
                     oldPassword = in.next();
                     while (admini.getPassword() != oldPassword) {
                         System.out.println("输入的密码错误,请重新输入");
                         oldPassword = in.next();
                     }
                     System.out.println("请输入新密码");
                     newPassword = in.next();
                     if (Admini.changeAdminPassword(admini, oldPassword, newPassword))
                         System.out.println("修改成功");
                 break;
        }
    }
}
