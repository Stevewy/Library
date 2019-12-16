package Dao.DaoBook;

import Entity.Book.Book;
import Run.Main;

import java.io.*;
import java.util.ArrayList;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function 用于管理书的数据,被其他类使用
 */
public class StudentDaoBook {
    protected ArrayList<Book> books;

    public StudentDaoBook(){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Book\\book.txt"));
            books = (ArrayList<Book>) in.readObject();

            in.close();
        }
        catch (EOFException e){
            System.out.println("书库里似乎没有书,非管理员请退出");
            Main.stop();
        }
        catch (IOException e){
            System.out.println("没有找到书库");
            System.exit(0);
        }
        catch (ClassNotFoundException e){
            System.out.println("书库里面存放的书籍格式不对");
            System.exit(0);
        }
    }//直接读到内存,方便之后操作
    /**
     * 得到书库里面所有书
     * @return 一个书的集合
     */
    public ArrayList<Book> lookAllBook(){                                        //查看图书馆所有书籍
        return books;
    }

    /**
     * 得到书名具有name字符的书
     * @param name 书名字的一部分
     * @return 一个书的集合
     */
    public ArrayList<Book> searchBookByName(String name){       //用书名得到书
        ArrayList<Book> bookName = new ArrayList<>();
        for(int i = 0; i < books.size(); i++){             //遍历查找书名相同
            if(books.get(i).getName().matches(".*" + name + ".*")){
                bookName.add(books.get(i));
            }
        }
        return bookName;
    }

    public Book accurateSearchBookByName(String name){
        for(Book i : books){
            if(i.getName().equals(name))
                return i;
        }
        return null;
    }

    /**
     * 得到指定书号的书
     * @param number 书号
     * @return 一本书 或者null
     */
    public Book searchBookByBookNumber(String number){                     //用书号得到书
        for(int i = 0; i < books.size(); i++){
            if(number.equals(books.get(i).getBookNumber())){                  //遍历查找书号相同的
                return books.get(i);
            }
        }
        return null;
    }

    /**
     * 得到一个种类的所有书
     * @param kind 书的种类
     * @return 一个书的集合
     */
    public ArrayList<Book> searchBookByKind(String kind){             //查找某一种类所有书
        ArrayList<Book> bookKind = new ArrayList<>();
        for(int i = 0; i < books.size(); i++){
            if(kind.equals(books.get(i).getKind())){                         //遍历查找书种类相同的
                bookKind.add(books.get(i));
            }
        }
        return bookKind;
    }

    /**
     *
     * @param book
     * @param b 书库存在则输入ture,不确定是否存在则输入false
     * @return
     */
    public boolean updateBook(Book book, boolean b){
        int i;
        for(i = 0; i < books.size(); i++){                                           //遍历书库,如果有直接放书,没有判断b,此时i = b.size()
            if(book.equals(books.get(i))){
                if(book.getTotalAmount() == 0)
                    books.remove(i);
                else
                    books.set(i,book);
                break;
            }
        }
        if(i == books.size()){
            if(b)
                return false;
            books.add(book);
        }

        try {
            StorageBook(new ObjectOutputStream(new FileOutputStream("Book\\book.txt")), books);
//        StorageBook(new ObjectOutputStream(new FileOutputStream("copyBook.txt")), b);         //实时更新
        }
        catch (IOException e){
            System.out.println("没有找到书库");
        }
        return true;
    }

    /**
     * 将书存到文件(私有方法)
     * @param out 存到的文件
     * @param b 书
     * @throws IOException
     */
    private void StorageBook(ObjectOutputStream out,ArrayList<Book> b) throws IOException {
        out.writeObject(b);
        out.close();
    }

}