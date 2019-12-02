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
    public  boolean createAccount();

    public  boolean deleteStudent();

    public  Student searchStudent();

    public  boolean addBook();

    public boolean deleteBook();

    public  void viewAllStudentInfo();

    public  void viewAllStudentFile();

    public  boolean changeAdminPassword();

    public  boolean changeStudentPassword();


}