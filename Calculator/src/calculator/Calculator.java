package calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculator {

	public String read(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
		}
		String stringLine = sb.toString();
		return stringLine;
	}

	public void calculate(String stringLine) {
		char[] charArray = stringLine.toCharArray();
		List<Character> charArrayList = new ArrayList<Character>();
		for (char c : charArray) {
			charArrayList.add(c);
		}
		findBrace(charArrayList);
	}

	private void findBrace(List<Character> charArrayList) {
		outer: for (int i = 0; i < charArrayList.size(); i++) {
			if (charArrayList.get(i) == ')') {
				for (int j = i; j >= 0; j--) {
					if (charArrayList.get(j) == '(') {
						List<Character> newCharArrayList = charArrayList
								.subList(++j, i);
						for(char c:newCharArrayList){
							System.out.print(c);
						}

						/*
						 *  System.out.print(j + " ");
						 * System.out.println(charArray[j]); System.out.print(i
						 * + " "); System.out.println(charArray[--i]);
						 * 
						 * 
						 * for (int k = 0; k < newCharArray.length; k++) {
						 * System.out.println(newCharArray[k]); }
						 * 
						 * calculateMultiplyAndDivide(newCharArray);
						 */

						break outer;

					}
				}
			}
		}

	}

	private Double calculateMultiplyAndDivide(char[] newCharArray) {
		Double resultAB = null;
		for (int k = 0; k < newCharArray.length; k++) {
			if ((newCharArray[k] == '/') || (newCharArray[k] == '*')) {
				NumberAndPosition numberAndPositionA = findA(newCharArray, k);
				NumberAndPosition numberAndPositionB = findB(newCharArray, k);
				if (newCharArray[k] == '*') {
					resultAB = numberAndPositionA.getNumber()
							* numberAndPositionB.getNumber();

				} else if (newCharArray[k] == '/') {
					resultAB = numberAndPositionA.getNumber()
							/ numberAndPositionB.getNumber();
				}

				break;
			}
		}

		return resultAB;
	}

	private NumberAndPosition findA(char[] newCharArray, int k) {
		NumberAndPosition numberAndPositionA = new NumberAndPosition();
		StringBuilder s = new StringBuilder();
		while ((0 <= --k)
				&& ((Character.getNumericValue(newCharArray[k]) >= 0) || (newCharArray[k] == '.'))) {
			s.append(newCharArray[k]);
			numberAndPositionA.setPosition(k);
		}
		numberAndPositionA
				.setNumber(Double.parseDouble(s.reverse().toString()));

		return numberAndPositionA;

	}

	private NumberAndPosition findB(char[] newCharArray, int k) {
		NumberAndPosition numberAndPositionB = new NumberAndPosition();
		StringBuilder s = new StringBuilder();
		while ((newCharArray.length > ++k)
				&& ((Character.getNumericValue(newCharArray[k]) >= 0) || (newCharArray[k] == '.'))) {
			s.append(newCharArray[k]);
			numberAndPositionB.setPosition(k);
		}
		numberAndPositionB
				.setNumber(Double.parseDouble(s.reverse().toString()));

		return numberAndPositionB;

	}

	/*
	 * public void someMethod(String stringLine){ char[] charArray =
	 * stringLine.toCharArray();
	 * 
	 * for (int i = 0; i < charArray.length; i++) { if
	 * (Character.getNumericValue(charArray[i]) == -1) {
	 * System.out.println(charArray[i]); } else { StringBuilder s = new
	 * StringBuilder().append(charArray[i]); while
	 * ((Character.getNumericValue(charArray[++i]) != -1) || (charArray[i] ==
	 * '.')) { s.append(charArray[i]); } --i;
	 * 
	 * System.out.println(s);
	 * 
	 * }
	 * 
	 * } }
	 */

}
