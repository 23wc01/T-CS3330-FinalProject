package edu.mu.finalproject.model;

import java.util.List;
import java.util.ArrayList;

public class AccountContainerModel {
	
	private List<Account> activeAccounts;

	public AccountContainerModel() {
		this.activeAccounts = new ArrayList<Account>();
	}
	
	public AccountContainerModel(List<Account> activeAccounts) {
		this.activeAccounts = activeAccounts;
	}

	public List<Account> getActiveAccounts() {
		return activeAccounts;
	}

	public void setActiveAccounts(List<Account> activeAccounts) {
		this.activeAccounts = activeAccounts;
	}
	
}
