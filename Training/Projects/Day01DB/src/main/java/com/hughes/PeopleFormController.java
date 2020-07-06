package com.hughes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PeopleFormController {
	@Autowired
	private PersonDao personDao;
	
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}
	
	@PostMapping("/person")
	public String savePerson(@RequestParam String name, @RequestParam int age, HttpServletRequest request) {
		Person person = new Person();
		person.setName(name);
		person.setAge(age);
		personDao.save(person);
		request.setAttribute("message", "Person created with id: " + person.getId());
		return "index";
	}
}
