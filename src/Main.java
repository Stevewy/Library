import Dao.DaoBook.AdminiDaoBook;
import Dao.DaoBook.StudentDaoBook;
import Dao.DaoUser.DaoAdministrator;
import Dao.DaoUser.DaoSuper;
import Entity.Admini.Admini;
import Entity.Book.Book;
import Entity.Student.Student;
import Entity.Super.Super;

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
        System.out.print("1:借书                 2:还书\n"+
                            "3:修改密码             4:查看目前图书馆的书籍\n" +
                            "5:查看借阅图书         6:更换账号\n" +
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
                           "3:通过类别来找书      4:查看所有图书");
    }

    public static void adminiMenu(){
        System.out.println( "1:借书                 2:还书\n"+
                            "3:修改密码             4:查看目前图书馆的书籍\n" +
                            "5:查看借阅图书         6:退出系统");
    }

    public static void superMenu(){
        System.out.println("1.开关图书馆        2.查看管理员账号\n" +
                           "3.修改管理员密码    4.修改自己密码\n" +
                           "5.切换账号          6.格式化\n" +
                           "7.退出系统");
    }

    public static void stop(){
        System.out.println("按任意键回到主菜单");
        in.next();
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
//            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Book\\book.txt"));
//            o.writeObject(b);
//        }
//        catch (Exception e){
//            System.out.println("1");
//        }
//
//
//        Book b01 = new Book("1","1","1",2,"中国",5);
//        Book b02 = new Book("2","2","1",2,"中国",6);
//        Student s1 = new Student("456","456");
//        System.out.println(s1);
//        s.lookAllBook();
//        if(s.borrowBook(b01,1)) System.out.println("借书成功");
//        s.studentToString();
//        s.lookBookByName("1");
//        s.borrowBook(b01, 1);
//        s.studentToString();

        System.out.println("欢迎来到图书馆");

        if(!Super.isOpen()){
            System.out.println("图书馆已经关闭,如果你有紧急情况或者是超级用户");
            stop();
        }
        int x = 0;
        boolean continu = true;
        boolean back = false;
        Student student = null;
        Admini admini = null;
        Super supers = null;
        while(true) {
            while (true) {
                System.out.println("请选择你的账户类型");
                loadMenu();
                x = in.nextInt();
                if (x < 1 || x > 3){
                    System.out.println("输入错误,请重新输入");
                    continue;
                }
                in.nextLine();
                System.out.println("请输入你的账号和密码");
                System.out.print("账号:");
                String account = in.nextLine();

                System.out.print("密码:");
                String password = in.nextLine();

                boolean find = false;
                switch (x) {
                    case 1:
                        ArrayList<Student> s = DaoAdministrator.viewAllStudentInfo();
                        if (s == null) {
                            System.out.println("学生文档里面没有用户");
                            break;
                        }
                        for (Student i : s) {                                           //比对账号密码
                            if (i.getAccount().equals(account)) {
                                if (i.getPassword().equals(password)) {
                                    student = i;
                                    continu = false;
                                } else
                                    System.out.println("密码错误");
                                find = true;
                                break;
                            }
                        }
                        if (!find)
                            System.out.println("没有找到该用户,请重新输入");
                        break;
                    case 2:
                        ArrayList<Admini> a = DaoSuper.viewAllAdministratorInfo();
                        if (a == null) {
                            System.out.println("管理员文档里面没有用户");
                            break;
                        }
                        for (Admini i : a) {                                           //比对账号密码
                            if (i.getAccount().equals(account)) {
                                if (i.getPassword().equals(password)) {
                                    admini = i;
                                    continu = false;
                                } else
                                    System.out.println("密码错误");
                                find = true;
                                break;
                            }
                        }
                        if (!find)
                            System.out.println("没有找到该用户,请重新输入");
                        break;
                    case 3:
//                        if(account.equals("root") && password.equals());

                        break;
                    default:
                        System.out.println("请输入正确选项");
                }
                if (continu) {
                    System.out.println("是否退出系统, 是则按y");
                    if (in.next().charAt(0) == 'y')
                        System.exit(0);
                }
                else
                    break;
            }
            while (true) {
                if(back)
                    break;
                switch (x) {
                    case 1:
                        studentMenu();
                        switch (in.nextInt()) {
                            case 1:
                                while (true) {
                                    borrowBookMenu();
                                    int x1 = in.nextInt();
                                    in.nextLine();
                                    if (x1 == 1) {
                                        System.out.println("请输入你要查找的书名");
                                        String name = in.nextLine();
                                        ArrayList<Book> abook = a.searchBookByName(name);
                                        if (abook.isEmpty()) {
                                            System.out.println("没有找到你要的书籍,是否重新输入 是则按y");
                                            if (in.next().charAt(0) == 'y')
                                                continue;
                                            else
                                                break;
                                        }
                                        for (int i = 0; i < abook.size(); i++) {
                                            System.out.println((i + 1) + ": " + abook.get(i));
                                        }
                                        System.out.print("请依次输入你要借的书的编号\n编号:");
                                        int num = in.nextInt();
                                        if (num <= abook.size() && num > 0) {
                                            System.out.print("请输入你要借出的数量\n数量:");
                                            student.borrowBook(abook.get(num - 1), in.nextInt());
                                            System.out.println("借书成功");
                                        } else
                                            System.out.println("编号输入错误");
                                        break;
                                    } else if (x1 == 2) {
                                        System.out.println("请输入你要借书的书号");
                                        String number = in.next();
                                        System.out.println("请输入你要借书的数量");
                                        int num = in.nextInt();
                                        if (a.searchBookByBookNumber(number) != null) {
                                            if (student.borrowBook(a.searchBookByBookNumber(number), num)) {
                                                System.out.println("借书成功");
                                                break;
                                            } else
                                                System.out.println("你要借的数量大于图书馆有的数量,请重新输入");

                                        } else {
                                            System.out.println("你要借的书不存在,是否重新输入,是则按y");
                                            if (in.next().charAt(0) == 'y')
                                                continue;
                                            else
                                                break;
                                        }
                                    } else {
                                        System.out.println("输入错误,请重新输入");
                                    }
                                }
                                break;
                            case 2:
                                student.studentToString();
                                lentBookMenu();
                                break;
                            case 3:
                                in.nextLine();
                                System.out.println("请输入原来密码");
                                String oldPassword = in.nextLine();
                                System.out.println("请输入你要修改的密码");
                                String newPassword = in.nextLine();
                                System.out.println("请再次输入新密码");
                                if (newPassword.equals(in.nextLine())) {
                                    student.changePassword(oldPassword, newPassword);
                                    System.out.println("修改成功,正在关闭系统");
                                    System.exit(0);
                                } else
                                    System.out.println("两次输入不一致");
                                break;
                            case 4:
                                lookBookMenu();
                                switch (in.nextInt()) {
                                    case 1:
                                        in.nextLine();
                                        System.out.println("请输入书名");
                                        if (student.lookBookByName(in.nextLine()))
                                            stop();
                                        else
                                            System.out.println("没有找到包含该书名的书");
                                        break;
                                    case 2:
                                        in.nextLine();
                                        System.out.println("请输入书号");
                                        if(student.lookBookByBookNumber(in.nextLine()))
                                            stop();
                                        else
                                            System.out.println("没有找到包含该书号的书");
                                        break;
                                    case 3:
                                        in.nextLine();
                                        System.out.println("请输入你要查找的类别");
                                        if (student.lookBookByKind(in.nextLine()))
                                            stop();
                                        else
                                            System.out.println("没有找到包含该类别的书");
                                        break;
                                    case 4:
                                        student.lookAllBook();
                                        stop();
                                }
                                break;
                            case 5:
                                student.studentToString();
                                stop();
                                break;
                            case 6:
                                back = true;
                                break;
                            case 7:
                                System.out.println("系统正常退出");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("输入错误,请重新输入");
                                break;
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("输入错误,请重新输入");
                }
            }
            back = false;
        }
    }
}
