import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/todos";

class TodosService {
  getTodos() {
    return axios.get(API_URL, { headers: authHeader() });
  }

  createTodo(title, text) {
    return axios
      .post(
        API_URL + "/new",
        {
          title: title,
          text: text,
        },
        { headers: authHeader() }
      )
      .then((response) => {
        return response.data;
      });
  }
}

export default new TodosService();
