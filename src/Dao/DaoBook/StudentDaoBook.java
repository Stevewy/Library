package Dao.DaoBook;

import Entity.Book.Book;

import java.io.*;
import java.util.ArrayList;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function 用于管理书的数据,被其他类使用
 */
public class StudentDaoBook {

    /**
     * 得到书库里面所有书
     * @return 一个书的集合
     */
    public ArrayList<Book> lookAllBook(){                                        //查看图书馆所有书籍
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            return b;
        }
        catch (Exception e){                                                             //出现问题,关闭程序
            System.out.println("书库出问题,图书馆即将关闭");
            System.exit(0);
            return null;
        }
    }

    /**
     * 得到书名具有name字符的书
     * @param name 书名字的一部分
     * @return 一个书的集合
     */
    public ArrayList<Book> searchBookByName(String name){       //用书名得到书
        try{
            ArrayList<Book> books = new ArrayList<>();
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            for(int i = 0;i < b.size();i++){             //遍历查找书名相同
                if(b.get(i).getName().matches(".*" + name + ".*")){
                    books.add(b.get(i));
                }
            }
            if(books.size() != 0) return books;
            else return null;
        }catch (Exception e){
            System.out.println("书库出问题,图书馆即将关闭");
            System.exit(0);
            return null;
        }
    }

    /**
     * 得到指定书号的书
     * @param number 书号
     * @return 一本书 或者null
     */
    public Book searchBookByBookNumber(String number){                     //用书号得到书
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            for(int i = 0;i < b.size();i++){
                if(number.equals(b.get(i).getBookNumber())){                  //遍历查找书号相同的
                    return b.get(i);
                }
            }
            return null;
        }catch (Exception e){
            System.out.println("书库出问题,请尽快联系管理员修复问题,图书馆即将关闭");
            System.exit(0);
            return null;
        }
    }

    /**
     * 得到一个种类的所有书
     * @param kind 书的种类
     * @return 一个书的集合
     */
    public ArrayList<Book> searchBookByKind(String kind){             //查找某一种类所有书
        ArrayList<Book> books = new ArrayList<>();
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            for(int i = 0;i < b.size();i++){
                if(kind.equals(b.get(i).getKind())){                         //遍历查找书种类相同的
                    books.add(b.get(i));
                }
            }
            if(books.size()!=0) return books;
            else return null;
        }catch (Exception e){
            System.out.println("书库出问题,图书馆即将关闭");
            System.exit(0);
            return null;
        }
    }

    /**
     * 在内存更新书的数量后,调用此方法来将文件的书数量改变
     * @param book 要更改数量的书(传进来已经改好内存的书)
     * @return 更改成功返回ture 否则返回false
     */
    public boolean updateBook(Book book){                                            //更新书
        try{
            int i;                                                                 //注意,i要定义在外面,后面需要根据i来操作
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            for(i = 0;i < b.size();i++){                                           //遍历书库,如果有直接放书,没有就结束,此时i=b.size()
                if(book.equals(b.get(i))){
                    b.set(i,book);
                    break;
                }
            }
            if(i == b.size())
                return false;

            StorageBook(new ObjectOutputStream(new FileOutputStream("book.txt")),b);
//            StorageBook(new ObjectOutputStream(new FileOutputStream("copyBook.txt")),b);
            return true;
        }catch (Exception e){
            System.out.println("书库出问题,图书馆即将关闭");
            System.exit(0);
            return false;
        }
    }

    /**
     * 将书存到文件(私有方法)
     * @param out 存到的文件
     * @param b 书
     * @throws IOException
     */
    private void StorageBook(ObjectOutputStream out,ArrayList<Book> b)throws IOException {
        out.writeObject(b);
        out.close();
    }

}