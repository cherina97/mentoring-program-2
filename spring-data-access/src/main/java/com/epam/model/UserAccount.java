package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    private BigDecimal money;

    public long getId() {
        return id;
    }

    public UserAccount setId(long id) {
        this.id = id;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public UserAccount setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public UserAccount setMoney(BigDecimal money) {
        this.money = money;
        return this;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", userId=" + userId +
                ", money=" + money +
                '}';
    }
}
