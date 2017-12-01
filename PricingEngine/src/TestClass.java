/**
 * 
 */

/**
 * @author pavan
 *
 */
public class TestClass {

	/**
	 * 
	 */
	public TestClass() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PalindromeValidator objPalindromeTest=new PalindromeValidator();
		objPalindromeTest.validatePalindrome("tattarrattat");
		objPalindromeTest.validatePalindrome("detartrated");
		objPalindromeTest.validatePalindrome("Rotavator");
		objPalindromeTest.validatePalindrome("redivider");
		objPalindromeTest.validatePalindrome("Malayalam");
		objPalindromeTest.validatePalindrome("george");
		objPalindromeTest.validatePalindrome("pin,nip");
		objPalindromeTest.validatePalindrome("snowfall");
		objPalindromeTest.validatePalindrome(null);
		objPalindromeTest.validatePalindrome("awt@@#$^,");
		
		//http://www.baeldung.com/java-read-lines-large-file
	}

}
