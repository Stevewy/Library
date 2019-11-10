package Entity.Student;

import Dao.DaoBook.DaoBook;
import Entity.Book.Book;

import java.util.ArrayList;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function 学生实现类
 */

public class Student implements StudentInterface{
    private String account;
    private String password;
    private ArrayList<Book> books;

//    public boolean checkPassword(String password){
//        if(password.equals(this.password)) return true;
//        else return false;
//    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public boolean changePassword(String password){
        if(!isChinese(password)) {
            this.password = password;
            return true;
        }
        else return false;
    }

    private boolean isChinese(String s){
        byte[] b = s.getBytes();
        if(b.length == s.length()) return false;
        else return true;
    }

    public void lookBook(){
        DaoBook.lookAllBook();
    }

    public void lookBookByKind(String kind){
        DaoBook.lookAllBookByKind(kind);
    }

    public boolean borrowBook(Book book,int number){
        if(book.getNowAmount() >= number){
            this.books.add(book);
            book.lentOut(number);
            return true;
        }
        else return false;
    }

    public void returnBook(Book book){

    }

    @Override
    public String toString() {
        if(books.isEmpty()) return "学生:" + account + " 密码是:" + password + " 目前没有未还的书籍";
        return "学生:" + account + " 密码是:" + password + " 借了" + books;
    }
}
