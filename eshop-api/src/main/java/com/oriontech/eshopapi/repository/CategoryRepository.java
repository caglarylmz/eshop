package com.oriontech.eshopapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oriontech.eshopapi.models.Category;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
	@Query("from Category c where c.parentCategory = null")
	public List<Category> findParentCategories();

	@Query("from Category where parentCategory=null and status = :status")
	public List<Category> findParentCategoriesWithStatus(@Param("status") boolean status);

	@Query("from Category where status = :status")
	public List<Category> findAllCategoriesWithStatus(@Param("status") boolean status);
	
	@Query("from Category where parent_category_id =:parent_category_id")
	public List<Category> findSubcategoriesById(@Param("parent_category_id") long parent_category_id); 

}
