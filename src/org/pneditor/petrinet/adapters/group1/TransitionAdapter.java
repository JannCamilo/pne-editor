package org.pneditor.petrinet.adapters.group1;

import java.util.ArrayList;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.group1.NoExistingObjectException;
import org.pneditor.petrinet.models.group1.Place;
import org.pneditor.petrinet.models.group1.Transition;

/**
 * This class represents an adapter for Petri net transitions. It adapts between a general Petri net transition and a specific
 * implementation in the context of a larger system.
 */
public class TransitionAdapter extends AbstractTransition {

    // The underlying Petri net transition
    private final Transition transition;
    private ArrayList<ArcAdapter> inArcs = new ArrayList<ArcAdapter>();
	private ArrayList<ArcAdapter> outArcs= new ArrayList<ArcAdapter>();
	private boolean isFireble = false;

    /**
     * Constructor for TransitionAdapter. It creates an adapter for a Petri net transition based on the specified label.
     *
     * @param label The label for the transition
     * @throws NoExistingObjectException If there is an issue creating the transition
     */
    public TransitionAdapter(String label) throws NoExistingObjectException {
        super(label);
        // Initializes the underlying Petri net transition with empty lists for input and output arcs
        this.transition = new Transition();
    }

    /**
     * Checks if the transition is a place.
     *
     * @return Always returns false for transitions
     */
    @Override
    public boolean isPlace() {
        return false;
    }

    /**
     * Checks if the transition is enabled, i.e., if it is fireable in the Petri net.
     *
     * @return True if the transition is enabled, false otherwise
     */
    public boolean isEnabled() {
        return this.transition.fireable();
    }

    /**
     * Fires (activates) the transition in the Petri net.
     *
     * @throws NoExistingObjectException If there is an issue firing the transition
     */
    public void fire() throws NoExistingObjectException {
        if (this.getIsFireable()) {
            this.transition.fire();

        }
    }

    /**
     * Gets the underlying Petri net transition.
     *
     * @return The underlying Petri net transition
     */
    public Transition getTransition() {
        return this.transition;
    }

	public void setInArcs(ArrayList<ArcAdapter> inArcs) {
		this.inArcs = inArcs;
	}
	
	public void setOutArcs(ArrayList<ArcAdapter> outArcs) {
		this.outArcs = outArcs;
	}
	
	public void addInArc(ArcAdapter inArc) {
		this.inArcs.add(inArc);
        this.transition.addInArc(inArc.getArc());
	}
	
	public void addOutArc(ArcAdapter outArc) {
		this.outArcs.add(outArc);
        this.transition.addOutArc(outArc.getArc());
	}
	
	public ArrayList<ArcAdapter> getInArcs() {
		return this.inArcs;
	}
	
	public ArrayList<ArcAdapter> getOutArcs() {
		return this.outArcs;
	}

    public boolean getIsFireable() {
        return this.isFireble;
    }

    public void setIsFireable(boolean isFireable) {
        this.isFireble = isFireable;
    }
}