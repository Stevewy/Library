package Entity.Student;

import Dao.DaoBook.Daobook;
import Entity.Book.Book;

import java.util.ArrayList;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function 学生实现类
 */

public class Student /*implements StudentInterface*/{
    private String account;
    private String password;
    private ArrayList<Book> books;

    public boolean checkPassword(String password){
        if(password.equals(this.password)) return true;
        else return false;
    }

    public void lookBook(){
        Daobook.lookAllBook();
    }

    public void borrowBook(Book book){

    }
}
