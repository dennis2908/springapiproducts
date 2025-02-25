package com.example.live.product;

import com.example.live.product.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.io.Serializable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import org.springframework.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "products")
public class Product implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "sku",unique=true)
  private String sku;

  @Column(name = "image")
  private String image;

  @Nullable
  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private Integer price;

  @Column(name = "healthcarcat")
  private Integer healthcarcat;


  //getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }
  

  public Integer getHealthcarcat() {
    return healthcarcat;
  }

  public void setHealthcarcat(Integer healthcarcat) {
    this.healthcarcat = healthcarcat;
  }
}
