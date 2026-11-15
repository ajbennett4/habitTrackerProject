package app;

/**
 * The actions a {@link MenuOption} can perform when selected.
 */
enum optionBehavior {
    /** Switch to the menu whose index is the option's behavior value. */
    loadMenu,
    /** Load data from the save file (not yet implemented). */
    readFromFile,
    /** Write data to the save file (not yet implemented). */
    writeToFile,
    /** End the program. */
    terminateProgram,
    /** Invoke a method (not yet implemented). */
    callMethod
}
