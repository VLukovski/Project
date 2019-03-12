import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import axios from 'axios';
import './App.css';

import RoutingExample from './Router.js';

class Home extends Component {

  render() {
    return (

      <div>
        <RoutingExample />
		</div>
    );
  }
}
export default Home;