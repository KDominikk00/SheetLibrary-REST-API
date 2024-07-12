package com.scores.sheets_rest_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "Sheetmusic")
public class SheetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private int id;

    @NotBlank(message = "Title is mandatory")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author is mandatory")
    @Column(nullable = false)
    private String author;

    @Size(max = 1500, message = "Description can be at most 1500 characters long")
    private String description;

    @NotBlank(message = "Sheet URL is mandatory")
    @Size(max = 1000, message = "Sheet URL can be at most 1000 characters long")
    @Column(nullable = false, length = 1000)
    private String sheeturl;

    @Min(value = 1, message = "Difficulty must be at least 1")
    @Column(nullable = false)
    private int difficulty;

    public SheetEntity() {
    }

    public SheetEntity(int id, String title, String author, String description, String sheeturl, int difficulty) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.sheeturl = sheeturl;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSheeturl() {
        return sheeturl;
    }

    public void setSheeturl(String sheeturl) {
        this.sheeturl = sheeturl;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
