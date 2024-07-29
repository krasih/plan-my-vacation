package com.example.planmyvacation.model;

import com.example.planmyvacation.model.dto.PlanSummaryDTO;

import java.util.List;

public class PlansSummaryPages {
    private List<PlanSummaryDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;

    public PlansSummaryPages() {
    }

    public List<PlanSummaryDTO> getContent() {
        return content;
    }

    public PlansSummaryPages setContent(List<PlanSummaryDTO> content) {
        this.content = content;
        return this;
    }

    public int getPageNo() {
        return pageNo;
    }

    public PlansSummaryPages setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PlansSummaryPages setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public PlansSummaryPages setTotalElements(long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public PlansSummaryPages setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public PlansSummaryPages setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
        return this;
    }
}
