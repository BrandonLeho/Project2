/**
 * This program allows the user to input a postfix expression
 * into the program. If the user inputs an expression that
 * has variables to be filled in ex: a = 1, b = 2, c =3...
 * then the program will allow the user to fill in those
 * variables. After that, the program will evaluate the
 * postfix and show the answer to the user.
 * 
 * @authors: Brandon Leho, Brandon Lequang, Sean Solomon
 */
import java.util.Scanner;

public class ArrayStackTest {

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);

		String quit = "";
		
		do 
		{
			System.out.print("Enter a postfix to evaluate (no spaces): ");
			String postfix = scan.nextLine();
			
			//23*42-/56*+
			
			//ab*ca-/de*+
			//a = 2, b = 32, c = 4, d = 5, e = 6
			
			/* This next chunk of code starting from here to the line with "postfix = new String(tempPostfix);"
			 * is here to assign variables to the expression if the user inputed variable to their expression
			 * i.e. a = 1, b = 2, c = 3... 
			 * 
			 * It will first create a char array that will copy the postfix that the user inputed. 
			 * Then it will go through each character in the string and check to see if it's a letter.
			 * If it's a letter, it will prompt the user to assign the letter a value.
			 * All updated values will be put into the duplicate array of the postfix.
			 * If it detects a repeated letter, it will assign that letter the number it was assigned
			 * and move on to the next character.
			 * 
			 * Once it has gone through the entire string, the duplicate array will dump all of its 
			 * updated values into the postfix. 
			 */
			
			//duplicate char array of postfix
			char[] tempPostfix = new char[postfix.length()];	
			for (int i = 0; i < postfix.length(); i++)
			{
				tempPostfix[i] = postfix.charAt(i);
			}		
			
			for (int i = 0; i < postfix.length(); i++)
			{
				char character = postfix.charAt(i); //current character	
				char value;	//updated value
				boolean found = false; //found repeated letter?
						
				//check to see if the current character is a letter
				if (Character.isLetter(character))
				{	
					//search for repeated letters
					for (int j = 0; j < postfix.length(); j++)
					{
						char tempCharacter = tempPostfix[j];	
						
						//does the character letter repeat and is it already assigned a number?
						if (character == postfix.charAt(j) && Character.isDigit(tempCharacter))
						{
							found = true;
							tempPostfix[i] = tempCharacter;
						}
					}
					//if no repeated letters where found, prompt user to assign the value
					if (found == false)
					{
						System.out.print("Enter the value of " + character + ": ");
						value = scan.nextLine().charAt(0);
					
						//if the user input is not a number, prompt user again until it's a number
						while (Character.isLetter(value))
						{
							System.out.print("Invalid Input: Dude, enter a number as the value\nEnter the value of " + character + ": ");
							value = scan.nextLine().charAt(0);
						}
						tempPostfix[i] = value;
					}
					
				}
				else //else if no letters are found, assign current character to char array
				{
					tempPostfix[i] = character; 
				}
			}
			postfix = new String(tempPostfix); //update postfix with the new values
			
			//show answer
			System.out.println("The postfix evaluation of " + postfix + " is: " + evaluatePostfix(postfix));
			
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
	
	/**Evaluates a postfix expression
	 @param postfix	The expression that is to be evaluates
	 @return The final value in the stack*/
	static int evaluatePostfix(String postfix)
	{
		//create a new empty stack called valueStack
		ResizeableArrayStack<Integer> valueStack = new ResizeableArrayStack<>();

		for (int i = 0; i < postfix.length(); i++)
		{
			char character = postfix.charAt(i); //current character in the string
			
			//If the current character is an operand, then push it to the stack.
			if(Character.isDigit(character))
			{
				valueStack.push(character - '0');
			}	
			else //else if the current character is an operator, pop the top two values and use them with the current operator.
			{
				int operandOne = valueStack.pop(); //gets value currently at the top of the stack
				int operandTwo = valueStack.pop(); //gets current second value from the top of the stack 
				
				//switch case to determine which operator to use, then push result to stack.
				switch(character)
				{
					case '+':
						valueStack.push(operandTwo + operandOne);
						break;
					case '-':
						valueStack.push(operandTwo - operandOne);
						break;
					case '/':
						valueStack.push(operandTwo / operandOne);
						break;
					case '*':
						valueStack.push(operandTwo * operandOne);
						break;
					default :
						break; //ignore invalid inputs
				}
			}
		}
		return valueStack.peek();
	} //end evaluatePostfix	
} //end ArrayStackTest
