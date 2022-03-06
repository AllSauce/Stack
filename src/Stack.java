import java.util.EmptyStackException;
/*
 * This is an interface for stacks.
 *
 * Since LinkedLists is very similiar to stacks,
 * this inteface can be used within a LinkedList class aswell.
 * The methods included in this interface are:
 * push(), pop(), top(), size() and isEmpty().
 *
 * @author Krenar Manxhuka
 * @version 2021-02-05
 */
public interface Stack<T>{

  /*
   * Adds an element
   * to the top of
   * the stack.
   */
  void push(T elem);

  /*
   * Removes the
   * top element
   * of the stack.
   */
  T pop() throws EmptyStackException;

  /*
   * Returns the
   * top element
   * of the stack.
   * @return top element of stack.
   */
  T top() throws EmptyStackException;

  /*
   * Returns the
   * size of the
   * stack.
   * @return size of stack as int.
   */
  int size();

  /*
   * Returns true if
   * the stack is empty.
   * @return true or false.
   */
  boolean isEmpty();

}
