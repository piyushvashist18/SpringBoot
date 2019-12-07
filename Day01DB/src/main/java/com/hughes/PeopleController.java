package com.hughes;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PeopleController {

	@Autowired
	private PersonDao personDao;
	
	@GetMapping("/meta")
	public String getInfo() {
		String meta = "";
		meta += "Class is " + personDao.getClass().getName() + "<br/>";
		for (Class<?> interfaceObj : personDao.getClass().getInterfaces()) {
			meta += "<br/> " + interfaceObj.getName();
		}
		meta += "<br><br/>";
		for (Method method : personDao.getClass().getMethods()) {
			meta += "<br/>" + method.getName();
		}
		return meta;
	}
	
	
	@GetMapping("/all/gt/{age}")
	public List<Person> getAllGreaterThanAge(@PathVariable int age) {
		return personDao.findAllWithAgeGreaterThan(age);	
	}
	
	@GetMapping("/all/{name}/{age}")
	public List<Person> getAllByNameAndAge(@PathVariable String name, @PathVariable int age) {
		return personDao.findAllByNameAndAge(name, age);
	}
	
	@GetMapping("/{id}/{name}/{age}")
	public Person getByIdAndNameAndAge(@PathVariable int id, @PathVariable String name, @PathVariable int age) {
		return personDao.findByIdAndNameAndAge(id, name, age);
	}
	
	@GetMapping("/{name}/{age}")
	public Person getByNameAndAge(@PathVariable String name, @PathVariable int age) {
		return personDao.findByNameAndAge(name, age);
	}
	
	@GetMapping("/")
	public List<Person> getAllPersons() {
		return (List<Person>)personDao.findAll();
	}
	
	@DeleteMapping("/{id}")
	public String deletePerson(@PathVariable int id) {
		Optional<Person> opt = personDao.findById(id);
		if(opt.isPresent()) {
			Person person = opt.get();
			personDao.delete(person);
			return "Person with id " + id + " deleted";
		}
		return "Person with id " + id + " is not present"; 
	}
	
	@PutMapping("/{id}/{age}")
	public String updateAge(@PathVariable int id, @PathVariable int age) {
		Optional<Person> opt = personDao.findById(id);
		if(opt.isPresent()) {
			Person person = opt.get();
			person.setAge(age);
			personDao.save(person);
			return "Person's age updated";
		}
		return "Person with id " + id + " is not present"; 
	}
	
	
	@PostMapping("/{name}/{age}")
	public String savePerson(@PathVariable String name, @PathVariable int age) {
		Person person = new Person();
		person.setName(name);
		person.setAge(age);
		personDao.save(person);
		return "Person created with id: " + person.getId();
	}
	
}




;