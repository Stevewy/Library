package Entity.Super;

import Entity.Admini.Admini;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function 超级用户接口
 */

public interface SuperInterface {

    boolean addAdmini(Admini admini);

    boolean deleteAdmini(Admini admini);

    Admini searchAdmini(String Account);

    boolean changeAdminiPassword(Admini admini, String newPassword);

    boolean closeLibrary();

    boolean openLibrary();
}
