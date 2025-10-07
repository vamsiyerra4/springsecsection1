package com.eazybytes.springsecsection1.repository;

import com.eazybytes.springsecsection1.model.Notices;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticesRepository extends CrudRepository<Notices,Long> {

    @Query(value = "from Notices n where CURDATE() BETWEEN noticBegDt and noticEndDt")
    List<Notices> findAllActiveNotices();

}
