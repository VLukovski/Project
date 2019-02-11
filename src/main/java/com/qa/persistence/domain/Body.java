package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Body {
	public Body() {
		
	}
	
	public Body(double x, double y, double vx, double vy, double mass) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.mass = mass;
	}
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long objectId;
	
	private double x, y;
	private double vx, vy;
	private double fx, fy;
	private double mass;
	
	public double getDistance(Body second) {
		return Math.sqrt(Math.pow((x - second.x), 2) + Math.pow((y - second.y), 2));
	}
	
	public void addForces(Body second) {
		double G = 1;
		double dist = getDistance(second);
		double force = G * (mass * second.mass) / (dist * dist);
		second.fx = force * (x - second.x) / dist;
		second.fy = force * (y - second.y) / dist;
		fx = force * (second.x - x) / dist;
		fy = force * (second.y - y) / dist;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getFx() {
		return fx;
	}

	public void setFx(double fx) {
		this.fx = fx;
	}

	public double getFy() {
		return fy;
	}

	public void setFy(double fy) {
		this.fy = fy;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}
}
