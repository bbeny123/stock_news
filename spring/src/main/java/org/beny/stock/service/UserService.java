package org.beny.stock.service;

import org.beny.stock.exception.StockException;
import org.beny.stock.model.User;
import org.beny.stock.repository.TokenRepository;
import org.beny.stock.repository.UserRepository;
import org.beny.stock.security.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;
import static org.beny.stock.exception.StockError.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private MailService mailService;

    @Override
    public UserContext loadUserByUsername(String login) throws UsernameNotFoundException {
        return new UserContext(repository.findOneByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("The login does not exist in database"))
        );
    }

    public void create(User user) throws StockException {
        repository.checkIfUserExists(user);

        user.setPassword(encoder.encode(user.getPassword()));
        user.setType(User.Type.USER);
        user.setActive(!mailService.isEnabled());
        user.setStringToken(randomUUID().toString());
        user = repository.save(user);

        mailService.sendActivationEmail(user.getEmail(), user.getToken().getToken());
    }

    public void resendToken(String email) throws StockException {
        User user = repository.findOneByEmail(email)
                .orElseThrow(EMAIL_NOT_EXISTS::exception);

        if (user.isActive()) {
            throw USER_ALREADY_ACTIVE.exception();
        }

        user.setStringToken(randomUUID().toString());
        user = repository.save(user);

        mailService.sendActivationEmail(user.getEmail(), user.getToken().getToken());
    }

    public void activate(String token) {
        activate(tokenRepository.findByToken(token).orElseThrow(TOKEN_NOT_EXISTS::exception).getUser());
    }

    private void activate(User user) {
        user.setActive(true);
        user.setToken(null);
        repository.save(user);
    }

}
