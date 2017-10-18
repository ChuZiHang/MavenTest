package com.lol.common.plugin;

/**
 * 分页服务类
 * 
 * @author cunxing
 *
 */
public class PagerService {

    public static Pager getPager(int currentPage, int total, int pageSize, int offSet) {
        Pager pager=new Pager(total, pageSize, offSet);
        if(currentPage != 0) {
            pager.refresh(currentPage);
        }
        pager.refresh();
        pager.refreshBigPage();
        return pager;
    }
    
}
