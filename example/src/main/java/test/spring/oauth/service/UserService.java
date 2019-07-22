package test.spring.oauth.service;

import java.util.List;

import test.spring.oauth.model.User;

public interface UserService {

	public List<User> findAllUsers() ;
	
	public User findById(long id) ;
	
	public void saveUser(User user);

	public void deleteUserById(long id) ;

	public boolean isUserExist(User user) ;
	
	public void deleteAllUsers();
}
