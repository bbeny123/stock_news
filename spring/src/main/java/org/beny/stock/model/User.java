package org.beny.stock.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USERS")
@SequenceGenerator(sequenceName = "SEQ_USR", name = "SEQ_USR")
public class User {

    public enum Type {
        ADMIN,
        USER
    }

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Token token;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USR")
    @Column(name = "USR_ID")
    private Long id;

    @Column(name = "USR_LOGIN", length = 30, nullable = false, unique = true)
    private String login;

    @Column(name = "USR_EMAIL", length = 60, nullable = false, unique = true)
    private String email;

    @Column(name = "USR_PASSWORD", nullable = false)
    private String password;

    @Column(name = "USR_NAME", length = 120)
    private String name;

    @Column(name = "USR_TYPE", length = 5, nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type = Type.USER;

    @Column(name = "USR_ACTIVE")
    private boolean active;

    public void setStringToken(String token) {
        if (this.token == null) {
            this.token = new Token();
            this.token.setUser(this);
        }
        this.token.setToken(token);
    }

}
