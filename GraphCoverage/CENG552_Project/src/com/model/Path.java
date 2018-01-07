package com.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Path {
	public List<Node> path = new ArrayList<Node>();

	public Path() {
	}

	public Path(Edge e) {
		this.path.add(e.getComingFrom());
		this.path.add(e.getGoingTo());
	}

	public Path(Node n) {
		this.path.add(n);
	}

	public Path(Path p) {
		Iterator<Node> nodeiter = p.path.iterator();
		while (nodeiter.hasNext()) {
			Node node = (Node) nodeiter.next();
			this.path.add(node);
		}
	}

	public Iterator<Node> getNodeIterator() {
		return this.path.iterator();
	}

	public List<Edge> getEdgeList() {
		List<Edge> edges = new ArrayList<Edge>();
		if (this.path.size() > 1) {
			for (int i = 0; i < this.path.size() - 1; ++i) {
				edges.add(new Edge((Node) this.path.get(i), (Node) this.path.get(i + 1)));
			}
		}
		return edges;
	}

	public Node getEnd() {
		return (Node) this.path.get(this.path.size() - 1);
	}

	public void extendPath(Path p) {
		Iterator<Node> nodes = p.getNodeIterator();
		while (nodes.hasNext()) {
			this.path.add((Node) nodes.next());
		}
	}

	public void extendPath(Node n) {
		this.path.add(n);
	}

	public boolean containsCycle() {
		return ((Node) this.path.get(0)).equals((Node) this.path.get(this.path.size() - 1));
	}

	public Object copy() {
		Path p = new Path((Node) this.path.get(0));
		for (int i = 1; i < this.path.size(); ++i) {
			p.extendPath((Node) this.path.get(i));
		}
		return p;
	}

	public Node get(int index) {
		return (Node) this.path.get(index);
	}

	public int indexOf(Node n) {
		for (int i = 0; i < this.path.size(); ++i) {
			if (((Node) this.path.get(i)).equals(n)) {
				return i;
			}
		}
		return -1;
	}

	public Path subPath(int i) {
		Node newNode = (Node) this.path.get(i);
		Path subPath = new Path(newNode);

		for (int counter = i + 1; counter < this.path.size(); ++counter) {
			subPath.extendPath((Node) this.path.get(counter));
		}

		return subPath;
	}

	public Path subPath(int begin, int end) {
		Node newNode = (Node) this.path.get(begin);
		Path subPath = new Path(newNode);

		for (int counter = begin + 1; counter < end; ++counter) {
			subPath.extendPath((Node) this.path.get(counter));
		}

		return subPath;
	}

	public int nextIndexOf(Node n, int index) {
		if (index <= -1) {
			return -1;
		} else if (index >= this.path.size()) {
			return -1;
		} else {
			for (int i = index + 1; i < this.path.size(); ++i) {
				if (((Node) this.path.get(i)).equals(n) && i != index) {
					return i;
				}
			}

			return -1;
		}
	}

	public int lastIndexOf(Node n) {
		for (int i = this.path.size() - 1; i > -1; --i) {
			if (((Node) this.path.get(i)).equals(n)) {
				return i;
			}
		}
		return -1;
	}

	public int indexOf(Path p) {
		int iSub = 0;
		int rtnIndex = -1;
		boolean isPat = false;
		int subjectLen = this.path.size();

		for (int i = p.path.size(); !isPat && iSub + i - 1 < subjectLen; ++iSub) {
			if (this.get(iSub).equals(p.get(0))) {
				rtnIndex = iSub;
				isPat = true;

				for (int j = 1; j < i; ++j) {
					if (!this.get(iSub + j).equals(p.get(j))) {
						rtnIndex = -1;
						isPat = false;
						break;
					}
				}
			}
		}
		return rtnIndex;
	}

	public boolean isSubpath(Path s) {
		int iSub = 0;
		int rtnIndex = -1;
		boolean isPat = false;
		int subjectLen = s.path.size();

		for (int i = this.path.size(); !isPat && iSub + i - 1 < subjectLen; ++iSub) {
			if (s.get(iSub).equals(this.get(0))) {
				rtnIndex = iSub;
				isPat = true;

				for (int j = 1; j < i; ++j) {
					if (!s.get(iSub + j).equals(this.get(j))) {
						rtnIndex = -1;
						isPat = false;
						break;
					}
				}
			}
		}
		return rtnIndex != -1;
	}

	public String toString() {
		if (this.path.size() == 0) {
			return "[]";
		} else {
			String result = "[" + this.path.get(0);

			for (int i = 1; i < this.path.size() - 1; ++i) {
				result = result + "," + ((Node) this.path.get(i)).toString();
			}

			return this.path.size() == 1 ? result + "]" : result + "," + this.path.get(this.path.size() - 1) + "]";
		}
	}

	public boolean equals(Path anotherPath) {
		Path tempPath = anotherPath;
		if (this.path.size() != anotherPath.path.size()) {
			return false;
		} else {
			for (int i = 0; i < this.path.size(); ++i) {
				if (!((Node) this.path.get(i)).equals(tempPath.get(i))) {
					return false;
				}
			}
			return true;
		}
	}
}
