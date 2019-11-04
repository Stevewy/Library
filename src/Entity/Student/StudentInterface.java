package Entity.Student;

import Entity.Book.Book;

public interface StudentInterface {
    void borrowbook(Book book);

    void returnbook(Book book);

    void lookbook();

    void changepassword();
}
