package me.josephzhu.springrabbitmqdemo;

import me.ele.lpd.core.util.JsonUtils;

import java.util.Date;

/**
 * Created by zhuye on 05/10/2016.
 */
public class DemoMessage {
    private Long id;
    private String item;
    private Double price;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public DemoMessage(Long id, String item, Double price) {
        this.id = id;
        this.item = item;
        this.price = price;
        this.date = new Date();
    }

    public DemoMessage() {
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
