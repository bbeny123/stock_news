package org.beny.stock.model;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_CMT")
    @Column(name = "CMT_ID")
    private Long id;

    @Column(name = "CMT_CONTENT", length = 600, nullable = false)
    private String content;

    @Column(name = "CMT_DATE", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "CMT_USR", nullable = false)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CMT_NEWS", nullable = false)
    private News news;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
