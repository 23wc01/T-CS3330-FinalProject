package edu.mu.finalproject.view;

public class DownloadPlaylistView {
	public Boolean displayDownloadMessage(String outputFilepath, Boolean writeIsSuccess) {
		if (writeIsSuccess) {
			System.out.println("Saved playlist's html file to '" + outputFilepath + "'");
			return true;
		}
		else {
			System.out.println("Error in writing to '" + outputFilepath + "'");
			return false;
		}
		
	}
}
