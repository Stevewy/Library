package Dao.DaoUser;

import Entity.Student.Student;

import javax.print.DocFlavor;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Zengfanyu
 * @date 2019/11/4
 * @function
 */
public class DaoStundent {
static Scanner in=new Scanner(System.in);
    public static boolean viewOwnStudent(String name){
        File account = new File("Students/"+name+".txt");
        if (account.exists())
            return true;
        else
            return false;
    }


}

