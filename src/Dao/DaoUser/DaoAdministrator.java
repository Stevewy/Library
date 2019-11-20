package Dao.DaoUser;

import Entity.Student.Student;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Zengfanyu
 * @date 2019/11/13
 * @function
 */
public class DaoAdministrator {


    public static boolean createStudent(String account)throws IOException {
        File student = new File("Students/"+account+".txt");
        if (student.exists()) {
            return false;
        } else {
            student.createNewFile();
            return true;
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

    public static Student deleteStudent(String account) {
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


  /*  public static ArrayList<Student> viewAllStudents()throws IOException{
        File account =new File ("Students");
        if(account.exists()){

        }
        else
            return null;
    }*/


}
