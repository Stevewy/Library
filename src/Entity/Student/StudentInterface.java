package Entity.Student;

import Entity.Book.Book;

public interface StudentInterface {
    void borrow(Book book);

    void returnbook(Book book);

    void look();

    void change();
}
