package Entity.Student;

import Dao.DaoBook.StudentDaoBook;
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
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Integer> number = new ArrayList<>();

    public Student(String account, String password) {
        this.account = account;
        this.password = password;
    }            //测试时用,完工时删除

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public boolean changePassword(String password){
        if(!isChinese(password)) {
            this.password = password;
            return true;
        }
        else return false;
    }

    private boolean isChinese(String s){
        byte[] b = s.getBytes();
        if(b.length == s.length()) return false;
        else return true;
    }

    public void lookAllBook(){
        printf(StudentDaoBook.lookAllBook());
    }

    private void printf(ArrayList<Book> b ){
        for(int i = 0;i < b.size();i++){
                System.out.print(b.get(i)+"       ");
                if((i + 1) % 3 == 0&&i != b.size() - 1) System.out.println();      // 打印3本书后换行
            }
            System.out.println();
    }

    public boolean lookBookByKind(String kind){
        if(StudentDaoBook.searchBookByKind(kind) != null){
            printf(StudentDaoBook.searchBookByKind(kind));
            return true;
        }
        else return false;
    }

    public boolean lookBookByName(String name){                                //如果找到就返回ture 否则返回false
        if(StudentDaoBook.searchBookByName(name) != null){
            System.out.println(StudentDaoBook.searchBookByName(name));
            return true;
        }
        else return false;
    }

    public boolean lookBookByBookNumber(String bookNumber){                    //如果找到就返回ture 否则返回false
        if(StudentDaoBook.searchBookByBookNumber(bookNumber) != null){
            System.out.println(StudentDaoBook.searchBookByBookNumber(bookNumber));
            return true;
        }
        else return false;
    }

    public boolean borrowBook(Book book,int number){                          //如果借出成功返回ture 否则返回false
        if(book.lentOut(number)){
            this.books.add(book);
            this.number.add(number);
            return true;
        }
        else return false;
    }

    public void returnBook(Book book,int number){

    }

    public void superToString() {
        if(books.isEmpty()) System.out.println("学生:" + account + "  密码是:" + password + " 目前没有未还的书籍");
        else{
            System.out.println("学生:" + account + "  密码是:" + password + "     借了以下书籍");
            for (int i = 0;i < books.size();i++){
                System.out.println("书名:" + books.get(i).getName() +
                        "   书号:" + books.get(i).getBookNumber() +
                        "   出版社:" + books.get(i).getPress() +
                        "   种类" + books.get(i).getKind() +
                        "   借了" + number.get(i) + "本");
                if((i + 1) % 3 == 0&&i != books.size() - 1) System.out.println();              // 打印3本书后换行
            }
        }
    }

    public void studentToString() {
        if(books.isEmpty()) System.out.println("您是:" + account + "  目前没有未还的书籍");
        else{
            System.out.println("您是:" + account + "     借了以下书籍");
            for (int i = 0;i < books.size();i++){
                System.out.print("书名:" + books.get(i).getName() +
                        "   书号:" + books.get(i).getBookNumber() +
                        "   出版社:" + books.get(i).getPress() +
                        "   种类" + books.get(i).getKind() +
                        "   借了" + number.get(i) + "本");
                if((i + 1) % 3 == 0&&i != books.size() - 1) System.out.println();              // 打印3本书后换行
                else System.out.print("          ");
            }
        }
    }
}