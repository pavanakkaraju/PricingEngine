import java.util.Scanner;

/**
 * @author Pavan
 * The class holds the logic to validate an input 
 * String is palindrome or not.
 * Complexity : O(N/2)
 * Logic:
 * 1.Take input through Sytem.in
 * 2.Start iterating through the string
 * 3.compare nth letter to length-nth letter until half of the String
 * 4.If at any point nth character != length -nth char ,it is not a palindrome
 * 5.Else it is a palindrome 
 *
 */
public class PalindromeValidator {

	String constructorInput="";
	
	/**
	 * Default constructor
	 */
	public PalindromeValidator() {
		super();
	}
	
	/**
	 * Overloaded constructor to call from another object
	 */
	public PalindromeValidator(String constructorInput) {
		super();
		validatePalindrome(constructorInput);
	}
	
	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String input = "";
		PalindromeValidator objPalindromeTest = new PalindromeValidator();
		if(null==args || args.length==0){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a word to check if it is a palindrome");
		input = in.nextLine();
		}
		else{
			input=args[0];
		}
		objPalindromeTest.validatePalindrome(input);
	}

	public void validatePalindrome(String input) {
		if(null!=input && input.trim().length()>0){
		int length = input.length();
		for (int i = 0; i <= length / 2; i++) {
			if (input.toLowerCase().charAt(i) != input.toLowerCase().charAt(length - 1 - i)){
				System.out.println(input + " is not a palindrome.");
			return;
			}
		}
		System.out.println(input + " is a palindrome.");
		}
		else{
			System.out.println("Invalid input");
		}
	}
}
