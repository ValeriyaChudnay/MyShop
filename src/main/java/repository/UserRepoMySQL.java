package repository;

import db.ConnectionHolder;
import db.SQLConst;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepoMySQL implements UserRepository {

    public <S extends User> boolean save(S user) throws SQLException {
        PreparedStatement pstmt = ConnectionHolder.getConnection().get().prepareStatement(SQLConst.INSERT_INTO_USER_VALUES);
        int placeHolder = 1;
        pstmt.setString(placeHolder++, user.getEmail());
        pstmt.setString(placeHolder++, user.getPassword());
        pstmt.setString(placeHolder++, user.getFirstName());
        pstmt.setString(placeHolder++, user.getSecondName());
        pstmt.setString(placeHolder++, user.getImgPath());
        pstmt.setBoolean(placeHolder, user.isEmailSend());
        pstmt.executeUpdate();
        pstmt.close();
        return true;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return new ArrayList<>();
    }

    @Override
    public Optional<User> getUserByEmail(String email) throws SQLException {
        PreparedStatement pstmt = ConnectionHolder.getConnection().get().prepareStatement(SQLConst.SELECT_FROM_USER_WHERE_EMAIL);
        int placeHolder = 1;
        pstmt.setString(placeHolder, email);
        pstmt.execute();
        ResultSet resultSet = pstmt.getResultSet();
        User user = null;
        if (resultSet.next()) {
            user = new User();
            placeHolder = 1;
            user.setEmail(resultSet.getString(placeHolder++));
            user.setPassword(resultSet.getString(placeHolder++));
            user.setFirstName(resultSet.getString(placeHolder++));
            user.setSecondName(resultSet.getString(placeHolder++));
            user.setImgPath(resultSet.getString(placeHolder++));
            user.setEmailSend(Boolean.parseBoolean(resultSet.getString(placeHolder)));
        }
        resultSet.close();
        pstmt.close();
        return Optional.ofNullable(user);
    }
}
