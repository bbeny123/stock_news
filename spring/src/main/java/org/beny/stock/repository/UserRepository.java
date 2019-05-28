package org.beny.stock.repository;

import org.beny.stock.exception.StockException;
import org.beny.stock.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static org.beny.stock.exception.StockError.*;

@Repository
public interface UserRepository extends BaseRepository<User> {

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

    default void checkIfUserExists(User user) throws StockException {
        if (existsByLogin(user.getLogin())) {
            throw LOGIN_EXISTS.exception();
        }

        if (existsByEmail(user.getEmail())) {
            throw EMAIL_EXISTS.exception();
        }
    }

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByLogin(String login);

    default User findByLogin(String login) throws StockException {
        return findOneByLogin(login)
                .orElseThrow(LOGIN_NOT_EXISTS::exception);
    }

}
