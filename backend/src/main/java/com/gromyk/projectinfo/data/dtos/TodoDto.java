package com.gromyk.projectinfo.data.dtos;

import java.time.LocalDateTime;

public class TodoDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private String text;
    private Long ownerID;

    public TodoDto() {
    }

    public TodoDto(Long id, String title, LocalDateTime createdAt, String text, Long ownerID) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.text = text;
        this.ownerID = ownerID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Long ownerID) {
        this.ownerID = ownerID;
    }
}
