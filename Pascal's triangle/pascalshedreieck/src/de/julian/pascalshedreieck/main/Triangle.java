package de.julian.pascalshedreieck.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;

public class Triangle {

	ArrayList<BigInteger[]> lines = new ArrayList<>();
	int linesamount;

	public Triangle(int linesamount) {
		if (linesamount > 0) {
			this.linesamount = linesamount;
			calculate();
		} else {
			System.out.println("The amount of lines is too small.");
			System.out.println("Too small numbers(under 1) will result in nothing being printed.");
			System.exit(0);
		}
	}

	private void calculate() {
		lines.add(new BigInteger[] { BigInteger.valueOf(1) });
		for (int i = 1; i < linesamount; i++) {
			BigInteger[] add = new BigInteger[lines.get(i - 1).length + 1];
			BigInteger[] old = lines.get(i - 1);
			add[0] = BigInteger.valueOf(1);

			if (lines.get(i - 1).length > 1) {
				for (int j = 1; j < add.length - 1; j++) {
					add[j] = old[j - 1].add(old[j]);
				}
			}

			add[add.length - 1] = BigInteger.valueOf(1);
			lines.add(add);
		}
	}

	public void printToTXTFile(File file) {
		long start = System.nanoTime();
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Could not create new file!");
		}
		FileOutputStream out;
		BufferedWriter writer;
		try {
			out = new FileOutputStream(file);
			writer = new BufferedWriter(new OutputStreamWriter(out));
		} catch (FileNotFoundException e) {
			out = null;
			writer = null;
			System.out.println("Could not find file!");
		}
		ArrayList<String> printlines = new ArrayList<>();
		for (BigInteger[] n : lines) {
			String print = "";
			for (int i = 0; i < n.length; i++) {
				print = print + " " + n[i];
			}
			print = print.trim();
			printlines.add(print);
		}
		for (String print : printlines) {
//			System.out.println("Line " + printlines.indexOf(print));
			long space = (printlines.get(printlines.size() - 1).length() - print.length()) / 2;
			final String printsave = print;
			for (int i = 0; i < space; i++) {
				print = " " + print;
			}
			try {
				writer.write(print);
				if (printlines.indexOf(printsave) != printlines.size() - 1) {
					writer.newLine();
				}
			} catch (IOException ex) {
				System.out.println("Could not write to file!");
			}
		}
		try {
			writer.close();
			out.close();
		} catch (IOException e) {
			System.out.println("Could not close writer and/or stream!");
		}
		long stop = System.nanoTime();
		float time = (stop - start) / (1000 * 1000);
		System.out.println("Printtime: " + time + " ms");
		System.out.println("Done.");
	}
	
	public void print() {
		ArrayList<String> printlines = new ArrayList<>();
		for (BigInteger[] n : lines) {
			String print = "";
			for (int i = 0; i < n.length; i++) {
				print = print + " " + n[i];
			}
			print = print.trim();
			printlines.add(print);
		}
		for (String print : printlines) {
			long space = (printlines.get(printlines.size() - 1).length() - print.length()) / 2;
			for (int i = 0; i < space; i++) {
				print = " " + print;
			}
			System.out.println(print);
		}
		System.out.println("Done.");
	}
}
