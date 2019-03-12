import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import axios from 'axios';
import './App.css';

import Home from './Home';
import RoutingExample from './Router.js';

class App extends Component {
    
  render() {
    return (        
        <RoutingExample />
    );
  }
}
export default App;