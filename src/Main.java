import Dao.DaoBook.AdminiDaoBook;
import Dao.DaoBook.StudentDaoBook;
import Dao.DaoUser.DaoAdministrator;
import Entity.Book.Book;
import Entity.Student.Student;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function
 */

public class Main {
    private static AdminiDaoBook a = new AdminiDaoBook();
    private static Scanner in = new Scanner(System.in);

    public static void loadMenu(){
        System.out.println("******************");
        System.out.println("*1:我是学生      *");
        System.out.println("*2:我是管理员    *");
        System.out.println("*3:我是图书馆馆长*");
        System.out.println("******************");
    }

    public static void studentMenu(){
        System.out.println("1:借书                 2:还书\n"+
                            "3:修改密码             4:查看目前图书馆的书籍\n" +
                            "5:查看借阅图书         6:查看当前账号\n" +
                            "7:退出系统\n");
    }

    public static void borrowBookMenu(){
        System.out.println("1:通过书名来借书      2:通过书号来借书");
    }

    public static void lentBookMenu(){
        System.out.println("1:通过书名来还书      2:通过书号来还书");
    }

    public static void lookBookMenu(){
        System.out.println("1:通过书名来找书      2:通过书号来找书\n" +
                           "3:查看所有图书        4:通过类别来找书");
    }

    public static void adminiMenu(){
        System.out.println( "1:借书                 2:还书\n"+
                            "3:修改密码             4:查看目前图书馆的书籍\n" +
                            "5:查看借阅图书         6:退出系统");
    }

    public static void main(String[] args){
//        Timer timer = new Timer();     //每隔10000毫秒运行一次,拷贝
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                a.copyBook();
//            }
//        },0,10000);


//        Book b1 = new Book("1","1","1",2,"中国",3);
//        Book b2 = new Book("15","2","1",2,"中国",1);
//        Book b3 = new Book("3","3","1",2,"中国",2);
//        Book b4 = new Book("4","4","1",2,"中国",3);
//        Book b5 = new Book("115","5","1",2,"d",4);
//        Book b6 = new Book("6","6","1",2,"中国",8);
//        Book b7 = new Book("7","7","1",2,"中国",9);
//        Book b8 = new Book("8","8","1",2,"q",4);
//        Book b9 = new Book("9","9","1",2,"中国",3);
//        Book b10 = new Book("10","10","1",2,"n",1);
//        ArrayList<Book> b = new ArrayList<>();
//        b.add(b1);
//        b.add(b2);
//        b.add(b3);
//        b.add(b4);
//        b.add(b5);
//        b.add(b6);
//        b.add(b7);
//        b.add(b8);
//        b.add(b9);
//        b.add(b10);
//        try {
//            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("book.txt"));
//            o.writeObject(b);
//        }
//        catch (Exception e){
//            System.out.println("1");
//        }


//        Book b01 = new Book("1","1","1",2,"中国",5);
//        Book b02 = new Book("2","2","1",2,"中国",6);
//        Student s = new Student("456","456");
//        s.lookAllBook();
//        if(s.borrowBook(b01,1)) System.out.println("借书成功");
//        s.studentToString();
//        s.lookBookByName("1");
//        s.borrowBook(b01, 1);
//        s.studentToString();

        System.out.println("欢迎来到图书馆");
        System.out.println("请选择你的账户类型");
        loadMenu();
        int x = in.nextInt();

        boolean continu = true;
        while(continu){
            System.out.println("请输入你的账号和密码");
            System.out.print("账号:");
            String account = in.nextLine();
            System.out.println();

            System.out.print("密码:");
            String password = in.nextLine();
            System.out.println();
            switch (x){
                case 1:
                    ArrayList<Student> s = DaoAdministrator.viewAllStudentInfo();
                    if(s == null){
                        System.out.println("没有找到该用户");
                        break;
                    }
                    for(Student i : s){                           //比对账号密码
                        if(i.getAccount().equals(account)){
                            if(i.getPassword().equals(password)){
                                continu = false;
                                break;
                            }
                            else {
                                System.out.println("密码错误");
                                break;
                            }
                        }
                    }
                    break;
                case 2:

                case 3:


                default:
                    System.out.println("请输入正确选项");
            }
        }


    }
}
