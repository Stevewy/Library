package Dao.DaoUser;

import java.io.File;
import java.io.IOException;

/**
 * @author Zengfanyu
 * @date 2019/11/13
 * @function
 */
public class DaoAdministrator {
    public boolean createStudent(String name)throws IOException {
        File account = new File("Students/"+name+".txt");
        if (account.exists()) {
            return false;
        } else {
            account.createNewFile();
            return true;
        }
    }

    public boolean deleteStudent(String name)throws IOException{
        File account =new File ("Students/"+name+".txt");
        if (account.exists()) {
            return false;
        } else {
            account.delete();
            return true;
        }
    }

    public boolean viewAllStudents()throws IOException{
        File account =new File ("Students");
        if(account.exists())
            return true;
        else
            return false;
    }

    public boolean searchStudent(String name){
        File account = new File("Students/"+name+".txt");
        if (account.exists())
            return true;
        else
            return false;
    }

}
