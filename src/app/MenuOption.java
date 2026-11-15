package app;

/**
 * One selectable entry in a {@link MenuList}.
 */
class MenuOption {

	/** Text displayed for this option. */
	String name;
	/** What happens when the option is selected. */
    optionBehavior behavior;
    /** Argument for {@link #behavior}, e.g. the index of the menu to load. */
    int behaviorValue;

    /**
     * Creates an option.
     *
     * @param nam         text to display
     * @param behave      action performed on selection
     * @param behaveValue argument for the action
     */
    public MenuOption(String nam, optionBehavior behave, int behaveValue){
        this.name = nam;
        this.behavior = behave;
        this.behaviorValue = behaveValue;
    }

    /**
     * Performs the option's behavior.
     *
     * @return the index of the menu to show next, or {@code -1} to terminate the program
     */
    public int select(){
        switch (behavior){
            case loadMenu:
                return behaviorValue;
            case terminateProgram:
                return -1;
            default: return 0;
        }
    }
}
