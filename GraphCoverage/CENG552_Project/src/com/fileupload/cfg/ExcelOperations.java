package com.fileupload.cfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.model.Edge;
import com.model.Node;
import com.model.Path;

public class ExcelOperations {
	String name = "";
	List<Node> nodes = new ArrayList<Node>();
	List<Edge> edges = new ArrayList<Edge>();
	FindTestPaths findTestPaths = new FindTestPaths();

	public List<Path> resultPath(String choice, String name, String locationOfOutputFile) {
		List<Path> coveragePaths = new ArrayList<Path>();

		try {
			FileInputStream excelFile = new FileInputStream(new File(name));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			XSSFWorkbook workbook2 = new XSSFWorkbook();
			XSSFSheet sheet = workbook2.createSheet();

			List<Path> primeList = new ArrayList<Path>();
			List<Path> primePathList = new ArrayList<Path>();
			List<Path> optimizationResultPaths = new ArrayList<Path>();
			List<Path> testPaths = new ArrayList<Path>();
			List<Edge> edgesList = new ArrayList<Edge>();
			CfgOperations operations = new CfgOperations();
			Iterator<Row> rowIterator = datatypeSheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getColumnIndex() == 1) {
						Node a = operations.createNode(String.valueOf(cell.getNumericCellValue()).substring(0,
								String.valueOf(cell.getNumericCellValue()).length() - 2));
						operations.addInitialNode(a);
					} else if (cell.getColumnIndex() == 0) {
						String[] aa = cell.getStringCellValue().split(" ");
						Node node1 = operations.createNode(aa[0]);
						Node node2 = operations.createNode(aa[1]);
						edgesList.add(operations.createEdge(node1, node2));
					} else if (cell.getColumnIndex() == 2) {
						Node a = operations.createNode(String.valueOf(cell.getNumericCellValue()).substring(0,
								String.valueOf(cell.getNumericCellValue()).length() - 2));
						operations.addEndingNode(a);

					}
				}
			}
			workbook.close();
		//	coveragePaths = findTestPaths
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

			// prim path coverage icin

			if (choice.equals("Prime Path Coverage")) {
				primeList = operations.findSimplePaths(edgesList);
				primePathList = operations.findPrimePaths(primeList);
				optimizationResultPaths = operations.optimization(primePathList);
				testPaths = operations.findTestPath();
				coveragePaths = operations.findCoveragePaths(optimizationResultPaths.get(0), testPaths);
			}
			int rowNum = 0;
			for (Path datatype : coveragePaths) {
				Row row = sheet.createRow(rowNum++);
				Cell cell = row.createCell(0);
				cell.setCellValue(datatype.toString());

			}

			locationOfOutputFile = locationOfOutputFile + "/result.xlsx";
			FileOutputStream outputStream = new FileOutputStream(locationOfOutputFile);
			workbook2.write(outputStream);
			workbook2.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {

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
