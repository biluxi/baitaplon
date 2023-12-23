package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(name = "NAMESP", nullable = false)
    private String NameSP;
    @Column(name = "PRICE")
    private double Price;
    @Column(name = "DESCRIPTION")
    private String Description;
    
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getNameSP() {
		return NameSP;
	}
	public void setNameSP(String nameSP) {
		this.NameSP = nameSP;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Product(String nameSP, double price, String description) {
		super();
		NameSP = nameSP;
		Price = price;
		Description = description;
	}
	public Product(Long iD, String nameSP, double price, String description) {
		super();
		ID = iD;
		NameSP = nameSP;
		Price = price;
		Description = description;
	}
	public Product() {
		super();
	}

}