package Dao.DaoUser;

import java.io.File;
import java.io.IOException;

/**
 * @author Zengfanyu
 * @date 2019/11/13
 * @function
 */
public class DaoSuper {
    public boolean createAdministrator(String name)throws IOException {
        File account = new File("Administrator/"+name+".txt");
        if (account.exists()) {
            return false;
        } else {
            account.createNewFile();
            return true;
        }
    }

    public boolean deleteAdministrator(String name)throws IOException{
        File account =new File ("Administrator/"+name+".txt");
        if (account.exists()) {
            return false;
        } else {
            account.delete();
            return true;
        }
    }

    public boolean viewAllAdministrator()throws IOException{
        File account =new File ("Administrator");
        if(account.exists())
            return true;
        else
            return false;
    }

    public boolean searchAdministrator(String name) {
        File account = new File("Administrator/" + name + ".txt");
        if (account.exists())
            return true;
        else
            return false;

    }
}
