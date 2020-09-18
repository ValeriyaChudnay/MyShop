package chain;

import entity.UserRequestParameters;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class PriceMiddleware extends Middleware {
    private static final Logger logger = Logger.getLogger(PriceMiddleware.class);
    private int priceFrom;
    private int priceTo;

    @Override
    public String check(UserRequestParameters urp, String sql) {
        priceFrom = -1;
        priceTo = -1;
        if (Objects.nonNull(urp.getPriceFrom())) {
            priceFrom = Integer.parseInt(urp.getPriceFrom());
            sql += " price > ? and ";
        }
        if (Objects.nonNull(urp.getPriceTo())) {
            priceTo = Integer.parseInt(urp.getPriceTo());
            sql += " price < ? and ";
        }
        return checkNext(urp, sql);
    }

    @Override
    public ResultSet setParameterToStatement(int count, PreparedStatement statement) throws SQLException {
        logger.debug(statement.toString());
        if (priceFrom >= 0) {
            statement.setInt(count++, priceFrom);
        }
        if (priceTo >= 0) {
            statement.setInt(count++, priceTo);
        }
        return setNextParameterToStatement(count, statement);
    }
}
