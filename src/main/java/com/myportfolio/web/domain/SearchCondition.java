package com.myportfolio.web.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition {
//    private Integer offset = 0;
    private Integer page = 1;   // 현재 페이지
    private Integer pageSize = 10;  // 페이지 크기
    private String keyword = "";    // 검색 키워드
    private String option = ""; // 검색 옵션,  제목 + 내용, 제목, 작성자

    public SearchCondition() {} // 기본 생성자
    public SearchCondition(Integer page, Integer pageSize, String keyword, String option) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option = option;
    }

    public String getQueryString(Integer page) {
        // ?page=1&pageSize=10&option=T&keyword="title"
        return  UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("option", option)
                .queryParam("keyword", keyword)
                .build().toString();
    }
    public String getQueryString() {
        return getQueryString(page);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return (page-1) * pageSize;
    }


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", offset=" + getOffset() +
                ", keyword='" + keyword + '\'' +
                ", option='" + option + '\'' +
                '}';
    }
}
