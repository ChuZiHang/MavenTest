package com.lol.common.plugin;

/**
 * 分页计算
 * 
 * @author cunxing
 *
 */
public class Pager {

    private int pageSize = 8; // 每页显示的行数

    private int currentPage; // 当前页号

    private long total = -1; // 总条数

    private int totalPages; // 总页数

    private int startRow; // 当前页在数据库中的起始行

    private int offSet; // 偏移量

    private int bigOffset = 8; // 分区页码偏移量

    private int listBegin;// 页码起始

    private int listEnd;// 页码结束

    private int bigPageBegin;// 分区页码起始

    private int bigPageEnd;// 分区页码结束

    private int bigCurrentPage;

    private int previous;// 上一页索引

    private int next;// 下一页索引

    public Pager() {
    }

    public Pager(int currentPage) {
    }

    // 初始化并设置每页记录条数和偏移量
    public Pager(int _total, int _pageSize, int _offSet) {
        pageSize = _pageSize;
        offSet = _offSet;
        total = _total;
        totalPages = (int)((total / _pageSize) + (total % _pageSize > 0 ? 1 : 0));
        currentPage = 1;
        startRow = 0;
        if(totalPages == 1) {
            previous = 0;
            next = 0;
        } else {
            previous = 0;
            next = currentPage + 1;
        }
    }

    public void refresh(int _currentPage) {
        currentPage = _currentPage;
        if(totalPages == 1) {
            previous = 0;
            next = 0;
        } else {
            if(currentPage == 1) {
                previous = 0;
                next = currentPage + 1;
            } else if(currentPage < totalPages) {
                previous = currentPage - 1;
                next = currentPage + 1;
            } else if(currentPage >= totalPages) {
                previous = currentPage - 1;
                next = 0;
            }
            startRow = (currentPage - 1) * pageSize;
        }
    }

    // 刷新页码变量
    public void refresh() {
        int halfOffSet = (int)Math.ceil((double)offSet / 2);
        if(totalPages <= offSet) {
            listBegin = 1;
            listEnd = totalPages;
        } else {
            if(currentPage <= halfOffSet) {
                listBegin = 1;
                listEnd = offSet;
            } else if(currentPage <= totalPages - halfOffSet) {
                listBegin = (currentPage - halfOffSet);
                listEnd = listBegin + offSet;
            } else {
                listBegin = totalPages - offSet + 1;
                listEnd = totalPages;
            }
        }
    }

    // 刷新页码变量
    public void refreshBigPage() {
        bigCurrentPage = (int)Math.floor((double)(currentPage - 1) / 10) + 1;
        int halfOffSet = (int)Math.ceil((double)bigOffset / 2);
        int bigTotalPages = (int)Math.floor((double)totalPages / 10) + 1;
        if(bigTotalPages <= bigOffset) {
            bigPageBegin = 1;
            bigPageEnd = bigTotalPages;
        } else {
            if(bigCurrentPage <= halfOffSet) {
                bigPageBegin = 1;
                bigPageEnd = bigOffset;
            } else if(bigCurrentPage <= bigTotalPages - halfOffSet) {
                bigPageBegin = (bigCurrentPage - halfOffSet);
                bigPageEnd = bigPageBegin + bigOffset;
            } else {
                bigPageBegin = bigTotalPages - bigOffset + 1;
                bigPageEnd = bigTotalPages;
            }
        }
    }

    public int getListBegin() {
        return listBegin;
    }

    public void setListBegin(int listBegin) {
        this.listBegin = listBegin;
    }

    public int getListEnd() {
        return listEnd;
    }

    public void setListEnd(int listEnd) {
        this.listEnd = listEnd;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getOffSet() {
        return offSet;
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getBigOffset() {
        return bigOffset;
    }

    public void setBigOffset(int bigOffset) {
        this.bigOffset = bigOffset;
    }

    public int getBigPageBegin() {
        return bigPageBegin;
    }

    public void setBigPageBegin(int bigPageBegin) {
        this.bigPageBegin = bigPageBegin;
    }

    public int getBigPageEnd() {
        return bigPageEnd;
    }

    public void setBigPageEnd(int bigPageEnd) {
        this.bigPageEnd = bigPageEnd;
    }

    public int getBigCurrentPage() {
        return bigCurrentPage;
    }

    public void setBigCurrentPage(int bigCurrentPage) {
        this.bigCurrentPage = bigCurrentPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
