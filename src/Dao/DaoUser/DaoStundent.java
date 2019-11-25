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
    public static boolean viewOwnAccount(String account){
        File infomation = new File("Students/"+account+".txt");
        if (infomation.exists())
            return true;
        else
            return false;
    }
}

