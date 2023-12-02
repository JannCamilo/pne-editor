package org.pneditor.petrinet.models.group1;

/**
 * The 'ZeroArc' class represents a specialized type of Arc that does not contribute to token flow.
 * It extends the basic 'Arc' class and overrides specific methods to implement its behavior.
 */
public class ZeroArc extends Arc {
	
	/**
	 * Constructs a new ZeroArc with specified Place and Transition.
	 */
	public ZeroArc() {
		super();
	}
	
	/**
	 * Determines if this ZeroArc is active (always returns false as it does not contribute to token flow).
	 * @return Always false.
	 */	
	public boolean isActive() {
		return false;
	}

}
