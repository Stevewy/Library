package Entity.Book;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function
 */

public class Book implements Serializable {
    private String name;
    private String number;
    private String press;
    private int totalAmount;
    private int nowAmount;
    private String kind;

    public Book(String name, String number, String press) {
        this.name = name;
        this.number = number;
        this.press = press;
    }

    public Book(String name, String number, String press, int totalAmount) {
        this.name = name;
        this.number = number;
        this.press = press;
        this.totalAmount = totalAmount;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getPress() {
        return press;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getNowAmount() {
        return nowAmount;
    }

    public void addNumber(int number){
        totalAmount += number;
        nowAmount += number;
    }

    private void setNumber(int number){
        totalAmount = number;
        nowAmount = number;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) &&
                Objects.equals(number, book.number) &&
                Objects.equals(press, book.press) &&
                Objects.equals(kind, book.kind);
    }


    @Override
    public String toString() {
        return "书名:" + name +"   书号:" + number + "   出版社:" + press + "   目前数量" + nowAmount + "           ";
    }
}
