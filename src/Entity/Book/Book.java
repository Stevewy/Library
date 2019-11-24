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

    public Book(String name, String bookNumber, String press, int totalAmount, String kind, int price) {
        this.name = name;
        this.bookNumber = bookNumber;
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

    public int getLentAmount() {
        return lentAmount;
    }

    public int getPrice() {
        return price;
    }

    public String getKind() {
        return kind;
    }

    public int getNowAmount() {
        return nowAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setNowAmount(int nowAmount) {
        this.nowAmount = nowAmount;
    }

    public void setLentAmount(int lentAmount) {
        this.lentAmount = lentAmount;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return  Objects.equals(name, book.name) &&
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
