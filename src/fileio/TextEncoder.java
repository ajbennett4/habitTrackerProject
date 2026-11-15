package fileio;

import java.io.IOException;
import java.util.Map;

/**
 * Public interface for saving data to the habit tracker's text save file.
 *
 * <p>Uses composition: a {@code TextEncoder} <em>has a</em> {@link SaveFile}
 * (file handling) and builds one {@link Datablock} at a time (formatting).
 * Both components are package-private, so callers only ever deal with this class.
 *
 * <p>Typical use:
 * <pre>{@code
 * TextEncoder encoder = new TextEncoder();
 * encoder.beginObject(habit.getHabitName(), habit)
 *        .addField("DaysLen", String.valueOf(habit.getStreakLength()))
 *        .endObject();
 * encoder.save();
 * }</pre>
 *
 * <p>Nothing replaces the real save file until {@link #save()} is called.
 */
public class TextEncoder {

	private final SaveFile saveFile;
	private Datablock currentBlock;

	/**
	 * Creates an encoder that writes to the default save file.
	 *
	 * @throws IOException if the temporary save file cannot be opened
	 */
	public TextEncoder() throws IOException {
		this.saveFile = new SaveFile();
	}

	/**
	 * Starts a new object block. Any block already open is written out first.
	 *
	 * @param name the object's name, written as the block's first line
	 * @param type the object being saved; its simple class name is recorded as the block's type
	 * @return this encoder, so calls can be chained
	 * @throws IOException if the previous block cannot be written
	 */
	public TextEncoder beginObject(String name, Object type) throws IOException {
		endObject();
		this.currentBlock = new Datablock(this.saveFile.openFileStream(), name, type);
		return this;
	}

	/**
	 * Adds or overwrites a field in the current block.
	 *
	 * @param field the field name
	 * @param value the field value
	 * @return this encoder, so calls can be chained
	 * @throws IllegalStateException if no block is open (see {@link #beginObject})
	 */
	public TextEncoder addField(String field, String value) {
		requireOpenBlock().put(field, value);
		return this;
	}

	/**
	 * Adds or overwrites every entry of the map as a field in the current block.
	 *
	 * @param fields field names mapped to their values
	 * @return this encoder, so calls can be chained
	 * @throws IllegalStateException if no block is open (see {@link #beginObject})
	 */
	public TextEncoder addFields(Map<String, String> fields) {
		Datablock block = requireOpenBlock();
		for (Map.Entry<String, String> entry : fields.entrySet()) {
			block.put(entry.getKey(), entry.getValue());
		}
		return this;
	}

	/**
	 * Writes the current block to the temporary save file and closes it.
	 * Does nothing if no block is open.
	 *
	 * @return this encoder, so calls can be chained
	 * @throws IOException if the block cannot be written
	 */
	public TextEncoder endObject() throws IOException {
		if (this.currentBlock != null) {
			this.currentBlock.write();
			this.currentBlock = null;
		}
		return this;
	}

	/**
	 * Writes any open block, then replaces the real save file with the new contents.
	 * The encoder cannot be used after this call.
	 *
	 * @throws IOException if writing or replacing the save file fails
	 */
	public void save() throws IOException {
		endObject();
		this.saveFile.commit();
	}

	private Datablock requireOpenBlock() {
		if (this.currentBlock == null) {
			throw new IllegalStateException("No object open; call beginObject first.");
		}
		return this.currentBlock;
	}
}
