package Entity.Student;

import Entity.Book.Book;

public interface StudentInterface {
    void borrowBook(Book book);

    void returnBook(Book book);

    void lookBook();

    void changePassword();
}
