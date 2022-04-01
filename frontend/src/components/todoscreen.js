import React, { Component } from "react";
import { Container, Row } from "react-bootstrap";
import todosService from "../services/todos.service";
import NewTodo from "./NewTodo";
import TodoList from "./TodoList";

export default class TodoScreen extends Component {
  state = {}
  constructor(props) {
    super(props);

    this.state = {
      todos: []
    };
    this.onNewTodoAdded = this.onNewTodoAdded.bind(this);
    this.deleteItem = this.deleteItem.bind(this);

  }

  componentDidMount() {
    todosService.getTodos().then(
      response => {
        console.log("todoScreen");
        console.log(response.data);
        console.log(response.toString());
        this.setState({
          todos: response.data
        });
      }
    );
  }

  onNewTodoAdded(todo) {
    const todosTemp = [...this.state.todos];
    todosTemp.push(todo);
    this.setState({ todos: todosTemp });
  }

  deleteItem(id) {
    console.log("id to delete", id);
    todosService.deleteItem(id)
      .then((response) => {
        console.log(response);
        if (response.data) {
          const todosTemp = [...this.state.todos].filter(it => it.id !== id);
          this.setState({ todos: todosTemp });
        }
      });
  }

  render() {
    return (
      <Container>
        <Row>
          <NewTodo onTodoAdded={this.onNewTodoAdded} />
          <TodoList todos={this.state.todos} deleteItem={this.deleteItem} />
        </Row>
      </Container>
    );
  }
}
