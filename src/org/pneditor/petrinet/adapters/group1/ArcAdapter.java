package org.pneditor.petrinet.adapters.group1;
import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.adapters.group1.ArcAdapter;
import org.pneditor.petrinet.models.group1.Arc;
import org.pneditor.petrinet.models.group1.DrainerArc;
import org.pneditor.petrinet.models.group1.ExistingObjectException;
import org.pneditor.petrinet.models.group1.NegativeTokenInsertedException;
import org.pneditor.petrinet.models.group1.NoExistingObjectException;
import org.pneditor.petrinet.models.group1.ZeroArc;

/**
 * This class represents an adapter for Petri net arcs. It adapts between a general Petri net arc and a specific
 * implementation in the context of a larger system.
 */
public class ArcAdapter extends AbstractArc {

    // The underlying Petri net arc
    private Arc arc;

    // Start and end places
    private PlaceAdapter startPlace;
    private PlaceAdapter endPlace;

    // Start and end transitions
    private TransitionAdapter startTransition;
    private TransitionAdapter endTransition;

    // Type of the arc
    private final ArcType arcType;

    /**
     * Constructor for ArcAdapter. It creates an adapter for a Petri net arc based on the specified type, start, and end nodes.
     *
     * @param type  The type of the arc (Regular, Reset, Inhibitory)
     * @param start The start node of the arc
     * @param end   The end node of the arc
     * @throws ExistingObjectException If an object already exists
     */
    public ArcAdapter(ArcType type, AbstractNode start, AbstractNode end) throws ExistingObjectException {
        switch (type) {
            case Regular: {
                this.arc = new Arc();
                this.arc.setStart(start);
                this.arc.setEnd(end);

                this.setStart(start);
                this.setEnd(end);
            }
            break;
            case Reset: {
                this.arc = new DrainerArc();
                this.arc.setStart(start);
                this.arc.setEnd(end);

                this.setStart(start);
                this.setEnd(end);
            }
            break;
            case Inhibitory: {
                this.arc = new ZeroArc();
                this.arc.setStart(start);
                this.arc.setEnd(end);

                this.setStart(start);
                this.setEnd(end);
            }
            break;
        }
        this.arcType = type;
    }

    /**
     * Sets the start node of the arc.
     *
     * @param start The start node to be set
     * @throws ExistingObjectException If the start place or start transition already exists
     */
    public void setStart(Object start) throws ExistingObjectException {
        if (this.startPlace != null || this.startTransition != null) {
            throw new ExistingObjectException();
        } else if (start.getClass() == PlaceAdapter.class) {
            PlaceAdapter startPlaceAdapter = (PlaceAdapter) start;
            this.startPlace = startPlaceAdapter;
            startPlaceAdapter.setOutArc(this);
            this.arc.setStart(startPlaceAdapter.getPlace());

        } else if (start.getClass() == TransitionAdapter.class) {
            TransitionAdapter startTransitionAdapter = (TransitionAdapter) start;
            this.startTransition = startTransitionAdapter;
            startTransitionAdapter.addOutArc(this);
            this.arc.setStart(startTransitionAdapter.getTransition());
        }
    }

    /**
     * Sets the end node of the arc.
     *
     * @param end The end node to be set
     * @throws ExistingObjectException If the end place or end transition already exists
     */
    public void setEnd(Object end) throws ExistingObjectException {
        if (this.endPlace != null || this.endTransition != null) {
            throw new ExistingObjectException();
        } else if (end.getClass() == PlaceAdapter.class) {
            PlaceAdapter endPlaceAdapter = (PlaceAdapter) end;
            this.endPlace = endPlaceAdapter;
            this.arc.setEnd(endPlaceAdapter.getPlace());
            endPlaceAdapter.setInArc(this);
        } else if (end.getClass() == TransitionAdapter.class) {
            TransitionAdapter endTransitionAdapter = (TransitionAdapter) end;
            this.endTransition = endTransitionAdapter;
            this.arc.setEnd(endTransitionAdapter.getTransition());
            endTransitionAdapter.addInArc(this);
        }
    }

    /**
     * Gets the start node of the arc.
     *
     * @return The start node
     * @throws NoExistingObjectException If no start place or start transition exists
     */
    public Object getStart() throws NoExistingObjectException {
        if (this.startTransition == null && this.startPlace == null) {
            throw new NoExistingObjectException();
        }

        if (this.startPlace != null) {
            return this.startPlace;
        } else {
            return this.startTransition;
        }
    }

    /**
     * Gets the end node of the arc.
     *
     * @return The end node
     * @throws NoExistingObjectException If no end place or end transition exists
     */
    public Object getEnd() throws NoExistingObjectException {
        if (this.endTransition == null && this.endPlace == null) {
            throw new NoExistingObjectException();
        }

        if (this.endPlace != null) {
            return this.endPlace;
        } else {
            return this.endTransition;
        }
    }

    /**
     * Gets the underlying Petri net arc.
     *
     * @return The underlying Petri net arc
     */
    public Arc getArc() {
        return arc;
    }

    /**
     * Gets the source node of the arc.
     *
     * @return The source node
     */
    @Override
    public AbstractNode getSource() {
        try {
            return (AbstractNode) this.getStart();
        } catch (NoExistingObjectException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the destination node of the arc.
     *
     * @return The destination node
     */
    @Override
    public AbstractNode getDestination() {
        try {
            return (AbstractNode) this.getEnd();
        } catch (NoExistingObjectException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if two arcs are equal based on their source and destination nodes.
     *
     * @param o The object to compare with
     * @return True if the arcs are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        ArcAdapter arc = (ArcAdapter) o;
        if (this.getSource() == arc.getSource() && this.getDestination() == arc.getDestination()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the arc is of type Reset.
     *
     * @return True if the arc is of type Reset, false otherwise
     */
    @Override
    public boolean isReset() {
        return arcType == ArcType.Reset;
    }

	/**
     * Checks if the arc is of type Regular.
     *
     * @return True if the arc is of type Regular, false otherwise
     */
    @Override
    public boolean isRegular() {
        return arcType == ArcType.Regular;
    }

    /**
     * Checks if the arc is of type Inhibitory.
     *
     * @return True if the arc is of type Inhibitory, false otherwise
     */
    @Override
    public boolean isInhibitory() {
        return arcType == ArcType.Inhibitory;
    }

    /**
     * Gets the multiplicity (weight) of the arc.
     *
     * @return The multiplicity (weight) of the arc
     * @throws ResetArcMultiplicityException If there is an issue retrieving the multiplicity
     */
    @Override
    public int getMultiplicity() throws ResetArcMultiplicityException {
        return arc.getWeight();
    }

    /**
     * Sets the multiplicity (weight) of the arc.
     *
     * @param multiplicity The multiplicity (weight) to be set
     */
    @Override
    public void setMultiplicity(int multiplicity) {
        try {
            // Sets the weight of the arc to the specified multiplicity
            arc.setWeight(multiplicity);
        } catch (NegativeTokenInsertedException e) {
            // Handles the case where a negative token insertion exception occurs
            // This is likely to be an exceptional situation, and the stack trace is printed for debugging purposes
            e.printStackTrace();
        }
    }
}