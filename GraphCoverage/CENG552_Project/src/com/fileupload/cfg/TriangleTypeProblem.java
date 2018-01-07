package com.fileupload.cfg;

public class TriangleTypeProblem {
	public String triangle_type(int side_a, int side_b, int side_c) {

		String type;
		if (side_a > side_b) {
			int t = side_a;
			side_a = side_b;
			side_b = t;
		}
		if (side_a > side_c) {
			int t = side_a;
			side_a = side_c;
			side_c = t;
		}
		if (side_b > side_c) {
			int t = side_b;
			side_b = side_c;
			side_c = t;
		}
		if (side_a + side_b <= side_c) {
			type = "Not a Triangle";
		} else {
			type = "Scalene";
			if (side_a == side_b && side_b == side_c) {
				type = "Equilateral";
			} else if (side_a == side_b || side_b == side_c) {
				type = "Isosceles";
			}
		}

		return type;
	}
}
