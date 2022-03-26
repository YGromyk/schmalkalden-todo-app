import React, { Component } from "react";
import { Container, Row } from "react-bootstrap";
import { Redirect } from "react-router-dom";
import AuthService from "../services/auth.service";
import todosService from "../services/todos.service";
import NewTodo from "./NewTodo";
import TodoList from "./TodoList";

export default class TodoScreen extends Component {
  constructor(props) {
    super(props);

    this.state = {
        todos: []
    };
  }
 
  render() {
    return (
      <Container>
        <Row>
          <NewTodo />
          <TodoList/>
        </Row>
      </Container>
    );
  }
}
