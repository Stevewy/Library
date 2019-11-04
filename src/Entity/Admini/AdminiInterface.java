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
      * @param student
     */
    void creatAccount(Student student);

    void deleteAccount(Student student);

    void increaseBook(Book book);

    void deleteBook(Book book);
}
