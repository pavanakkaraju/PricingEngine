import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 	Pavan The class holds the logic to find the complementary sum against a given ref 
 *         	Complexity : O(N) 
 *          Logic: 
 *          1.Take input int array and reference number
 *          2.Form a hashmap with array element & array index as key-value pair
 *         	3.Iterate through the inputArray and to find
 *         complementary pair inputArray[i] + hashmap.(get(k-inputArray[i])) 
 *          4.if above element exist , complementary pair is found.
 */

public class ComplemetaryFinder {

	/**
	 * 
	 */
	public ComplemetaryFinder() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ComplemetaryFinder objComplemetaryFinder=new ComplemetaryFinder();
		int[] inputArray={-1,-7,-8,-11,4,16,12,1122,23,-42,-10,-5};
		objComplemetaryFinder.printComplementaryPairs(inputArray, -15);
		
		int[] inputArray1={1,7,8,11,4,16,12,1122,23,42,10,5};
		objComplemetaryFinder.printComplementaryPairs(inputArray1, 15);
		
		int[] inputArray2={1,7,8,11,4,16,12,1122,23,42,10,5};
		objComplemetaryFinder.printComplementaryPairs(inputArray2, 15);
		
		int[] inputArray3={-1,7,8,-11,4,16,12,1122,23,-42,-10,-5};
		objComplemetaryFinder.printComplementaryPairs(inputArray3, -15);

	}
	
	public void printComplementaryPairs(int[] inputArray,int compReference){
		Map<Integer,Integer> inputMap=new LinkedHashMap<>();
		int temp=0;
		int indexOfMap=0;
		
		/**form a hashmap with array element & array index as key-value pair
		 * */
		if(null!=inputArray && inputArray.length>0){
			for(int i=0;i<inputArray.length;i++){
				inputMap.put(inputArray[i], i);
			}
			System.out.println(inputMap);
			/**Iterate through the inputArray and to find complementary pair
			 * inputArray[i] + hashmap.(get(k-inputArray[i]))
			 * if above element exist , complementary pair is found.
			 * */	
			Iterator<Integer> iterInputMap =inputMap.keySet().iterator();
			indexOfMap=0;
			while(iterInputMap.hasNext()){
				temp=iterInputMap.next();
				if(null!=inputMap.get(compReference-temp)){
					System.out.println(compReference +"= inputArray["+indexOfMap+"] + inputArray["+inputMap.get(compReference-temp)+"]");
				}
				indexOfMap++;
			}
		}
	}

}
