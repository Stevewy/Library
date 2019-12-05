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

    private static boolean open = false;

    public static boolean isOpen() {
        return open;
    }

    public boolean closeLibrary(){                    //如果图书馆已经关闭,返回false
        if(open){
            open = false;
            return true;
        }else return false;
    }

    public boolean openLibrary(){                    //如果图书馆已经关闭,返回ture
        if(open){
            return false;
        }else {
            open = true;
            return true;
        }
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
        admini.setPassword(newPassword);
        DaoAdministrator.updateAdmini(admini);
        return true;
    }

    public boolean changePassword(String oldPassword, String nowPassword){
        if(!password.equals(oldPassword)){
            System.out.println("密码错误");
        }
        else if(Student.isLegal(password)) {
            this.password = nowPassword;
            DaoSuper.updateSuper(this);
            return true;
        }
        else
            System.out.println("密码只能输入字母与数字");
        return false;
    }

    public void format(){
        SuperDaoBook.format();
    }

}
