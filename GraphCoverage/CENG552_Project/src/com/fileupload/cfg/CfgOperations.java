package com.fileupload.cfg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.model.Edge;
import com.model.Node;
import com.model.Path;

public class CfgOperations {

	public List<Node> starts;
	public List<Node> ends;
	public List<Edge> edges = new ArrayList<Edge>();
	public List<Node> nodes = new ArrayList<Node>();

	public void addInitialNode(Node n) {
		if (this.starts == null) {
			this.starts = new ArrayList<Node>();
		}
		this.starts.add(n);
	}

	public void addEndingNode(Node n) {
		if (this.ends == null) {
			this.ends = new ArrayList<Node>();
		}
		this.ends.add(n);
	}

	public Node createNode(Object addingNode) {
		for (int node = 0; node < this.nodes.size(); ++node) {
			if (((Node) this.nodes.get(node)).getNodeVar().equals(addingNode)) {
				return (Node) this.nodes.get(node);
			}
		}
		Node nodeToadd = new Node(addingNode);
		this.nodes.add(nodeToadd);
		return nodeToadd;
	}

	public Edge createEdge(Node source, Node destination) {
		Iterator<Edge> goingEdges = null;
		Node sourceNode = null;
		Iterator<Node> nodeIter = this.nodes.iterator();
		Edge edge;
		Node node;
		while (nodeIter.hasNext()) {
			node = (Node) nodeIter.next();
			if (node.equals(source)) {
				sourceNode = node;
				goingEdges = node.getGoingIterator();
				break;
			}
		}
		if (goingEdges != null) {
			while (goingEdges.hasNext()) {
				edge = (Edge) goingEdges.next();
				if (destination.equals(edge.goingTo)) {
					return edge;
				}
			}
		}
		node = null;
		if (sourceNode != null) {
			edge = new Edge(sourceNode, destination);
		} else {
			edge = new Edge(source, destination);
		}
		this.edges.add(edge);
		return edge;
	}

	public Edge createEdge(Edge edge) {
		Iterator<Edge> goingEdges = null;
		Node sourceNode = null;
		Iterator<Node> nodeIter = this.nodes.iterator();

		Node nodeVar;
		while (nodeIter.hasNext()) {
			nodeVar = (Node) nodeIter.next();
			if (nodeVar.equals(edge.comingFrom)) {
				sourceNode = nodeVar;
				goingEdges = nodeVar.getGoingIterator();
				break;
			}
		}
		Edge edgeVar;
		if (goingEdges != null) {
			while (goingEdges.hasNext()) {
				edgeVar = (Edge) goingEdges.next();
				if (edge.goingTo.equals(edgeVar.goingTo)) {
					return edgeVar;
				}
			}
		}
		nodeVar = null;
		if (sourceNode != null) {
			edgeVar = new Edge(sourceNode, edge.goingTo);
		} else {
			edgeVar = new Edge(edge.comingFrom, edge.goingTo);
		}
		this.edges.add(edgeVar);
		return edgeVar;
	}

	public void addEdge(Edge e) {
		this.edges.add(e);
	}

