package org.pneditor.petrinet.models.group1;

/**
 * The 'Place' class represents a location in a Petri net where tokens can be stored.
 * It contains methods for managing the number of tokens and associated arcs.
 */
public class Place {
	
	private int nbTokens; // Number of tokens in this place.
	private Arc inArc;
	private Arc outArc;
	private String id = "";
	
	/**
	 * Constructs a new Place with a specified initial number of tokens and associated arcs.
	 * @param nbTokens The initial number of tokens in the place.
	 * @param arcs A list of arcs connected to this place.
	 */	
	public Place(int nbTokens, Arc inArc, Arc outArc) throws NegativeTokenInsertedException{
		if (nbTokens < 0){
			throw new NegativeTokenInsertedException("Not is posible a number of thokens inferior to 0");
		}
		this.nbTokens = nbTokens;
		this.inArc = inArc;
		this.outArc = outArc;
	}
	
	public Place() {
		this.nbTokens = 0; // Number of tokens for default is 0
	};
	
	/**
	 * Constructs a new Place with a specified initial number of tokens.
	 * @param nbTokens The initial number of tokens in the place.
	 * @throws NegativeTokenInsertedException
	 */
	public Place(int nbTokens) throws NegativeTokenInsertedException {
		if (nbTokens < 0){
			throw new NegativeTokenInsertedException("The number of thokens can not be inferior to 0");
		}
		this.nbTokens = nbTokens;
	}
	
	/**
	 * Retrieves the current number of tokens in this place.
	 * @return The number of tokens in this place.
	 */
	public int getNbTokens() {
		return this.nbTokens;
	}
	
	/**
	 * Sets the number of tokens in this place to a specified value.
	 */
	public void setNbTokens(int nbTokens) {
		this.nbTokens = nbTokens;
	}
	
	
	public void setInArc(Arc inArc) {
		this.inArc = inArc;
	}
	
	public void setOutArc(Arc outArc) {
		this.outArc = outArc;
	}
	
	public Arc getInArc() {
		return this.inArc;
	}
	
	public Arc getOutArc() {
		return this.outArc;
	}
	
	/**
	 * Returns a string representation of this place.
	 * @return A string representing this place.
	 */
	public String toString() {
		return this.id;
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
