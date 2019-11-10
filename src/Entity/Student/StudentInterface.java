package Entity.Student;

import Entity.Book.Book;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function 学生类接口
 */

public interface StudentInterface {

    boolean borrowBook(Book book,int number);

    void returnBook(Book book);

    void lookBook();

    boolean changePassword(String password);
}
