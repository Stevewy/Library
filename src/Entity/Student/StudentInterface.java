package Entity.Student;

import Entity.Book.Book;
/**
 * @author Zengfanyu
 * @date 2019/11/4
 * @function
 */
public interface StudentInterface {
    void borrowbook(Book book);

    void returnbook(Book book);

    void lookbook();

    void changepassword();
}
