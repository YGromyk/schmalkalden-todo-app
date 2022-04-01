import axios from 'axios';

const API_URL = "http://backend2.eu-central-1.elasticbeanstalk.com:8080/api/auth/";

const axiousInstance = axios.create({
  baseURL: API_URL,
  headers: {
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json',
  }
});


class AuthService {
  login(email, password) {
    return axiousInstance
      .post(API_URL + "signin", {
        email,
        password
      })
      .then(response => {
        if (response.data.token) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(username, email, password) {
    return axiousInstance.post(API_URL + "signup", {
      username,
      email,
      password
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }

  refreshToken() {
    var refreshToken = JSON.parse(localStorage.getItem('user')).refreshToken;
    if (refreshToken !== null)
      return axiousInstance.post(API_URL + "refreshToken", {
        refreshToken
      }).then(res => {
        return res.data;
      })
        .catch(error => console.log(error));
    else return new Promise();
  }
}

export default new AuthService();
