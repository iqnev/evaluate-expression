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

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.iqnev.expression.context.Strategy;

/**
 * Evaluations of Infix Expression using Two Stacks.
 * 
 * <pre>
 * Algorithm:
 * </pre>
 * <p>
 * <ul>
 * <li>Operator Stack.
 * <li>Operand Stack.
 * </ul>
 * <p>
 * If we come across an operand , We push the operand into the Operand Stack. If
 * we come across an an operator, We follow the following algorithm for
 * operators.
 * <p>
 * Priority of operators: *,/ --> 3 +,- --> 2 ( --> 1
 * </p>
 * 
 * @author Ivelin Yanev <bgfortran@gmail.com>
 * @since 19.01.2021
 *
 */
public class TwoStacksAlgorithm implements Strategy {
	public final static String TWO_STACKS_STRATEGY = "twostacks";
	private List<String> precedence;

	public TwoStacksAlgorithm() {
		precedence = new ArrayList<String>();
		precedence.add("+");
		precedence.add("-");
		precedence.add("*");
		precedence.add("/");
	}

	@Override
	public double doCalculate(String expression) {
		if (expression == null)
			return 0;
		String[] str = expression.split("");

		return extracted(str);
	}

	/**
	 * If the operator stack is empty, push the operator into the stack. If the
	 * operator stack is not empty, compare the priority of operator on the top of
	 * the stack to the current operator let t = priority of operator on the top of
	 * the stack, p = priority of current operator.
	 * 
	 * @param tokens
	 * @return
	 */
	public double extracted(String[] tokens) {
		Stack<String> ops = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();

		for (String s : tokens) {
			if (s.equals("(")) {
				ops.push("(");
			} else if (s.equals(")")) {
				while (!ops.peek().equals("(")) {
					double r = vals.pop();
					double l = vals.pop();
					double result = calculate(ops.pop(), l, r);
					vals.push(result);

				}
				ops.pop();
			} else if (precedence.contains(s)) {
				int pC = lookUpPriority(s);
				while (!ops.empty() && lookUpPriority(ops.peek()) >= pC) {
					double r = vals.pop();
					double l = vals.pop();
					double result = calculate(ops.pop(), l, r);
					vals.push(result);
				}
				ops.push(s);
			} else {
				vals.push(Double.parseDouble(s));
			}
		}

		while (!ops.empty()) {
			// if(ops.peek().equals("(")) break;
			double r = vals.pop();
			double l = vals.pop();
			double result = calculate(ops.pop(), l, r);
			vals.push(result);
		}

		return vals.pop();

	}

	public int lookUpPriority(String ch) {
		switch (ch) {
		case "(":
			return 1;
		case "+":
		case "-":
			return 2;

		case "*":
		case "/":
			return 3;
		}
		return -1;
	}

	public double calculate(String op, double l, double r) {
		if (op.equals("+")) {
			return l + r;
		} else if (op.equals("-")) {
			return l - r;
		} else if (op.equals("*")) {
			return l * r;
		} else if (op.equals("/")) {
			if (r > 0) {
				return l / r;
			}
			return 0;
		}
		return -1;
	}
}
