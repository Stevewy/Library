package Entity.Book;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function 用于与DaoBook连接
 */

public class Book implements Serializable {
    private String name;
    private String bookNumber;
    private String press;
    private String kind;
    private int totalAmount;
    private int nowAmount;
    private int lentAmount;
    private int price;

    public Book(String name, String bookNumber, String press, String kind, int price) {
        this.name = name;
        this.bookNumber = bookNumber;
        this.press = press;
        this.kind = kind;
        this.price = price;
    }

    public Book(String name, String number, String press, int totalAmount, String kind, int price) {
        this.name = name;
        this.bookNumber = number;
        this.press = press;
        this.totalAmount = totalAmount;
        this.nowAmount = totalAmount;
        this.kind = kind;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public String getPress() {
        return press;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String getBooknumber() {
        return bookNumber;
    }

    public int getLentAmount() {
        return lentAmount;
    }

    public int getPrice() {
        return price;
    }

    public void changePrice(int price) {
        this.price = price;
    }

    public String getKind() {
        return kind;
    }

    public int getNowAmount() {
        return nowAmount;
    }

    public void addBook(int number){                                         //添加书
        totalAmount += number;
        nowAmount += number;
    }

    public boolean getBook(int nummber){                                     //学生归还书
        if(totalAmount < nowAmount + nummber) return false;
        else {
            nowAmount += nummber;
            return true;
        }
    }

    public boolean deleteBook(int number){                               //删除书
        if(nowAmount < number) return false;
        else{
            nowAmount -= number;
            totalAmount -= number;
            return true;
        }
    }

    public boolean lentOut(int number){                                         //学生借出书
        if(nowAmount < number) return false;
        else{
            nowAmount -= number;
            lentAmount += number;
            return true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) &&
                Objects.equals(bookNumber, book.getBookNumber()) &&
                Objects.equals(press, book.press) &&
                Objects.equals(kind, book.kind);
    }

    @Override
    public String toString() {
        return "书名:" + name +
                "   书号:" + bookNumber +
                "   出版社:" + press +
                "   种类" + kind +
                "   目前剩余数量" + nowAmount;
    }

}
