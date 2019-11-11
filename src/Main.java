import Dao.DaoBook.DaoBook;
import Entity.Book.Book;
import Entity.Student.Student;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function
 */

public class Main {

    public static void studentMenu(){
        System.out.println("1:借书                 2:还书"+
                           "3:修改密码             4:查看目前图书馆的书籍" +
                           "5:查看借阅图书         6:退出系统");
    }

    public static void borrowBookMenu(){
        System.out.println("1:通过书名来借书      2:通过书号来借书");
    }

    public static void lentBookMenu(){
        System.out.println("1:通过书名来还书      2:通过书号来还书");
    }

    public static void lookBookMenu(){
        System.out.println("1:通过书名来找书      2:通过书号来找书" +
                           "3:查看所有图书        4:通过类别来找书");
    }

    public static void main(String[] args){
//        Book b1 = new Book("1","1","1",2,"中国");
//        Book b2 = new Book("2","2","1",2,"中国");
//        Book b3 = new Book("3","3","1",2,"中国");
//        Book b4 = new Book("4","4","1",2,"中国");
//        Book b5 = new Book("5","5","1",2,"d");
//        Book b6 = new Book("6","6","1",2,"中国");
//        Book b7 = new Book("7","7","1",2,"中国");
//        Book b8 = new Book("8","8","1",2,"q");
//        Book b9 = new Book("9","9","1",2,"中国");
//        Book b10 = new Book("10","10","1",2,"n");
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
        Book b1 = new Book("1","1","1",2,"中国");
        Book b2 = new Book("2","2","1",2,"中国");
        Student s = new Student("456","456");
        s.borrowBook(b1,1);
        s.borrowBook(b2,1);
        s.studentToString();
    }
}
