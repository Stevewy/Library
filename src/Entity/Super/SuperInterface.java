package Entity.Super;

import Entity.Admini.Admini;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function 超级用户接口
 */

public interface SuperInterface {
    /**
     *
     * @param admini
     */
    void addAdmini(Admini admini);

    void deleteAdmini(Admini admini);

    void closeLibrary();

    void openLibrary();
}
