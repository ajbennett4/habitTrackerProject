/**
 * Wrapper for java IO. Provides interface with file system and handles that shenanigans.
 * */

package fileio;

import java.nio.file.Files;
import java.io.BufferedWriter;
import java.nio.file.Path;
import java.io.IOException;

class SaveFile {
	private final Path path;
	private BufferedWriter writer;
	public final String DEFAULT_SAVE_NAME = "HabitTrackSave.txt";
	
	public SaveFile() {
		this.path = Path.of(DEFAULT_SAVE_NAME);
		try {
			this.writer = new BufferedWriter(Files.newBufferedWriter(this.path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void closeFile() throws IOException {
		this.writer.close();
	}
	
	/**
	 * Returns the BufferedWriter object. Used to access writer stream from another class
	 * */
	BufferedWriter openFileStream() {
		return this.writer;
	}
}
