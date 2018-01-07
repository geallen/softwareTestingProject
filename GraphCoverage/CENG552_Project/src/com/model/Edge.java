package com.model;

public class Edge {
	public Node comingFrom;
	public Node goingTo;
	

	public Edge(Node c, Node g) {
		this.comingFrom = c;
		this.goingTo = g;
		this.comingFrom.addGoing(this);
	}

	public Node getComingFrom() {
		return comingFrom;
	}

	public void setComingFrom(Node comingFrom) {
		this.comingFrom = comingFrom;
	}

	public Node getGoingTo() {
		return goingTo;
	}

	public void setGoingTo(Node goingTo) {
		this.goingTo = goingTo;
	}
	
	
	public boolean equals(Edge e) {
		return this.comingFrom.equals(e.getComingFrom()) && this.goingTo.equals(e.getGoingTo());
	}
	
	}
