package de.julian.pascalshedreieck.main;

import java.io.File;
import java.nio.file.Paths;

public class Generator {

	public static void generate(int lineamount) {
		String dir = Paths.get("").toAbsolutePath().toString();
		Triangle triangle = new Triangle(lineamount);
		triangle.printToTXTFile(new File(dir, "Pascal's triangle.txt"));
	}
}