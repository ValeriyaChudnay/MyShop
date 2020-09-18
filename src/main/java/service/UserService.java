package service;

import db.TransactionManager;
import entity.User;
import exeption.CaptchaException;
import exeption.LoginException;
import org.apache.log4j.Logger;
import repository.UserRepository;

import java.util.Optional;


public class UserService {
    private UserRepository repo;
    private TransactionManager transactionManager;
    private static final Logger logger = Logger.getLogger(UserService.class);

    public UserService(UserRepository repo, TransactionManager transactionManager) {
        this.repo = repo;
        this.transactionManager = transactionManager;
    }

    public boolean registerUser(User user) {
        transactionManager.doSQL(() -> repo.save(user));
        return true;
    }

    public boolean isEmailContainsInRepo(String email) {
        Optional<User> optional = transactionManager.doSQL(() -> repo.getUserByEmail(email));
        return optional.isPresent();
    }

    public boolean valid(String psw2, User user) throws CaptchaException {
        String regex = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
        if (!(psw2.equals(user.getPassword()) && psw2.length() > 0)) {
            throw new CaptchaException("The password you have entered is not valid!" +
                    " Password must contain at least 1 lowercase " +
                    "1 uppercase alphabetical character, 1 numeric character and have length more than 6 characters"
            );
        }
        if (!user.getEmail().matches(regex)) {
            throw new CaptchaException("Email not valid");
        }
        if (!(user.getFirstName().length() > 0
                || user.getSecondName().length() > 0)) {
            throw new CaptchaException("Check you First and Second name");
        }
        if (isEmailContainsInRepo(user.getEmail())) {
            throw new CaptchaException("This email already exists");
        }
        return true;
    }

    public User getUserByEmailAndPassword(String email, String psw) {
        Optional<User> optional = transactionManager.doSQL(() -> repo.getUserByEmail(email));
        if (!optional.isPresent()) {
            throw new LoginException("This email dosen't exists");
        }
        if (!optional.map(User::getPassword).get().equals(psw)) {
            throw new LoginException("Password not matches");
        }
        return optional.get();
    }
}
