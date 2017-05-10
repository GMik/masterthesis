package com.vp.plugin.repositories;

import java.util.List;

public interface CrudRepository<T> {

	void create(T t);

	T read(long id);

	List<T> readAll();

	void update(T t);

	void delete(long id);

}
