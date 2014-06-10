package calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

		for (int i = 0; i < charArray.length; i++) {
			if (Character.getNumericValue(charArray[i]) == -1) {
				System.out.println(charArray[i]);
			} else {
				StringBuilder s = new StringBuilder().append(charArray[i]);
				while ((Character.getNumericValue(charArray[++i]) != -1)
						|| (charArray[i] == '.')) {
					s.append(charArray[i]);
				}
				--i;

				System.out.println(s);

			}

		}

	}

}
