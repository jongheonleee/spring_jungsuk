package com.fastcampus.ch2;

public class PageHandler {
    int totalCnt; // 전체 게시물의 개수
    int pageSize = 20; // 한 페이지의 크기
    int pageCnt; // 전체 페이지의 개수

    int naviSize = 10; // navi 한 페이지의 크기
    int naviStart = 1; // navi의 시작 페이지
    int naviEnd; // navi의 끝 페이지

    int currPage; // 현재 페이지

    PageHandler(int totalCnt, int currPage, int pageSize) {
        this.totalCnt = totalCnt;
        this.currPage = currPage;
        this.pageSize = pageSize;

        // 계산
        pageCnt = totalCnt / pageSize + (totalCnt%pageSize == 0 ? 0 : 1);
        naviStart = currPage / naviSize * naviSize + 1;
        naviEnd = naviStart + naviSize - 1;
        naviEnd = naviEnd > pageCnt ? pageCnt : naviEnd;
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "totalCnt=" + totalCnt +
                ", pageSize=" + pageSize +
                ", pageCnt=" + pageCnt +
                ", naviSize=" + naviSize +
                ", naviStart=" + naviStart +
                ", naviEnd=" + naviEnd +
                ", currPage=" + currPage +
                '}';
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCnt() {
        return pageCnt;
    }

    public void setPageCnt(int pageCnt) {
        this.pageCnt = pageCnt;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getNaviStart() {
        return naviStart;
    }

    public void setNaviStart(int naviStart) {
        this.naviStart = naviStart;
    }

    public int getNaviEnd() {
        return naviEnd;
    }

    public void setNaviEnd(int naviEnd) {
        this.naviEnd = naviEnd;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }
}
