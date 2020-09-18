package chain;


public class MiddlewareFactory {
    private MiddlewareFactory() {
    }

    public static Middleware getMiddleware() {
        Middleware category = new CategoryMiddleware();
        Middleware brand = new BrandMiddleware();
        Middleware name = new NameMiddleware();
        Middleware price = new PriceMiddleware();
        Middleware sort = new SortTypeMiddleware();
        Middleware page = new PageChangeMiddleware();
        category.linkWith(brand);
        brand.linkWith(name);
        name.linkWith(price);
        price.linkWith(sort);
        sort.linkWith(page);
        return category;
    }

    public static Middleware getMiddlewareForCount() {
        Middleware category = new CategoryMiddleware();
        Middleware brand = new BrandMiddleware();
        Middleware name = new NameMiddleware();
        Middleware price = new PriceMiddleware();
        Middleware sort = new SortTypeMiddleware();
        category.linkWith(brand);
        brand.linkWith(name);
        name.linkWith(price);
        price.linkWith(sort);
        return category;
    }
}
