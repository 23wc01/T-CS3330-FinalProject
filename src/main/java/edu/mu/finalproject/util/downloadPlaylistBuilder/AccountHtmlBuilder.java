package edu.mu.finalproject.util.downloadPlaylistBuilder;

import edu.mu.finalproject.model.Account;


public class AccountHtmlBuilder extends HtmlBuilder {
	private Account account;
	
	public AccountHtmlBuilder(Object model) {
		super();
		Boolean success = setModel(model);
		if (!success) {
			return;
		}
		getReplaceHtml().add("@accountUsername");
		
		getModelFields().add("Anonymous"); // Default name is "Anonymous"

		setTemplateFileLocation("files/playlistAccountHtmlTemplate.txt");
	}
	@Override
	protected Boolean setModel(Object model) {
		this.account = (Account) model;
		
		return true;
	}

	@Override
	protected Boolean getDynamicFields() {
		if (account.getUsername() != null) {
			getModelFields().set(0, account.getUsername());
		}

		return true;
	}

}
