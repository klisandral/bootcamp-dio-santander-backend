package com.bytebank.bootcamp.model.dto;

import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class StockDTO {

	private Long id;

	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	@NotNull
	private LocalDate date;

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@PositiveOrZero
	@Digits(integer = 6, fraction = 2)
	private Double price;

	@NotNull
	@Digits(integer = 3, fraction = 2)
	private Double variation;

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getVariation() {
		return variation;
	}

	public void setVariation(Double variation) {
		this.variation = variation;
	}

}
