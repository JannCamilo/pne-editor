package org.pneditor.petrinet.models.group1;

import java.util.ArrayList;
import java.util.List;

/**
 * The 'PetriNetwork' class represents a Petri net with places, transitions, and arcs.
 * It implements the IPetriNetwork interface, providing methods to manipulate and analyze the network.
 */
public class PetriNetwork implements IPetriNetwork {
	
	private final ArrayList<Place> places = new ArrayList<Place>();
	private final ArrayList<Transition> transitions = new ArrayList<Transition>();
	private final ArrayList<Arc> arcs = new ArrayList<Arc>();
	
	protected int placePosition = 1;
	private int transitionPosition = 1;
	private int arcPosition = 1;
	
	protected String s = " -- ";
	
	/**
	 * Constructs a new PetriNetwork with specified lists of places, transitions, and arcs.
	 * @param places The list of places in the Petri network.
	 * @param transitions The list of transitions in the Petri network.
	 * @param arcs The list of arcs in the Petri network.
	 */
	public PetriNetwork(ArrayList<Place> places, ArrayList<Transition> transitions, ArrayList<Arc> arcs) {
		
		for (Place p : places) {
			  this.addPlace(p);
		}
		
		for (Transition t : transitions) {
			  this.addTransition(t);
		}
		
		for (Arc a : arcs) {
			  this.addArc(a);
		}
	}

	@Override
	public void fireAll() throws NoExistingObjectException {
		// TODO Auto-generated method stub
        for (Transition t : this.transitions) {
        	t.fire();
        }		
	}

	@Override
	public void addPlace(Place place) {
		place.setId("P_"+this.placePosition);
		this.placePosition++;
		this.places.add(place);
	}

	@Override
	public void addArc(Arc arc) {
		arc.setId("A_"+this.arcPosition);
		this.arcPosition++;
		this.arcs.add(arc);
		
	}

	@Override
	public void addTransition(Transition transition) {
		transition.setId("T_"+this.transitionPosition);
		this.transitionPosition++;
		this.transitions.add(transition);
		
	}

	@Override
	public void deletePlace(Place place) {
		// TODO Auto-generated method stub
		this.places.remove(place);
		
	}

	@Override
	public void deleteArc(Arc arc) {
		// TODO Auto-generated method stub
		this.arcs.remove(arc);
		
	}

	@Override
	public void deleteTransition(Transition transition) {
		this.transitions.remove(transition);
		
	}

	@Override
	public void fireTransition(Transition transition) throws NoExistingObjectException {
		transition.fire();
	}
	
	/**
	 * Retrieves the list of places in the Petri network.
	 * @return The list of places.
	 */
	public List<Place> getPlaces() {
		return this.places;	
	}
	
	/**
	 * Retrieves the list of transitions in the Petri network.
	 * @return The list of transitions.
	 */
	public List<Transition> getTransitions() {
		return this.transitions;
	}	
		
	/**
	 * Retrieves the list of arcs in the Petri network.
	 * @return The list of arcs.
	 */
	public List<Arc> getArcs() {
		return this.arcs;
	}
	
	/**
	 * Returns a string representation of the Petri network.
	 * @return A string representing the Petri network.
	 */
	public String toString() {
		
		String res = "";
		
		res += "----------Places---------- \n";

		for (Place p : this.places) {
			
			Arc inArc = p.getInArc();
			Arc outArc = p.getOutArc();
			
			res += ((inArc != null ? inArc.toString() + this.s : "") + p.toString() + (outArc != null ? this.s + outArc.toString() : ""));
			res += "\n";
		}
		
		res += "--------Transitions------- \n";
		
		for (Transition t : this.transitions) {
			
			ArrayList<Arc> inArcs = t.getInArcs();
			ArrayList<Arc> outArcs = t.getOutArcs();
			
			for (Arc inArc : inArcs) {
				for (Arc outArc: outArcs) {
					res += ((inArc != null ? inArc.toString() + this.s : "") + t.toString() + (outArc != null ? this.s + outArc.toString() : ""));
					res += "\n";
				}
			}

		}
		
		res += "-----------Arcs----------- \n";
		
		for (Arc a : this.arcs) {
						
			Object start;
			try {
				start = a.getStart();
				Object end = a.getEnd();
				
				res += ((start != null ? start.toString() + this.s : "") + a.toString() + (end != null ? this.s + end.toString() : ""));
				res += "\n";
				
			} catch (NoExistingObjectException e) {
			}
					
		}
		
		return res;
	}
	
	/**
	 * @return the placePosition
	 */
	public int getPlacePosition() {
		return placePosition;
	}

	/**
	 * @return the transitionPosition
	 */
	public int getTransitionPosition() {
		return transitionPosition;
	}

	/**
	 * @return the arcPosition
	 */
	public int getArcPosition() {
		return arcPosition;
	}
	
	public static void main(String[] args) {
//		try {
//			//PetriNetwork pn1 = new PetriNetwork();
//			
//			Place p1 = new Place(2);
//			Arc a1 = new Arc();
//			Transition t1 = new Transition();
//			Arc a2 = new Arc();
//			Place p2 = new Place(2);
//			
//			p1.setOutArc(a1);
//			a1.setStart(p1);
//			a1.setEnd(t1);
//			t1.addInArc(a1);
//			t1.addOutArc(a2);
//			a2.setStart(t1);
//			a2.setEnd(p2);
//			p2.setInArc(a2);
//			a1.setWeight(2);	
//			a2.setWeight(2);
//			
//			/*pn1.addPlace(p1);
//			pn1.addArc(a1);
//			pn1.addTransition(t1);
//			pn1.addArc(a2);		
//			pn1.addPlace(p2);
//			
//			System.out.println(pn1.toString());*/
//
//			ArrayList<Place> places = new ArrayList<Place>();
//			ArrayList<Transition> transitions = new ArrayList<Transition>();
//			ArrayList<Arc> arcs = new ArrayList<Arc>();
//
//			places.add(p1);
//			places.add(p2);
//			transitions.add(t1);
//			arcs.add(a1);
//			arcs.add(a2);
//			PetriNetwork PN1 = new PetriNetwork(places, transitions, arcs);
//			System.out.println(PN1.toString());
//
//			System.out.println("\n------Before to do Fire------");
//			System.out.println("Number of tokens in "+ p1 + " : " + p1.getNbTokens());
//			System.out.println("Number of tokens in "+ p2 + " : " + p2.getNbTokens());
//
//			t1.fire(); // Do Fire
//			System.out.println("\n------After to do Fire-------");
//			System.out.println("Number of tokens in "+ p1 + " : " + p1.getNbTokens());
//			System.out.println("Number of tokens in "+ p2 + " : " + p2.getNbTokens());
//			Place p3 = new Place(-5);
//			
//		} catch (Exception e) {
//			System.out.println("\nError: " + e.getMessage());
//		}
	}
}
	


	

