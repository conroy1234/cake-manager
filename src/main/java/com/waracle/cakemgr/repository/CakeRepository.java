package com.waracle.cakemgr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.waracle.cakemgr.entity.CakeEntity;

@Repository
public interface CakeRepository extends CrudRepository<CakeEntity, Integer> {

}

