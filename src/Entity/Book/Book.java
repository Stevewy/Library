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

    public String getKind() {
        return kind;
    }

    public int getNowAmount() {
        return nowAmount;
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
        if(name.length() <= 4)
            return String.format("书名:%-15s\t\t\t书号:%-15s     出版社:%-10s     种类:%-10s\t价格:%-5d     目前剩余数量%-5d本", name, bookNumber, press, kind, price, nowAmount);
        else if(name.length() <= 9)
            return String.format("书名:%-15s\t\t书号:%-15s     出版社:%-10s     种类:%-10s\t价格:%-5d     目前剩余数量%-5d本", name, bookNumber, press, kind, price, nowAmount);
        return String.format("书名:%-15s\t书号:%-15s     出版社:%-10s     种类:%-10s\t价格:%-5d     目前剩余数量%-5d本", name, bookNumber, press, kind, price, nowAmount);
    }
}