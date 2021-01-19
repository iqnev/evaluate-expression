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
package com.iqnev.expression.app;

import com.iqnev.expression.algorithm.ReversePolishAlgorithm;
import com.iqnev.expression.algorithm.TwoStacksAlgorithm;
import com.iqnev.expression.context.Context;

/**
 * @author Ivelin Yanev <bgfortran@gmail.com>
 * @since 19.01.2021
 *
 */
public class App {

	private App() {
	}

	private static class LazyHolder {
		static final App INSTANCE = new App();
	}

	public static App getInstance() {
		return LazyHolder.INSTANCE;
	}

	/**
	 * 
	 * @param expression
	 * @param strategy
	 */
	public void start(String expression, String strategy) {

		if (strategy == null || strategy.equals(TwoStacksAlgorithm.TWO_STACKS_STRATEGY)) {
			Context context = new Context(new TwoStacksAlgorithm());
			System.out.println("The result is: " + context.executeStrategy(expression));
		} else {
			Context context = new Context(new ReversePolishAlgorithm());
			System.out.println("The result is: " + context.executeStrategy(expression));
		}
	}
}
