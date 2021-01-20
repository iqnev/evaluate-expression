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

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import com.iqnev.expression.algorithm.ReversePolishAlgorithm;

/**
 * Unit test for the validation of Reverse Polish Algorithm.
 * 
 * @author Ivelin Yanev <bgfortran@gmail.com>
 * @since 20.01.2021
 *
 */
public class ReversePolishAlgorithmTest {

	private static ReversePolishAlgorithm instance;

	@BeforeClass
	public static void setUpTest() {
		instance = new ReversePolishAlgorithm();
	}

	@Test
	public void testDoCalculate() {
		final String input = "(2+1)*(3+3)";
		double realResult = 18.0;
		double currentResult = instance.doCalculate(input);
		assertThat("The result is not correct!", realResult, is(currentResult));

	}

	@Test
	public void testIsOperator() {
		final String testOperator = "+";

		boolean result = instance.isOperator(testOperator);
		assertThat("The operation is not correct!", result);
	}

	@Test
	public void testInfixToPostfix() {
		final String input = "(2+1)*(3+3)";
		final String realResult = "21+33+*";
		final String currentResult = instance.infixToPostfix(input);

		assertThat("The result is not correct!", realResult, is(currentResult));
	}
}
