package Dao.DaoUser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Zengfanyu
 * @date 2019/11/4
 * @function
 */
public class DaoUser {
    public void createNewAccout(String name)throws IOException {
        File account = new File("name.txt");
        account.createNewFile();
    }
   // public void viewAllAccount()
}
