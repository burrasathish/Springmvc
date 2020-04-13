package com.mountblue.Blogproject.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String excerpt;

    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    @NotEmpty
    private String author;

    @CreationTimestamp
    @Column(updatable = false)
    private Date published_at;

    private boolean is_published;

    @CreationTimestamp
    @Column(updatable = false)
    private Date created_at;

    @UpdateTimestamp
    private Date updated_at;

    @ManyToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Tag> tags;


    //ch
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CommenterDetails> commenterDetails;


    public void setTags(List<Tag> tag) {
        this.tags = tag;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublished_at() {
        return published_at;
    }

    public void setPublished_at(Date published_at) {
        this.published_at = published_at;
    }

    public boolean isIs_published() {
        return is_published;
    }

    public void setIs_published(boolean is_published) {
        this.is_published = is_published;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}



