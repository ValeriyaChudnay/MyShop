package entity.product;


import java.util.Objects;

public class Sneakers extends Shoes {
    private boolean hasShoelaces;


    public Sneakers(String name, String description, int size, double price, boolean hasShoelaces) {
        super(name, description, size, price);
        this.hasShoelaces = hasShoelaces;
    }

    public Sneakers() {
    }


    public boolean isHasShoelaces() {
        return hasShoelaces;
    }


    public void setHasShoelaces(boolean hasShoelaces) {
        this.hasShoelaces = hasShoelaces;
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasShoelaces);
    }

    @Override
    public String toString() {
        return "Sneakers{" + '\n' +
                "name=" + super.getName() + '\n' +
                "size=" + super.getSize() + '\n' +
                "price=" + super.getPrice() + '\n' +
                "hasShoelaces=" + hasShoelaces +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Sneakers sneakers = (Sneakers) o;
        return hasShoelaces == sneakers.hasShoelaces;
    }
}
