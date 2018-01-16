import java.util.*;
public class QuickSort {
	
	static Random rand;

	static void swap(int a[], int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/*
		Proof of correctness of procedure partition:

		We maintain 3 invariants. 

		1. l <= i <= pivot <= j <= r
		2. all x in [l, i) satisfy A[x] <= A[pivot]
		3. all x in (j, r] satisfy A[x] >= A[pivot]

		Initialization: 

			This is trivially true.
	
		Maintenance:

			The first two while loops preserve all properties.
			Following that, we swap the values of the elements at i and j.
		
			There are a couple cases

			Case 1: i == pivot and j == pivot
				Since i <= pivot <= j this happens iff i==j
				We are done partitioning and return pivot
			Case 2: i == pivot and pivot < j
				After setting pivot = j, i+1 <= pivot so 
				property 1 is satisfied. 
				Properties 2 and 3 clearly are as well.
			Case 3: i < pivot and pivot == j
				Symmetric with Case 2. 
			Case 4:
				Since i < pivot < j
				i+1 <= pivot <= j-1 so property 1 is satisfied.
				And clearly after swapping properties 2 and 3 are satisfied.

		Termination
		
			The code terminates iff i==j in the while loop.

			This must happen because j-i is decreasing after each 
			iteration but by invariant 1 j-i >= 0. 

			By invariants 2 and 3, at termination, 
			all x in [l, pivot-1] have A[x] <= A[pivot]
			all x in [pivot+1, r] have A[x] >= A[pivot]
	*/

	static int partition(int A[], int l, int r, int pivot) {
		int i = l, j = r;
		while (true) {
			while (i < pivot && A[i] <= A[pivot]) ++i;
			while (j > pivot && A[j] >= A[pivot]) --j;
			if (i==j) return pivot;
			swap(A, i, j);
			if (i==pivot) {
				pivot = j;
				++i;
			} else if (j==pivot) {
				pivot = i;
				--j;
			} else {
				++i;
				--j;
			}
		}
	}

	static void quickSort(int a[], int l, int r) {
		if (l>=r) return;
		int p = partition(a, l, r, rand.nextInt(r-l)+l);
		quickSort(a, l, p-1);
		quickSort(a, p+1, r);
	}

	public static void main(String[] args) {
		rand = new Random();
		int N = 10000;
		int[] a = new int[N];
		for (int i = 0; i < N; ++i) a[i] = rand.nextInt(10+1);
		quickSort(a, 0, a.length-1);
		for (int i = 0; i + 1 < a.length; ++i) {
			if (a[i+1] < a[i]) {
				System.out.println("Failed to sort");
				return;
			}
		}
		System.out.println("Success");
	}
}