package com.unipi.application.corona.models;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The persistent class for the COVID19_NEWS database table.
 *
 */
@Entity
@Table(name="COVID19_NEWS")
public class Covid19News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="author" , columnDefinition="TEXT")
    private String author;

    @Column(name="title" , columnDefinition="TEXT")
    private String title;

    @Column(name="description", columnDefinition="TEXT")
    private String description;

    @Column(name="url" , columnDefinition="TEXT")
    private String url;

    @Column(name="urlToImage" , columnDefinition="TEXT")
    private String urlToImage;

    @Column(name="publishedAt")
    private Timestamp publishedAt;

    @Column(name="content", columnDefinition="TEXT")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public Timestamp getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Timestamp publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
