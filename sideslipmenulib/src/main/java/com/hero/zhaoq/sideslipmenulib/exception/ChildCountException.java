package com.hero.zhaoq.sideslipmenulib.exception;

/**
 * author: zhaoqiang
 * date:2017/10/20 / 09:57
 * zhaoqiang:zhaoq_hero@163.com
 */
//Custom exception class If the count is not equal to the count we make   throw the exception:
public class ChildCountException extends RuntimeException {

    /**
     * @param message     will throw message
     * @param actualCount actual viewGroups child count
     * @param makeCount   the count of viewgroup we make
     */
    public ChildCountException(String message, int actualCount, int makeCount) {
        if (actualCount != makeCount) {
            throw new ChildCountException(message);
        }
    }

    //throw exception message:    exit Process
    public ChildCountException(String message) {
        super(message);
    }
}
