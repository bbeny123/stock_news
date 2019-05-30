package org.beny.stock.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "NEWS")
public class News {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_NES")
    @Column(name = "NES_ID")
    private Long id;

    @Column(name = "NES_TITLE", length = 60, nullable = false)
    private String title;

    @Column(name = "NES_DESCRIPTION", length = 300, nullable = false)
    private String description;

    @Column(name = "NES_LINK", length = 1000, nullable = false)
    private String link;

    @Column(name = "NES_DATE", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "NES_USR", nullable = false)
    private User user;

    @OneToMany(mappedBy = "news", cascade = ALL, orphanRemoval = true)
    private Set<Comment> comments;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Comment> getComments() {
        if (comments == null) {
            comments = new HashSet<>();
        }
        return comments;
    }

    public void addComment(Comment comment) {
        comment.setNews(this);
        getComments().add(comment);
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

}
