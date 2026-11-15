//Previously named MathTools.java

// Example use case:
// MathTools tracker = new MathTools();
// tracker.quitDate = LocalDate.of(2026, 11, 6);
// tracker.cigarettesPerDay = 10;
// tracker.quitStreakCounter();
// tracker.moneySavedCounter();
// tracker.lifeSavedCounter();

package habits;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Calculates quit statistics (streak, money saved, life saved) from a quit date.
 *
 * <p>Call order matters: {@link #quitStreakCounter()} first, then
 * {@link #moneySavedCounter()} / {@link #lifeSavedCounter()}, and
 * {@link #lifeSavedTextOutput()} after {@link #lifeSavedCounter()}.
 */
class HabitCalculation {

	private LocalDate quitDate; // Set this variable before calling quitStreakCounter!
	private int cigarettesPerDay; // Set this variable before calling moneySavedCounter and lifeSavedCounter!
	private long quitStreak;
	private double moneySaved;
	private double lifeSaved;
	private String lifeSavedString;

	/**
	 * @param quitStart the date the habit was quit
	 */
	protected HabitCalculation(LocalDate quitStart) {
		this.quitDate = quitStart;
	}

	/**
	 * Computes the streak as whole days between the quit date and today.
	 *
	 * @return the streak length in days
	 */
	public long quitStreakCounter() {
		LocalDate today = LocalDate.now();
		quitStreak = (ChronoUnit.DAYS.between(quitDate, today));

		return quitStreak;
	}
	
	/**
	 * @param newDate the new quit date
	 */
	protected void setQuitDate(LocalDate newDate) {
		this.quitDate = newDate;
	}

	/**
	 * Computes money saved, based on $10.15 per pack of 20 cigarettes (US average).
	 * Call {@link #quitStreakCounter()} first.
	 *
	 * @return money saved in dollars
	 */
	public double moneySavedCounter() {
		// Based on $10.15 per pack ( the US average ) and 20 cigarettes a pack
		final double costPerPack = 10.15;
		final int cigarettesPerPack = 20;
		double costPerCigarette = costPerPack / cigarettesPerPack;
		moneySaved = (cigarettesPerDay * costPerCigarette * quitStreak);

		return moneySaved;
			
		}
	
	/**
	 * Computes life saved, assuming roughly 20 minutes per cigarette not smoked.
	 * Call {@link #quitStreakCounter()} first.
	 *
	 * @return life saved in minutes
	 */
	public double lifeSavedCounter() {
		// based on a rough average of life saved per cigarette not smoked being 20 minutes, more complex calculations can be done if we have time
		final double lifeSavedPerCigarretteQuit = 20.0;
		lifeSaved = (cigarettesPerDay * lifeSavedPerCigarretteQuit * quitStreak);

		return lifeSaved;
			
		}
	
	/**
	 * Formats the life saved using the largest fitting unit (minutes, hours, days or years).
	 * Call {@link #lifeSavedCounter()} first.
	 *
	 * @return a readable string such as {@code "3.50 hours"}
	 */
	public String lifeSavedTextOutput() {
		if (lifeSaved < 60.0) {
			lifeSavedString = (String.format("%.2f",lifeSaved) + " minutes");
		}
		else if (lifeSaved < 1440.0) {
			lifeSavedString = (String.format("%.2f",lifeSaved / 60.0) + " hours");
		}
		
		else if (lifeSaved < 525600.0) {
			lifeSavedString = (String.format("%.2f",lifeSaved / 1440.0) + " days");
		}
		
		else {
			lifeSavedString = (String.format("%.2f",lifeSaved /  525600.0) + " years");
		}
		
		return lifeSavedString;
			
		}
	
}
