package edu.mu.finalproject.view;

public class DownloadPlaylistView {
	public void displayDownloadMessage(String outputFilepath, Boolean writeIsSuccess) {
		if (writeIsSuccess) {
			System.out.println("Saved playlist's html file to '" + outputFilepath + "'");
		}
		else {
			System.out.println("Error in writing to '" + outputFilepath + "'");
		}
	}
}
