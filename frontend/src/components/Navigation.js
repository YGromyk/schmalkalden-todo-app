import React from "react";

import { Navbar, Container, Nav } from "react-bootstrap";
import { Link } from "react-router-dom";
import eventBus from "../common/EventBus";
import authService from "../services/auth.service";

class Navigation extends React.Component {
  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);

    this.state = {
      currentUser: undefined,
    };
  }

  componentDidMount() {
    const user = authService.getCurrentUser();
    if (user) {
      this.setState({ currentUser: user });
    }

    eventBus.on("logout", () => {
      this.logOut();
    });
  }

  componentWillUnmount() {
    eventBus.remove("logout");
  }
  logOut() {
    authService.logout();
    this.setState({
      currentUser: undefined,
    });
  }

  render() {
    const { currentUser } = this.state;
    return (
      <Navbar bg="primary" variant="dark" expand="lg">
        <Container fluid>
          <Navbar.Brand as={Link} to="/">
            Todo App
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="responsive-navbar-nav">


            {currentUser ? (
              <>
                <Nav className="me-auto">

                  <Nav.Link as={Link} to="/todos">
                    Todos
                  </Nav.Link>
                </Nav>
                <Nav>
                  <Nav.Link as={Link} to="/profile">
                    {currentUser.username}
                  </Nav.Link>
                  <Nav.Link as={Link} to="/profile" onClick={this.logOut}>
                    LogOut
                  </Nav.Link>
                </Nav>
              </>
            ) : (
              <>
                <Nav className="me-auto" />
                <Nav>
                  <Nav.Link as={Link} to="/login">
                    Login
                  </Nav.Link>
                  <Nav.Link as={Link} to="/register">
                    Sign Up
                  </Nav.Link>
                </Nav>
              </>
            )}

          </Navbar.Collapse>
        </Container>
      </Navbar >
    );
  }
}

export default Navigation;
