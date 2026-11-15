//TODO add a cleanup method that runs on terminate state.
//cleanup() : closes scanners and runs SaveFile.closeFile()

package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import fileio.TextEncoder;
import habits.Habit;

/**
 * Console menu system and program entry point.
 */
public class Menu {

    /** All menu screens; index 0 is the main menu. */
    public ArrayList<MenuList> menus = new ArrayList<>();
    int currentMenu = 0;

    /**
     * Builds the menu screens: main menu, habits, alternative activities and resources.
     */
    public void populateMenus(){
        {String menuNames[] = {"Habit Tracker", "Alternative Activities", "Additional Resources", "Exit Program"};
        optionBehavior menuBehaviors[] = {optionBehavior.loadMenu, optionBehavior.loadMenu, optionBehavior.loadMenu, optionBehavior.terminateProgram};
        int behaviorValues[] = {1, 2, 3, 0};
        menus.add(new MenuList(menuNames, menuBehaviors, behaviorValues));}

        {String menuNames[] = {"Habit 1", "Habit 2", "Habit 3", "Return to Main Menu"};
        optionBehavior menuBehaviors[] = {optionBehavior.loadMenu, optionBehavior.loadMenu, optionBehavior.loadMenu, optionBehavior.loadMenu};
        int behaviorValues[] = {0, 0, 0, 0};
        menus.add(new MenuList(menuNames, menuBehaviors, behaviorValues));}

        {String menuNames[] = {"Activity 1", "Activity 2", "Activity 3", "Return to Main Menu"};
        optionBehavior menuBehaviors[] = {optionBehavior.loadMenu, optionBehavior.loadMenu, optionBehavior.loadMenu, optionBehavior.loadMenu};
        int behaviorValues[] = {0, 0, 0, 0};
        menus.add(new MenuList(menuNames, menuBehaviors, behaviorValues));}

        {String menuNames[] = {"Resource 1", "Resource 2", "Resource 3", "Return to Main Menu"};
        optionBehavior menuBehaviors[] = {optionBehavior.loadMenu, optionBehavior.loadMenu, optionBehavior.loadMenu, optionBehavior.loadMenu};
        int behaviorValues[] = {0, 0, 0, 0};
        menus.add(new MenuList(menuNames, menuBehaviors, behaviorValues));}
    }

    /**
     * Program entry point. Saves a test habit, then runs the menu loop until the user exits.
     *
     * @param args unused
     * @throws IOException if the save file cannot be written
     */
    public static void main(String[] args) throws IOException{
    	TextEncoder encoder = new TextEncoder();
    	Habit testHabit = new Habit("quit smoking");

    	encoder.beginObject(testHabit.getHabitName(), testHabit)
    	       .addField("DaysLen", String.valueOf(testHabit.getStreakLength()))
    	       .endObject();
    	encoder.save();
    	
        Menu App = new Menu();

        App.populateMenus();

        Scanner scan = new Scanner(System.in);

        boolean terminate = false;

        while (!terminate){
            App.menus.get(App.currentMenu).display();

            int choice = scan.nextInt();
            int returned = App.menus.get(App.currentMenu).menuOptions.get(choice).select();

            System.out.println();

            if (returned == -1) terminate = true;
            else App.currentMenu = returned;
        }
        scan.close();
    }
}
