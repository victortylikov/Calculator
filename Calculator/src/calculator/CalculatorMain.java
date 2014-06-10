package calculator;

import java.io.IOException;

import javax.script.ScriptException;

public class CalculatorMain {

	public static void main(String[] args) throws IOException {
		Calculator calculator=new Calculator();
		String line=calculator.read("C://calculator.txt");
	    System.out.println(line);
		calculator.calculate(line);
//asd
	}

}
