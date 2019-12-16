package Dao.DaoUser;

import Entity.Admini.Admini;
import Entity.Book.Book;
import Entity.Student.Student;

import java.io.*;
import java.util.ArrayList;

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
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(student));
                Student stu = (Student) in.readObject();
                in.close();
                return stu;

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
            System.out.println("文件读取错误");
            return null;
        }
    }

    public static boolean creatStudent(Student student){
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
    public static void copyAccount(){
        File file = new File("StudentsCopy");
        try{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Students"));
        ObjectOutputStream oos = new ObjectOutputStream((new FileOutputStream("StudentsCopy")));
        Student student ;
        String fileName[] = file.list();
        for(String path : fileName){
            student = (Student)ois.readObject();
            oos.writeObject(student);
        }
        }catch (IOException ioe){
            System.out.println("打开文件异常");
        }
        catch (ClassNotFoundException cle){
            System.out.println("备存时出现错误");
        }
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


