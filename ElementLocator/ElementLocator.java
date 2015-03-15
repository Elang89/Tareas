import java.util.*; 

public class ElementLocator {
	
	/*In the main entry point for the program various methods are called out 
	to determine the positions of the largest numbers, a loop has also been created
	to allow the user whether he wants to continue with another array or to exit the
	program*/

	public static void main(String[] args){
		int[] unsortedArray;
		int[] sortedArray;
		boolean continueProgram = true;
		Scanner inputScanner = new Scanner(System.in);
		String answer = new String();
		while (continueProgram == true) {
			unsortedArray = buildArray();
			sortedArray = new int[unsortedArray.length];
			System.arraycopy( unsortedArray, 0, sortedArray, 0, unsortedArray.length);
			System.out.println(Arrays.toString(unsortedArray));
			quickSort(sortedArray, 0, sortedArray.length - 1);
			returnPositions(sortedArray, unsortedArray);
			System.out.println("Do you wish to create another list of elements? y/n");
			while(!inputScanner.hasNext("[yn]")){
				System.out.println("Invalid input. Enter y for yes or n for no");
				inputScanner.next();
			}
			answer = inputScanner.nextLine();
			if (answer.equals("n")){
				continueProgram = false;
			}
		}
	}
	
	public static int[] buildArray() {
		Scanner inputScanner = new Scanner(System.in);
		int elementsInserted = 0;
		int number;
		int listLength;
		int [] list;
		
		/*
		* This part of the algorithm asks the user for input, specifically, 
		* numbers that are positive. If the number is not positive or it is less 
		* than 2 then the do-while loop will continue to execute. If what the user 
		* inputs is not a number, then the inner while loop will continue its execution, 
		* if not then, the scanner will assign the number chosen by the user to the variable
		* listLength which is used to construct the list itself
		*/
		
		do{
			System.out.println("Enter a positive number as the length of your list");
			while (!inputScanner.hasNextInt()){
				System.out.println("Invalid input. Enter a positive number");
				inputScanner.next();
			}
			listLength = inputScanner.nextInt();
			list = new int[listLength];
		} while (listLength < 2);   /* At least two numbers are necessary to extract the two largest indexes from the list */
		
		/* Through these loops, the user is asked to input the number of elements chosen before
		* the scanner makes sure that what is being used as input is a number, otherwise it 
		* maintains the user within the loop, finally after having built the array it is printed
		* on the screen */
		
		while (elementsInserted < listLength){
			Random randomNumber = new Random();
			number = randomNumber.nextInt(100);
			list[elementsInserted] = number;
			elementsInserted++;
		}
		return list;
	}
	
	/* The divide and conquer algorithm called the quicksort, is used 
	as a piggyback to sort the list in order to obtain the two largest elements
	which should be found in the last two indexes of the sorted array */
	
	/* Quicksort retrieved from: http://www.programcreek.com/2012/11/quicksort-array-in-java/*/
	
	public static void quickSort(int[] list, int low, int high) {
 
		if (list == null || list.length == 0)
			return;
 
		if (low >= high)
			return;
 
		int middle = low + (high - low) / 2;
		int pivot = list[middle];
 
		int i = low, j = high;
		while (i <= j) {
			while (list[i] < pivot) {
				i++;
			}
 
			while (list[j] > pivot) {
				j--;
			}
 
			if (i <= j) {
				int temp = list[i];
				list[i] = list[j];
				list[j] = temp;
				i++;
				j--;
			}
		}
 
		if (low < j)
			quickSort(list, low, j);
 
		if (high > i)
			quickSort(list, i, high);
	}
	
	/* After having sorted the array, a comparison is made between both the sorted and unsorted array.
	The largest elements, which were found at the end in the sorted array, are compared against the 
	elements in the unsorted array, once they are found, their position is printed on the screen, so that the user
	may verify */
	
	public static void returnPositions(int [] sortedArray, int [] unsortedArray){
		int length = unsortedArray.length;
		int firstLargest = sortedArray[length - 1];
		int secondLargest = sortedArray[length - 2];
		for (int i = 0; i < length; i++){
			if (firstLargest == unsortedArray[i]){
				firstLargest = i;
			}if (secondLargest == unsortedArray[i]) {
				secondLargest = i;
			}
		}
		System.out.println("The largest element is found in position " + firstLargest);
		System.out.println("The second largest element is found in position " + secondLargest);
	}
	
}	
