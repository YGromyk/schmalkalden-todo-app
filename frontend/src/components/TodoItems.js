import React, { Component } from "react";
import { Button, Card, Col, Row } from "react-bootstrap";

class TodoItems extends Component {
  constructor(props) {
    super(props);
    this.createTasks = this.createTasks.bind(this);
    this.delete = this.delete.bind(this);
  }

  delete(key) {
    this.props.delete(key);
  }

  createTasks(item) {
    return (
      <Col key={item.id}>
        <Card
          style={{ width: "18rem" }}
          key={item.id}
        >
          <Card.Body>
            <Card.Title>{item.title}</Card.Title>

            <Card.Text>{item.text}</Card.Text>
          </Card.Body>
          <Card.Footer>
            <small className="text-muted">
              Created: {item.createdAt}
            </small>
            <br/>
            <Button variant="danger" onClick={() => this.delete(item.id)}>Remove</Button>
          </Card.Footer>
        </Card>
      </Col>
    );
  }

  render() {
    var todoEntries = this.props.todos;
    var listItems = todoEntries.map(this.createTasks);

    return (
      <Row xs={1} md={2} className="g-4">
        {listItems}
      </Row>
    );
  }
}

export default TodoItems;
