package com.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.model.User;
import com.course.repositories.UserRepository;
import com.course.services.exception.ResourceNotFountException;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRespository;
	
	public List<User> findAll(){
		return userRespository.findAll();
	}
	
	public User findById(Integer id){
		Optional<User> user = userRespository.findById(id);
		return user.orElseThrow(
					() -> new ResourceNotFountException(id)
				);
	}
	
	public User insert(User user) {
		return userRespository.save(user);
	}
	
	public void delete(Integer id) {
			userRespository.deleteById(id);
	}
	
	public User update (Integer id, User user) {
		User entity = userRespository.getOne(id);	
		updateData(entity, user);
		return userRespository.save(entity);	
}

	private void updateData(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPhone(user.getPhone());
	}
}
