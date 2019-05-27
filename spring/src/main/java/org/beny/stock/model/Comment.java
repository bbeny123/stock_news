package org.beny.stock.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = "COMMENTS")
public class Comment {

    @OneToOne
    @JoinColumn(name = "CMT_USR", nullable = false)
    private User user;

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "SEQ_CMT")
    @Column(name = "CMT_ID")
    private Long id;

    @Column(name = "CMT_CONTENT", length = 600, nullable = false)
    private String content;

    @Column(name = "CMT_DATE", nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "CMT_NEWS", nullable = false)
    private News news;

}