	public List<Path> optimization(List<Path> listOfPaths) {
		List<Path> minimumPaths = new ArrayList<Path>();
		Iterator<Edge> edgesIterator = this.edges.iterator();
		List<Path> primePaths = listOfPaths;
		List<Path> finalPathsSet = new ArrayList<Path>();
		ArrayList<Node> leftSide = new ArrayList<Node>();
		ArrayList<Node> rightSide = new ArrayList<Node>();
		Path minimumPath = new Path();
		while (edgesIterator.hasNext()) {
			Edge start1 = (Edge) edgesIterator.next();
			boolean signForLeft = true;
			boolean perfectMatching = true;
			Iterator<Node> counterTwo = leftSide.iterator();
			Node counterOne;
			while (counterTwo.hasNext()) {
				counterOne = (Node) counterTwo.next();
				if (start1.comingFrom.equals(counterOne)) {
					signForLeft = false;
				}
			}
			if (signForLeft) {
				leftSide.add(start1.comingFrom);
			}
			counterTwo = rightSide.iterator();
			while (counterTwo.hasNext()) {
				counterOne = (Node) counterTwo.next();
				if (start1.goingTo.equals(counterOne)) {
					perfectMatching = false;
				}
			}

			if (perfectMatching) {
				rightSide.add(start1.goingTo);
			}
		}

		ArrayList<Edge> arg35 = new ArrayList<Edge>();

		Iterator<Edge> edgesLeft = null;
		int sizeOfPerfectMatching;
		int numberOfFinalSets;
		int numberOfSetsSelected;
		Edge numberOfSubs;
		Iterator arg54;
		for (sizeOfPerfectMatching = 0; sizeOfPerfectMatching < leftSide.size()
				&& (arg35.size() < rightSide.size() || rightSide.size() >= leftSide.size()); ++sizeOfPerfectMatching) {
			Node paths = (Node) leftSide.get(sizeOfPerfectMatching);
			edgesLeft = paths.getGoingIterator();
			int path = paths.sizeOfOutEdges();
			int signForMatching = 0;
			while (edgesLeft.hasNext()) {
				Edge end1 = (Edge) edgesLeft.next();
				++signForMatching;
				boolean p = false;
				for (int duration1 = 0; duration1 < arg35.size(); ++duration1) {
					if (((Edge) arg35.get(duration1)).goingTo.equals(end1.goingTo)) {
						p = true;
						break;
					}
				}
				if (!p) {
					arg35.add(end1);
					break;
				}
				if (path == signForMatching) {
					edgesLeft = paths.getGoingIterator();
					boolean arg46 = false;

					label252: while (edgesLeft.hasNext() && sizeOfPerfectMatching != arg35.size() - 1) {
						Edge sign = (Edge) edgesLeft.next();
						Node start2 = sign.goingTo;
						Node m = null;
						numberOfFinalSets = 0;
						for (numberOfSetsSelected = 0; numberOfSetsSelected < arg35.size(); ++numberOfSetsSelected) {
							if (((Edge) arg35.get(numberOfSetsSelected)).goingTo.equals(start2)) {
								m = ((Edge) arg35.get(numberOfSetsSelected)).comingFrom;
								numberOfFinalSets = numberOfSetsSelected;
								break;
							}
						}
						if (m != null) {
							arg54 = m.getGoingIterator();
							while (arg54.hasNext()) {
								numberOfSubs = (Edge) arg54.next();

								boolean end2 = false;
								for (int y = 0; y < arg35.size(); ++y) {
									if (numberOfSubs.goingTo.equals(((Edge) arg35.get(y)).goingTo)) {
										end2 = true;
										break;
									}
								}

								if (!end2) {
									arg35.set(numberOfFinalSets, numberOfSubs);
									arg35.add(sign);
									arg46 = true;
									break label252;
								}
							}
						}
					}

					if (!arg46) {
						break;
					}
				}
			}
		}
		sizeOfPerfectMatching = arg35.size();
		List<Path> arg38 = new ArrayList<Path>();
		Path arg39 = new Path();
		boolean arg40 = false;
		if (arg35.size() >= 1) {
			arg39.extendPath(((Edge) arg35.get(0)).comingFrom);
			arg39.extendPath(((Edge) arg35.get(0)).goingTo);
			arg35.remove(0);
		}
		while (arg35.size() > 0) {
			if (arg39.path.size() == 0) {
				arg39.extendPath(((Edge) arg35.get(0)).comingFrom);
				arg39.extendPath(((Edge) arg35.get(0)).goingTo);
				arg35.remove(0);
			}
			arg40 = false;
			for (int arg41 = 0; arg41 < arg35.size(); ++arg41) {
				if (arg39.get(0).equals(((Edge) arg35.get(arg41)).goingTo)) {
					Path arg44 = new Path();
					arg44.extendPath(((Edge) arg35.get(arg41)).comingFrom);
					arg44.extendPath(arg39);
					new Path();
					arg39 = arg44;
					arg35.remove(arg41);
					if (arg35.size() == 0) {
						arg38.add(arg44);
						arg39 = new Path();
					}
					--arg41;
					arg40 = true;
					break;
				}
				if (arg39.getEnd().equals(((Edge) arg35.get(arg41)).comingFrom)) {
					arg39.extendPath(((Edge) arg35.get(arg41)).goingTo);
					arg35.remove(arg41);
					if (arg35.size() == 0) {
						arg38.add(arg39);
						arg39 = new Path();
					}
					--arg41;
					arg40 = true;
					break;
				}
			}
			if (!arg40) {
				arg38.add(arg39);
				arg39 = new Path();

			}
		}

		Iterator<Path> arg45 = arg38.iterator();
		int arg57;
		while (arg45.hasNext()) {
			Path arg42 = (Path) arg45.next();
			arg39 = new Path();
			List<Node> arg48 = arg42.path;
			boolean arg47 = false;
			if (((Node) arg48.get(0)).equals((Node) arg48.get(arg48.size() - 1))) {
				arg47 = true;
			}
			int arg49 = arg48.size();
			for (int arg51 = 0; arg51 < arg49 - 1; ++arg51) {
				Node arg52 = (Node) arg48.get(arg51);
				Node arg55 = (Node) arg48.get(arg51 + 1);
				numberOfSubs = new Edge(arg52, arg55);
				if (arg51 == arg49 - 3 && arg47) {
					arg39.extendPath((Path) ((Node) arg48.get(arg51 + 1)).getNodeVar());
					break;
				}
			}
			minimumPath.extendPath(arg39);
			finalPathsSet.add(arg39);
		}

		minimumPath = new Path();
		arg54 = listOfPaths.iterator();
		while (arg54.hasNext()) {
			Path arg53 = (Path) arg54.next();
			finalPathsSet.add(arg53);
		}
		numberOfFinalSets = finalPathsSet.size();
		numberOfSetsSelected = 0;
		List<Integer> arg56 = new ArrayList<Integer>();
		for (arg57 = 0; arg57 < finalPathsSet.size(); ++arg57) {
			arg56.add(new Integer(0));
		}
		while (primePaths.size() > 0) {
			double arg58 = 100.0D;
			int duration2 = 0;
			int i;
			for (i = 0; i < finalPathsSet.size(); ++i) {
				Integer index1 = Integer.valueOf(0);
				Iterator<Path> arg33 = primePaths.iterator();
				while (arg33.hasNext()) {
					Path primePath = (Path) arg33.next();
					if (((Path) finalPathsSet.get(i)).indexOf(primePath) != -1) {
						index1 = Integer.valueOf(index1.intValue() + 1);
					}
				}
				arg56.set(i, index1);
				if (((Integer) arg56.get(i)).intValue() != 0 && (double) (((Path) finalPathsSet.get(i)).path.size()
						/ ((Integer) arg56.get(i)).intValue()) < arg58) {
					arg58 = (double) ((Path) finalPathsSet.get(i)).path.size()
							/ (double) ((Integer) arg56.get(i)).intValue();
					duration2 = i;

				}
			}
			for (i = 0; i < primePaths.size(); ++i) {
				int arg60 = ((Path) finalPathsSet.get(duration2)).indexOf((Path) primePaths.get(i));
				if (arg60 != -1) {
					primePaths.remove(i);
					--i;
				}
			}
			minimumPath.extendPath((Path) finalPathsSet.get(duration2));
			++numberOfSetsSelected;
			finalPathsSet.remove(duration2);
			arg56.remove(duration2);
		}

		minimumPaths.add(minimumPath);
		return minimumPaths;
	}

