package fileio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Wrapper for Java IO. Provides the interface with the file system.
 *
 * <p>Output goes to a temporary file first; {@link #commit()} then replaces the
 * real save file, so a failed save never corrupts existing data.
 *
 * <p>Package-private: use {@link TextEncoder} from outside this package.
 */
class SaveFile {

	/** Name of the save file, relative to the working directory. */
	public final String DEFAULT_SAVE_NAME = "HabitTrackSave.txt";

	private final Path path;      // the real save file
	private final Path tempPath;  // changes are written here until commit()
	private BufferedWriter writer;

	boolean fileExists;

	/**
	 * Opens the temporary file for writing and records whether a save file already exists.
	 *
	 * @throws IOException if the temporary file cannot be created
	 */
	public SaveFile() throws IOException {
		this.path = Path.of(DEFAULT_SAVE_NAME);
		this.tempPath = Path.of(DEFAULT_SAVE_NAME + ".tmp");

		// Does the save file already exist?
		this.fileExists = Files.exists(this.path);

		// Write to the temp file; the real file is untouched until commit()
		this.writer = Files.newBufferedWriter(this.tempPath);
	}

	private void closeFile() throws IOException {
		this.writer.close();
	}

	/**
	 * Flushes the temp file, closes it, and replaces the real save file with it.
	 *
	 * @throws IOException if flushing, closing or moving the file fails
	 */
	public void commit() throws IOException {
		this.writer.flush();
		this.closeFile();

		Files.move(this.tempPath, this.path, StandardCopyOption.REPLACE_EXISTING);
		this.fileExists = true;
	}

	/**
	 * Returns the writer for the temporary file. Used to give other classes access to the stream.
	 *
	 * @return the open writer; closed after {@link #commit()}
	 */
	public BufferedWriter openFileStream() {
		return this.writer;
	}
}
