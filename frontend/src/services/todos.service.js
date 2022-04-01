import { axiosApiInstance } from "./auth-header";

const API_URL = "/todos";

class TodosService {
  getTodos() {
    return axiosApiInstance.get(API_URL)
      .catch((error) => console.log(error));
    ;
  }

  createTodo(title, text) {
    return axiosApiInstance
      .post(
        API_URL + "/new",
        {
          title: title,
          text: text,
        }
      )
      .then((response) => {
        return response.data;
      })
      .catch((error) => console.log(error.body));
    ;
  }

  deleteItem(id) {
    return axiosApiInstance.delete(`${API_URL}/delete/${id}`)
      .catch((error) => console.log(error));
    ;
  }
}

export default new TodosService();
