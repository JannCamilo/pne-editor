package org.pneditor.petrinet.models.group1;

/**
 * The 'DrainerArc' class represents a specialized type of Arc that drains tokens from a Place when activated.
 * It extends the basic 'Arc' class and overrides specific methods to implement its behavior.
 */
public class DrainerArc extends Arc {
	
	/**
	 * Constructs a new DrainerArc with specified Place and Transition.
	 */
	public DrainerArc() {
		super();
	}
	
	/**
	 * Determines if this DrainerArc is active (always returns false as it is a draining arc).
	 * @return Always false.
	 */	
	public boolean isActive() {
		return false;
	}	
	
	/**
	 * Updates the associated Place based on the characteristics of this DrainerArc.
	 * (Overrides the method in the parent class)
	 */
	@Override
	public void updatePlace() {}

}
