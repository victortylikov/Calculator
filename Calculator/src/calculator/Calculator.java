package calculator;

import java.io.BufferedReader;
import java.util.*;
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
		calculateMultiplyAndDivide(charArrayList);
		calculateMinusAndPlus(charArrayList);
		for (char zz : charArrayList) {
			System.out.print(zz);
		}
	}

	private void findBrace(List<Character> charArrayList) {
		ListIterator<Character> li;
		boolean flag = false;
		outer: do {
			li = charArrayList.listIterator(0);
			while (li.hasNext()) {
				if (li.next() == ')') {

					int i = li.previousIndex();
					li.remove();
					do {

						if (li.previous() == '(') {

							li.remove();

							int j = li.previousIndex();
							List<Character> newCharArrayList = charArrayList
									.subList(++j, --i);

							calculateMultiplyAndDivide(newCharArrayList);

							calculateMinusAndPlus(newCharArrayList);

							flag = true;
							continue outer;
						}
					} while (li.hasPrevious());

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
			for (int k = 1; k < newCharArrayList.size(); k++) {
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
		boolean negativeNumber = false;
		NumberAndPosition numberAndPositionB = new NumberAndPosition();
		StringBuilder s = new StringBuilder();
		while ((newCharArrayList.size() > ++k)
				&& ((Character.getNumericValue(newCharArrayList.get(k)) >= 0) || (newCharArrayList
						.get(k) == '.')||((newCharArrayList
								.get(k) == '-')&&(negativeNumber==false)))) {
			if(newCharArrayList
					.get(k) == '-'){negativeNumber=true;}
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
