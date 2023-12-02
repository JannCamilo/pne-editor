package org.pneditor.petrinet.adapters.group1;

import java.util.ArrayList;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.group1.ExistingObjectException;
import org.pneditor.petrinet.models.group1.NoExistingObjectException;

/**
 * This class serves as an adapter for a Petri Net interface. It adapts between a general Petri Net interface and a specific
 * implementation in the context of a larger system.
 */
public class PetriNetInterfaceAdapter extends PetriNetInterface {

    // Lists to store the adapted Petri Net elements
    private final ArrayList<ArcAdapter> arcs = new ArrayList<ArcAdapter>();
    private final ArrayList<PlaceAdapter> places = new ArrayList<PlaceAdapter>();
    private final ArrayList<TransitionAdapter> transitions = new ArrayList<TransitionAdapter>();

    /**
     * Adds a place to the Petri Net.
     *
     * @return The added place
     */
    @Override
    public AbstractPlace addPlace() {
        PlaceAdapter place = new PlaceAdapter(null);
        places.add(place);
        return place;
    }

    /**
     * Adds a transition to the Petri Net.
     *
     * @return The added transition
     */
    @Override
    public AbstractTransition addTransition() {
        TransitionAdapter transition;
        try {
            transition = new TransitionAdapter(null);
            transitions.add(transition);
            return transition;
        } catch (NoExistingObjectException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Adds a regular arc to the Petri Net.
     *
     * @param source      The source node of the arc
     * @param destination The destination node of the arc
     * @return The added regular arc
     */
    @Override
    public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
        ArcAdapter arc;
        try {
            arc = new ArcAdapter(ArcType.Regular, source, destination);
            arcs.add(arc);
            return arc;
        } catch (ExistingObjectException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Adds an inhibitory arc to the Petri Net.
     *
     * @param place      The place as the source node of the arc
     * @param transition The transition as the destination node of the arc
     * @return The added inhibitory arc
     */
    @Override
    public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
            throws UnimplementedCaseException {
        ArcAdapter arc;
        try {
            arc = new ArcAdapter(ArcType.Inhibitory, place, transition);
            arcs.add(arc);
            return arc;
        } catch (ExistingObjectException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Adds a reset arc to the Petri Net.
     *
     * @param place      The place as the source node of the arc
     * @param transition The transition as the destination node of the arc
     * @return The added reset arc
     */
    @Override
    public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
            throws UnimplementedCaseException {
        ArcAdapter arc;
        try {
            arc = new ArcAdapter(ArcType.Reset, place, transition);
            arcs.add(arc);
            return arc;
        } catch (ExistingObjectException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Removes a place from the Petri Net.
     *
     * @param place The place to be removed
     */
    @Override
    public void removePlace(AbstractPlace place) {
        places.remove(place);
    }

    /**
     * Removes a transition from the Petri Net.
     *
     * @param transition The transition to be removed
     */
    @Override
    public void removeTransition(AbstractTransition transition) {
        transitions.remove(transition);
    }

    /**
     * Removes an arc from the Petri Net.
     *
     * @param arc The arc to be removed
     */
    @Override
    public void removeArc(AbstractArc arc) {
        arcs.remove(arc);
    }

    /**
     * Checks if a transition is enabled in the Petri Net.
     *
     * @param transition The transition to be checked
     * @return True if the transition is enabled, false otherwise
     * @throws ResetArcMultiplicityException If there is an issue checking the transition's enablement
     */
    @Override
    public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
        for (TransitionAdapter t : transitions) {
            if (t.equals(transition)) {
                return t.isEnabled();
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Fires (activates) a transition in the Petri Net.
     *
     * @param transition The transition to be fired
     * @throws ResetArcMultiplicityException If there is an issue firing the transition
     */
    @Override
    public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
        for (TransitionAdapter t : transitions) {
            if (t.equals(transition)) {
                try {
                    t.fire();
                } catch (NoExistingObjectException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                System.out.println("Transition not implemented");
            }
        }
    }
}