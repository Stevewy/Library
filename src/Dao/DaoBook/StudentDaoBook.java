package Dao.DaoBook;

import Entity.Book.Book;

import java.io.*;
import java.util.ArrayList;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function 用于管理书的数据,被其他类使用
 */
public class StudentDaoBook implements Serializable {
    protected ArrayList<Book> books;

    public StudentDaoBook(){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            books = (ArrayList<Book>) in.readObject();
            in.close();
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
     * 在内存更新书的数量后,调用此方法来将文件的书数量改变
     * @param book 要更改数量的书(传进来已经改好内存的书)
     * @return 更改成功返回ture 否则返回false
     */
    public boolean updateBook(Book book){                                            //更新书
        int i;
        for(i = 0; i < books.size(); i++){                                           //遍历书库,如果有直接放书,没有就结束,此时i = b.size()
            if(book.equals(books.get(i))){
                books.set(i,book);
                break;
            }
        }
        if(i == books.size())
            return false;

        try {
            StorageBook(new ObjectOutputStream(new FileOutputStream("book.txt")), books);
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