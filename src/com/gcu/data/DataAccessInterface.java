package com.gcu.data;

import java.util.List;

public interface DataAccessInterface<T> {
	
	/**
	 * See UserDataService.create and EventDataService.create
	 */
	public int create(T t);
	
	/**
	 * See UserDataService.update and EventDataService.update
	 */
	public int update(T t);
	
	/**
	 * See UserDataService.delete and EventDataService.delete
	 */
	public int delete(int id);
	
	/**
	 * See UserDataService.viewAll and EventDataService.viewAll
	 */
	public List<T> viewAll();
	
	/**
	 * See UserDataService.viewById and EventDataService.viewById
	 */
	public T viewById(int id);

	/**
	 * See UserDataService.viewAllREST and EventDataService.viewAllREST
	 */
	public List<T> viewAllREST();
	
	/**
	 * See UserDataService.viewUserREST and EventDataService.viewUserREST
	 */
	public List<T> viewUserREST();

}