	public void validate() {
		List<Node> linkedNodes = new ArrayList<Node>();
		List<Node> nodesCopy = new ArrayList<Node>();
		int nodeStr;
		for (nodeStr = 0; nodeStr < this.nodes.size(); ++nodeStr) {
			nodesCopy.add((Node) this.nodes.get(nodeStr));
		}

		for (nodeStr = 0; nodeStr < this.starts.size(); ++nodeStr) {
			linkedNodes.add((Node) this.starts.get(nodeStr));
		}
		for (nodeStr = this.starts.size() - 1; nodeStr >= 0; --nodeStr) {

			int node = this.nodes.indexOf(this.starts.get(nodeStr));

			if (node >= 0 && node < nodesCopy.size()) {
				nodesCopy.remove(node);

			}
		}

		for (nodeStr = 0; nodeStr < linkedNodes.size(); ++nodeStr) {

			Iterator<Edge> arg5 = ((Node) linkedNodes.get(nodeStr)).getGoingIterator();

			while (arg5.hasNext()) {

				Node des = ((Edge) arg5.next()).goingTo;
				if (nodesCopy.contains(des)) {
					nodesCopy.remove(des);
					linkedNodes.add(des);
				}
			}
		}
		Node arg6;
		Iterator<Node> arg7;
		String arg8;
		if (nodesCopy.size() != 0 && nodesCopy.size() != 1) {
			arg8 = "";
			for (arg7 = nodesCopy.iterator(); arg7.hasNext(); arg8 = arg8 + " " + arg6.toString()) {
				arg6 = (Node) arg7.next();
			}
		} else if (nodesCopy.size() == 1) {
			arg8 = "";
			for (arg7 = nodesCopy.iterator(); arg7.hasNext(); arg8 = arg8 + " " + arg6.toString()) {
				arg6 = (Node) arg7.next();
			}
		}
	}

