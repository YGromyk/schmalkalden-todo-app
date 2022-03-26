import React, { Component } from "react";
import { Card, ListGroup } from "react-bootstrap";
import FlipMove from "react-flip-move";
import todosService from "../services/todos.service";

class TodoItems extends Component {
  constructor(props) {
    super(props);
    this.state = {
      items: [],
    };
    this.createTasks = this.createTasks.bind(this);
  }
  componentDidMount() {
    todosService.getTodos().then(
      response => {
        this.setState({
            items: response.data
        });
      },
      error => {
        this.setState({
          content:
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        });
      }
    );
  }
  delete(key) {
    this.props.delete(key);
  }

  createTasks(item) {
    return (
      <Card
        style={{ width: "18rem" }}
        onClick={() => this.delete(item.key)}
        key={item.id}
      >
        {console.log(item)}
        <Card.Body>
          <Card.Title>{item.title}</Card.Title>
          <Card.Subtitle className="mb-2 text-muted">
            Created: {item.createdAt}
          </Card.Subtitle>
          <Card.Text>{item.text}</Card.Text>
        </Card.Body>
      </Card>
    );
  }

  render() {
    var todoEntries = this.state.items;
    var listItems = todoEntries.map(this.createTasks);

    return (
      <ul className="theList">
        <FlipMove duration={250} easing="ease-out">
          {listItems}
        </FlipMove>
      </ul>
    );
  }
}

export default TodoItems;
