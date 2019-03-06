package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {

	public Account() {

	}

	public Account(Integer uniqueKey, String login, String password) {
		this.login = login;
		this.password = password;
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long accountID;

	private Integer uniqueKey;
	private String login;
	private String password;

	public Long getAccountID() {
		return accountID;
	}

	public void setAccountID(Long accountID) {
		this.accountID = accountID;
	}

	public Integer getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(Integer uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
