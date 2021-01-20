# evaluate-expression

A command-line application in Java that allows the user to Evaluate the Value of an Arithmetic Expression.



## About
The application implements two approaches to solving a problem. The algorithms I use are described below.


## Input Format
The input format of input expression is:
`(2+1)*(3+3)`

## Output Format
The application prints the result in double precision. For example the output of an above expression is: 
`The result is: 18.0`

## How does it work?

The first approach in the first approach I use two stacks: Double-stack algorithm.

**Operand:** then push onto the operand stack.

**Operator:** then push onto the operator stack.

This algorithm has Time Complexity: **O(n)**.

The second approach is with Reverse Polish notation. For more information, please look 
https://en.wikipedia.org/wiki/Reverse_Polish_notation
https://en.wikipedia.org/wiki/Shunting-yard_algorithm
https://leachlegacy.ece.gatech.edu/revpol/

This algorithm has Time Complexity: **O(n)**

## Packaging and running the application
The application can be packaged using:

    mvn clean install

It produces the algoritm-0.0.1-SNAPSHOT-jar-with-dependencies.jar file in the /target directory

If you want to run the application, you have to set the following input arguments:

**Option 1: reversepolis | twostacks [expression]**

Where reversepolis | twostacks is the given algorithm, and [expression] is out Arithmetic Expression

**Option 2: [expression]**
Here you can set only the out Arithmetic Expression. The default algorithm is twostacks(TwoStacksAlgorithm)

## Usage:

```java
java -jar algoritm-0.0.1-SNAPSHOT-jar-with-dependencies.jar '(2+1)*(3+3)'

```
or

```java
java -jar algoritm-0.0.1-SNAPSHOT-jar-with-dependencies.jar reversepolis '(2+1)*(3+3)'
```


## License
This project is licensed under the Apache License 2.0. Check out the license text inside the `LICENSE` file.


## TODO:
Implement full Test coverage(because the current unit tests do not cover all cases) and code optimization.
