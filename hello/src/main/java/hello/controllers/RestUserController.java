package hello.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hello.model.YUser;
import hello.repositories.UserRepository;

/**
 * CRUD API REST
 * 	CREATE / READ / UPDATE / DELETE
 * @author ryan
 *
 */
@RestController
public class RestUserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/user")
	public Iterable<YUser> getUsers() {		
		Iterable<YUser> users = userRepository.findAll();	
		System.out.println("trace getUsers");
		return users;		
	}
	
	@GetMapping("/user/{id}")
	public Optional<YUser> getUser(@PathVariable("id") int id) 
			throws InterruptedException {
		Optional<YUser> user = userRepository.findById(id);		
		System.out.println("Retrieve " + user.get().getUsername());
		return user;		
	}
	
	@PostMapping("/user")
	public YUser createUser(@RequestBody YUser user) {
		user = userRepository.save(user);		
		return user;
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		userRepository.deleteById(id);
	}
	
	@PutMapping("/user/{id}")
	public YUser updateUser(@PathVariable("id") int id, @RequestBody YUser user) {
		YUser currentUser = userRepository.findById(id).get();	
		
		String username = user.getUsername();
		if(username != null) {
			currentUser.setUsername(username);
		}		
		String password = user.getPassword();
		if(password != null) {
			currentUser.setPassword(password);
		}
		
		currentUser = userRepository.save(currentUser);
		return currentUser;
	}	
	
}
