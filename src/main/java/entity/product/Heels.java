package entity.product;

import java.util.Objects;


public class Heels extends Shoes {
    private double height;

    public Heels(String name, String descr, int size, double price, double height) {
        super(name, descr, size, price);
        this.height = height;

    }

    public Heels() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Heels heels = (Heels) o;
        return Double.compare(heels.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), height);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }


    @Override
    public String toString() {
        return "Heels{" + '\n'
                + "height=" + height + '\n' +
                "name='" + super.getName() + '\n' +
                "price=" + super.getPrice() + '\n' +
                "size=" + super.getSize() + '\n' +
                '}';
    }

}
