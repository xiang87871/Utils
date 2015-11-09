package SortUtil;

import java.util.Random;

public class MergeSort {
	
	public static void main(String[] args) {
//		int[] a = new int[20];
//		Random random = new Random();
//		for (int i = 0; i < a.length; i++) {
//			a[i] = random.nextInt(100);
//		}
//		
//		for (int i = 0; i < a.length; i++) {
//			System.out.print(a[i] + ",");
//		}
//		System.out.println();
//		mergeSort(a, 0, 19);
//
//		for (int i = 0; i < a.length; i++) {
//			System.out.print(a[i] + ",");
//		}

//		int[] a = new int[]{13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
		int[] a = new int[]{1,-4,3,-4};
		int[] maxSonArray = maxSonArray(a, 0, a.length-1);
		for (int i = maxSonArray[0]; i <= maxSonArray[1]; i++) {
			System.out.print(a[i] + ",");
		}
		
		
	}
	
	public static void mergeSort(int[] a, int p, int r) {
		if(p < r){
			int q = (p + r) / 2;
			mergeSort(a, p, q);
			mergeSort(a, q + 1, r);
			merge(a, p, q, r);
		}
	}
	
	public static void merge(int[] a, int p, int q, int r) {
		
		int[] s1 = new int[q+1-p];
		int[] s2 = new int[r-q];
		
		System.arraycopy(a, p, s1, 0, s1.length);
		System.arraycopy(a, q + 1, s2, 0, s2.length);
		
		for (int i = 0, j = 0; i < s1.length || j < s2.length; p++) {
			if(i>=s1.length) {
				System.arraycopy(s2, j, a, p, s2.length-j);
				break;
			}else if(j>=s2.length) {
				System.arraycopy(s1, i, a, p, s1.length-i);
				break;
			}
			if(s1[i] <= s2[j]) {
				a[p] = s1[i];
				i ++;
			}else {
				a[p] = s2[j];
				j ++;
			}
		}
	}
	
	
	//最大子数组问题
	public static int[] maxSonArray(int[] a, int p, int r) {
		if(p < r) {
			int q = (r+p)/2;
			int[] msa1 = maxSonArray(a, p, q);
			int[] msa2 = maxSonArray(a, q+1, r);
			int[] msa3 = midMax(a, p, q, r);
			int suma1 = suma(a, msa1[0], msa1[1]);
			int suma2 = suma(a, msa2[0], msa2[1]);
			int suma3 = suma(a, msa3[0], msa3[1]);
			if(suma1 >= suma2 && suma1 >= suma3) {
				return msa1;
			}else if(suma2 >= suma1 && suma2 >= suma3) {
				return msa2;
			}else {
				return msa3;
			}
		}else {
			return new int[]{p, r};
		}
	}
	
	public static int suma(int[] a, int p, int r) {
		int s = 0;
		for(int i = p; i <= r; i++ ) {
			s += a[i];
		}
		return s;
	}

	
	public static int[] midMax(int[] a, int p, int q, int r){
		int z = q;
		int y = q+1;

		for (int i = q, s = 0, dump = a[q]; i >= p; i--) {
			s += a[i];
			if(s > dump) {
				dump = s;
				z = i;
			}
		}

		
		for (int i = q + 1, s = 0, dump = a[q + 1]; i <= r; i++) {
			s += a[i];
			if(s > dump) {
				dump = s;
				y = i;
			}
		}
		
		int[] maxa = new int[]{z,y};
		return maxa;
	}
}
