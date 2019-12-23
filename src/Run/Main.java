package Run;

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
    private static Scanner in = new Scanner(System.in);
    private static AdminiDaoBook a = new AdminiDaoBook();

    private static void loadMenu(){
        System.out.println("******************");
        System.out.println("*1:我是学生      *");
        System.out.println("*2:我是管理员    *");
        System.out.println("*3:我是图书馆馆长*");
        System.out.println("******************");
    }

    private static void studentMenu(){
        System.out.print("1:借书                 2:还书\n"+
                            "3:修改密码             4:查看目前图书馆的书籍\n" +
                            "5:查看借阅图书         6:更换账号\n" +
                            "7:退出系统\n");
    }

    private static void borrowBookMenu(){
        System.out.println("1:通过书名来借书      2:通过书号来借书\n" +
                "3.返回上一级");
    }

    private static void lentBookMenu(){
        System.out.println("1:通过书名来还书      2:通过书号来还书\n" +
                "3.返回上一级");
    }

    private static void lookBookMenu(){
        System.out.println("1:通过书名来找书      2:通过书号来找书\n" +
                           "3:通过类别来找书      4:查看所有图书\n" +
                "5.返回上一级");
    }

    public static void menu(){
        System.out.println("1.查看账户列表");
        System.out.println("2.查看所有账户信息");
        System.out.println("3.查找学生");
        System.out.println("4.管理账户");
        System.out.println("5.管理图书");
        System.out.println("6.修改密码");
        System.out.println("7.备份和还原");
        System.out.println("8.退出系统");
        System.out.println("9.切换账号");
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
        System.out.println("1.备份");
        System.out.println("2.还原");
    }

    private static void superMenu(){
        System.out.println("1.开关图书馆        2.修改管理员\n" +
                           "3.查看所有管理员    4.修改自己密码\n" +
                           "5.切换账号          6.格式化\n" +
                           "7.退出系统");
    }

    private static void admin(){
        System.out.println("1.增加管理员          2.删除管理员\n" +
                           "3.查看管理员密码      4.修改管理员密码\n" +
                "5.返回上一级");
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


//        Book b1 = new Book("1","1","1",2,"中国",3);//手动加书
//        Book b2 = new Book("15","2","1",2,"中国",1);
//        Book b3 = new Book("3","3","1",2,"中国",2);
//        Book b4 = new Book("4","4","1",2,"中国",3);
//        Book b5 = new Book("115","5","1",2,"在",4);
//        Book b6 = new Book("6","6","1",2,"中国",8);
//        Book b7 = new Book("7","7","1",2,"中国",9);
//        Book b8 = new Book("8","8","1",2,"好",4);
//        Book b9 = new Book("9","9","1",2,"中国",3);
//        Book b10 = new Book("10","10","1",2,"去好好",1);
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
//            o.close();
//        }
//        catch (Exception e){
//            System.out.println("1");
//        }

        System.out.println("欢迎来到图书馆");
        Super supers = DaoSuper.viewSelf("root");
        if(!supers.isOpen()) {
            System.out.println("图书馆已经关闭");
            stop();
        }
        int x;

        boolean back = false;
        Student student = null;
        Admini admini = null;
        while(true) {
            while (true) {
                boolean continu = true;
                System.out.println("请选择你的账户类型");
                loadMenu();
                if(a.getBooks() != null){
                    System.out.println("当前最热图书:");
                    a.sortBookAndPrint();
                }
                else
                    System.out.println("真的没有书,出问题后果自负");
                x = in.nextInt();
                if(x == 9)
                    Super.format();

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
                                    student.setStudentDaoBook(new StudentDaoBook());
                                    continu = false;
                                } else
                                    System.out.println("密码错误");
                                find = true;
                                break;
                            }
                        }
                        if (!find)
                            System.out.println("用户名错误");
                        break;
                    case 2:
                        ArrayList<Admini> a = DaoSuper.viewAllAdministratorInfo();
                        if (a == null) {
                            System.out.println("文档损坏");
                            break;
                        }
                        for (Admini i : a) {                                           //比对账号密码
                            if (i.getAccount().equals(account)) {
                                if (i.getPassword().equals(password)) {
                                    System.out.println("登陆成功");
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
                        if((supers = DaoSuper.viewSelf(account)) != null ){
                            if(supers.getPassword().equals(password)) {
                                System.out.println("登陆成功");
                                continu = false;
                            }
                            else
                                System.out.println("密码错误");
                        }
                        else
                            System.out.println("用户名错误");
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
                                        System.out.print("编号:");
                                        int num = in.nextInt();
                                        if (num <= abook.size() && num > 0) {
                                            System.out.print("请输入你要借出的数量\n数量:");
                                            if(student.borrowBook(abook.get(num - 1), in.nextInt())){
                                                System.out.println("借书成功");
                                                break;
                                            }
                                            else
                                                System.out.println("你要借的数量不符合规定,请重新输入");
                                        } else
                                            System.out.println("编号输入错误");
                                    } else if (x1 == 2) {
                                        System.out.println("请输入你要借书的书号");
                                        String number = in.next();
                                        System.out.println("请输入你要借书的数量");
                                        int num = in.nextInt();
                                        if (a.searchBookByBookNumber(number) != null) {
                                            if (student.borrowBook(a.searchBookByBookNumber(number), num)){
                                                System.out.println("借书成功");
                                                break;
                                            }
                                            else
                                                System.out.println("你要借的数量不合法");

                                        } else {
                                            System.out.println("你要借的书不存在,是否重新输入,是则按y");
                                            if (in.next().charAt(0) != 'y')
                                                break;
                                        }
                                    } else if(x1 == 3){
                                        System.out.println("已返回上一级");
                                        break;
                                    }
                                    System.out.println("请输入正确的选项");
                                }
                                break;
                            case 2:
                                while(true){
                                    student.studentToString();
                                    lentBookMenu();
                                    int number = in.nextInt();
                                    in.nextLine();
                                    if(number == 1){
                                        System.out.print("书名:");
                                        Book book = a.accurateSearchBookByName(in.nextLine());
                                        System.out.print("数量:");
                                        int n = in.nextInt();
                                        if(book == null){
                                            System.out.println("书名输入错误");
                                            break;
                                        }
                                        if(n > book.getTotalAmount() - book.getNowAmount() || n < 1){
                                            System.out.println("数量输入错误");
                                            break;
                                        }
                                        student.returnBook(book, n);
                                        System.out.println("还书成功");
                                        break;
                                    }
                                    else if(number == 2){
                                        System.out.print("书号:");
                                        Book book = a.searchBookByBookNumber(in.nextLine());
                                        System.out.print("数量:");
                                        int n = in.nextInt();
                                        if(book == null){
                                            System.out.println("书号输入错误");
                                            break;
                                        }
                                        if(n > book.getTotalAmount() - book.getNowAmount() || n < 1){
                                            System.out.println("数量输入错误");
                                            break;
                                        }
                                        student.returnBook(book, n);
                                        System.out.println("还书成功");
                                        break;
                                    }else if(number == 3){
                                        System.out.println("已返回上一级");
                                        break;
                                    }
                                    System.out.println("请输入正确的选项");
                                }
                                break;
                            case 3:
                                in.nextLine(); //
                                System.out.println("请输入原来密码");
                                String oldPassword = in.nextLine();
                                System.out.println("请输入新密码");
                                String newPassword = in.nextLine();
                                System.out.println("请再次输入新密码");
                                if (newPassword.equals(in.nextLine())) {
                                    if(student.changePassword(oldPassword, newPassword)){
                                        System.out.println("修改成功,正在关闭系统");
                                        System.exit(0);
                                    }

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
                                        break;
                                    case 5:
                                        System.out.println("已返回上一级");
                                        break;
                                    default:
                                        System.out.println("请输入正确的选项");
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
                        String account ;
                        String password;
                        String oldPassword;
                        String newPassword;
                        String bookName ;
                        String inChoice ;
                        String indirect ;
                        int bookNumber ;
                        int choice;
                        Book book;
                        menu();
                        while (!back){
                            choice = in.nextInt();
                            switch (choice){
                                case 1:
                                    Admini.viewAllStudentFile() ;
                                    stop();
                                    menu();
                                    break;

                                case 2:
                                    Admini.viewAllStudentInfo();
                                    stop();
                                    menu();
                                    break;

                                case 3:
                                    System.out.println("请输入学生账号");
                                    account = in.next();
                                    student = Admini.searchStudent(account);
                                    if(student!=null)
                                        student.superToString();
                                    else
                                        System.out.println("未找到该学生");
                                    stop();
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
                                                stop();
                                                menu();
                                                accountStatus = false ;
                                                break;

                                            case 2:
                                                System.out.println("请输入修改的学生账号");
                                                account = in.next();
                                                student = DaoAdministrator.searchStudent(account);
                                                while (student == null) {
                                                    System.out.println("输入账号有误，请确认后重新输入");
                                                    account = in.next();
                                                    student = DaoAdministrator.searchStudent(account);
                                                }
                                                System.out.println("请输入新密码");
                                                password = in.next();
                                                if (Admini.changeStudentPassword(student , password))
                                                    System.out.println("修改成功");
                                                stop();
                                                menu();
                                                accountStatus = false ;
                                                break;

                                            case 3:
                                                System.out.println("请输入删除的账号");
                                                account = in.next();
                                                student = DaoAdministrator.searchStudent(account);
                                                while (student == null) {
                                                    System.out.println("输入账号有误，请确认后重新输入");
                                                    account = in.next();
                                                    student = DaoAdministrator.searchStudent(account);
                                                }
                                                if (Admini.deleteStudent(account))
                                                    System.out.println("删除成功");
                                                else
                                                    System.out.println("删除失败");
                                                stop();
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
                                                    if (Admini.addBook(book,a))
                                                        System.out.println("添加成功");
                                                    else
                                                        System.out.println("添加失败");}
                                                System.out.println("按Y/y返回主菜单或按其余键继续添加");
                                                inChoice = in.next();
                                                while (!inChoice.equals("y") && !inChoice.equals("Y")) {
                                                    System.out.println("书名      ISBN号       出版社     总数量     类别      价格");
                                                    book = new Book(in.next(), in.next(), in.next(), in.nextInt(), in.next(), in.nextInt());
                                                    if(Admini.ensure(book)){
                                                        if (Admini.addBook(book,a))
                                                            System.out.println("添加成功");
                                                        else
                                                            System.out.println("添加失败");}
                                                    System.out.println("按Y/y返回主菜单或按其余键继续添加");
                                                    inChoice = in.next();
                                                }
                                                if (inChoice.equals("y") || inChoice.equals("Y"))
                                                    bookStatus = false ; //
                                                menu();
                                                break;

                                            case 2:
                                                ArrayList <Book> books = new ArrayList<>();
                                                System.out.println("请输入要删除的书名");
                                                bookName = in.next();
                                                books = a.searchBookByName(bookName);
                                                if (books.size() != 0) {
                                                    System.out.println("请选择要删除的图书");
                                                    for(int i = 1 ; i <= books.size() ; i++ ){
                                                        System.out.println(i+"."+books.get(i-1));
                                                    }
                                                    int deleteChoice = in.nextInt() ;
                                                    while( deleteChoice > books.size() || deleteChoice <= 0 ){
                                                        System.out.println("输入有误，请重新选择");
                                                        deleteChoice = in.nextInt() ;
                                                    }
                                                    book = books.get(deleteChoice - 1) ;
                                                    System.out.println("请输入要删除的数量");
                                                    bookNumber = in.nextInt();
                                                    while (Admini.deleteBook(book, bookNumber, a) == false || bookNumber < 0){
                                                        System.out.println("请输入正确的数量");
                                                        bookNumber = in.nextInt() ;
                                                    }
                                                    System.out.println("删除成功");
                                                }
                                                if (books.size() == 0) System.out.println("找不到该图书");
                                                System.out.println("按Y/y返回主菜单或其余键继续删除");
                                                inChoice = in.next();
                                                while (!inChoice.equals("y") && !inChoice.equals("Y")) {
                                                    System.out.println("请输入要删除的书名");
                                                    bookName = in.next();
                                                    books = a.searchBookByName(bookName);
                                                    if (books.size() != 0) {
                                                        System.out.println("请选择要删除的图书");
                                                        for(int i = 1 ; i <= books.size() ; i++ ){
                                                            System.out.println(i+"."+books.get(i-1));
                                                        }
                                                        int deleteChoice = in.nextInt() ;
                                                        while( deleteChoice > books.size() || deleteChoice <= 0 ){
                                                            System.out.println("输入有误，请重新选择");
                                                            deleteChoice = in.nextInt() ;
                                                        }
                                                        book = books.get(deleteChoice - 1) ;
                                                        System.out.println("请输入要删除的数量");
                                                        bookNumber = in.nextInt();
                                                        while (bookNumber < 0){
                                                            System.out.println("请输入正确的数量");
                                                            bookNumber = in.nextInt() ;
                                                        }
                                                        while (Admini.deleteBook(book, bookNumber,a) == false) {
                                                            System.out.println("书籍数量不足，请核实后输入");
                                                            bookNumber = in.nextInt();
                                                            Admini.deleteBook(book, bookNumber,a);
                                                        }
                                                        System.out.println("删除成功");
                                                    }if (books.size() == 0) System.out.println("找不到该图书");
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
                                        stop();
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
                                                if (DaoAdministrator.copyAccount(student) &&
                                                        DaoAdministrator.copyBook())
                                                    System.out.println("备份成功");
                                                stop();
                                                reveseStatus = false ;
                                                menu();
                                                break;

                                            case 2:
                                                if (DaoAdministrator.reveseAccount(student) &&
                                                        DaoAdministrator.reveseBook(a))
                                                    System.out.println("还原成功");
                                                stop();
                                                break;

                                            default:
                                                System.out.println("输入有误，请重新选择");
                                        }
                                    }  break;

                                case 8:
                                    System.exit(0);

                                case 9:
                                    back = true;
                                    break;
                                default:
                                    System.out.println("输入有误,请重新选择");

                            }
                        }
                        break;
                    case 3:
                        superMenu();
                        switch (in.nextInt()){
                            case 1:
                                if(supers.isOpen()){
                                    supers.closeLibrary();
                                    System.out.println("关闭成功");
                                }
                                else {
                                    supers.openLibrary();
                                    System.out.println("打开成功");
                                }
                                break;
                            case 2:
                                admin();
                                switch (in.nextInt()){
                                    case 1:
                                        in.nextLine();
                                        System.out.println("请输入要增加管理员的账号和密码,以空格分隔");
                                        if(supers.addAdmini(new Admini(in.next(), in.next())))
                                            System.out.println("增加成功");
                                        else
                                            System.out.println("增加失败");
                                        break;
                                    case 2:
                                        in.nextLine();
                                        System.out.println("请输入要删除管理员的账号");
                                        admini = supers.searchAdmini(in.nextLine());
                                        if (admini != null && supers.deleteAdmini(admini))
                                                System.out.println("删除成功");
                                        else
                                            System.out.println("删除失败");
                                        break;
                                    case 3:
                                        in.nextLine();
                                        System.out.println("请输入要查看管理员的账号");
                                        Admini a = supers.searchAdmini(in.nextLine());
                                        if(a == null)
                                            System.out.println("没有找到该管理员");
                                        else
                                            System.out.println(a);
                                        stop();
                                        break;
                                    case 4:
                                        in.nextLine();
                                        System.out.println("请输入要修改的账号");
                                        String acc = in.nextLine();
                                        System.out.println("请输入新的密码");
                                        String pass = in.nextLine();
                                        System.out.println("请确认");
                                        if(in.nextLine().equals(pass)){
                                            if(supers.changeAdminiPassword(DaoSuper.searchAdministrator(acc), pass))
                                                System.out.println("修改成功");
                                        }
                                        else
                                            System.out.println("两次密码不一致,修改失败");
                                        break;
                                    case 5:
                                        System.out.println("已返回上一级");
                                        break;
                                    default:
                                        System.out.println("输入错误,请重新输入");
                                }
                                break;
                            case 3:
                                ArrayList<Admini> a = supers.lookAllAdmin();
                                if (a != null)
                                    for(int i = 0; i < a.size(); i++)
                                        System.out.println(a.get(i));
                                else
                                    System.out.println("没有管理员");
                                break;
                            case 4:
                                in.nextLine();
                                System.out.print("旧密码:");
                                String oldpass = in.nextLine();
                                System.out.print("新密码:");
                                String newpass = in.nextLine();
                                System.out.print("再次输入:");
                                if(in.nextLine().equals(newpass)) {
                                    if (supers.changePassword(oldpass, newpass))
                                        System.out.println("修改成功");
                                }
                                else
                                    System.out.println("两次密码不一致");
                                break;
                            case 5:
                                back = true;
                                break;
                            case 6:
                                Super.format();
                                break;
                            case 7:
                                System.exit(0);
                                break;
                            default:
                                System.out.println("输入错误,请重新输入");
                        }
                        break;
                    default:
                        System.out.println("输入错误,请重新输入");
                }
            }
            back = false;
        }
    }
}
