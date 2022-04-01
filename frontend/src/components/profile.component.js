import React, { Component } from "react";
import { Button, Card, Container, ListGroup, Row } from "react-bootstrap";
import { Redirect } from "react-router-dom";
import authService from "../services/auth.service";
import AuthService from "../services/auth.service";
import TodoList from "./TodoList";

export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      redirect: null,
      userReady: false,
      currentUser: { username: "" },
    };
  }

  componentDidMount() {
    const currentUser = AuthService.getCurrentUser();

    if (!currentUser) this.setState({ redirect: "/home" });
    this.setState({ currentUser: currentUser, userReady: true });
  }

logOut() {
  AuthService.logout();
  const currentUser = undefined;
  this.setState({ currentUser: currentUser, userReady: false, redirect: "/login"  });
}
  
  render() {
    if (this.state.redirect) {
      return <Redirect to={this.state.redirect} />;
    }

    const { currentUser } = this.state;

    return (
      <Container fluid>
        {this.state.userReady ? (
          <Card style={{ width: '18rem' }}>
            <Card.Header>              <strong>{currentUser.username}</strong> Profile
            </Card.Header>
            <ListGroup variant="flush">
              <ListGroup.Item>Id: {currentUser.id}</ListGroup.Item>
              <ListGroup.Item>Email: {currentUser.email}</ListGroup.Item>
              <Button variant="primary" onClick={() => this.logOut()}>Log out</Button>
            </ListGroup>
          </Card>
        ) : null
        }
      </Container>
    );
  }
}
