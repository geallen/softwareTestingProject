package com.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node {
	private List<Edge> goingEdges;
	private Object nodeVar;

	public Node(Object nodeVar) {
		if (nodeVar == null) {
			this.nodeVar = new Object();
		} else {
			this.nodeVar = nodeVar;
		}

		this.goingEdges = new ArrayList<Edge>();
	}

	public List<Edge> getGoingEdges() {
		return goingEdges;
	}

	public void setGoingEdges(List<Edge> goingEdges) {
		this.goingEdges = goingEdges;
	}

	public Object getNodeVar() {
		return nodeVar;
	}

	public void setNodeVar(Object nodeVar) {
		this.nodeVar = nodeVar;
	}

	public String toString() {
		return this.nodeVar.toString();
	}

	public boolean equals(Node n) {
		if(n == null){
			return false;
		}
		return this.nodeVar.equals(n.getNodeVar());
	}

	public void addGoing(Edge e) {
		this.goingEdges.add(e);
	}

	public void removeGoing(Edge e) {
		this.goingEdges.remove(e);
	}

	public int sizeOfOutEdges() {
		return this.goingEdges.size();
	}

	public Iterator<Edge> getGoingIterator() {
		return this.goingEdges.iterator();
	}

}
