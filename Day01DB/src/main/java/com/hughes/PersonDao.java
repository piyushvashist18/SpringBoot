package com.hughes;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PersonDao extends CrudRepository<Person, Integer> {
	
	@Query("select p from com.hughes.Person p where p.age > :p1")
	List<Person> findAllWithAgeGreaterThan(@Param("p1") int age);
	
	//findBy
	Person findByIdAndNameAndAge(int id, String name, int age);
	
	Person findByName(String name);
	Person findByAge(int age);
	Person findByNameAndAge(String name, int age);
	Person findByIdAndAge(int id, int age);
	Person findByAgeAndName(int age, String name);
	
	
	//findAllBy
	List<Person> findAllByName(String name);
	List<Person> findAllByAge(int age);
	List<Person> findAllByNameAndAge(String name, int age);
	List<Person> findAllByIdAndAge(int id, int age);
	List<Person> findAllByAgeAndName(int age, String name);
}










