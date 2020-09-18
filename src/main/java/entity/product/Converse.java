package entity.product;


import java.util.Objects;

public class Converse extends Sneakers {
    private String collection;

    public Converse(String name, String desc, int size, double price, boolean hasShoelaces, String collection) {
        super(name, desc, size, price, hasShoelaces);
        this.collection = collection;
    }

    public Converse() {
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Converse converse = (Converse) o;
        return Objects.equals(collection, converse.collection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), collection);
    }

    @Override
    public String toString() {
        return "Converse{" + '\n' +
                "collection=" + collection + '\n' +
                "name=" + super.getName() + '\n' +
                "size=" + super.getSize() + '\n' +
                "price=" + super.getPrice() + '\n' +
                "hasShoelaces=" + super.isHasShoelaces() +
                '}';
    }
}
