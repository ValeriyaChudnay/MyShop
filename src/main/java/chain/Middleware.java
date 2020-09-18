package chain;


import entity.UserRequestParameters;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Middleware {
    private static final Logger logger = Logger.getLogger(Middleware.class);
    private Middleware next;

    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }

    public abstract String check(UserRequestParameters urp, String sql);

    public abstract ResultSet setParameterToStatement(int count, PreparedStatement statement) throws SQLException;

    protected String checkNext(UserRequestParameters urp, String sql) {
        if (next == null) {
            return sql;
        }
        return next.check(urp, sql);
    }

    protected ResultSet setNextParameterToStatement(int count, PreparedStatement statement) throws SQLException {
        if (next == null) {
            logger.debug(statement.getResultSet());
            return statement.executeQuery();
        }
        return next.setParameterToStatement(count, statement);
    }
}
