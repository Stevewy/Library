import Entity.Admini.Admini;
import Entity.Student.Student;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * @author Zengfanyu
 * @date 2019/12/1
 * @function
 */
public class NewMain {
    public static void main(String[] args) {
        System.out.println("1.查看账户列表");
        System.out.println("2.查看所有账户信息");
        System.out.println("3.查找学生");
        System.out.println("4.创建新账户");
        System.out.println("5.修改学生账户密码");
        System.out.println("6.删除账户");
        System.out.println("7.添加图书");
        System.out.println("8.删除图书");
        System.out.println("9.管理员修改密码");

        Scanner in = new Scanner(System.in);
        String account ;
        String password ;
        int choice = in.nextInt();
        switch (choice){
            case 1:
                System.out.println("学生列表如下");
                Admini.viewAllStudentFile();
                break;

            case 2:
                System.out.println("所有学生情况如下");
                Admini.viewAllStudentInfo();
                break;

            case 3:
                System.out.println("请输入学生账号");
                account = in.next();
                Student student = Admini.searchStudent(account);
                student.studentToString();

            case 4:
                System.out.println("请输入账号");
                account = in.next();
                System.out.println("请输入初始密码");
                password = in.next();
                if(Admini.createAccount(account,password))
                    System.out.println("创建成功");
                break;
        }
    }
}
