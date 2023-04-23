package com.myportfolio.web.domain;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class PageHandler {

    //    private int page; // 현재 페이지
//    private int pageSize; // 한 페이지 크기
    private SearchCondition sc;
    private int totalCnt; // 총 게시물 개수
    private int totalPage; // 총 페이지 수
    private int nav_size = 10;  // 페이지 내비게이션 크기
    private int beginPage; // 시작 페이지
    private int endPage; // 마지막 페이지
    private boolean showPrev;   // 이전 페이지로 이동하는 링크를 보여줄 것인지
    private boolean showNext;   // 다음 페이지로 이동하는 링크를 보여줄 것인지

    public PageHandler(int totalCnt, SearchCondition sc) {
        this.totalCnt = totalCnt;
        this.sc = sc;

        doPaging(totalCnt, sc);
    }

    public void doPaging(int totalCnt, SearchCondition sc) {
            this.totalCnt = totalCnt;

        totalPage = (int) Math.ceil(totalCnt / (double) sc.getPageSize());
        beginPage = (sc.getPage()-1) / nav_size * nav_size + 1;
        endPage = Math.min(beginPage + nav_size - 1, totalPage);
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;

    }

    void print() {
        System.out.println("page = " + sc.getPage());
        System.out.print(showPrev ? "[이전] " : "");

        for (int i = beginPage; i <= endPage; i++) {
            System.out.print(i + " ");
        }
        System.out.println(showNext ? "[다음]" : "");
    }



    public SearchCondition getSc() {
        return sc;
    }

    public void setSc(SearchCondition sc) {
        this.sc = sc;
    }
    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getNav_size() {
        return nav_size;
    }

    public void setNav_size(int nav_size) {
        this.nav_size = nav_size;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "sc=" + sc +
                ", totalCnt=" + totalCnt +
                ", totalPage=" + totalPage +
                ", nav_size=" + nav_size +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