	public List<Path> findSpanningTree() {
		this.validate();
		List<Path> result = new ArrayList<Path>();
		for (int j = 0; j < this.starts.size(); ++j) {
			List<Node> nodesCopy = new ArrayList<Node>();
			int paths;
			for (paths = 0; paths < this.nodes.size(); ++paths) {
				nodesCopy.add((Node) this.nodes.get(paths));
			}
			int i;
			for (paths = this.starts.size() - 1; paths >= 0; --paths) {
				i = this.nodes.indexOf(this.starts.get(paths));
				nodesCopy.remove(i);
			}
			List<Path> arg10 = new ArrayList<Path>();
			arg10.add(new Path((Node) this.starts.get(j)));
			while (arg10.size() != 0) {
				Path path;
				Node end1;
				for (i = 0; i < arg10.size(); ++i) {
					path = (Path) arg10.get(i);
					end1 = path.getEnd();
					if (end1.getGoingEdges().size() > 1) {
						end1.getGoingEdges().remove(0);
					}
					Iterator<Edge> outEdges = end1.getGoingIterator();
					int count = 0;
					while (outEdges.hasNext()) {
						++count;
						if (count == end1.sizeOfOutEdges()) {
							path.extendPath(((Edge) outEdges.next()).goingTo);

						} else {
							Path newPath = (Path) path.copy();
							newPath.extendPath(((Edge) outEdges.next()).goingTo);
							arg10.add(i + 1, newPath);
							++i;
						}
					}
				}
				for (i = 0; i < arg10.size(); ++i) {
					path = (Path) arg10.get(i);
					end1 = path.getEnd();
					if (nodesCopy.contains(end1)) {
						nodesCopy.remove(end1);
					} else {
						arg10.remove(i);
						--i;
						result.add(path);
					}
				}
			}
		}
		return result;
	}

	public List<Path> findTestPath() {
		List<Path> paths = findSpanningTree();
		List<Path> result = new ArrayList<Path>();
		int oldsize;
		int p;
		Path path;
		for (oldsize = 0; oldsize < paths.size(); ++oldsize) {
			Path pathStr = (Path) paths.get(oldsize);
			if (this.ends.contains(pathStr.getEnd())) {
				result.add(pathStr);
				paths.remove(oldsize);
				--oldsize;
			} else {
				for (p = 0; p < this.ends.size(); ++p) {
					int testPath = pathStr.indexOf((Node) this.ends.get(p));
					if (testPath != -1) {
						path = pathStr.subPath(0, testPath + 1);
						boolean index = false;
						for (int subpath = 0; subpath < result.size(); ++subpath) {
							if (path.isSubpath((Path) result.get(subpath))) {
								index = true;
								break;
							}
						}
						if (!index) {
							result.add(path);

						}
					}
				}
			}
		}
		if (result.size() == 0) {
			System.out.println("error");
		}
		do {
			oldsize = paths.size();
			for (int arg10 = 0; arg10 < result.size(); ++arg10) {
				for (p = 0; p < paths.size(); ++p) {

					Path arg12 = (Path) result.get(arg10);
					path = (Path) paths.get(p);
					int arg15 = arg12.indexOf(path.getEnd());
					if (arg15 != -1) {
						Path arg16 = arg12.subPath(arg15 + 1);
						path.extendPath(arg16);
						result.add(path);
						paths.remove(p);
						--p;
						if (paths.size() == 0) {
						} else {
							String arg11 = "";
							Path arg14;
							for (Iterator<Path> arg13 = paths.iterator(); arg13
									.hasNext(); arg11 = arg11 + " " + arg14.toString()) {
								arg14 = (Path) arg13.next();
							}
						}
					}
				}
			}
		} while (oldsize - paths.size() > 0);

		return result;
	}

