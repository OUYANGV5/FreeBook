package com.ouyang.freebook.modle.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class CategoryBooks {
    @JSONField(name = "Category")
    private String Category;

    @JSONField(name = "Books")
    private List<Book> Books;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public List<Book> getBooks() {
        return Books;
    }

    public void setBooks(List<Book> books) {
        Books = books;
    }
}
