package org.beny.stock.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TOKENS")
public class Token {

    @OneToOne
    @MapsId
    @JoinColumn(name = "TKN_ID")
    private User user;

    @Id
    private Long id;

    @Column(name = "TKN_TOKEN", nullable = false, unique = true)
    private String token;

}