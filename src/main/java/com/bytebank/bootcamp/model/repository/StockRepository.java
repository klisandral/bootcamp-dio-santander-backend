package com.bytebank.bootcamp.model.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bytebank.bootcamp.model.entity.StockEntity;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

	public Optional<StockEntity> findByNameAndDate(String name, LocalDate date);

	@Query("SELECT stock FROM StockEntity stock "
			+ "WHERE stock.id <> :id AND stock.name = :name AND stock.date = :date")
	public Optional<StockEntity> findAnotherStock(Long id, String name, LocalDate date);

	@Query("SELECT stock FROM StockEntity stock "
			+ "WHERE stock.date = :date")
	public List<StockEntity> findByToday(LocalDate date);
	
	
}
