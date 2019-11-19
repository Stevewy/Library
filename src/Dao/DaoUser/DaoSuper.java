package Dao.DaoUser;

import java.io.File;
import java.io.IOException;

/**
 * @author Zengfanyu
 * @date 2019/11/13
 * @function
 */
public class DaoSuper {
    public static boolean createAdministrator(String account)throws IOException {
        File administrator = new File("Administrator/"+account+".txt");
        if (administrator.exists()) {
            return false;
        } else {
            administrator.createNewFile();
            return true;
        }
    }

    public static boolean deleteAdministrator(String account)throws IOException{
        File administrator =new File ("Administrator/"+account+".txt");
        if (administrator.exists()) {
            return false;
        } else {
            administrator.delete();
            return true;
        }
    }

    public static boolean viewAllAdministrator()throws IOException{
        File account =new File ("Administrator");
        if(account.exists())
            return true;
        else
            return false;
    }

    public static boolean searchAdministrator(String account) {
        File administrator = new File("Administrator/" + account + ".txt");
        if (administrator.exists())
            return true;
        else
            return false;

    }
}
