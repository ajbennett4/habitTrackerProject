package habits;

import java.time.LocalDate;

/**
 * A habit the user is trying to quit, with its current streak.
 */
public class Habit {
	private final HabitCalculation habitCalculation;
	private String habitName;
	private long streakLength;

	/**
	 * Creates a habit whose quit date is today and whose streak is zero.
	 *
	 * @param habitName display name of the habit
	 */
	public Habit(String habitName) {
		habitCalculation = new HabitCalculation(LocalDate.now());
		this.habitName = habitName;
		this.streakLength = 0;
		
		//required setup for HabitCalculation
		habitCalculation.lifeSavedCounter();
	}
	
	/**
	 * @return the habit's name
	 */
	public String getHabitName() {
		return this.habitName;
	}

	/**
	 * Resets the streak to zero and moves the quit date to today.
	 */
	public void breakStreak() {
		this.streakLength = 0;
		this.changeQuitDate(LocalDate.now());
	}

	/**
	 * Recalculates the streak length as the number of days since the quit date.
	 */
	public void incrementStreak() {
		this.streakLength = this.habitCalculation.quitStreakCounter();
	}
	
	private void changeQuitDate(LocalDate newDate) {
		this.habitCalculation.setQuitDate(newDate);
	}
	
	/**
	 * @return the streak length in days, as of the last {@link #incrementStreak()}
	 */
	public long getStreakLength() {
		return this.streakLength;
	}

	/**
	 * @return the calculator holding this habit's quit date and derived statistics
	 */
	public HabitCalculation getCalculation() {
		return this.habitCalculation;
	}
	
}
