package Entity.Book;

import java.io.Serializable;

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

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getPress() {
        return press;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", press='" + press + '\'' +
                '}';
    }
}
