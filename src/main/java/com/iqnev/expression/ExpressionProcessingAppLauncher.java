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
package com.iqnev.expression;

import com.iqnev.expression.algorithm.ReversePolishAlgorithm;
import com.iqnev.expression.algorithm.TwoStacksAlgorithm;
import com.iqnev.expression.app.App;

/**
 * The launcher class of the File Processing Application. Parsers the input
 * arguments and runs the application.
 * 
 * @author Ivelin Yanev <bgfortran@gmail.com>
 * @since 19.01.2021
 *
 */
public class ExpressionProcessingAppLauncher {

	public static void main(String[] args) {
		if (args.length > 0 && args.length <= 2) {
			final App app = App.getInstance();
			if (TwoStacksAlgorithm.TWO_STACKS_STRATEGY.equals(args[0])
					|| ReversePolishAlgorithm.REVESE_POLIS_STRATEGY.equals(args[0])) {
				if (args[1] != null) {
					app.start(args[1], args[0]);
				} else {
					printAppHelp();
				}
			} else {
				app.start(args[0], null);
			}
		} else {
			printAppHelp();
		}

	}

	private static void printAppHelp() {
		System.out.println("######## Invalid Arguments ##########");
		System.out.println("Option 1: reversepolis | twostacks [expression]");
		System.out.println("Option 2: [expression]");

	}

}
