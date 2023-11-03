import java.util.Arrays;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;




public class main {
	public static int naive(int a, int n) {
		int acc = 1;
		for (int i = 0; i < n; i++) {
			acc = acc * a;

		}

		return acc;

	}

	public static int DivideAndConquer(int a, int n) {
		if (n == 0) {
			return 1;

		} else if (n % 2 == 0) {

			int newPower = DivideAndConquer(a, n / 2);
			return newPower * newPower;
		} else {

			int newPower = DivideAndConquer(a, n / 2);
			return a * newPower * newPower;
		}
	}

	public static String findPairsWithSum(int[] arr, int target) {
		String s = "";
		int n = arr.length - 1;
		for (int i = 0; i < n; i++) {
			int valueTobeFound = target - arr[i]; // 10-1=9 then go and find 9 if it exists in the array
			int found = binarySearch(arr, i + 1, n, valueTobeFound);

			if (found != -1) {
				s = s + ("Pair found: (" + arr[i] + ", " + arr[found] + ")");
			}
		}
		return s;
	}

	public static void mergeHelper(int length, int[] left, int[] right) {
		int[] arr = new int[length];
		int i = 0, j = 0, k = 0;//// i is left j is right k is the array

		while (i < left.length && j < right.length) {
			if (right[j] <= left[i]) { // if right smaller than left then place the right element first
				arr[k] = right[j];
				j++;
			} else {
				arr[k] = left[i]; // if the left element smaller then place the left element first
				i++;

			}
			k++;
		}

		while (i < left.length) { // if left array still not placed
			arr[k] = left[i];
			i++;
			k++;
		}

		while (j < right.length) { // if right array still not placed
			arr[k] = right[j];
			j++;
			k++;
		}
	}

	public static void mergeSort(int[] arr) {
		if (arr.length > 1) {

			int[] left = new int[arr.length / 2]; // first half of the array
			int[] right = new int[arr.length - arr.length / 2]; // second half of the array

			mergeSort(left);
			mergeSort(right);

			mergeHelper(arr.length, left, right);
		}
	}

	public static int binarySearch(int[] arr, int left, int right, int complement) {
		if (left <= right) {
			int middle = left + (right - left) / 2;

			if (arr[middle] == complement) {
				return middle;
			}

			if (arr[middle] < complement) {
				return binarySearch(arr, middle + 1, right, complement);
			}

			return binarySearch(arr, left, middle - 1, complement);
		}
		return -1;/// not found
	}

	public static void main(String[] args) {
		int acc = naive(5, 7);
		System.out.println(acc);
		int result = DivideAndConquer(5, 7);
		System.out.println(result);

		

		System.out.println("n,a, Naive Time (ms), Divide & Conquer Time (ms)");

		for (int n = 1; n <= 1000000; n *= 10) {
		

			int a = 5;
			long startTime = System.nanoTime();
			naive(a, n);
			long endTime = System.nanoTime();
			long NaiveTime = (endTime - startTime) / 1000000; 
			

			startTime = System.nanoTime();
			DivideAndConquer(a, n);
			endTime = System.nanoTime();
			long DivideAndConquerTime = (endTime - startTime) / 1000000; 
			

			System.out.println(n + "," + a + ", " + NaiveTime + ", " + DivideAndConquerTime);
            
		}

		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8 };
		int targetSum = 10;

		Arrays.sort(arr); // Sort the array using Merge Sort for efficient binary search

		String s = findPairsWithSum(arr, targetSum);
		System.out.println(s);
		

		System.out.println("n, pairs (ms)");

		for (int n = 1; n <= 1000000; n *= 10) {
		

			long startTime = System.nanoTime();
			findPairsWithSum(arr, targetSum);
			long endTime = System.nanoTime();
			long PairsTime = (endTime - startTime) / 1000000; 
			

			System.out.println(n + "," + PairsTime);

		}
	}
	
	
	// question 1 c)In the naive iterative method, we multiply 'a' by itself 'n' times
	// which means it has a O(n) complexity.
	// In the divide-and-conquer method,  The recurrence relation is
	// T(n) = T(n/2) + O(1) similar to binary search
			
    //	using recursive trees		
	// T(n)
	// / \
	// T(n/2) T(n/2)
	// / \ / \
	// T(n/4) T(n/4) ...

	// The  number of levels in the tree log₂(n)
	// because we keep dividing 'n' by 2 until it becomes 1.
	// At each level, we perform constant work, which is O(1). Therefore, the total
	// work done at each level is O(1).
	// Total Work = O(1) * log₂(n) = O(log(n))

	/*using master theorem
	 *  we need to compare f(n) to n^log_b(a). In this case: a=1 b=2 f(n)= O(1)
	 *  n^log_b(a) = n^log_2(1) = n^0 = 1 so it falls
	 * within Case 2 of the Master theorem as f(n)=n^log_b(a) as O(1)=1 so since T(n) = Θ(n^log_b(a)*log(n))then
	 * T(n)=O(1*log(n))
	 * T(n) = Θ(log n)
	 */
	// the experimental results meet the theortical results
	
	
	
	

	//question 2 c)merge sort recurrence from lecture is 2T(n/2)+O(1)+O(n) and its time complexity using recursive tree is O(n*log(n))
	//binary search recurrence from lecture is T(n/2)+O(1)and its time complexity using recursive tree is O(log(n))
	// since we are using binary search n times as  binary searches is performed while looping through the array O(n)
	//so the overall complexity is O(n)*O(log(n))= O(n*log(n)) since the merge sort and looping with binary search is O(n*log(n))
	//then overall complexity is O(n*log(n))
	//the experimental results meet the theortical results
}
