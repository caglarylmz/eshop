export default function authHeader() {
  const USER_KEY = 'auth-user';
  let user = JSON.parse(window.sessionStorage.getItem(USER_KEY));


  if (user && user.token) {
    return { Authorization: 'Bearer ' + user.token }; // for Spring Boot back-end
  } else {
    return {};
  }
}