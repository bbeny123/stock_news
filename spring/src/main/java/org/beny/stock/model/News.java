package org.beny.stock.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "NEWS")
public class News {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "NES_USR", nullable = false)
    private User user;
    
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

    @OneToMany(mappedBy = "news", cascade = ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @PrePersist
    @PreUpdate
    protected void prePersistOrUpdate() {
        comments.forEach(c -> c.setNews(this));
    }

}
