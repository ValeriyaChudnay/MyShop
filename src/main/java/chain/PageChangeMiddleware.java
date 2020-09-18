package chain;

import entity.UserRequestParameters;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class PageChangeMiddleware extends Middleware {
    private static final Logger logger = Logger.getLogger(PageChangeMiddleware.class);
    private int pageNumberFrom;
    private int amount;

    private void setLimit(UserRequestParameters urp) {
        int pageNumber = Integer.parseInt(urp.getPageNumber());
        if (pageNumber <= 0) {
            pageNumber = 1;
        }
        int amountOnPage = Integer.parseInt(urp.getAmountOnPage());
        int res = amountOnPage * pageNumber - amountOnPage;
        while (res > urp.getCount()) {
            res = res - amountOnPage;
        }
        urp.setPageNumber(String.valueOf(res / amountOnPage + 1));
        pageNumberFrom = res;
        amount = amountOnPage;
    }

    @Override
    public String check(UserRequestParameters urp, String sql) {
        pageNumberFrom = -1;
        amount = -1;
        if (Objects.nonNull(urp.getPageNumber())) {
            setLimit(urp);
            sql += " limit ?,?";
        }
        return checkNext(urp, sql);
    }

    @Override
    public ResultSet setParameterToStatement(int count, PreparedStatement statement) throws SQLException {
        logger.debug(statement.toString());
        statement.setInt(count++, pageNumberFrom);
        statement.setInt(count++, amount);
        return setNextParameterToStatement(count, statement);
    }
}
