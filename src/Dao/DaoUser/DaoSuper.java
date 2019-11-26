package Dao.DaoUser;

import Entity.Admini.Admini;
import Entity.Student.Student;

import java.io.*;

/**
 * @author Zengfanyu
 * @date 2019/11/13
 * @function
 */
public class DaoSuper {
    public static boolean createAdministrator(Admini admini){
        File administrator = new File("Administrator/"+admini.getAccount()+".txt");
        if (administrator.exists()) {
            return false;
        }
        else {
            try {
                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(administrator));
                oos.writeObject(admini);
                return true;
            }
           catch (IOException ie){
                ie.printStackTrace();
                return false;
            }
        }
    }

    public static boolean deleteAdministrator(String account){
        File administrator =new File ("Administrator/"+account+".txt");
        if (administrator.exists()) {
            administrator.delete();
            return true;
        }
        else {
            return false;
        }
    }

    public static File viewAllAdministratorInfo(){
        File file =new File("Administrator");
        File[]info=file.listFiles();
        return file;
    }


    public static Admini searchAdministrator(String account) {
        File admini = new File("Administrator/" + account + ".txt");
        if (admini.exists()){
            try {
               ObjectInputStream in = new ObjectInputStream(new FileInputStream(admini));
               Admini adm = (Admini) in.readObject();
               return adm;

            }
            catch (ClassNotFoundException ce){
                 ce.printStackTrace();
                 return null;
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        else
            return null;
    }
}
