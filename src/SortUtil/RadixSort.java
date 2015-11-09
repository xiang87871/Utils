package SortUtil;

import java.util.Arrays;

public class RadixSort {
	public static void main(String[] args) {
		int[] a = new int[]{121,2,3,42,56,932,33,21,356,4,1,9,34};
		a = radixSort(a, 3);
		System.out.println(Arrays.toString(a));
	}
	
	public static int[] radixSort(int[] a, int d) {
		for (int i = 1; i <= d; i++) {
			radixCountingSort(a, i);
		}
		return a;
	}
	
	private static int getInD(int x,int max, int d) {
		int radix = (int) Math.pow(max, d);
		int radix1 = (int) Math.pow(max, d-1);
		return x%radix/radix1;
	}
	
	public static void radixCountingSort(int[] a, int d) {
		
		int max = 10;
		
		int[] b = new int[a.length];
		
		int[] c = new int[max];

		for (int i : a) {
			c[getInD(i, max, d)] = c[getInD(i, max, d)] + 1;
		}
		
		for (int i = 1; i < c.length; i++) {
			c[i] = c[i-1] + c[i];
		}
		

		for (int i = a.length-1; i >= 0; i--) {
			b[c[getInD(a[i], max, d)]-1] = a[i];
			c[getInD(a[i], max, d)] = c[getInD(a[i], max, d)] - 1;
		}
		System.arraycopy(b, 0, a, 0, b.length);;
	}
	
	
}
