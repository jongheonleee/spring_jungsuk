package com.fastcampus.web3.domain;

import org.springframework.web.util.UriComponentsBuilder;

// 📌 나중에 이넘으로 변환
public class PageHandler {
    public final int NAV_SIZE = 10;
    public final int PAGE_SIZE = 10;
    private int currPage; // 현재 페이지
    private int totalCnt; // 전체 게시글
    private int totalPage; // 전체 페이지
    private int beginPage; // 내비게이션의 첫번째 페이지 수
    private int endPage; // 내비게이션의 마지막 페이지 수
    private boolean showPrev = false; // < 버튼
    private boolean showNext = false; // > 버튼

    public PageHandler(int currPage, int totalCnt) {
        this.currPage = currPage;
        this.totalCnt = totalCnt;
        doPaging(currPage, totalCnt);
    }

    // 계산하는 로직
    private void doPaging(int currPage, int totalCnt) {
        this.totalPage = totalCnt / PAGE_SIZE + (totalCnt % PAGE_SIZE == 0 ? 0 : 1);
        this.currPage = Math.min(currPage, totalPage);
        this.beginPage = (currPage-1) / NAV_SIZE * NAV_SIZE + 1;
        this.endPage = Math.min(beginPage + NAV_SIZE - 1, totalPage);
        this.showPrev = beginPage != 1;
        this.showNext = endPage != totalPage;

    }

    public String getQueryString() {
        // ?page=10&pageSize=10
        return UriComponentsBuilder.newInstance()
                .queryParam("currPage", currPage)
                .queryParam("pageSize", PAGE_SIZE)
                .build()
                .toString();
    }

    // 네비게이션 부분(< 11 12 13 14 15 ,,, 20 > 출력
    void print() {
        System.out.println("currPage = " + currPage);

        System.out.print(showPrev ? "< " : "");
        for (int i=beginPage; i<=endPage; i++) {
            System.out.print(i + " ");
        }
        System.out.println(showNext ? " >" : "");
    }

    public int getNAV_SIZE() {
        return NAV_SIZE;
    }

    public int getCurrPage() {
        return currPage;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public int getTotalPage() {
        return totalPage;
    }


    public int getBeginPage() {
        return beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "NAV_SIZE=" + NAV_SIZE +
                ", PAGE_SIZE=" + PAGE_SIZE +
                ", currPage=" + currPage +
                ", totalCnt=" + totalCnt +
                ", totalPage=" + totalPage +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
