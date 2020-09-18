package chain;

import entity.UserRequestParameters;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class NameMiddleware extends Middleware {
    private static final Logger logger = Logger.getLogger(NameMiddleware.class);
    private String name;

    @Override
    public String check(UserRequestParameters urp, String sql) {
        if (Objects.nonNull(urp.getName())) {
            sql += " name like concat('%', ?, '%') and";
            name = urp.getName();
        }
        return checkNext(urp, sql);
    }

    @Override
    public ResultSet setParameterToStatement(int count, PreparedStatement statement) throws SQLException {
        logger.debug(statement.toString());
        if (Objects.nonNull(name)) {
            statement.setString(count++, name);
        }
        return setNextParameterToStatement(count, statement);
    }
}
