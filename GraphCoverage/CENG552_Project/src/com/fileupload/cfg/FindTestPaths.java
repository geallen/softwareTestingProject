package com.fileupload.cfg;

import java.util.ArrayList;
import java.util.List;

import com.model.Edge;
import com.model.Node;
import com.model.Path;

public class FindTestPaths {
	CfgOperations operations = new CfgOperations();
	public List<Path> testPaths(String choice, List<Edge> edgesList){
		List<Path> optimizationResultPaths = new ArrayList<Path>();
		List<Path> testPaths = new ArrayList<Path>();
		List<Path> coveragePaths = new ArrayList<Path>();
	if (choice.equals("Edge Coverage")) {
		List<Path> edgesListPath = new ArrayList<Path>();
		for (int i = 0; i < edgesList.size(); i++) {
			Path p = new Path(edgesList.get(i));
			edgesListPath.add(p);
		}
		optimizationResultPaths = operations.optimization(edgesListPath);
		testPaths = operations.findTestPath();
		coveragePaths = operations.findCoveragePaths(optimizationResultPaths.get(0), testPaths);
	}

	if (choice.equals("Edge Pair Coverage")) {
		List<Path> edgePairList = new ArrayList<Path>();

		for (int i = 0; i < edgesList.size(); i++) {
			List<Edge> nextEdgeList = findNextEdgesList(edgesList.get(i).getGoingTo(), edgesList);
			for (int j = 0; j < nextEdgeList.size(); j++) {
				Path p = new Path(edgesList.get(i));
				Path p1 = new Path(nextEdgeList.get(j).getGoingTo());
				p.extendPath(p1);
				edgePairList.add(p);

			}
		}
		optimizationResultPaths = operations.optimization(edgePairList);
		testPaths = operations.findTestPath();
		coveragePaths = operations.findCoveragePaths(optimizationResultPaths.get(0), testPaths);
	}
	return coveragePaths;
}
	public static List<Edge> findNextEdgesList(Node comingFrom, List<Edge> edgesList) {
		List<Edge> nextEdgeList = new ArrayList<Edge>();
		for (int i = 0; i < edgesList.size(); i++) {
			if (edgesList.get(i).getComingFrom() == comingFrom) {
				nextEdgeList.add(edgesList.get(i));
			}
		}
		return nextEdgeList;
	}


}
