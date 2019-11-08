package Entity.Student;

import Entity.Book.Book;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function 学生类接口
 */

public interface StudentInterface {

    void borrowBook(Book book);

    void returnBook(Book book);

    void lookBook();

    void checkPassword();
}
