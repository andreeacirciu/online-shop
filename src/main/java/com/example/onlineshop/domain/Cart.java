package com.example.onlineshop.domain;

import javax.persistence.*;

@Entity
public class Cart {

    @Id
    private long id;

    //relatie intre cart si user; un user are un singur cart
    //relatie one to one

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                '}';
    }
}
