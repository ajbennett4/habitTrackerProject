package fileio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * One saved object. Collects named fields and writes them to a writer in this format:
 *
 * <pre>
 * {:
 * objectName
 * ClassName
 * [field]:[val]
 * :}
 * </pre>
 *
 * <p>Package-private: use {@link TextEncoder} from outside this package.
 */
class Datablock {

	private final BufferedWriter writer;
	private final String name;
	private final String type;
	// LinkedHashMap keeps fields in insertion order so the file output is stable
	private final Map<String, String> fields = new LinkedHashMap<>();

	/**
	 * Creates an empty block.
	 *
	 * @param writer where {@link #write()} sends the block
	 * @param name   the object's name
	 * @param type   the object being saved; its simple class name is stored as the type
	 */
	public Datablock(BufferedWriter writer, String name, Object type) {
		this.writer = writer;
		this.name = name;
		this.type = type.getClass().getSimpleName();
	}

	/**
	 * Adds or overwrites a field.
	 *
	 * @param field the field name
	 * @param val   the field value
	 * @return this block, so calls can be chained
	 */
	public Datablock put(String field, String val) {
		this.fields.put(field, val);
		return this;
	}

	/**
	 * Writes the block to the writer.
	 *
	 * @throws IOException if the writer fails
	 */
	public void write() throws IOException {
		this.writer.write("{:");
		this.writer.newLine();

		this.writer.write(this.name);
		this.writer.newLine();
		this.writer.write(this.type);
		this.writer.newLine();

		for (Map.Entry<String, String> entry : this.fields.entrySet()) {
			this.writer.write(entry.getKey() + ":" + entry.getValue());
			this.writer.newLine();
		}

		this.writer.write(":}");
		this.writer.newLine();
	}
}
