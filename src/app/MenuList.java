package app;

import java.util.ArrayList;

class MenuList {
    public ArrayList<MenuOption> menuOptions = new ArrayList<>();

    public MenuList(String[] optionNames, optionBehavior[] optionBehaviors, int[] behaviorValues){
        for (int i = 0; i < optionNames.length; ++i){
            menuOptions.add(new MenuOption(optionNames[i], optionBehaviors[i], behaviorValues[i]));
        }
    }
 
    public void display(){
        for (int i = 0; i < menuOptions.size(); ++i){
            System.out.println(i + " > " + menuOptions.get(i).name);
        }
        System.out.print("\nType the letter corresponding to the menu option to select it: ");
    }
}
