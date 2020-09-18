package chain;


import entity.UserRequestParameters;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SortTypeMiddleware extends Middleware {
    private static final Map<String, String> sortTypeMap = new HashMap<>();
    private static final Logger logger = Logger.getLogger(SortTypeMiddleware.class);

    static {
        sortTypeMap.put("1", " order by price");// price from smal to big
        sortTypeMap.put("2", " order by price desc ");
        sortTypeMap.put("3", " order by name");
        sortTypeMap.put("4", " order by name desc ");//name
    }

    @Override
    public String check(UserRequestParameters urp, String sql) {
        if (sql.length() - sql.indexOf("where") == 5) {
            sql = sql.substring(0, sql.length() - 5);
        } else {
            sql = sql.substring(0, sql.length() - 4);
        }
        sql += sortTypeMap.get(urp.getSortType());
        return checkNext(urp, sql);
    }

    @Override
    public ResultSet setParameterToStatement(int count, PreparedStatement statement) throws SQLException {
        logger.debug(statement.toString());
        return setNextParameterToStatement(count, statement);
    }
}
