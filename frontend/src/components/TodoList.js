import React, { Component } from "react";
import "./TodoList.css";
import TodoItems from "./TodoItems";
import { Card, CardGroup, Container, ListGroup, Row } from "react-bootstrap";

class TodoList extends Component {
  constructor(props) {
    super(props);

    this.state = {
      items: [],
    };

    this.addItem = this.addItem.bind(this);
    this.deleteItem = this.deleteItem.bind(this);
  }
 

  addItem(e) {
    var itemArray = this.state.items;

    if (this._inputElement.value !== "") {
      itemArray.unshift({
        text: this._inputElement.value,
        key: Date.now(),
      });

      this.setState({
        items: itemArray,
      });

      this._inputElement.value = "";
    }
    console.log(itemArray);
    e.preventDefault();
  }

  deleteItem(key) {
    var filteredItems = this.state.items.filter(function (item) {
      return item.key !== key;
    });

    this.setState({
      items: filteredItems,
    });
  }

  render() {
    return (
      <Row xs={1} md={2} className="g-4">
        <TodoItems entries={this.state.items} delete={this.deleteItem} />
      </Row>
    );
  }
}

export default TodoList;
