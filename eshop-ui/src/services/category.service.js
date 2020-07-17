import axios from 'axios';

const API_URL = 'http://localhost:8081/api/open/category/';

class CategoryService {
  getParentCategories() {
    return axios.get(API_URL + 'parents').then(response => {
      return response.data;
    });
  }
  getSubCategories(id) {
    return axios.get(API_URL + id + '/subs',).then(response => {
      return response.data;
    });
  }

  getCategory(id) {
    return axios.get(API_URL + id).then(response => {
      return response.data;
    });
  }
  getParentCategory(id) {
    return axios.get(API_URL + "parent/" + id).then(response => {
      return response.data;
    });
  }
  addParentCategory(category) {
    return axios.post(API_URL, {
      name: category.name,
    });
  }
  addSubCategory(category, parentId) {
    return axios.post(API_URL + parentId, {
      name: category.name,
    });
  }
  updateCategory(category, id) {
    return axios.put(API_URL + id, {
      id: category.id,
      name: category.name,
      status: category.status,
      icon: category.icon,
      tips: category.tips

    });
  }
  deleteCategory(id) {
    return axios.delete(API_URL + id);
  }
  getTipsOfCategory(id) {
    return axios.get(API_URL + id + '/tips',).then(response => {
      return response.data;
    });
  }



}

export default new CategoryService();