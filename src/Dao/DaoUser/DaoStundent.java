package Dao.DaoUser;

import java.io.*;
import java.util.Scanner;

/**
 * @author Zengfanyu
 * @date 2019/11/4
 * @function
 */
public class DaoStundent {

    public boolean viewOwnStudent(String name){
        File account = new File("Students/"+name+".txt");
        if (account.exists())
            return true;
        else
            return false;
    }

    public void  revisePassword(int accountNumber,String password){

    }
}

