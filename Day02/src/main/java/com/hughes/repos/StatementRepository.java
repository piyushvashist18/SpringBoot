package com.hughes.repos;

import org.springframework.data.repository.CrudRepository;

import com.hughes.entities.Statement;

public interface StatementRepository extends CrudRepository<Statement, Long>{

}
