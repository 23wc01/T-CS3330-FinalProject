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
			System.out.println("(Refresh folder if html file doesn't appear immidiately)\n");
			return true;
		}
		else {
			System.out.println("Error in writing to '" + outputFilepath + "'");
			return false;
		}
	}
	/**
	 * Displays suggestion to user if the setup_preference wasn't done before 'download_recommended playlist' command
	 * @return false to indicate error
	 */
	public Boolean displayMissingStepsError() {
		System.err.println("Try 'setup_preference' first!");
		return false;
	}
}
