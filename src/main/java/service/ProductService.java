package service;

import chain.Middleware;
import db.TransactionManager;
import entity.Cart;
import entity.product.Shoes;
import entity.UserRequestParameters;
import org.apache.log4j.Logger;
import repository.ProductRepoMySQL;
import repository.ProductRepository;

import java.util.List;
import java.util.Objects;

public class ProductService {

    private static final Logger logger = Logger.getLogger(ProductService.class);
    private ProductRepository repo;
    private TransactionManager transactionManager;

    public ProductService(ProductRepository repo, TransactionManager transactionManager) {
        this.repo = repo;
        this.transactionManager = transactionManager;
    }

    public void addProduct(Shoes shoes) {
        transactionManager.doSQL(() -> repo.save(shoes));
    }

    public List<Shoes> getAll(Middleware middleware, UserRequestParameters urp) {
        logger.debug("Start fetch products");
        return transactionManager.doSQL(() -> repo.query(middleware.check(urp, " where"), middleware));
    }

    public int getCount(Middleware middleware, UserRequestParameters urp) {
        return transactionManager.doSQL(() -> repo.count(middleware.check(urp, " where"), middleware));
    }

    public Shoes getById(int id) {
        return transactionManager.doSQL(()->repo.getById(id));
    }
    public Cart getOldCardByUser(String mail){
        return transactionManager.doSQL(()->repo.getCart(mail));
    }

    public void addInTemporaryCart(String mail,Cart cart) {
        if(Objects.nonNull(cart)){
            transactionManager.doSQL(()->repo.saveInTemporaryCart(mail,cart));
        }
    }
    public void cleanUserTemporaryCart(String mail){
        transactionManager.doSQL(()->repo.cleanUserTemporaryCart(mail));
    }
}
