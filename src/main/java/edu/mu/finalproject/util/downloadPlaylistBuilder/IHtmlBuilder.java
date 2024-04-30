package edu.mu.finalproject.util.downloadPlaylistBuilder;

public interface IHtmlBuilder {
	Boolean setModelHtml(ModelHtml modelHtml);
	Boolean setModel(Object model);
	Boolean readTemplate();
	Boolean getDynamicFields();
	Boolean mapToHtml();
	Boolean createHtmlStr();
	String getHtmlStr();
}
