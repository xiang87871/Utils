package SortUtil;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		int[] a = new int[]{10,9,8,7,6,5,4,3,2,1,0};
		quickSort(a, 0, a.length-1);
		System.out.println(Arrays.toString(a));
	}
	
	public static void quickSort(int[] a, int p, int r) {
		if(p < r) {
			int q = partition(a, p, r);
			quickSort(a, p, q-1);
			quickSort(a, q+1, r);
		}
	}
	
	public static int partition(int[] a, int p, int r) {
		int flag = p;
		
		for(int i=p; i<=r; i++) {
			if(a[i] <= a[r]) {
				int dump = a[i];
				a[i] = a[flag];
				a[flag] = dump;
				flag ++;
			}
		}
		return --flag;
	}
	
}
