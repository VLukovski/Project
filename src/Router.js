import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import axios from 'axios';

import Home from './Home';
import Simulation from './Simulation';
import Database from './Database';
import Login from './Login';


function RoutingExample() {
  return (
    <Router>
      <div>
      <h2>Simple N-Body Simulator</h2>
        <ul >
          <button>
            <Link to="/">Home</Link>
          </button>
          <button>
            <Link to="/Simulation">Simulation</Link>
          </button>
          <button>
            <Link to="/Database">Database</Link>
          </button>   
          </ul>
        <hr />

        <Route exact path="/" component={Home} />
        <Route path="/Simulation" component={Simulation} />
        <Route path="/Database" component={Database} />
      </div>
    </Router>
  );
}

class RefExample extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data: 'Initial data...'
    }
    this.updateState = (e) => {
      e.preventDefault();
      this.setState({
        data: e.target.value
      });
    }
    this.clearInput = (e) => {
      e.preventDefault();
      this.setState({
        data: ""
      });
      this.textInput.focus();
    }
  }
  render() {
    return (
      <form>
        <h2>RefExample Component</h2>
        <input type="text" value={this.state.data} onChange={this.updateState} ref={(input) => this.textInput = input} />
        <h4>{this.state.data}</h4>
        <button onClick={this.clearInput}>Clear</button>
      </form>
    );
  }
}

export default RoutingExample;