package Dao.DaoUser;

import Dao.DaoBook.AdminiDaoBook;
import Entity.Admini.Admini;
import Entity.Book.Book;
import Entity.Student.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;

/**
 * @author Zengfanyu
 * @date 2019/11/13
 * @function
 */
public class DaoAdministrator implements Serializable {

    public static boolean studentExist(String account) {
        File student = new File("Students/"+account+".txt");
        if (student.exists()) {
            return true;
        }
        else {
            return false;
        }
    }

    public static Student searchStudent(String account) {
        File student = new File("Students/" + account + ".txt");
        if (student.exists()){
            try {
                Student s ;
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(student));
                s = (Student) in.readObject();
                in.close();
                return s;

            }
            catch (FileNotFoundException  fe) {
                System.out.println("找不到该文件");
                return null;
            }
            catch(Exception e){
                System.out.println("文件读取错误");
                return null;
            }
        }
        else
            return null;
    }

    public static ArrayList<Student> viewAllStudentInfo(){
        File file = new File("Students/");
        String[]students=file.list();
        ArrayList<Student>names=new ArrayList<>();
        Student info;
        try{
            for(String stu:students){
                ObjectInputStream ois =new ObjectInputStream(new FileInputStream("Students/"+stu));
                info=(Student)ois.readObject();
                names.add(info);
            }
            return names;
        }
        catch (FileNotFoundException fie){
            System.out.println("找不到文件");
            return null;
        }
        catch (Exception e){
            System.out.println("找不到相应文件");
            return null ;
        }
    }

    public static boolean creatStudent(Student student){
        File file = new File("Students");
        if(!file.exists())
            file.mkdir();
        try{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Students/" + student.getAccount() + ".txt"));
        oos.writeObject(student);
        oos.close();
        return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public static boolean deleteStudent(String account){
        try{
            File file = new File("Students/" +account+ ".txt");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Student student=(Student)ois.readObject();
            for(int i=0;i<student.getBooks().size();i++){
            student.returnBook(student.getBooks().get(i),student.getNumber().get(i));
            }
            ois.close();
            if(file.delete())
                return true;
            return false;
        }
        catch (FileNotFoundException fe){
            System.out.println("找不到学生文件");
            return false;
        }
        catch (Exception e){
            System.out.println("读取学生文件失败");
            return false;
        }
    }

    public static boolean updateAdmini(Admini admini){
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("Administrator/"+ admini.getAccount()+".txt"));
            oos.writeObject(admini);
            oos.close();
            return true;
        }
        catch (FileNotFoundException fe){
            System.out.println("找不到该文件");
            return false;
        }
        catch (IOException ioe ){
            System.out.println("文件读取失败");
            return false;
        }
    }

    /**
     * 用于备份学生账户信息
     */
    public static boolean copyAccount(Student student){
        File file = new File("Students");
        File toDelete = new File("StudentsCopy");
        if (!toDelete.exists())
            toDelete.mkdir() ;
        String delete[] = toDelete.list();
        if(delete.length != 0)
        {
            for(String name : delete){
                new File("StudentsCopy/"+name).delete();
            }
        }
        String fileName[] = file.list();
        if(fileName.length != 0){
        try {
            for (String path : fileName) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Students/" + path));
                ObjectOutputStream oos = new ObjectOutputStream((new FileOutputStream("StudentsCopy/" + path)));
                student = (Student) ois.readObject();
                oos.writeObject(student);
                ois.close();
                oos.close();
            }
            return true;
        }catch (IOException ioe){
            System.out.println("读取文件信息异常");
            return false ;
        }
        catch (ClassNotFoundException cle){
            System.out.println("备存时找不到指定文件，出现错误");
            return false ;
        }
     }else {
            System.out.println("没有账户可备份");
            return  false ;
        }
}

    public static boolean copyBook(){
        AdminiDaoBook adb = new AdminiDaoBook();
        if(adb.copyBook())
            return true;
        else return false ;
    }

    /**
     *用于还原备份的信息
     */
    public static boolean reveseAccount(Student student) {
        File file = new File("StudentsCopy");
        File toDelete = new File("Students");
        if (!toDelete.exists()){
            toDelete.mkdir() ;
        }
        String delete[] = toDelete.list();
        if(delete.length != 0)
        {
            for(String name : delete){
                new File("Students/"+name).delete();
            }
        }
        try {
            String names[] = file.list();
            if (names.length != 0) {
                for (String path : names) {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("StudentsCopy/"+path));
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Students/"+path));
                    student = (Student) ois.readObject();
                    oos.writeObject(student);
                    ois.close();
                    oos.close();
                }return true ;
            } else {
                System.out.println("您还没备份过用户文件");
                return false;
            }
        } catch (IOException ioe) {
            System.out.println("读取文件信息文件异常");
            return false;
        } catch (ClassNotFoundException cle) {
            System.out.println("还原时找不到指定文件，出现错误");
            return false;
        }
    }
    public static boolean reveseBook(AdminiDaoBook a){
         if (a.revese())
             return true;
         else
             return false;
        }

//    public static void studentReturnBooks(int studentReturn) {
//        try{
//        FileOutputStream fos = new FileOutputStream(new File("judge.txt"));
//        fos.write(studentReturn);
//            }
//        catch (Exception e){
//            System.out.println("提交还书请求错误");
//        }
//    }
//
//    public static int getReturn(){
//        try{
//           FileInputStream fis = new FileInputStream(new File("judge.txt"));
//           int num = fis.read();
//           return  num;
//        }
//        catch (Exception e){
//            System.out.println("还书请求获取失败");
//            return 0;
//        }
//    }
}


