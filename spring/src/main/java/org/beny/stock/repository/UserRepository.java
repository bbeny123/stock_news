package org.beny.stock.repository;

import org.beny.stock.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByLogin(String login);

}