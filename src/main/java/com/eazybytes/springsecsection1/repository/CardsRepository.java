package com.eazybytes.springsecsection1.repository;

import com.eazybytes.springsecsection1.model.Cards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends CrudRepository<Cards,Long> {

    List<Cards> findByCustomerId(Long customerId);
}
