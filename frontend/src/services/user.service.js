import axios from 'axios';
import authHeader, { axiosApiInstance } from './auth-header';


class UserService {
  getPublicContent() {
    return axiosApiInstance.get('user');
  }

  getUserBoard() {
    return axiosApiInstance.get('user', { headers: authHeader() });
  }
}

export default new UserService();
