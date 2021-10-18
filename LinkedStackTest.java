/**
 * This program takes an infix expression and converts it
 * to a postfix expression. Users are allowed to type in
 * any infix expression they would like.
 * 
 * @authors Brandon Leho, Brandon Lequang, Sean Solomon
 */
import java.util.Scanner;

public class LinkedStackTest 
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		
		String quit = "";
		
		do
		{
			System.out.print("Enter an infix to convert: ");
			String infix = scan.nextLine();
		
			//a*b/(c-a)+d*e
		
			System.out.println("The infix expression " + infix + " converted to postfix is: " + convertToPostfix(infix));
			
			//ask user to run the program again
			System.out.print("Would you like to use the program again? (yes or no): ");
			quit = scan.nextLine();
			quit = quit.toLowerCase();
			
			//if user doesn't enter yes or no, prompt user until they enter yes or no
			if (!quit.equals("no") && !quit.equals("n") && !quit.equals("yes") && !quit.equals("y") && !quit.equals("ye"))
			{
				System.out.print("Invalid Input: plz enter yes or no: ");
				quit = scan.nextLine();
				quit = quit.toLowerCase();
			}
			
		}while (quit.equals("yes") || quit.equals("y") || quit.equals("ye"));
		
		System.out.print("Thank you for using our program! Bye Bye :)");
	
		scan.close();
	} //end main

	private static int getPriority(char character)
	{
		switch(character)
		{
		case '(': //open parenthesis will always go first
			return 0;
		case '/': case '*': //multiplication and division will always go last
			return 2;
		case '+': case '-': //addition and subtraction will always go before multiplication and division.
			return 1;
		default:
			return 3;	
		}
	}
	
	public static String convertToPostfix(String infix)
	{
		//add closed parenthesis to make sure extra operators are put into the final output
		infix = infix + ")"; 
		
		//create new empty stack called operatorStack
		LinkedStack<Character> operatorStack = new LinkedStack<Character>();
		
		/**push an open parenthesis to initiate the stack, 
		so the program won't yell at us for having an empty stack*/
		operatorStack.push('(');
		
		//create string for the final output
		String postfix = "";
		
		for (int i = 0; i < infix.length(); i++)
		{
			char character = infix.charAt(i); //current character in the string
			
			//checks to see if the current character is a letter
			if (Character.isLetter(character))
			{
				postfix = postfix + character;
			}
			else if (character == '(') //push open parenthesis to stack
			{
				operatorStack.push(character);
			}
			else if (character == ')')
			{
				//pop every character out of the stack until next open parenthesis
				while (operatorStack.peek() != '(')
				{
					postfix = postfix + operatorStack.peek();
					operatorStack.pop();
				}
				operatorStack.pop();
			}
			else
			{
				int operatorOne = getPriority(character);
				int operatorTwo = getPriority(operatorStack.peek());
				
				/*pop every character until '(' meets '+' or '-'
				 or until '+' or '-' meet '*' or '/'.
				 Then push current character.*/
				while (operatorOne <= operatorTwo)
				{
					postfix = postfix + operatorStack.peek();
					operatorStack.pop();
					operatorTwo = getPriority(operatorStack.peek());
				}
				operatorStack.push(character);
			}
		}		
		return postfix;
	} //end convertToPostfix
} //end LinkedStackTest
