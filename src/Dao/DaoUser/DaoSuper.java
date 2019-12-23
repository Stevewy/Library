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
    public static boolean createAdministrator(Admini admini) {
        File file = new File("Administrator");
        if(!file.exists())
            file.mkdir();
        File administrator = new File("Administrator/" + admini.getAccount() + ".txt");
        if (administrator.exists()) {
            return false;
        } else {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(administrator));
                oos.writeObject(admini);
                return true;
            } catch (IOException ie) {
                ie.printStackTrace();
                return false;
            }
        }
    }

    public static boolean deleteAdministrator(Admini admini) {
        File administrator = new File("Administrator/" + admini.getAccount() + ".txt");
        if (administrator.exists()) {
            administrator.delete();
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Admini> viewAllAdministratorInfo() {
        File file = new File("Administrator");
        if(!file.exists()){return null;}
        String info[] = file.list();
        ArrayList<Admini> adm = new ArrayList<>();
        try {
            for (String names : info) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Administrator/"+names));
                adm.add((Admini) ois.readObject());
            }
            return adm;
        } catch (FileNotFoundException fe) {
            System.out.println("不能找到相关文件");
            return null;
        } catch (IOException ioe) {
            System.out.println("文件读取错误");
            return null;
        } catch (ClassNotFoundException cle) {
            System.out.println("文件读取错误");
            return null;
        }
    }

    public static Admini searchAdministrator(String account) {
        File admini = new File("Administrator/" + account + ".txt");
        if (admini.exists()) {
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(admini));
                Admini adm = (Admini) in.readObject();
                in.close();
                return adm;
            } catch (FileNotFoundException ce) {
                System.out.println("找不到文件");
                return null;
            } catch (Exception e) {
                System.out.println("文件读取失败");
                return null;
            }
        } else
            return null;
    }

    public static boolean updateSuper(Super sup) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Super/" + sup.getAccount() + ".txt"));
            oos.writeObject(sup);
            return true;
        } catch (FileNotFoundException fe) {
            System.out.println("超级管理员文件查找失败");
            return false;
        } catch (IOException ioe) {
            System.out.println("文件读取失败");
            return false;
        }
    }

    public static Super viewSelf(String account) {
        File file = new File("Super/" + account + ".txt");
        if (file.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                Super sup = (Super) ois.readObject();
                return sup ;

            } catch (FileNotFoundException fe) {
                System.out.println("找不到该文件");
                return null;
            } catch (Exception ioe) {
                System.out.println("读取文件失败");
                return null;
            }
        } else
            return null;
    }

    public static void format(){
//        File file = new File("Administrator");
        File fileS = new File("Students");
        File toDelete ;
//        String[] fileName = file.list();
        String[] fileNameS = fileS.list();
//        if(fileName.length != 0){
//        for(String delete : fileName){
//            toDelete = new File("Administrator/"+delete);
//            toDelete.delete();
//        }
//    }
        if(fileNameS.length != 0){
        for(String deleteS : fileNameS){
            toDelete = new File("Students/"+deleteS);
            toDelete.delete();
        }
     }
        try{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Super/root.txt"));
        Super sup = new Super();
        sup.setAccount("root");
        sup.setPassword("123456");
        oos.writeObject(sup);
        oos.close();
        }catch (Exception e){
            System.out.println("格式化失败");
        }
    }

}