package com.security.oauth.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.security.oauth.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		return em.createQuery("from User u order by u.name").getResultList();
	}
	
	public User findById(long id) {
		return (User) em.createQuery("from User u wherer u.name = ?")
				.setParameter(1, id).getSingleResult();
	}
	
	public void saveUser(User user) {
		em.persist(user);
	}

	public void deleteUserById(long id) {
		em.remove(findById(id));
	}

	public boolean isUserExist(User user) {
		return findById(user.getId())!=null;
	}
	
	public void deleteAllUsers(){
		List<User> list = findAllUsers();
		list.forEach(u -> deleteUserById(u.getId()));
	}
}
