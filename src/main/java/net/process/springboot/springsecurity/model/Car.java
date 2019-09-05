package net.process.springboot.springsecurity.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {
	public String id;
    public String brand;
    public int year;
    public String color;
    public int price;
    public boolean sold;
    public Car() {}
    
    public Car(String id, String brand, int year, String color) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.color = color;
    }
     
    public Car(String id, String brand, int year, String color, int price, boolean sold) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.color = color;
        this.price = price;
        this.sold = sold;
    }
  
 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Sucursal [id=" + id + ", brand=" + brand + ", year=" + year + ", color=" + color + ", price=" + price
				+ ", sold=" + sold + "]";
	}
    
}
