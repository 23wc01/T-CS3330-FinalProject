package edu.mu.finalproject.util.downloadPlaylistBuilder;

import edu.mu.finalproject.model.Account;


public class AccountHtmlBuilder extends HtmlBuilder {
	private Account account;
	
	/**
	 * Initializes html template file path. Will search through html template for what's added to replaceHtml
	 * Sets the default value that'll replaceHtml if anything goes wrong with fetching fields from model 
	 * @param model
	 */
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
	/**
	 * Sets account using @param model
	 * @return true
	 */
	protected Boolean setModel(Object model) {
		this.account = (Account) model;
		return true;
	}

	@Override
	/**
	 * Gets the dynamic field that'll replace replaceHtml. If field = null will do nothing (thus, falling back on default values set in constructor)
	 * @return true
	 */
	protected Boolean getDynamicFields() {
		if (account.getUsername() != null) {
			getModelFields().set(0, account.getUsername());
		}

		return true;
	}

}
