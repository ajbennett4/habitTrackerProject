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

class HabitCalculation {
	
	private LocalDate quitDate; // Set this variable before calling quitStreakCounter!
	private int cigarettesPerDay; // Set this variable before calling moneySavedCounter and lifeSavedCounter!
	private long quitStreak;
	private double moneySaved;
	private double lifeSaved;
	private String lifeSavedString;
	
	protected HabitCalculation(LocalDate quitStart) {
		this.quitDate = quitStart;
	}
	
	public long quitStreakCounter() {
		LocalDate today = LocalDate.now();
		quitStreak = (ChronoUnit.DAYS.between(quitDate, today));

		return quitStreak;
	}
	
	protected void setQuitDate(LocalDate newDate) {
		this.quitDate = newDate;
	}
	
	// Call quitStreakCounter first!
	public double moneySavedCounter() {
		// Based on $10.15 per pack ( the US average ) and 20 cigarettes a pack
		final double costPerPack = 10.15;
		final int cigarettesPerPack = 20;
		double costPerCigarette = costPerPack / cigarettesPerPack;
		moneySaved = (cigarettesPerDay * costPerCigarette * quitStreak);

		return moneySaved;
			
		}
	
	// Call quitStreakCounter first!
	public double lifeSavedCounter() {
		// based on a rough average of life saved per cigarette not smoked being 20 minutes, more complex calculations can be done if we have time
		final double lifeSavedPerCigarretteQuit = 20.0;
		lifeSaved = (cigarettesPerDay * lifeSavedPerCigarretteQuit * quitStreak);

		return lifeSaved;
			
		}
	
	// Call lifeSavedCounter first!
	// To generate a clean String with time other than minutes
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
