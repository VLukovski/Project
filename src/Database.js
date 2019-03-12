import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import axios from 'axios';
import './App.css';

import Home from './Home';
import RoutingExample from './Router.js';

class Database extends Component {
	constructor(props) {
		super(props);
		this.state = {
			data: ""
		}
		this.addBody = (e) => {
			axios.post("http://localhost:8080/SoloProject/api/body/createBody", { "posX": this.posx.value, "posY": this.posy.value, "velX": this.velx.value, "velY": this.vely.value, "mass": this.mass.value }).then(response => {
				console.log(response.data);
				this.setState({ data: response.data.message });
			});
		}
		this.removeAll = (e) => {
			axios.delete("http://localhost:8080/SoloProject/api/body/removeBodies").then(response => {
				console.log(response.data);
				this.setState({ data: response.data.message });
			});

		}
	}
	render() {
		return (
			<div>
				<a>
					<input type="number" required ref={(input) => this.posx = input} />Position X
					<input type="number" required ref={(input) => this.posy = input} />Position Y<br />
					<input type="number" required ref={(input) => this.velx = input} />Velocity X
					<input type="number" required ref={(input) => this.vely = input} />Velocity Y<br />
					<input type="number" required ref={(input) => this.mass = input} />Mass<br />
					<button onClick={this.addBody}>Add Body</button><br />
				</a>
				<a>
					<button onClick={this.removeAll}>Reset System</button><br />
				</a>
				<h4>{this.state.data}</h4>
			</div>
		);
	}
}

export default Database;