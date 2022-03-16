import React, { Component }  from 'react';
import { Route, Redirect } from 'react-router-dom';


export { PrivateRoute };

function PrivateRoute({ component: Component, ...rest }) {
    const user = localStorage.getItem("user");
    const isAuthenticated = user != null && JSON.parse(user).token != null;
    return (
        <Route {...rest} render={props => {
            if (!isAuthenticated) {
                // not logged in so redirect to login page with the return url
                return <Redirect to={{ pathname: '/login', state: { from: props.location } }} />
            }

            // authorized so return component
            return <Component {...props} />
        }} />
    );
}
