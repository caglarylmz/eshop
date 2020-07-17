import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8081/api/user/';

class UserService {
  getProfile() {
    return axios.get(API_URL + 'profile',{ headers: authHeader() });
  }

  getUsers() {
    return axios.get(API_URL + 'all', { headers: authHeader() });
  }
}

export default new UserService();