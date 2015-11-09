package SortUtil;

import java.util.Arrays;

public class CountingSort {

	public static void main(String[] args) {
		int[] a = new int[]{121,2,3,42,56,932,33,21,356,4,1,9,34};
		a = countingSort(a, 1000);
		System.out.println(Arrays.toString(a));
	}
	
	public static int[] countingSort(int[] a, int max) {
		int[] b = new int[a.length];
		
		int[] c = new int[max];
		
		for (int i : a) {
			c[i] = c[i] + 1;
		}
		
		for (int i = 1; i < c.length; i++) {
			c[i] = c[i-1] + c[i];
		}
		

		for (int i = a.length-1; i >= 0; i--) {
			b[c[a[i]]-1] = a[i];
			c[a[i]] = c[a[i]] - 1;
		}
		return b;
	}
}
