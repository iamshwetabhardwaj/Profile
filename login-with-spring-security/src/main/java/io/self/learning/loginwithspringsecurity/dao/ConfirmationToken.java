package io.self.learning.loginwithspringsecurity.dao;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created in login-with-spring-security.
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Entity public class ConfirmationToken {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "token_id") private long tokenid;

    @Column(name = "confirmation_token") private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP) private Date createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER) @JoinColumn(nullable = false, name = "user_id") private User user;

    public ConfirmationToken(User user) {
        this.user = user;
        createdDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }
}
