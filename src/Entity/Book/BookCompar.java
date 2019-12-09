package Entity.Book;

import java.util.Comparator;

/**
 * @author WangYao
 * @date 2019/12/9
 * @function
 */
public class BookCompar implements Comparator<Book> {

    public int compare(Book a, Book b){
        if(a.getLentAmount() > b.getLentAmount())
            return -1;
        return 1;
    }
}