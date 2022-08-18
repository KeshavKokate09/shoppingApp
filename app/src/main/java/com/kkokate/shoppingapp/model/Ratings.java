package com.kkokate.shoppingapp.model;

import java.io.Serializable;

public class Ratings implements Serializable {
    private float rate;
    private int count;

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
