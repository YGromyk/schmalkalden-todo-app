import React, {Component} from "react";
import {Redirect, Route, Switch} from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";


import Login from "./components/login.component";
import Register from "./components/register.component";
import Home from "./components/home.component";
import Profile from "./components/profile.component";

// import AuthVerify from "./common/auth-verify";
import {PrivateRoute} from "./components/PrivateRoute";
import {Container} from "react-bootstrap";
import Navigation from "./components/Navigation";
import TodoScreen from "./components/todoscreen";

class App extends Component {
    render() {
        return (
            <Container fluid style={{paddingLeft: 0, paddingRight: 0}}>
                <Navigation/>
                <Switch>
                    <PrivateRoute exact path={["/", "/home"]} component={Home} />
                    <Route exact path="/login" component={Login} />
                    <Route exact path="/register" component={Register} />
                    <PrivateRoute exact path="/profile" component={Profile} />
                    <PrivateRoute exact path="/todos" component={TodoScreen} />
                    <Redirect from="*" to="/" />
                </Switch>
            </Container>
        );
    }
}

export default App;
