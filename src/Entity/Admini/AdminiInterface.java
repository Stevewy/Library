package Entity.Admini;
import DAO.Book.Book;
import Entity.Student.Student;
public interface AdminiInterface {
    void creatAccount(Student student);
    void deleteAccount(Student student);
    void increaseBook(Book book);
    void deleteBook(Book book);
}
