package com.example.a01_19478061_huynhtuananh.model;

public class Book {
    private int id ;
    private String tenSach;
    private String tenTacGia;

    public Book(int id, String tenHoaHoc, String tenTacGia) {
        super();
        this.id = id;
        this.tenSach = tenSach;
        this.tenTacGia = tenTacGia;
    }
    public Book() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", tenSach='" + tenSach + '\'' +
                ", tenTacGia='" + tenTacGia + '\'' +
                '}';
    }
}
