package SortUtil;

import java.util.Arrays;

public class HeapSort {

	
	
	private static void maxHeapify(int[] a, int i, int heapsize) {
		int l = 2*i + 1;
		int r = 2*i + 2;
		
		int large = i;
		
		if(l < heapsize && a[l] > a[large]) {
			large = l;
		}
		
		if(r < heapsize && a[r] > a[large]) {
			large = r;
		}
		
		if(large != i) {
			int dump = a[i];
			a[i] = a[large];
			a[large] = dump;
			
			maxHeapify(a, large, heapsize);
		}
	}
	
	private static void buildMaxHeap(int[] a, int heapsize) {
		for (int i = heapsize/2-1; i >= 0; i--) {
			maxHeapify(a, i, heapsize);
		}
	}
	
	private static void heapSort(int[] a) {
		buildMaxHeap(a, a.length);
		System.out.println(Arrays.toString(a));
		int heapsize = a.length;
		
		for (int i = a.length-1; i >= 1; i--) {
			int dump = a[0];
			a[0] = a[i];
			a[i] = dump;
			
			maxHeapify(a, 0, --heapsize);
		}
	}
	
	
	public static void main(String[] args) {
		int[] a = new int[]{121,2,3,42,56,932,33,21,356,4,1,9,34};
		heapSort(a);
		System.out.println(Arrays.toString(a));
	}
}
