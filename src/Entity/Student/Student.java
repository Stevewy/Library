package Entity.Student;

import Dao.DaoBook.StudentDaoBook;
import Dao.DaoUser.DaoStundent;
import Entity.Book.Book;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function 学生实现类
 */

public class Student implements StudentInterface, Serializable {
    private String account;
    private String password;
    private transient boolean lock;
    private transient int wrongSum;
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Integer> number = new ArrayList<>();
    private transient StudentDaoBook studentDaoBook = new StudentDaoBook();

    public void setAccount(String account) {
        this.account = account;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public void setWrongSum(int wrongSum) {
        this.wrongSum = wrongSum;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStudentDaoBook(StudentDaoBook studentDaoBook) {
        this.studentDaoBook = studentDaoBook;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Integer> getNumber() {
        return number;
    }

    public void setNumber(ArrayList<Integer> number) {
        this.number = number;
    }

    public Student(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * 将合法的密码存到内存
     * @param newPassword 新的密码
     * @return 不合法ture 合法 false
     */
    public boolean changePassword(String oldPassword, String newPassword){
        if(lock){
            System.out.println("由于错误太多次,密码被锁定");
        }
        else if(!password.equals(oldPassword)){
            System.out.println("密码错误");
            wrongSum++;
            if(wrongSum == 3)
                lock = true;
        }
        else if(isLegal(newPassword)) {
            this.password = newPassword;
            DaoStundent.updateStudent(this);
            return true;
        }
        else
            System.out.println("密码只能输入字母与数字且长度为6到16位");
        return false;
    }

    /**
     * 私有方法,判断是否有合法
     * @param s
     * @return
     */
    private static boolean isLegal(String s){
        char[] chars = s.toCharArray();
        if(chars.length < 6 || chars.length > 16)
            return false;
        for(int i = 0; i < chars.length; i++){
            if(chars[i] < '0' || (chars[i] > '9' && chars[i] < 'A') || (chars[i] > 'Z' && chars[i] < 'a') || chars[i] > 'z')
                return false;
        }
        return true;
    }

    public void lookAllBook(){
        printf(studentDaoBook.lookAllBook());
    }

    public boolean lookBookByKind(String kind){
        if(studentDaoBook.searchBookByKind(kind).size() != 0){
            printf(studentDaoBook.searchBookByKind(kind));
            return true;
        }
        return false;
    }

    public boolean lookBookByName(String name){                                //如果找到就返回ture 否则返回false
        if(studentDaoBook.searchBookByName(name).size() != 0){
            printf(studentDaoBook.searchBookByName(name));
            return true;
        }
        return false;
    }

    public boolean lookBookByBookNumber(String bookNumber){                    //如果找到就返回ture 否则返回false
        if(studentDaoBook.searchBookByBookNumber(bookNumber) != null){
            System.out.println(studentDaoBook.searchBookByBookNumber(bookNumber));
            return true;
        }
        return false;
    }

    /**
     * 打印书籍
     * @param b 书籍
     */
    private void printf(ArrayList<Book> b ) {
        for(int i = 0; i < b.size(); i++){
            System.out.print(b.get(i)+"(图书馆总共" + b.get(i).getTotalAmount() + "本)       ");
            if((i + 1) % 3 == 0 || i == b.size() - 1) System.out.println();      // 打印3本书后换行
        }
        System.out.println();
    }

    /**
     * 如果可以借就先修改内存,在用dao方法修改文件
     * @param book 要借的书
     * @param number 要借的数量
     * @return 能借就返回ture 否则返回false
     */
    public boolean borrowBook(Book book, int number){                                      //如果借出成功返回ture 否则返回false
        if(book.getNowAmount() >= number && number > 0){
            book.setNowAmount(book.getNowAmount() - number);
            book.setLentAmount(book.getLentAmount() + number);

            int i;
            for(i =0; i < books.size(); i++){
                if(books.get(i).equals(book)){
                    if(this.number.get(i) > number)
                        this.number.set(i, this.number.get(i) - number);
                    else if(this.number.get(i) == number){
                        this.number.remove(i);
                        this.books.remove(i);
                    }

                    break;
                }
            }
            if(i == books.size()){
                this.books.add(book);
                this.number.add(number);
            }

            studentDaoBook.updateBook(book, true);
            DaoStundent.updateStudent(this);
            return true;
        }
        return false;
    }

    public void returnBook(Book book, int number){
        int i = 0;
        int j = books.size();
        for(; i < j; i++){
            if(books.get(i).equals(book)){
                if(this.number.get(i) < number){
                    System.out.println("你好像没有借这么多本书哦");
                    return;
                }
                else if(this.number.get(i) == number){
                    books.remove(i);
                    this.number.remove(i);
                }
                else
                    this.number.set(i, this.number.get(i) - number);
                break;
            }
        }
        if(i == j){
            System.out.println("你好像没有借这本书哦");
            return;
        }

        book.setNowAmount(book.getNowAmount() + number);
        studentDaoBook.updateBook(book, true);
        DaoStundent.updateStudent(this);
    }

    /**
     * 高级用户查看学生账号
     */
    public void superToString() {
        if(books.isEmpty()) System.out.println("学生:" + account + "  密码是:" + password + " 目前没有未还的书籍");
        else{
            System.out.println("学生:" + account + "  密码是:" + password + "     借了以下书籍");
            for (int i = 0;i < books.size();i++){
                System.out.println("书名:" + books.get(i).getName() +
                        "\t   书号:" + books.get(i).getBookNumber() +
                        "\t   出版社:" + books.get(i).getPress() +
                        "\t   种类" + books.get(i).getKind() +
                        "\t   借了" + number.get(i) + "本");
                if((i + 1) % 3 == 0 || i == books.size() - 1) System.out.println();              // 打印3本书后换行
            }
        }
    }

    /**
     * 学生自己看自己账号
     */
    public void studentToString() {
        if(books.isEmpty()) System.out.println("您是:" + account + "  目前没有未还的书籍");
        else{
            System.out.println("您是:" + account + "     借了以下书籍");
            for (int i = 0;i < books.size();i++){
                System.out.print("书名:" + books.get(i).getName() +
                        "\t   书号:" + books.get(i).getBookNumber() +
                        "\t   出版社:" + books.get(i).getPress() +
                        "\t   种类" + books.get(i).getKind() +
                        "\t   借了" + number.get(i) + "本");
                if((i + 1) % 3 == 0 || i == books.size() - 1) System.out.println();              // 打印3本书后换行
                else System.out.print("          ");
            }
        }
    }
}