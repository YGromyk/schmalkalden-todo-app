import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AuthService from "./services/auth.service";

import Login from "./components/login.component";
import Register from "./components/register.component";
import Home from "./components/home.component";
import Profile from "./components/profile.component";


// import AuthVerify from "./common/auth-verify";
import EventBus from "./common/EventBus";
import { PrivateRoute } from "./components/PrivateRoute";
import { Redirect } from "react-router-dom";
import { Container, Nav, Navbar, NavDropdown } from "react-bootstrap";
import Navigation from "./components/Navigation";

class App extends Component {
  render() {
    const currentUser = AuthService.getCurrentUser();

    return (
      <Container>
        <Navigation />
        <Switch className="mt-3">
          <PrivateRoute exact path={["/", "/home"]} component={Home} />
          <Route exact path="/login" component={Login} />
          <Route exact path="/register" component={Register} />
          <PrivateRoute exact path="/profile" component={Profile} />
          <Redirect from="*" to="/" />
        </Switch>
        {/*<AuthVerify logOut={this.logOut}/> */}
      </Container>
    );
  }
}

export default App;
