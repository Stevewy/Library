import Dao.DaoBook.AdminiDaoBook;
import Dao.DaoBook.StudentDaoBook;
import Dao.DaoUser.DaoAdministrator;
import Dao.DaoUser.DaoStundent;
import Dao.DaoUser.DaoSuper;
import Entity.Admini.Admini;
import Entity.Book.Book;
import Entity.Student.Student;
import Entity.Super.Super;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Zengfanyu
 * @date 2019/12/1
 * @function
 */

public class NewMain {
    private static AdminiDaoBook a = new AdminiDaoBook();
    public static void menu(){
        System.out.println("1.查看账户列表");
        System.out.println("2.查看所有账户信息");
        System.out.println("3.查找学生");
        System.out.println("4.管理账户");
        System.out.println("5.管理图书");
        System.out.println("6.修改密码");
        System.out.println("7.备份和还原");
        System.out.println("8.退出系统");
    }
    public static void accountMenu(){
        System.out.println("1.创建新账户");
        System.out.println("2.修改学生账户密码");
        System.out.println("3.删除账户");
    }

    public static void bookMenu(){
        System.out.println("1.添加图书");
        System.out.println("2.删除图书");
    }

    public static void reveseMenu(){
        System.out.println("1.书籍备份");
        System.out.println("2.书籍还原");
    }

