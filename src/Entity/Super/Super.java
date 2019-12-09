package Entity.Super;

import Dao.DaoBook.SuperDaoBook;
import Dao.DaoUser.DaoAdministrator;
import Dao.DaoUser.DaoSuper;
import Entity.Admini.Admini;
import Entity.Student.Student;

import java.io.Serializable;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function 超级用户实现类
 */

public class Super implements SuperInterface, Serializable {
    private String account;
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private static boolean open = true;

    public static boolean isOpen() {
        return open;
    }

    public boolean closeLibrary(){                    //如果图书馆已经关闭,返回false
        if(open){
            open = false;
            DaoSuper.updateSuper(this);
            return true;
        }
        return false;
    }

    public boolean openLibrary(){                    //如果图书馆已经关闭,返回ture
        if(!open){
            open = true;
            DaoSuper.updateSuper(this);
            return true;
        }
        return false;
    }

    public boolean addAdmini(Admini admini){
        return DaoSuper.createAdministrator(admini);
    }

    public boolean deleteAdmini(Admini admini){
        return DaoSuper.deleteAdministrator(admini);
    }

    public Admini searchAdmini(String Account){
        return DaoSuper.searchAdministrator(Account);
    }

    public boolean changeAdminiPassword(Admini admini, String newPassword){
        if(isLegal(newPassword)){
            admini.setPassword(newPassword);
            DaoAdministrator.updateAdmini(admini);
            return true;
        }
        System.out.println("密码不符合规定");
        return false;
    }

    public boolean changePassword(String oldPassword, String nowPassword){
        if(!password.equals(oldPassword)){
            System.out.println("密码错误");
        }
        else if(isLegal(password)) {
            this.password = nowPassword;
            DaoSuper.updateSuper(this);
            return true;
        }
        else
            System.out.println("密码只能输入字母与数字且长度为6到16位");
        return false;
    }

    private static boolean isLegal(String s){
        char[] chars = s.toCharArray();
        if(chars.length < 6 || chars.length > 16)
            return false;
        for (char c: chars) {
            if(c < '0' || ( c > '9' && c < 'A') || ( c > 'Z' && c < 'a') || c > 'z'){
                return false;
            }
        }
        return true;
    }

    public static void format(){
        SuperDaoBook.format();
    }

}
