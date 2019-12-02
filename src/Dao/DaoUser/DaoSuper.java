package Dao.DaoUser;

import Entity.Admini.Admini;
import Entity.Student.Student;
import Entity.Super.Super;

import java.io.*;
import java.util.ArrayList;

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

    public static boolean deleteAdministrator(Admini admini){
        File administrator =new File ("Administrator/"+admini.getAccount()+".txt");
        if (administrator.exists()) {
            administrator.delete();
            return true;
        }
        else {
            return false;
        }
    }

    public static ArrayList<Admini> viewAllAdministratorInfo(){
        File file =new File("Administrator");
        String info[]=file.list();
        ArrayList<Admini>adm=new ArrayList<>();
        try{
        for(String names:info){
            ObjectInputStream ois =new ObjectInputStream(new FileInputStream("naems"));
            adm.add((Admini)ois.readObject());
        }
        return adm;
     }
        catch (FileNotFoundException fe){
            System.out.println("不能找到相关文件");
            return null;
        }
        catch (IOException ioe){
            System.out.println("文件读取错误");
            return null;
        }
        catch (ClassNotFoundException cle){
            System.out.println("文件读取错误");
            return null;
        }
    }


    public static Admini searchAdministrator(String account) {
        File admini = new File("Administrator/" + account + ".txt");
        if (admini.exists()){
            try {
               ObjectInputStream in = new ObjectInputStream(new FileInputStream(admini));
               Admini adm = (Admini) in.readObject();
               in.close();
               return adm;
            }
            catch (FileNotFoundException ce){
                System.out.println("找不到文件");
                 return null;
            }
            catch (Exception e) {
                System.out.println("文件读取失败");
                return null;
            }
        }
        else
            return null;
    }

    public static boolean updateSuper(Super sup){
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(sup.getAccount()+".txt"));
            oos.writeObject(sup);
            return true;
        }
        catch (FileNotFoundException fe){
            System.out.println("超级管理员文件查找失败");
            return false;
        }
        catch (IOException ioe ){
            System.out.println("文件读取失败");
            return false;
        }
    }
}
