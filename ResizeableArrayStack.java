import java.util.EmptyStackException;
import java.util.Arrays;

public class ResizeableArrayStack<T> implements StackInterface<T>
{
	
	private T[]stack;	//Array of stack entries
	private int topIndex;	//index of top entry
	private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 1000;
	
	public ResizeableArrayStack()
	{
		this(DEFAULT_CAPACITY);
	} //end default constructor
	
	public ResizeableArrayStack(int initialCapacity)
	{
		integrityOK = false;
		checkCapacity(initialCapacity);
		
		//The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[]tempStack = (T[])new Object[initialCapacity];
		stack = tempStack;
		topIndex = -1;
		integrityOK = true;
	} //end constructor
	
	public void push(T newEntry)
	{
		checkIntegrity();
		ensureCapacity();
		stack[topIndex + 1] = newEntry;
		topIndex++;
	} //end push
	
	private void ensureCapacity()
	{
		if (topIndex >= stack.length - 1) //if array is full, double its size
		{
			int newLength = 2 * stack.length;
			checkCapacity(newLength);
			stack = Arrays.copyOf(stack, newLength);
		} //end if
	} //end ensureCapacity
	
	public T pop()
	{
		checkIntegrity();
		if (isEmpty())
		{
			throw new EmptyStackException();
		}
		else
		{
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			return top;
		} //end if
	} //end pop
	
	public T peek()
	{
		checkIntegrity();
		if (isEmpty()) 
		{
			throw new EmptyStackException();
		}
		else
		{
			return stack[topIndex];
		} //end if
	} //end peek
	
	public boolean isEmpty()
	{
		return topIndex < 0;
	} //end isEmpty
	
	// Throws an exception if the client requests a capacity that is too large.
	private void checkCapacity(int capacity)
	{
		if(capacity > MAX_CAPACITY)
		{
			throw new IllegalStateException("Ayo! U tryna make a bag capacity bigger than the maximum of " + MAX_CAPACITY);
		}
	} //ends checkCapacity
	
	//Throws an exception if object is not initialized.
	private void checkIntegrity()
	{
		if(integrityOK == false)
		{
			throw new SecurityException("Bro, your input is invalid >:(");
		}
	} //end checkIntegrity
	
	public void clear()
	{
		checkIntegrity();
		
		//Remove references to the objects in the stack,
		//but do not deallocate the array
		while (topIndex > -1)
		{
			stack[topIndex] = null;
			topIndex--;
		} //end while
		//Assertion: topIndex is -1
	} //end clear
} //end ResizeableArrayStack