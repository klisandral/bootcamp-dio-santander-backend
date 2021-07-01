package com.bytebank.bootcamp.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.bytebank.bootcamp.model.dto.StockDTO;
import com.bytebank.bootcamp.model.entity.StockEntity;

@Component
public class StockMapper {

	public StockEntity toEntity(StockDTO dto) {
		StockEntity stock = new StockEntity();
		
		stock.setId(dto.getId());
		stock.setName(dto.getName());
		stock.setDate(dto.getDate());
		stock.setPrice(dto.getPrice());
		stock.setVariation(dto.getVariation());
		
		return stock;
	}
	
	public List<StockEntity> toEntity(List<StockDTO> dtoList) {
		return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
	}

	public StockDTO toDto(StockEntity entity) {
		StockDTO dto = new StockDTO();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDate(entity.getDate());
		dto.setPrice(entity.getPrice());
		dto.setVariation(entity.getVariation());
		
		return dto;
	}
	
	public List<StockDTO> toDto(List<StockEntity> entityList) {
		return entityList.stream().map(this::toDto).collect(Collectors.toList());
	}

}
