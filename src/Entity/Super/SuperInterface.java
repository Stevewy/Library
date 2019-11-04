package Entity.Super;

import Entity.Admini.Admini;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function
 */

public interface SuperInterface {

    void addAdmini(Admini admini);

    void deleteAdmini(Admini admini);

    void closeLibrary();

    void openLibrary();
}
