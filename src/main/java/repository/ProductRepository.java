package repository;

import chain.Middleware;
import db.ConnectionHolder;
import db.SQLConst;
import entity.Cart;
import entity.product.Shoes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ProductRepository {

    <S extends Shoes> boolean save(S product) throws SQLException;

    List<Shoes> query(String s, Middleware middleware) throws SQLException;

    int count(String s, Middleware middleware) throws SQLException;

    Shoes getById(int id) throws SQLException;

    Cart getCart(String mail) throws SQLException;

    boolean saveInTemporaryCart(String mail, Cart cart) throws SQLException;
    boolean cleanUserTemporaryCart(String mail) throws SQLException;

}
