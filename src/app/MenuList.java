package app;

import java.util.ArrayList;

/**
 * A single screen of the menu: an ordered list of {@link MenuOption}s.
 */
class MenuList {
    /** The options shown on this screen, in display order. */
    public ArrayList<MenuOption> menuOptions = new ArrayList<>();

    /**
     * Builds a menu from three parallel arrays; index {@code i} of each describes option {@code i}.
     *
     * @param optionNames     text shown for each option
     * @param optionBehaviors what each option does when selected
     * @param behaviorValues  argument for each behavior (e.g. the menu index to load)
     */
    public MenuList(String[] optionNames, optionBehavior[] optionBehaviors, int[] behaviorValues){
        for (int i = 0; i < optionNames.length; ++i){
            menuOptions.add(new MenuOption(optionNames[i], optionBehaviors[i], behaviorValues[i]));
        }
    }

    /**
     * Prints the numbered options and the selection prompt to standard output.
     */
    public void display(){
        for (int i = 0; i < menuOptions.size(); ++i){
            System.out.println(i + " > " + menuOptions.get(i).name);
        }
        System.out.print("\nType the letter corresponding to the menu option to select it: ");
    }
}
