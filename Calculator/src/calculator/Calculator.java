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
		boolean flag = false;
		do {
			outer:

			for (int i = 0; i < charArrayList.size(); i++) {
				if (charArrayList.get(i) == ')') {
					charArrayList.remove(i);
					for (int j = i; j >= 0; j--) {
						if (charArrayList.get(j) == '(') {
							for (char c : charArrayList) {
								System.out.print(c);
							}
							System.out.print("AAAAAA");
							
							List<Character> newCharArrayList = charArrayList
									.subList(++j, i);
							System.out.println();
							for (char c : newCharArrayList) {
								System.out.print(c);
							}
							System.out.println();
							
							calculateMultiplyAndDivide(newCharArrayList);
							
							calculateMinusAndPlus(newCharArrayList);
									
							//charArrayList.remove(j);
							flag = true;
							
							break outer;

						}
					}
				}
				flag = false;
			}
		} while (flag == true);
	}

	private List<Character> calculateMultiplyAndDivide(
			List<Character> newCharArrayList) {
		Double resultAB = null;
		do {
			resultAB = null;
			for (int k = 0; k < newCharArrayList.size(); k++) {
				if ((newCharArrayList.get(k) == '/')
						|| (newCharArrayList.get(k) == '*')) {
					NumberAndPosition numberAndPositionA = findA(
							newCharArrayList, k);
					NumberAndPosition numberAndPositionB = findB(
							newCharArrayList, k);

					if (newCharArrayList.get(k) == '*') {
						resultAB = numberAndPositionA.getNumber()
								* numberAndPositionB.getNumber();

					} else if (newCharArrayList.get(k) == '/') {

						resultAB = numberAndPositionA.getNumber()
								/ numberAndPositionB.getNumber();
						
					}
					newCharArrayList = replaceList(newCharArrayList,
							numberAndPositionA.getPosition(),
							numberAndPositionB.getPosition(), resultAB);

					break;
				}
			}
		} while (resultAB != null);

		return newCharArrayList;
	}

	private List<Character> calculateMinusAndPlus(
			List<Character> newCharArrayList) {
		Double resultAB = null;
		do {
			resultAB = null;
			for (int k = 0; k < newCharArrayList.size(); k++) {
				if ((newCharArrayList.get(k) == '-')
						|| (newCharArrayList.get(k) == '+')) {
					NumberAndPosition numberAndPositionA = findA(
							newCharArrayList, k);
					NumberAndPosition numberAndPositionB = findB(
							newCharArrayList, k);

					if (newCharArrayList.get(k) == '-') {
						resultAB = numberAndPositionA.getNumber()
								- numberAndPositionB.getNumber();

					} else if (newCharArrayList.get(k) == '+') {

						resultAB = numberAndPositionA.getNumber()
								+ numberAndPositionB.getNumber();
					}
					newCharArrayList = replaceList(newCharArrayList,
							numberAndPositionA.getPosition(),
							numberAndPositionB.getPosition(), resultAB);

					break;
				}
			}
		} while (resultAB != null);
		
		return newCharArrayList;

	}

	private NumberAndPosition findA(List<Character> newCharArrayList, int k) {
		NumberAndPosition numberAndPositionA = new NumberAndPosition();
		StringBuilder s = new StringBuilder();
		while ((0 <= --k)
				&& ((Character.getNumericValue(newCharArrayList.get(k)) >= 0) || (newCharArrayList
						.get(k) == '.'))) {
			s.append(newCharArrayList.get(k));
			numberAndPositionA.setPosition(k);
		}
		numberAndPositionA
				.setNumber(Double.parseDouble(s.reverse().toString()));
		
		return numberAndPositionA;

	}

	private NumberAndPosition findB(List<Character> newCharArrayList, int k) {
		NumberAndPosition numberAndPositionB = new NumberAndPosition();
		StringBuilder s = new StringBuilder();
		while ((newCharArrayList.size() > ++k)
				&& ((Character.getNumericValue(newCharArrayList.get(k)) >= 0) || (newCharArrayList
						.get(k) == '.')||(newCharArrayList
								.get(k) == '-'))) {
			s.append(newCharArrayList.get(k));
			numberAndPositionB.setPosition(k);
		}

		numberAndPositionB.setNumber(Double.parseDouble(s.toString()));
		
		return numberAndPositionB;

	}


	private List<Character> replaceList(List<Character> newCharArrayList,
			int positionA, int positionB, Double resultAB) {
		int quantityOfNumber = positionB - positionA;
		while ((quantityOfNumber--) >= 0) {
			newCharArrayList.remove(positionA);
		}

		char[] charArray = new StringBuilder(resultAB.toString()).reverse()
				.toString().toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			newCharArrayList.add(positionA, charArray[i]);
		}

		return newCharArrayList;
	}

}
