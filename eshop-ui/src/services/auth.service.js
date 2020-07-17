import axios from 'axios';

const API_URL = 'http://localhost:8081/api/auth/';
const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'signin', {
        usernameOrEmail: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data) {
          window.sessionStorage.removeItem(USER_KEY);
          window.sessionStorage.removeItem(TOKEN_KEY);
          window.sessionStorage.setItem(USER_KEY, JSON.stringify(response.data));
          window.sessionStorage.setItem(TOKEN_KEY, JSON.stringify(response.data.token));
          //localStorage.setItem(TOKEN_KEY, JSON.stringify(response.data.token));
          //localStorage.setItem(USER_KEY, JSON.stringify(response.data));
        }
        console.log(response.data);
        return response.data;
      });
  }

  logout() {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.removeItem(TOKEN_KEY);

  }

  register(user) {
    return axios.post(API_URL + 'signup', {
      name: user.name,
      username: user.username,
      email: user.email,
      password: user.password
    });
  }

  profile(userProfile) {
    return axios
      .post(API_URL + 'profile', userProfile)
      .then(response => {
        console.log(response.data);
        return response.data;
      });
  }
}

export default new AuthService();