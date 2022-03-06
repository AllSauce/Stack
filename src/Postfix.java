import java.util.NoSuchElementException;
import java.util.regex.*;
/**
 * The Postfix class implements an evaluator for integer postfix expressions.
 *
 * Postfix notation is a simple way to define and write arithmetic expressions
 * without the need for parentheses or priority rules. For example, the postfix
 * expression "1 2 - 3 4 + *" corresponds to the ordinary infix expression
 * "(1 - 2) * (3 + 4)". The expressions may contain decimal 32-bit integer
 * operands and the four operators +, -, *, and /. Operators and operands must
 * be separated by whitespace.
 *
 * @author  NN // TODO
 * @version 2017-12-12
 */
public class Postfix extends LinkedList<Integer>{
    public static class ExpressionException extends Exception {
        public ExpressionException(String message) {
            super(message);
        }
    }

    /**
     * Evaluates the given postfix expression.
     *
     * @param expr  Arithmetic expression in postfix notation
     * @return      The value of the evaluated expression
     * @throws      ExpressionException if the expression is wrong
     */
    public static int evaluate(String expr) throws ExpressionException {
         String trimExpr = expr.trim();
         String[] tokens = trimExpr.split("\\s+");

         LinkedList<Integer> stack = new LinkedList<Integer>();
         for(String token : tokens){
             System.out.print(token + ",");
         }
         for(String token : tokens) {
             if(isInteger(token)) {
                 int digit = Integer.parseInt(token);
                 stack.push(digit);
             }
             else if(isOperator(token)) {
                 String operator = token;
                  if (stack.size() < 2){
                      throw new ExpressionException("Too few operands in expression: " +expr);
                  }
                 int digit1 = stack.pop();
                 int digit2 = stack.pop();
                 switch (operator) {
                     case "+":
                         stack.push(digit1 + digit2);
                         break;
                     case "-":
                         stack.push(digit2 - digit1);
                         break;
                     case "*":
                         stack.push(digit1 * digit2);
                         break;
                     case "/":
                         if (digit1 == 0){
                             throw new ExpressionException("Cannot divide by  zero in expression: " +expr);
                         }
                         stack.push(digit2 / digit1);
                         break;

                     default:
                         throw new ExpressionException("Invalid operator in expression " + expr);
                 }
             }
             else {
                 throw new ExpressionException("Invalid expression: "+expr);
             }
         }
         int result = stack.pop();
         if (stack.size() > 0){
             throw new ExpressionException("Too many operands in expression: " +expr);
         }


         return result;
      }

    /**
     * Returns true if s is an operator.
     *
     * A word of caution on using the String.matches method: it returns true
     * if and only if the whole given string matches the regex. Therefore
     * using the regex "[0-9]" is equivalent to "^[0-9]$".
     *
     * An operator is one of '+', '-', '*', '/'.
     */
    private static boolean isOperator(String s) {
        return s.matches("[+]|[-]|[*]|[/]");
    }

    /**
     * Returns true if s is an integer.
     *
     * A word of caution on using the String.matches method: it returns true
     * if and only if the whole given string matches the regex. Therefore
     * using the regex "[0-9]" is equivalent to "^[0-9]$".
     *
     * We accept two types of integers:
     *
     * - the first type consists of an optional '-'
     *   followed by a non-zero digit
     *   followed by zero or more digits,
     *
     * - the second type consists of an optional '-'
     *   followed by a single '0'.
     */
    private static boolean isInteger(String s) {
      boolean nonZeroNumber = s.matches("-?[1-9]\\d*");
      boolean zeroNumber = s.matches("-?0");
      return nonZeroNumber || zeroNumber; 
    }
}
