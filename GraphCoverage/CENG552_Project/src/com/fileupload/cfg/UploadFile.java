package com.fileupload.cfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = "C:/Users/Gamze/Desktop";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		File directory = new File("new folder");
		String name = "";
		if (!directory.exists()) {
			System.out.println("creating directory: " + directory.getName());
			boolean result = false;

			try {
				directory.mkdir();
				result = true;
			} catch (SecurityException se) {
				se.printStackTrace();
			}
			if (result) {
				System.out.println("DIR created");
			}
		}

		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> multiparts = upload.parseRequest(request);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						name = new File(item.getName()).getName();
						String name2 = UPLOAD_DIRECTORY + File.separator + name;
						item.write(new File(name2));
					}
				}

				request.setAttribute("message", "Your file has been uploaded!");
				request.setAttribute("location", UPLOAD_DIRECTORY + "/" + name);
				request.setAttribute("locationtoplace", UPLOAD_DIRECTORY + "/");
			} catch (Exception e) {
				request.setAttribute("message", "File Upload Failed due to " + e);
			}
		} else {
			request.setAttribute("message", "This Servlet only handles file upload request");

		}
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}

	public String getFile() {

		StringBuilder result = new StringBuilder("");

		InputStream iimg = getClass().getClassLoader().getResourceAsStream("conf.txt");
		if (iimg == null) {
			try {
				iimg = new FileInputStream("file/conf.txt");
				Scanner scanner = new Scanner(iimg);
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					result.append(line).append("\n");
					
				}

				scanner.close();
			} catch (FileNotFoundException e) {
			}
		}
		if (iimg == null) {
			System.err.format("Icon not found...");
		}

		return result.toString();

	}

}