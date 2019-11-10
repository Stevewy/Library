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
    private String Booknumber;
    private String press;
    private int totalAmount;
    private int nowAmount;
    private int lentAmount;
    private String kind;

    public Book(String name, String number, String press, int totalAmount,String kind) {
        this.name = name;
        this.Booknumber = number;
        this.press = press;
        this.totalAmount = totalAmount;
        this.nowAmount = totalAmount;
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public String getBookNumber() {
        return Booknumber;
    }

    public String getPress() {
        return press;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String getBooknumber() {
        return Booknumber;
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

    public void addNumber(int number){
        totalAmount += number;
        nowAmount += number;
    }

//    private void setNumber(int number){
//        totalAmount = number;
//        nowAmount = number;
//    }

    public boolean lentOut(int number){
        if(nowAmount < number) return false;
        nowAmount -= number;
        lentAmount += number;
        return true;
    }

    public void getIn(int number){
        nowAmount += number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) &&
                Objects.equals(Booknumber, book.getBookNumber()) &&
                Objects.equals(press, book.press) &&
                Objects.equals(kind, book.kind);
    }

    @Override
    public String toString() {
        return "书名:" + name +"   书号:" + Booknumber + "   出版社:" + press + "   目前数量" + nowAmount + "   种类" + kind;
    }
}
