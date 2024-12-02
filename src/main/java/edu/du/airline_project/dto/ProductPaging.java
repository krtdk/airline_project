package edu.du.airline_project.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ProductPaging<T> {

	private List<T> list = new ArrayList<>();
    private ProductPagination productPagination;
    public ProductPaging(List<T> list, ProductPagination pagination) {
        this.list.addAll(list);
        this.productPagination = pagination;
    }
}
