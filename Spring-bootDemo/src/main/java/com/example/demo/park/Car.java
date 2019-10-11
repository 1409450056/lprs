package com.example.demo.park;

public class Car {
    private String number;
    private int position = 0;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Car(String number) {
        this.number = number;
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == Car.class) {
            Car c = (Car) obj;
            return c.number == this.number;
        }
        return false;
    }
}
