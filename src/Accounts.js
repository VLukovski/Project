import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import axios from 'axios';
import './App.css';

import Home from './Home';
import RoutingExample from './Router.js';

class Accounts extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: ""
        }
        this.addBody = (e) => {
            axios.post("http://35.198.109.158:8888/SoloProject/api/account/createAccount", { "text": this.name.text, "password": this.password.value, "number": this.uniqueKey.value }).then(response => {
                console.log(response.data);
                this.setState({ data: response.data.message });
            });
        }
        this.deleteAccount = (e) => {
            axios.delete("http://35.198.109.158:8888/SoloProject/api/account/deleteAccount/" + this.id.value).then(response => {
                console.log(response.data);
                this.setState({ data: response.data.message });
            });

        }
    }
    render() {
        return (
            <div>
                <a>
                    Login<br />
                    <input type="text" required ref={(input) => this.name = input} /> <br />
                    Password<br />
                    <input type="password" required ref={(input) => this.password = input} />  <br />
                    Unique Id<br />
                    <input type="number" required ref={(input) => this.uniqueKey = input} /><br />
                    <button onClick={this.addBody}>Add Account</button><br />
                </a>
                <a>
                    Id<br />
                    <input type="number" required ref={(input) => this.id = input} />Id<br />
                    <button onClick={this.deleteAccount}>Delete Account</button>
                </a>
                <h4>{this.state.data}</h4>
            </div>
        );
    }
}

export default Accounts;