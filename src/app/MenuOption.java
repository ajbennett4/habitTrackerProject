package app;

class MenuOption {
	
	String name;
    optionBehavior behavior;
    int behaviorValue;

    public MenuOption(String nam, optionBehavior behave, int behaveValue){
        this.name = nam;
        this.behavior = behave;
        this.behaviorValue = behaveValue;
    }

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
