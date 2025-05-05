package com.api.carrental.dto;

import java.util.List;
import org.springframework.stereotype.Component;
import com.api.carrental.model.RentalWithDriver;

@Component
public class RentalWithDriverDto {
    private List<RentalWithDriver> list;
    private int totalPages;
    private int totalElements;
    private int size;
    private int currentPage;
    
    public List<RentalWithDriver> getList() {
        return list;
    }
    public void setList(List<RentalWithDriver> list) {
        this.list = list;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public int getTotalElements() {
        return totalElements;
    }
    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}