package com.hero.zhaoq.listitemsideslipmenu;

import java.util.ArrayList;
import java.util.List;

/**
 * author: zhaoqiang
 * date:2017/10/18 / 17:04
 * zhaoqiang:zhaoq_hero@163.com
 */

public class VirtualData {


    public static List<Integer> getData() {

        List<Integer> lists = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            lists.add(i);
        }

        return lists;
    }


}
