package Select;

import SortUtil.QuickSort;

public class RandomizedQuickSort {

	public static void main(String[] args) {
		int[] a = new int[]{10,9,8,7,6,5,4,3,2,1,0};
		System.out.println(randomizedSelect(a, 2, a.length-5, 1));
	}
	
	/**
	 * @param a 数组
	 * @param p	开始下标
	 * @param r	结束下标
	 * @param i	选择的下标（第i小）
	 * @return	第i小的数
	 */
	private static int randomizedSelect(int[] a, int p, int r, int i) {
		
		if(p == r) return a[p];
		
		int q = QuickSort.partition(a, p, r);
		int k = q - p + 1;
		if(k == i) {
			return a[q];
		}else if(k > i) {
			return randomizedSelect(a, p, q-1, i);
		}else {
			return randomizedSelect(a, q+1, r, i - k);
		}
	}

}
