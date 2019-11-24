package Entity.Admini;
import Entity.Book.Book;
import Entity.Student.Student;
/**
 * @author Zengfanyu
 *  @date 2019/11/4
 *  @function
 */
public interface AdminiInterface {
    /**
     * @
      * @param
     */
    void createAccount();

    void deleteAccount();

    void addBook(Book book,int number);

    void deleteBook(Book book);
}