	public boolean edgeControl(Node start, Node end) {
		Iterator<Edge> ie = start.getGoingIterator();
		Edge e = null;
		while (ie.hasNext()) {
			e = (Edge) ie.next();
			if (e.goingTo.equals(end)) {
				return true;
			}
		}
		return false;
	}

	public boolean finalNodeControl(Node n) {
		if (ends != null && this.ends.size() != 0) {
			return this.ends.contains(n);
		}else{
			return false;
		}
	}

	public boolean initialNodeControl(Node n) {
		if(this.starts != null && this.starts.size() != 0){
			return this.starts.contains(n);
		}else{
			return false;
		}
	}

	public List<Path> findCoveragePaths(Path optimizedPath, List<Path> testPaths) {
		List<Path> paths = new ArrayList<Path>();
		Path testPath = new Path(optimizedPath.get(0));
		int end;
		int duration;
		for (end = 1; end < optimizedPath.path.size(); ++end) {
			Node path = optimizedPath.get(end);
			if (this.initialNodeControl(path) && !this.edgeControl(optimizedPath.get(end - 1), path)) {
				testPath = new Path(path);
			} else if (testPath == null) {
				testPath = new Path(path);
			} else {
				testPath.extendPath(path);
			}
			int anotherPath;
			int thePath;
			int j;
			Path arg14;
			if (end == optimizedPath.path.size() - 1) {
				if (!this.initialNodeControl(testPath.get(0))) {

					duration = 32255;
					anotherPath = 0;
					for (thePath = 0; thePath < testPaths.size(); ++thePath) {
						if (((Path) testPaths.get(thePath)).indexOf(testPath.get(0)) < duration
								&& ((Path) testPaths.get(thePath)).indexOf(testPath.get(0)) >= 0) {
							duration = ((Path) testPaths.get(thePath)).indexOf(testPath.get(0));
							anotherPath = thePath;
						}
					}
					arg14 = new Path(((Path) testPaths.get(anotherPath)).get(0));
					for (j = 1; j < duration; ++j) {
						arg14.extendPath(((Path) testPaths.get(anotherPath)).get(j));
					}

					for (j = 0; j < testPath.path.size(); ++j) {
						arg14.extendPath(testPath.get(j));
					}
					testPath = new Path();

					for (j = 0; j < arg14.path.size(); ++j) {
						testPath.extendPath(arg14.get(j));
					}
				}

				if (!this.finalNodeControl(testPath.getEnd())) {

					duration = 0;
					anotherPath = 0;
					for (thePath = 0; thePath < testPaths.size(); ++thePath) {
						if (((Path) testPaths.get(thePath)).lastIndexOf(testPath.getEnd()) > duration) {
							duration = ((Path) testPaths.get(thePath)).lastIndexOf(testPath.getEnd());
							anotherPath = thePath;
						}
					}

					for (thePath = duration + 1; thePath < ((Path) testPaths.get(anotherPath)).path.size(); ++thePath) {
						testPath.extendPath(((Path) testPaths.get(anotherPath)).get(thePath));
					}
				}

				paths.add(testPath);
			} else if (!this.edgeControl(path, optimizedPath.get(end + 1))) {
				if (!this.initialNodeControl(testPath.get(0))) {

					duration = 32255;
					anotherPath = 0;
					for (thePath = 0; thePath < testPaths.size(); ++thePath) {
						if (((Path) testPaths.get(thePath)).indexOf(testPath.get(0)) < duration
								&& ((Path) testPaths.get(thePath)).indexOf(testPath.get(0)) >= 0) {
							duration = ((Path) testPaths.get(thePath)).indexOf(testPath.get(0));
							anotherPath = thePath;
						}
					}

					arg14 = new Path(((Path) testPaths.get(anotherPath)).get(0));
					for (j = 1; j < duration; ++j) {
						arg14.extendPath(((Path) testPaths.get(anotherPath)).get(j));
					}

					for (j = 0; j < testPath.path.size(); ++j) {
						arg14.extendPath(testPath.get(j));
					}

					testPath = new Path();

					for (j = 0; j < arg14.path.size(); ++j) {
						testPath.extendPath(arg14.get(j));
					}
				}

				if (!this.finalNodeControl(testPath.getEnd())) {

					duration = 0;
					anotherPath = 0;
					for (thePath = 0; thePath < testPaths.size(); ++thePath) {
						if (((Path) testPaths.get(thePath)).lastIndexOf(testPath.getEnd()) > duration) {
							duration = ((Path) testPaths.get(thePath)).lastIndexOf(testPath.getEnd());
							anotherPath = thePath;

						}
					}

					arg14 = new Path(((Path) testPaths.get(anotherPath)).get(duration + 1));
					for (j = duration + 2; j < ((Path) testPaths.get(anotherPath)).path.size(); ++j) {
						arg14.extendPath(((Path) testPaths.get(anotherPath)).get(j));
					}

					for (j = 0; j < arg14.path.size(); ++j) {
						testPath.extendPath(arg14.get(j));
					}
				}

				paths.add(testPath);

				testPath = null;

			}
		}
		for (end = 0; end < paths.size(); ++end) {
			Path arg13 = (Path) paths.get(end);
			duration = end + 1;
			while (duration < paths.size()) {
				Path arg15 = (Path) paths.get(duration);
				if (arg13.equals(arg15)) {
					paths.remove(duration);

				} else {
					++duration;
				}
			}
		}
		return paths;
	}
	
