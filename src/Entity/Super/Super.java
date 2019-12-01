package Entity.Super;

import Dao.DaoUser.DaoAdministrator;
import Dao.DaoUser.DaoSuper;
import Entity.Admini.Admini;
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
}
