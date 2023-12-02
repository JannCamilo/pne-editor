package org.pneditor.petrinet.adapters.group1;

import java.util.ArrayList;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.group1.NoExistingObjectException;
import org.pneditor.petrinet.models.group1.Transition;

/**
 * This class represents an adapter for Petri net transitions. It adapts between a general Petri net transition and a specific
 * implementation in the context of a larger system.
 */
public class TransitionAdapter extends AbstractTransition {

    // The underlying Petri net transition
    private final Transition transition;

    /**
     * Constructor for TransitionAdapter. It creates an adapter for a Petri net transition based on the specified label.
     *
     * @param label The label for the transition
     * @throws NoExistingObjectException If there is an issue creating the transition
     */
    public TransitionAdapter(String label) throws NoExistingObjectException {
        super(label);
        // Initializes the underlying Petri net transition with empty lists for input and output arcs
        this.transition = new Transition(new ArrayList<>(), new ArrayList<>());
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
        this.transition.fire();
    }

    /**
     * Gets the underlying Petri net transition.
     *
     * @return The underlying Petri net transition
     */
    public Transition getTransition() {
        return this.transition;
    }
}