	public List<Path> findSimplePaths(List<Edge> edgesList) {
		int lastStart = 0;

		List<Path> simplePaths = new ArrayList<Path>();
		Edge e;
		Path p;
		for (int newPaths = 0; newPaths < edgesList.size(); ++newPaths) {
			e = (Edge) edgesList.get(newPaths);
			p = new Path(e);
			simplePaths.add(p);
		}

		int curSize;
		for (boolean arg9 = true; arg9; lastStart = curSize) {
			arg9 = false;
			curSize = simplePaths.size();
			for (int i = lastStart; i < curSize; ++i) {

				p = (Path) simplePaths.get(i);
				if (!p.containsCycle()) {

					for (int j = 0; j < edgesList.size(); ++j) {

						e = (Edge) edgesList.get(j);
						if (e.comingFrom.equals(p.get(p.path.size() - 1))
								&& (p.indexOf(e.goingTo) == -1 || p.indexOf(e.goingTo) == 0)) {

							arg9 = true;
							Path pnew = (Path) p.copy();
							pnew.extendPath(e.goingTo);
							simplePaths.add(pnew);

						}
					}
				}
			}
		}

		return simplePaths;
	}

	public List<Path> findPrimePaths(List<Path> inputPaths) {
		List<Path> simplePaths = inputPaths;
		List<Path> primePaths = new ArrayList<Path>();
		if (simplePaths.size() <= 0) {
		} else {
			primePaths.add((Path) simplePaths.get(simplePaths.size() - 1));
			for (int i = simplePaths.size() - 2; i > -1; --i) {
				boolean isSubpath = false;
				for (int j = 0; j < primePaths.size(); ++j) {
					if (((Path) simplePaths.get(i)).isSubpath((Path) primePaths.get(j))) {

						isSubpath = true;
						break;
					}
				}

				if (!isSubpath) {
					primePaths.add((Path) simplePaths.get(i));
				}
			}

		}
		return primePaths;
	}
}
