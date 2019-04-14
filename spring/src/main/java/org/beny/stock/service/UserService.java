package org.beny.stock.service;

import org.beny.stock.exception.StockException;
import org.beny.stock.model.User;
import org.beny.stock.repository.UserRepository;
import org.beny.stock.security.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;
import static org.beny.stock.exception.StockError.*;
import static org.beny.stock.util.MailUtil.sendActivationEmail;

@Service
public class UserService extends BaseService<User, UserRepository> implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserContext loadUserByUsername(String login) throws UsernameNotFoundException {
        return new UserContext(repository.findOneByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("The login does not exist in database"))
        );
    }

    public boolean existsByLogin(String login) {
        return repository.existsByLogin(login);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public void create(User user) throws StockException {
        if (existsByLogin(user.getLogin())) {
            throw LOGIN_EXISTS.exception();
        }

        if (existsByEmail(user.getEmail())) {
            throw EMAIL_EXISTS.exception();
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setType(User.Type.USER);
        user.setActive(false);
        user.setStringToken(randomUUID().toString());
        user = saveAndFlush(user);

        sendActivationEmail(user.getEmail(), user.getToken().getToken());
    }

    public void activate(User user) {
        user.setActive(true);
        user.setToken(null);
        save(user);
    }

    public void activate(UserContext ctx, Long userId) {
        activate(findOneAdmin(ctx, userId));
    }

    public User findByLogin(String login) throws StockException {
        return repository.findOneByLogin(login)
                .orElseThrow(LOGIN_NOT_EXISTS::exception);
    }

    public User findByEmail(String email) throws StockException {
        return repository.findOneByEmail(email)
                .orElseThrow(EMAIL_NOT_EXISTS::exception);
    }

    public void resendToken(User user) throws StockException {
        if (user.isActive()) {
            throw USER_ALREADY_ACTIVE.exception();
        }

        user.setStringToken(randomUUID().toString());
        user = saveAndFlush(user);

        sendActivationEmail(user.getEmail(), user.getToken().getToken());
    }

}
