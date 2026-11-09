package habits;

import java.time.LocalDate;

public class Habit {
	private final HabitCalculation habitCalculation;
	private String habitName;
	private long streakLength;
	
	public Habit(String habitName) {
		habitCalculation = new HabitCalculation(LocalDate.now());
		this.habitName = habitName;
		this.streakLength = 0;
		
		//required setup for HabitCalculation
		habitCalculation.lifeSavedCounter();
	}
	
	public String getHabitName() {
		return this.habitName;
	}
	
	public void breakStreak() {
		this.streakLength = 0;
		this.changeQuitDate(LocalDate.now());
	}
	
	public void incrementStreak() {
		this.streakLength = this.habitCalculation.quitStreakCounter();
	}
	
	private void changeQuitDate(LocalDate newDate) {
		this.habitCalculation.setQuitDate(newDate);
	}
	
	public long getStreakLength() {
		return this.streakLength;
	}
	
	public HabitCalculation getCalculation() {
		return this.habitCalculation;
	}
	
}
