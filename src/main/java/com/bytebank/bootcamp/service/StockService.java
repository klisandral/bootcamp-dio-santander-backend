package com.bytebank.bootcamp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytebank.bootcamp.exceptions.BusinessException;
import com.bytebank.bootcamp.exceptions.NotFoundException;
import com.bytebank.bootcamp.mapper.StockMapper;
import com.bytebank.bootcamp.model.dto.StockDTO;
import com.bytebank.bootcamp.model.entity.StockEntity;
import com.bytebank.bootcamp.model.repository.StockRepository;
import com.bytebank.bootcamp.util.MessageUtils;

@Service
public class StockService {

	@Autowired
	private StockRepository repository;
	
	@Autowired
	private StockMapper mapper;
	
	@Transactional
	public StockDTO save(StockDTO dto) {
		Optional<StockEntity> duplicate = repository.findByNameAndDate(dto.getName(), dto.getDate());
		if (duplicate.isPresent()) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXIST);
		}
		
		StockEntity entity = mapper.toEntity(dto);
		repository.save(entity);
		return mapper.toDto(entity);
	}
	
	@Transactional
	public StockDTO update(StockDTO dto) {
		Optional<StockEntity> duplicate = repository.findAnotherStock(dto.getId(), dto.getName(), dto.getDate());
		if (duplicate.isPresent()) {
			throw new BusinessException(MessageUtils.STOCK_ALREADY_EXIST);
		}
		
		StockEntity entity = mapper.toEntity(dto);
		repository.save(entity);
		return mapper.toDto(entity);
	}

	@Transactional
	public StockDTO delete(Long id) {
		StockDTO dto = this.findById(id);
		repository.deleteById(id);
		return dto;
	}

	@Transactional(readOnly = true)
	public List<StockDTO> findAll() {
		return mapper.toDto(repository.findAll());
	}

	@Transactional(readOnly = true)
	public StockDTO findById(Long id) {
		Optional<StockEntity> foundEntity = repository.findById(id);
		if (!foundEntity.isPresent()) {
			throw new NotFoundException(MessageUtils.NO_RECORDS_FOUND);
		}
		
		return foundEntity.map(mapper::toDto).get();
	}

	@Transactional
	public List<StockDTO> findByToday() {
		List<StockEntity> entityList = repository.findByToday(LocalDate.now());
		if (entityList.isEmpty()) {
			throw new NotFoundException(MessageUtils.NO_RECORDS_FOUND);
		}

		return entityList.stream().map(mapper::toDto).collect(Collectors.toList());
	}

}
