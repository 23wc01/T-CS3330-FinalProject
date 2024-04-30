package edu.mu.finalproject.view;

public class DownloadPlaylistView {
	/**
	 * Displays playlist being downloaded to @param outputFilepath. Returns false if parameters were null or if writeIsSuccess was false
	 * @param outputFilepath
	 * @param writeIsSuccess
	 * @return
	 */
	public Boolean displayDownloadMessage(String outputFilepath, Boolean writeIsSuccess) {
		if (outputFilepath == null || writeIsSuccess == null) {
			System.out.println("Output file path or writeIsSuccess was uninitialized.");
			return false;
		}
		if (writeIsSuccess) {
			System.out.println("Saved playlist's html file to '" + outputFilepath + "'");
			return true;
		}
		else {
			System.out.println("Error in writing to '" + outputFilepath + "'");
			return false;
		}
	}
	public Boolean displayChangeToSetup() {
		System.out.println("Hmmmm...\nCan't download recommended playlist unless we know your preference.\n\n");
		return true;
	}
}
