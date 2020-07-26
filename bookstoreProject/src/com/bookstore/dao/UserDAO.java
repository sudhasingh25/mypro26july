package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Users create(Users entity) {
		return super.create(entity);
	}

	@Override
	public void delete(Object id) {
		super.delete(Users.class, id);
	}

	@Override
	public long count() {
		return (long)super.countWithNamedQuery("Users.countAll");
	}

	@Override
	public Users get(Object id) {
		return super.find(Users.class,id);
	}

	@Override
	public Users update(Users entity) {
		return super.update(entity);
	}
	
	public boolean checkLogin(String email,String password){
		Map<String,Object> parameters= new HashMap<>();
		parameters.put("password", password);
		parameters.put("email", email);
		
		List<Users> listuser=super.findWithNamedQuery("Users.checkLogin", parameters);

		if(listuser.size()==1){
			return true;
		}
		return false;
	}

	@Override
	public List<Users> listAll() {
		return super.findWithNamedQuery("Users.findAll");
	}
	
	public Users findByEmail(String email){
		List<Users> listuser= super.findWithNamedQuery("Users.findByEmail","email",email);
		
		System.out.println("This is findbyemail ");
		
		for(Users user:listuser){
			System.out.println(user.getEmail());
		}
		
		System.out.println(" end of findbyemail ");

		
		if(listuser!=null && listuser.size()>0){
			return listuser.get(0);
		}
		return null;
	}

}
