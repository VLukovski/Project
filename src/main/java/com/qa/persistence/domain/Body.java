package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Body {
	public Body() {

	}

	public Body(double posX, double posY, double velX, double velY, double mass) {
		this.posX = posX;
		this.posY = posY;
		this.velX = velX;
		this.velY = velY;
		this.mass = mass;
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long objectId;

	private double posX, posY;
	private double velX, velY;
	private double forceX, forceY;
	private double mass;

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}
	
	public void addPosX(double change) {
		posX += change;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	public void addPosY(double change) {
		posY += change;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	public void addVelX(double change) {
		velX += change;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	public void addVelY(double change) {
		velY += change;
	}

	public double getForceX() {
		return forceX;
	}

	public void setForceX(double forceX) {
		this.forceX = forceX;
	}
	
	public void addForceX(double forceX) {
		this.forceX =+ forceX;
	}


	public double getForceY() {
		return forceY;
	}

	public void setForceY(double forceY) {
		this.forceY = forceY;
	}
	
	public void addForceY(double forceY) {
		this.forceY =+ forceY;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}
}
