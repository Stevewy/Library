package Entity.Super;

import java.io.Serializable;

/**
 * @author WangYao
 * @date 2019/11/4
 * @function 超级用户实现类
 */

public class Super/* implements SuperInterface, Serializable */{

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
        if(!open){
            open = true;
            return true;
        }else return false;
    }
}
