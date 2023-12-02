package org.pneditor.petrinet.models.group1;

/**
 * The 'Arc' class represents a connection between a Place and a Transition in a Petri net.
 * It defines methods for retrieving and setting associated elements, as well as activation status.
 * @param <T>
 */
public class Arc {
	
	private int weight;

	private Place startPlace;
	private Place endPlace;
	
	private Transition startTransition;
	private Transition endTransition;
	
	private String id;
	
	public Arc() {
		this.weight = 1; // weight for default is 1
	};
	
	public void setStart(Object start) throws ExistingObjectException {
		
		if (this.startPlace != null || this.startTransition != null) {
			throw new ExistingObjectException();
			
		} else if (start.getClass() == Place.class) {
			this.startPlace = (Place) start;
			
		} else if (start.getClass() == Transition.class) {
			this.startTransition = (Transition) start;
		}
	};
	
	public void setEnd(Object end) throws ExistingObjectException {
		if (this.endPlace != null || this.endTransition != null) {
			throw new ExistingObjectException();

		} else if (end.getClass() == Place.class) {
			this.endPlace = (Place) end;
			
		} else if (end.getClass() == Transition.class) {
			this.endTransition = (Transition) end;
		}
	};
	
	public Object getStart() throws NoExistingObjectException {
		
		if (this.startTransition == null && this.startPlace == null) {
			throw new NoExistingObjectException();
		};
		
		if (this.startPlace != null) {
			return this.startPlace;
		} else {
			return this.startTransition;
		}
	}

	public Object getEnd() throws NoExistingObjectException {
		
		if (this.endTransition == null && this.endPlace == null) {
			throw new NoExistingObjectException();
		};
		
		if (this.endPlace != null) {
			return this.endPlace;
		} else {
			return this.endTransition;
		}
	}
	
	/**
	 * Returns a string representation of this Arc.
	 * @return A string representing this Arc.
	 */
	public String toString() {
		return this.id != null ? this.id : "";}
	
	/**
	 * Updates the associated Place based on the characteristics of this Arc.
	 */
	public void updatePlace() {
		if (this.endPlace != null ) {
			endPlace.setNbTokens(endPlace.getNbTokens()+weight);
		} if (this.startPlace != null ) {
			startPlace.setNbTokens(startPlace.getNbTokens()-weight);
		}
	}
	
	/**
	 * @return the weight
	 */
	public int getWeight() {
		return this.weight;
	}

	/**
	 * @param weight the weight to set
	 * @throws NegativeTokenInsertedException
	 */
	public void setWeight(int weight) throws NegativeTokenInsertedException {
		if (weight < 0){
			throw new NegativeTokenInsertedException("Not is posible a weight inferior to 0");
		}
		this.weight = weight;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}

