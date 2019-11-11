package Dao.DaoBook;

import Entity.Book.Book;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function 用于管理书的数据,被其他类使用
 */
public class DaoBook {

    public static void lookAllBook(){                          //查看图书馆所有书籍
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            for(int i = 0;i < b.size();i++){
                System.out.print(b.get(i)+"        ");
                if((i + 1) % 3 == 0&&i != b.size() - 1) System.out.println();// 打印5本书后换行
            }
            System.out.println();
        }
        catch (Exception e){                            //出现问题,关闭程序
            System.out.println("书库出问题,请尽快联系管理员修复问题,图书馆即将关闭");
            System.exit(0);
        }
    }

    public static void lookAllBookByKind(String kind){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> c = (ArrayList<Book>) in.readObject();
            in.close();
            ArrayList<Book> b = new ArrayList<>();
            for(int i = 0;i < c.size();i++){
                if(c.get(i).getKind().equals(kind)) b.add(c.get(i));
            }
            if(b.isEmpty()){
                System.out.println("没有找到该种类的图书");
                return;
            }
            for(int i = 0;i < b.size();i++){
                System.out.print(b.get(i)+"       ");
                if((i + 1) % 3 == 0&&i != b.size() - 1) System.out.println();      // 打印3本书后换行
            }
            System.out.println();
        }
        catch (Exception e){                                                         //出现问题,关闭程序
            System.out.println("书库出问题,请尽快联系管理员修复问题,图书馆即将关闭");
            System.exit(0);
        }
    }

    public static Book searchBookByName(String name){       //用书名得到书
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            for(int i = 0;i < b.size();i++){             //遍历查找书名相同
                if(name.equals(b.get(i).getName())){
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

    public static Book searchBookByBookNumber(String number){                     //用书号得到书
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

    public static ArrayList<Book> searchBookByKind(String kind){             //得到一个ArrayList
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
            System.out.println("书库出问题,请尽快联系管理员修复问题,图书馆即将关闭");
            System.exit(0);
            return null;
        }
    }

    public static void addBook(Book book,int number){
        try{
            int i;                                         //注意,i要定义在外面,后面需要根据i来操作
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            for(i = 0;i < b.size();i++){                   //遍历书库,如果有直接加数量,没有就结束,此时i=b.size()
                if(book.equals(b.get(i))){
                    b.get(i).addNumber(number);
                    break;
                }
            }
            if(i == b.size()){                             //书库没有,直接添加
                book.addNumber(number);
                b.add(book);
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("book.txt"));  //写入book.txt
            ObjectOutputStream cout = new ObjectOutputStream(new FileOutputStream("copyBook.txt"));  //写入copyBook.txt
            out.writeObject(b);
            out.close();
            cout.writeObject(b);
            cout.close();
            System.out.println("已经为"+b.get(i).getName()+"增加了"+number+"本");
        }catch (Exception e){
            System.out.println("书库出问题,请尽快联系管理员修复问题,图书馆即将关闭");
            System.exit(0);
        }
    }

    public static boolean deleteBook(Book book,int number){
        try{
            int i;                                         //注意,i要定义在外面,后面需要根据i来操作
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            for(i = 0;i < b.size();i++){                   //遍历书库,如果有直接减少数量,数量不够就报错,没有就结束,此时i=b.size()
                if(book.equals(b.get(i))){
                    if(b.get(i).getNowAmount() < number){
                        System.out.println("书库中已有"+book.getNowAmount()+"本,数量小于你要删除的数量,请重新输入");
                        return false;
                    }
                    b.get(i).addNumber(-1 * number);
                    break;
                }
            }
            if(i == b.size()){                                //书库没有,直接报错
                System.out.println("书库中没有此书,请检查你的输入");
                return false;
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("book.txt"));             //写入book.txt
            ObjectOutputStream cout = new ObjectOutputStream(new FileOutputStream("copyBook.txt"));        //写入copyBook.txt
            out.writeObject(b);
            out.close();
            cout.writeObject(b);
            cout.close();
            System.out.println("已经为"+b.get(i).getName()+"删除了"+number+"本");
            return true;
        }catch (Exception e){
            System.out.println("书库出问题,请尽快联系管理员修复问题,图书馆即将关闭");
            System.exit(0);
            return true;
        }
    }

    public static boolean copyBook(){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("book.txt"));
            ArrayList<Book> b = (ArrayList<Book>) in.readObject();
            in.close();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("copyBook.txt"));
            out.writeObject(b);
            out.close();
            return true;
        }
        catch (Exception e){                            //出现问题,返回false
            return false;
        }
    }
}


