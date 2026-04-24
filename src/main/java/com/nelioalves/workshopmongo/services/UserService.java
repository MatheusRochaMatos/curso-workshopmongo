package com.nelioalves.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.repositories.UserRepository;
import com.nelioalves.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert (User obj) {
		return repository.insert(obj);
	}
	
	public void delete (String id) {
		if (!repository.existsById(id)) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		try {
			repository.deleteById(id);
		}
		catch (RuntimeException e){
			e.printStackTrace();
		}
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
