package com.oriontech.eshopapi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oriontech.eshopapi.models.Category;
import com.oriontech.eshopapi.models.Tip;
import com.oriontech.eshopapi.payloads.res.ApiResponse;
import com.oriontech.eshopapi.repository.CategoryRepository;

@RestController
@RequestMapping("/api/open/category")
@CrossOrigin("*")
public class CategoryController {
	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("/parents")
	@ResponseBody
	public List<Category> getParentCategories() {
		return categoryRepository.findParentCategoriesWithStatus(true);
	}
	
	@GetMapping("/parent/{id}")
	@ResponseBody
	public Category getParentCategory(@PathVariable long id) {
		Category cat = categoryRepository.findById(id).get();		
		return cat.getParentCategory();
	}


	@GetMapping("/{id}/subs")
	@ResponseBody
	public List<Category> getSubCategories(@PathVariable long id) {
		return categoryRepository.findById(id).get().getSubCategories();
	}

	@GetMapping("/{id}/tips")
	@ResponseBody
	public List<Tip> getTipsOgCategory(@PathVariable long id) {
		return categoryRepository.findById(id).get().getTips();
	}

	@GetMapping("/{id}")
	@ResponseBody
	public Category getCategory(@PathVariable long id) {
		return categoryRepository.findById(id).get();
	}

	/*Ana Kategori Ekleme*/
	@PostMapping("")
	@ResponseBody
	public ResponseEntity<ApiResponse> addParentCategory(@RequestBody Category category) {
		Category added = new Category();
		added.setName(category.getName());
		try {
			categoryRepository.save(added);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Category Added!"), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, e.getMessage()), HttpStatus.ACCEPTED);
		}

	}
	/*Alt Kategori ekleme*/
	@PostMapping("/{parentCatagoryId}")
	@ResponseBody
	public ResponseEntity<ApiResponse> addSubCategory(@PathVariable long parentCatagoryId, @RequestBody Category category) {
		Category parentCat = categoryRepository.findById(parentCatagoryId).get();
		Category subCat = new Category();		
		try {
			subCat.setName(category.getName());
			subCat.setParentCategory(parentCat);
			//parentCat.getSubCategories().add(subCat);
			categoryRepository.save(subCat);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "SubCategory Added!"), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, e.getMessage()), HttpStatus.ACCEPTED);
		}
	}

	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable long id, @RequestBody Category category) {
		Category updated = categoryRepository.findById(id).get();
		updated.setName(category.getName());
		updated.setIcon(category.getIcon());
		updated.setStatus(category.isStatus());
		updated.setTips(category.getTips());
		try {
			categoryRepository.save(updated);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Category Updated!"), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, e.getMessage()), HttpStatus.ACCEPTED);
		}

	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable long id) {
		Category deleted = categoryRepository.findById(id).get();
		if (deleted.getSubCategories().size() > 0) {
			return new ResponseEntity<ApiResponse>(
					new ApiResponse(false, "This Category has subcategories. Pls first delete it."),
					HttpStatus.ACCEPTED);

		} else {
			categoryRepository.deleteById(id);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Success Deleted"), HttpStatus.ACCEPTED);
		}

	}

}
