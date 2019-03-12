import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import axios from 'axios';
import './App.css';

import Home from './Home';
import RoutingExample from './Router.js';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      isLoggedIn: false
    }
    this.tryLogin = (e) => {
      axios.post("http://localhost:8080/SoloProject/api/account/checkAccount", { "login": this.apiLogin.value, "password": this.apiPassword.value }).then(response => {
        console.log(response.data);
        if (response.data === true) {
          this.setState({ isLoggedIn: true });
        }
        else {
          alert("wrong login/password");
        }
      });
    }
  }

  render() {
    if (this.state.isLoggedIn === false) {
      return (
        <div className="Login">
          <header className="Login-header">
            <p>
              Login<br />
              <input type="text" name="name" required ref={(input) => this.apiLogin = input} /><br />
              Password<br />
              <input type="password" name="pswd" required ref={(input) => this.apiPassword = input} /><br />
              <button onClick={this.tryLogin}>Login</button><br />
            </p>
          </header>
        </div>
      );

    }

    else {
      return (
        <Home />
      );
    }
  }
}
export default App;