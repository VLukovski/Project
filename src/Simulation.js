import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import axios from 'axios';
import './App.css';

import Home from './Home';
import RoutingExample from './Router.js';

class Simulation extends Component {
	constructor(props) {
		super(props);
		this.state = {
			data: 'iVBORw0KGgoAAAANSUhEUgAAAfQAAAH0CAIAAABEtEjdAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAavSURBVHhe7dQBDQAADMOg+ze9+2hABDcAcuQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5AwTJHSBI7gBBcgcIkjtAkNwBguQOECR3gCC5A+RsD5ZH6Uv1wfoZAAAAAElFTkSuQmCC',
			toggle: false
		}
		this.simulateStep = (e) => {
			e.preventDefault();
			axios.get("http://35.198.109.158:8888/SoloProject/api/body/getNextState/1").then(response => {
				console.log();
				this.setState({ data: response.data });
			});
		}
		this.simulateStep3 = (e) => {
			e.preventDefault();
			axios.get("http://35.198.109.158:8888/SoloProject/api/body/getNextState/3").then(response => {
				console.log(response.data);
				this.setState({ data: response.data });
			});
		}
		this.makeRandomBody = (e) => {
			e.preventDefault();
			axios.post("http://35.198.109.158:8888/SoloProject/api/body/createBody", { "posX": Math.random() * 200 - 100, "posY": Math.random() * 200 - 100, "velX": Math.random() * 6 - 3, "velY": Math.random() * 6 - 3, "mass": Math.random() * 30 + 10 }).then(response => {
				console.log(response.data);
			});
		}
	}
	render() {
		return (
			<div>
				<img src={`data:image/jpeg;base64,${this.state.data}`} /> <br />
				<button onClick={this.simulateStep}>Step</button>
				<button onClick={this.simulateStep3}>Step*3</button>
				<button onClick={this.makeRandomBody}>RandomBody</button>
			</div>
		);
	}
}
export default Simulation;