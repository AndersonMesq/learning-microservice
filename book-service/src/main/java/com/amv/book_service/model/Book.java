package com.amv.book_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 180)
    private String author;

    @Column(nullable = false, length = 250)
    private String title;

    @Column(name = "launch_date",nullable = false)
    private Date launchDate;

    @Column(nullable = false)
    private BigDecimal price;

    @Transient
    private String currency;

    @Transient
    private String environment;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}