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
        File student = new File("Students" + account + ".txt");
        if (student.exists()){
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(student));
                Student stu = (Student) in.readObject();
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
        File file = new File("Students");
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
        catch (IOException ioe){
            System.out.println("文件读取错误");
            return null;
        }
        catch (ClassNotFoundException cle){
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
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Students/" +account+ ".txt"));
            Student student=(Student)ois.readObject();
            for(int i=0;i<student.getBooks().size();i++){
            student.returnBook(student.getBooks().get(i),student.getNumber().get(i));
            }
            ois.close();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public static boolean updateAdmini(Admini admini){
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(admini.getAccount()+".txt"));
            oos.writeObject(admini);
            return true;
        }
        catch (FileNotFoundException fe){
            System.out.println("管理员文件查找失败");
            return false;
        }
        catch (IOException ioe ){
            System.out.println("文件读取失败");
            return false;
        }
    }
}

