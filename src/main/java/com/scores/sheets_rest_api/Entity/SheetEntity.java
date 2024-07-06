package com.scores.sheets_rest_api.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Sheetmusic")
public class SheetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author = "Frederic Chopin";
    @Column(length = 1500)
    private String description;
    @Column(nullable = false)
    private String sheeturl;
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

    public String getTitle() {
        return this.title;
    }

    public int getDifficulty() {
        return this.difficulty;
    }
}
