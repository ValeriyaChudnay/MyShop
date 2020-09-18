package service;

import db.RepositoryFunction;
import db.TransactionManager;
import entity.User;
import exeption.CaptchaException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.UserRepoMySQL;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

class UserServiceTest {


    @Test
    void valid() throws CaptchaException {
        TransactionManager tr = Mockito.mock(TransactionManager.class);
        UserRepoMySQL repo = Mockito.mock(UserRepoMySQL.class);
        User u = new User("Lora", "Por", "vas@mk.com", "Qwe123", true);
        doReturn(Optional.ofNullable(null)).when(tr).doSQL(any(RepositoryFunction.class));
        UserService userService = new UserService(repo, tr);
        assertTrue(userService.valid("Qwe123", u));
    }

    @Test
    void valid_EmailAlreadyExist() throws SQLException {
        TransactionManager tr = Mockito.mock(TransactionManager.class);
        UserRepoMySQL repo = Mockito.mock(UserRepoMySQL.class);
        User u = new User("Lora", "Por", "vas@mk.com", "Qwe123", true);
        doReturn(Optional.of(u)).when(tr).doSQL(any(RepositoryFunction.class));
        UserService userService = new UserService(repo, tr);
        assertThrows(CaptchaException.class, () -> userService.valid("Qwe123", u));
    }

    @Test
    void valid_notCorrectData() {
        TransactionManager tr = Mockito.mock(TransactionManager.class);
        UserRepoMySQL repo = Mockito.mock(UserRepoMySQL.class);
        User u = new User("Lora", "Por", "vas@mk.com", "Qwe123", true);
        UserService userService = new UserService(repo, tr);
        assertThrows(CaptchaException.class, () -> userService.valid("Qwe153", u));
    }
}