    public static void main(String[] args) throws Exception{
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Super/root.txt"));
//        Super sup = new Super();
//        sup.setAccount("root");
//        sup.setPassword("123456");
//        oos.writeObject(sup);
        Scanner in = new Scanner(System.in);
        String account ;
        String password;
        String oldPassword;
        String newPassword;
        Student stu ;
        Admini admini;
        String bookName ;
        String inChoice ;
        String indirect ;
        int bookNumber ;
        int choice;
        Book book;
        menu();
        while (true){
        choice = in.nextInt();
        switch (choice){
            case 1:
                System.out.println("学生列表如下");
                Admini.viewAllStudentFile();
                System.out.println("按任意键返回主菜单");
                indirect = in.next();
                menu();
                break;

            case 2:
                System.out.println("所有学生情况如下");
                Admini.viewAllStudentInfo();
                System.out.println("按任意键返回主菜单");
                indirect = in.next();
                menu();
                break;

            case 3:
                System.out.println("请输入学生账号");
                account = in.next();
                Student student = Admini.searchStudent(account);
                if(student!=null)
                    student.superToString();
                else
                    System.out.println("未找到该学生");
                System.out.println("按任意键返回主菜单");
                indirect = in.next();
                menu();
                break;

            case 4:
                accountMenu();
                boolean accountStatus = true;
                    int choosing;
                    while (accountStatus) {
                        choosing = in.nextInt();
                        switch (choosing) {
                            case 1:
                                System.out.println("请输入账号");
                                account = in.next();
                                System.out.println("请输入初始密码");
                                password = in.next();
                                if (Admini.createAccount(account, password))
                                    System.out.println("创建成功");
                                System.out.println("按任意键返回主菜单");
                                indirect = in.next();
                                menu();
                                accountStatus = false ;
                                break;

                            case 2:
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
                                if (Admini.changeStudentPassword(stu, password))
                                    System.out.println("修改成功");
                                System.out.println("按任意键返回主菜单");
                                indirect = in.next();
                                menu();
                                accountStatus = false ;
                                break;

                            case 3:
                                System.out.println("请输入删除的账号");
                                account = in.next();
                                stu = DaoAdministrator.searchStudent(account);
                                while (stu == null) {
                                    System.out.println("输入账号有误，请确认后重新输入");
                                    account = in.next();
                                    stu = DaoAdministrator.searchStudent(account);
                                }
                                if (Admini.deleteStudent(account))
                                    System.out.println("删除成功");
                                else
                                    System.out.println("删除失败");
                                System.out.println("按任意键返回主菜单");
                                indirect = in.next();
                                menu();
                                accountStatus = false ;
                                break;

                            default:
                                System.out.println("输入有误，请重新选择");
                        }
                    }   break;

                    case 5:
                        bookMenu();
                        boolean bookStatus = true ;
                        while (bookStatus){
                        int bookChoose = in.nextInt();
                        switch (bookChoose) {
                            case 1:
                                System.out.println("请列入书籍信息");
                                System.out.println("书名      ISBN号       出版社     总数量     类别      价格");
                                book = new Book(in.next(), in.next(), in.next(), in.nextInt(), in.next(), in.nextInt());
                                if(Admini.ensure(book)){
                                if (Admini.addBook(book))
                                    System.out.println("添加成功");
                                else
                                    System.out.println("添加失败");}
                                System.out.println("按Y/y返回主菜单或按其余键继续添加");
                                inChoice = in.next();
                                while (!inChoice.equals("y") && !inChoice.equals("Y")) {
                                    System.out.println("书名      ISBN号       出版社     总数量     类别      价格");
                                    book = new Book(in.next(), in.next(), in.next(), in.nextInt(), in.next(), in.nextInt());
                                    if(Admini.ensure(book)){
                                    if (Admini.addBook(book))
                                        System.out.println("添加成功");
                                    else
                                        System.out.println("添加失败");}
                                    System.out.println("按Y/y返回主菜单或按其余键继续添加");
                                    inChoice = in.next();
                                }
                                if (inChoice.equals("y") || inChoice.equals("Y"))
                                    bookStatus = false ;
                                    menu();
                                break;

                            case 2:
                                StudentDaoBook stdb = new StudentDaoBook();
                                ArrayList <Book> books = new ArrayList<>();
                                System.out.println("请输入要删除的书名");
                                bookName = in.next();
                                books = stdb.searchBookByName(bookName);
                                if (books != null) {
                                    System.out.println("请选择要删除的图书");
                                    for(int i = 1 ; i <= books.size() ; i++ ){
                                        System.out.println(i+"."+books.get(i-1));
                                    }
                                    int deleteChoice = in.nextInt() ;
                                    book = books.get(deleteChoice - 1) ;
                                    System.out.println("请输入要删除的数量");
                                    bookNumber = in.nextInt();
                                    while (Admini.deleteBook(book, bookNumber, a) == false && bookNumber < 0){
                                        System.out.println("请输入正确的数量");
                                        bookNumber = in.nextInt() ;
                                    }
                                    System.out.println("删除成功");
                                }
                                System.out.println("按Y/y返回主菜单或其余键继续删除");
                                inChoice = in.next();
                                while (!inChoice.equals("y") && !inChoice.equals("Y")) {
                                    System.out.println("请输入要删除的书名");
                                    bookName = in.next();
                                    books = stdb.searchBookByName(bookName);
                                    if (books != null) {
                                        System.out.println("请选择要删除的图书");
                                        for(int i = 1 ; i <= books.size() ; i++ ){
                                            System.out.println(i+"."+books.get(i-1));
                                        }
                                        int deleteChoice = in.nextInt() ;
                                        book = books.get(deleteChoice - 1) ;
                                        System.out.println("请输入要删除的数量");
                                        bookNumber = in.nextInt();
                                        while (bookNumber < 0){
                                            System.out.println("请输入正确的数量");
                                            bookNumber = in.nextInt() ;
                                        }
                                        while (Admini.deleteBook(book, bookNumber, a) == false) {
                                            System.out.println("书籍数量不足，请核实后输入");
                                            bookNumber = in.nextInt();
                                            Admini.deleteBook(book, bookNumber, a);
                                        }
                                        System.out.println("删除成功");
                                    }
                                    System.out.println("删除成功");
                                    System.out.println("按Y/y返回主菜单或其余键继续删除");
                                    inChoice = in.next();
                                }
                                if (inChoice.equals("y") || inChoice.equals("Y"))
                                    menu();
                                bookStatus = false ;
                                break;

                                default:
                                    System.out.println("输入有误，请重新选择");
                        }
                     }
                        break;

             case 6:
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
                     while (!admini.getPassword().equals(oldPassword)) {
                         System.out.println("输入的密码错误,请重新输入");
                         oldPassword = in.next();
                     }
                     System.out.println("请输入新密码");
                     newPassword = in.next();
                     if (Admini.changeAdminPassword(admini, oldPassword, newPassword)){
                         System.out.println("修改成功");
                         System.out.println("按任意键返回主菜单");
                         indirect = in.next();
                         menu();
                     }
                 break;

            case 7:
                reveseMenu();
                boolean reveseStatus = true ;
                while (reveseStatus){
                int reveseChoice = in.nextInt() ;
                switch (reveseChoice) {
                    case 1:
                        if (DaoAdministrator.copyAccount() &&
                                DaoAdministrator.copyBook())
                            System.out.println("备份成功");
                        System.out.println("按任意键返回主菜单");
                        indirect = in.next();
                        reveseStatus = false ;
                        menu();
                        break;

                    case 2:
                        if (DaoAdministrator.reveseAccount() &&
                                DaoAdministrator.reveseBook())
                            System.out.println("还原成功");
                        System.out.println("按任意键返回主菜单");
                        indirect = in.next();
                        reveseStatus = false ;
                        menu();
                        break;

                        default:
                            System.out.println("输入有误，请重新选择");
                }
          }  break;

            case 8:
                System.exit(0);

            default:
                    System.out.println("输入有误,请重新输入");

            }
        }
    }
}
