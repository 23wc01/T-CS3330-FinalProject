package edu.mu.finalproject.util.downloadPlaylistBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;


public abstract class HtmlBuilder {
	private String templateFileLocation;
	private String htmlTemplate;
	private ArrayList<String> replaceHtml;
	private ArrayList<String> modelFields;
	private HashMap<String, String> htmlMapModelFields;
	private String htmlStr;

	/**
	 * Calls parameterized constructor w/ some default values
	 */
	public HtmlBuilder() {
		this("", "", new ArrayList<String>(), new ArrayList<String>(), new HashMap<String, String>(), "");
	}
	
	/**
	 * Parameterized constructor initializes values so that no fields are null 
	 * @param templateFileLocation
	 * @param htmlTemplate
	 * @param replaceHtml
	 * @param modelFields
	 * @param htmlMapModelFields
	 * @param htmlStr
	 */
	public HtmlBuilder(String templateFileLocation, String htmlTemplate, ArrayList<String> replaceHtml, ArrayList<String> modelFields, HashMap<String, String> htmlMapModelFields, String htmlStr) {
		setTemplateFileLocation(templateFileLocation);
		setHtmlTemplate(htmlTemplate);
		setReplaceHtml(replaceHtml);
		setModelFields(modelFields); 
		setHtmlMapModelFields(htmlMapModelFields);
		setHtmlStr(htmlStr);
	}
	
	/**
	 * Converts model into concrete class's designated class type & stores it in concrete class
	 * @param model
	 * @return false if conversion fails/model = null. Else, true.
	 */
	protected abstract Boolean setModel(Object model);
	
	/**
	 * From model field that'll be stored in concrete class, extract some designated fields from that model
	 * @return false if model in concrete class = null. Else, true.
	 */
	protected abstract Boolean getDynamicFields();

	/**
	 * Reads from /files folder an html template specified by @param filepath
	 * @param filepath
	 * @return false if error occurs with reading file. Else, true.
	 */
	protected Boolean readTemplate() {
		Scanner fileInput = null;
		try {
			File templateFile = new File(templateFileLocation);
			fileInput = new Scanner(templateFile);
			while(fileInput.hasNextLine()) {
				htmlTemplate += fileInput.nextLine() + "\n"; // Preserve newline in html
			}
			fileInput.close();
			return true;
		}
		catch (IOException error) {
			error.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Fills in a HashMap with keys from replaceHtml mapped to values from modelFields 
	 * @return false if not every replaceHtml can be mapped to a modelField or if HashMap isn't initialized properly/is null
	 */
	protected Boolean mapToHtml() {	
		if (replaceHtml.size() != modelFields.size() || htmlMapModelFields == null) {
			return false;
		}
		for (int i = 0; i < replaceHtml.size(); ++i) {
			htmlMapModelFields.put(replaceHtml.get(i), modelFields.get(i));
		}
		return true;
	}

	/**
	 * First let htmlStr = templateHtml. Now for up to 3 instances if a replaceHtml is found in htmlStr, replace it with modelField
	 * @return false if htmlMapModelFields = null 
	 */
	protected Boolean createHtmlStr() {
		setHtmlStr(getHtmlTemplate()); // Initialize htmlStr as templateHtml
		if (htmlMapModelFields == null) {
			System.err.println("htmlMapModelFields is null. Can't return htmlStr");
			return false;
		}
		for (Map.Entry<String, String> htmlComponent : htmlMapModelFields.entrySet()) {
			htmlStr = StringUtils.replace(htmlStr, htmlComponent.getKey(), htmlComponent.getValue(), 3);
		}
		return true;
	}	
	
	
	// Getters & Setters
	public String getTemplateFileLocation() {
		return templateFileLocation;
	}

	public Boolean setTemplateFileLocation(String templateFileLocation) {
		if (templateFileLocation == null) {
			return false;
		}
		this.templateFileLocation = templateFileLocation;
		return true;
	}

	public String getHtmlTemplate() {
		return htmlTemplate;
	}

	public Boolean setHtmlTemplate(String htmlTemplate) {
		if (htmlTemplate == null) {
			return false;
		}
		this.htmlTemplate = htmlTemplate;
		return true;
	}

	public ArrayList<String> getReplaceHtml() {
		return replaceHtml;
	}

	public Boolean setReplaceHtml(ArrayList<String> replaceHtml) {
		if (replaceHtml == null) {
			return false;
		}
		this.replaceHtml = replaceHtml;
		return true;
	}

	public ArrayList<String> getModelFields() {
		return modelFields;
	}

	public Boolean setModelFields(ArrayList<String> modelFields) {
		if (modelFields == null) {
			return false;
		}
		this.modelFields = modelFields;
		return true;
	}

	public HashMap<String, String> getHtmlMapModelFields() {
		return htmlMapModelFields;
	}

	public Boolean setHtmlMapModelFields(HashMap<String, String> htmlMapModelFields) {
		if (htmlMapModelFields == null) {
			return false;
		}
		this.htmlMapModelFields = htmlMapModelFields;
		return true;
	}

	public Boolean setHtmlStr(String htmlStr) {
		if (htmlStr == null) {
			return false;
		}
		this.htmlStr = htmlStr;
		return true;
	}
	
	public String getHtmlStr() {
		if (htmlStr == "" || htmlStr == null) {
			System.err.println("htmlMapModelFields is empty/null. Can't return htmlStr");
			return null;
		}
		return htmlStr;
	}
}
