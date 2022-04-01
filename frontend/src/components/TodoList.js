import React, { Component } from "react";
import "./TodoList.css";
import TodoItems from "./TodoItems";
import { Row } from "react-bootstrap";

class TodoList extends Component {
  constructor(props) {
    super(props);

    this.deleteItem = this.deleteItem.bind(this);
  }
 
  deleteItem(key) {
    this.props.deleteItem(key);
  }

  render() {
    console.log("TodoList");
    console.log(this.props.todos);
    return (
      <Row xs={1} md={2} className="g-4">
        <TodoItems  todos={this.props.todos} delete={this.deleteItem} />
      </Row>
    );
  }
}

export default TodoList;
