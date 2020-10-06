package com.upgrad.Eshop.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "eshop_product")
public class Product {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "HIBERNATE_SEQUENCE "),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "product_id")
    private int id;
    @Column(name = "available_items",nullable = false)
    private int availableItems;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private Date created;
    @Column(nullable = false)
    private String description;
    @Column(name = "image_url",nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private String manufacturer;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Date updated;

}
