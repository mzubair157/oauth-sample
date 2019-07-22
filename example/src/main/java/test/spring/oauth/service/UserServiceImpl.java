package test.spring.oauth.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.spring.oauth.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		return em.createQuery("from User u order by u.name").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public User findById(long id) {
		List<User> list = em.createQuery("from User u where u.id = ?1")
				.setParameter(1, id).getResultList();
		if(!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@Transactional
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
