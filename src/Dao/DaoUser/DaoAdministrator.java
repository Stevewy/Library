package Dao.DaoUser;

import Entity.Book.Book;
import Entity.Student.Student;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Zengfanyu
 * @date 2019/11/13
 * @function
 */
public class DaoAdministrator {


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
                return stu;

            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else
            return null;
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













  /*  public static ArrayList<Student> viewAllStudents()throws IOException{
        File account =new File ("Students");
        if(account.exists()){

        }
        else
            return null;
    }*/


}
