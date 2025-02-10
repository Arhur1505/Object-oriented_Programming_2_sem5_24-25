import java.io.*;
import java.util.*;

public class Zadanie9 {
    public static void main(String[] args) {
        String filePath = "test.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                try {
                    System.out.println("Line " + lineNumber + ": " + line);
                    double result = evaluateExpression(line);
                    System.out.println("Result: " + result);
                } catch (SyntaxErrorException | ZeroDivisionException | RuntimeErrorException | IntegerLimitException
                        | IllegalCharacterException e) {
                    System.err.println("Error on line " + lineNumber + ": " + e.getMessage());
                }
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static double evaluateExpression(String expression) throws SyntaxErrorException, ZeroDivisionException,
            RuntimeErrorException, IntegerLimitException, IllegalCharacterException {
        if (!expression.matches("[0-9+\\-*/()=\\s]+")) {
            throw new IllegalCharacterException("Illegal characters found in the expression.");
        }

        if (!areParenthesesBalanced(expression)) {
            throw new SyntaxErrorException("Syntax error: ')' expected.");
        }

        try {
            Queue<String> rpn = toRPN(expression);
            return evaluateRPN(rpn);
        } catch (ArithmeticException e) {
            throw new ZeroDivisionException("Zero division error.");
        } catch (NumberFormatException e) {
            throw new IntegerLimitException("Integer limit (>32-bit required).");
        }
    }

    private static boolean areParenthesesBalanced(String expression) {
        int balance = 0;
        for (char c : expression.toCharArray()) {
            if (c == '(')
                balance++;
            if (c == ')')
                balance--;
            if (balance < 0)
                return false;
        }
        return balance == 0;
    }

    private static Queue<String> toRPN(String expression) throws SyntaxErrorException, IllegalCharacterException {
        Stack<Character> operators = new Stack<>();
        Queue<String> output = new LinkedList<>();
        StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/()=", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty())
                continue;

            if (token.matches("\\d+")) {
                output.add(token);
            } else if ("+-*/".contains(token)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token.charAt(0))) {
                    output.add(String.valueOf(operators.pop()));
                }
                operators.push(token.charAt(0));
            } else if (token.equals("(")) {
                operators.push('(');
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    output.add(String.valueOf(operators.pop()));
                }
                if (operators.isEmpty() || operators.pop() != '(') {
                    throw new SyntaxErrorException("Syntax error: ')' expected.");
                }
            } else if (token.equals("=")) {
                continue;
            } else {
                throw new IllegalCharacterException("Illegal characters found in the expression.");
            }
        }

        while (!operators.isEmpty()) {
            if (operators.peek() == '(') {
                throw new SyntaxErrorException("Syntax error: ')' expected.");
            }
            output.add(String.valueOf(operators.pop()));
        }

        return output;
    }

    private static double evaluateRPN(Queue<String> rpn)
            throws ArithmeticException, NumberFormatException, RuntimeErrorException {
        Stack<Double> stack = new Stack<>();

        while (!rpn.isEmpty()) {
            String token = rpn.poll();
            if (token.matches("\\d+")) {
                double value = Double.parseDouble(token);
                if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
                    throw new NumberFormatException("Value out of 32-bit range.");
                }
                stack.push(value);
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        if (b == 0)
                            throw new ArithmeticException("Division by zero.");
                        stack.push(a / b);
                        break;
                    default:
                        throw new RuntimeErrorException("Unknown operator: " + token);
                }
            }
        }

        return stack.pop();
    }

    private static int precedence(char operator) {
        if (operator == '+' || operator == '-')
            return 1;
        if (operator == '*' || operator == '/')
            return 2;
        return -1;
    }

    // Definicje wyjątków
    static class SyntaxErrorException extends Exception {
        public SyntaxErrorException(String message) {
            super(message);
        }
    }

    static class ZeroDivisionException extends Exception {
        public ZeroDivisionException(String message) {
            super(message);
        }
    }

    static class RuntimeErrorException extends Exception {
        public RuntimeErrorException(String message) {
            super(message);
        }
    }

    static class IntegerLimitException extends Exception {
        public IntegerLimitException(String message) {
            super(message);
        }
    }

    static class IllegalCharacterException extends Exception {
        public IllegalCharacterException(String message) {
            super(message);
        }
    }
}