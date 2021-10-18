import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T> {

	private Node topNode; // references the first node in the chain

	public LinkedStack() {
		topNode = null;
	} // end default constructor

	public void push(T newEntry) // start push method
	{
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
		// topNode = new Node(newEntry, topNode); // Alternate code
	} // end push method

	public T pop() // start pop method
	{
		T top = peek(); // Might throw EmptySttackException
		// Assertion: topNode != null
		topNode = topNode.getNextNode();

		return top;
	} // end pop method

	public T peek() // start peek method
	{
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			return topNode.getData();
		}
	} // end peek method

	public boolean isEmpty() // start isEmpty method
	{
		return topNode == null;
	} // end isEmpty method

	public void clear() // start clear method
	{
		topNode = null;
	} // end clear method

	private class Node {
		private T data; // entry in stack
		private Node next; // link to next node

		private Node(T dataPortion) // start constructor
		{
			this(dataPortion, null);
		} // end constructor

		private Node(T dataPortion, Node nextNode) // start constructor
		{
			data = dataPortion;
			next = nextNode;
		} // end constructor

		private T getData() // start getData method
		{
			return data;
		} // end getData method

		private Node getNextNode() // start getNextNode method
		{
			return next;
		} // end getNextNode method
	} // end Node
}// end LinkedStack
