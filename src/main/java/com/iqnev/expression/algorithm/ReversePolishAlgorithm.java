/*
 * Copyright (C) 2021 Ivelin Yanev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iqnev.expression.algorithm;

import java.util.Stack;

import com.iqnev.expression.context.Strategy;

/**
 * An implementation of the Shunting Yard Algorithm and evaluate the value of an
 * arithmetic expression in Reverse Polish Notation.
 * 
 * @author Ivelin Yanev <bgfortran@gmail.com>
 * @since 19.01.2021
 *
 * 
 */
public class ReversePolishAlgorithm implements Strategy {
	public final static String REVESE_POLIS_STRATEGY = "reversepolis";

	@Override
	public double doCalculate(String expression) {
		String postfixStr = infixToPostfix(expression);
		if (postfixStr == null)
			return 0;
		String[] str = postfixStr.split("");
		return evaluateRPN(str);
	}

	/**
	 * Evaluates the value of an arithmetic expression in Reverse Polish Notation.
	 * 
	 * @see {@link https://en.wikipedia.org/wiki/Reverse_Polish_notation}.
	 * @param tokens the given postfix object.
	 * @return
	 */
	public double evaluateRPN(String[] tokens) {
		if (tokens == null || tokens.length == 0)
			return 0;

		Stack<Double> stack = new Stack<>();
		for (String str : tokens) {
			if (!isOperator(str))
				stack.push(Double.parseDouble(str));
			else {
				double numB = stack.pop(), numA = stack.pop();
				stack.push(eval(numA, numB, str));
			}
		}
		return stack.pop().intValue();
	}

	public static boolean isOperator(String str) {
		if (str.length() != 1)
			return false;
		char c = str.charAt(0);
		return c == '+' || c == '-' || c == '/' || c == '*';
	}

	public static double eval(double a, double b, String str) {
		double result = 0;
		char operator = str.charAt(0);
		if (operator == '+')
			result = a + b;
		if (operator == '-')
			result = a - b;
		if (operator == '*')
			result = a * b;
		if (operator == '/')
			result = a / b;
		return result;
	}

	/**
	 * Converts infix expression to postfix expression.
	 * 
	 * @see {@link https://en.wikipedia.org/wiki/Shunting-yard_algorithm}
	 * @param infix the input expression.
	 * @return the {@link String} object in <b>Reverse Polish notation (RPN)</b>.
	 */
	public String infixToPostfix(final String infix) {
		final String ops = "-+/*";

		StringBuilder sb = new StringBuilder();
		Stack<Integer> s = new Stack<>();

		for (String token : infix.split("")) {
			if (token.isEmpty())
				continue;
			char c = token.charAt(0);
			int idx = ops.indexOf(c);

			// check for operator
			if (idx != -1) {
				if (s.isEmpty())
					s.push(idx);

				else {
					while (!s.isEmpty()) {
						int prec2 = s.peek() / 2;
						int prec1 = idx / 2;
						if (prec2 > prec1 || prec2 == prec1)
							sb.append(ops.charAt(s.pop()));
						else
							break;
					}
					s.push(idx);
				}
			} else if (c == '(') {
				s.push(-2); // -2 stands for '('
			} else if (c == ')') {
				while (s.peek() != -2)
					sb.append(ops.charAt(s.pop()));
				s.pop();
			} else {
				sb.append(token);
			}
		}
		while (!s.isEmpty()) {
			sb.append(ops.charAt(s.pop()));
		}
		return sb.toString();
	}

}
