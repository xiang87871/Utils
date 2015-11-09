package SortUtil;

import java.util.Arrays;
import java.util.LinkedList;

public class BucketSort {
	
	public static void main(String[] args) {
		double[] a = new double[]{0.12, 0.32,0.11,0.42,0.98,0.42,0.64,0.78,0.86,0.89};
		Object[] bucketSort = bucketSort(a);
		System.out.println(Arrays.toString(bucketSort));
	}
	
	private static Object[] bucketSort(double[] a) {
		
		
		@SuppressWarnings("unchecked")
		LinkedList<Double>[] b = new LinkedList[a.length];
		for (double d : a) {
			int x = (int) (d*a.length);
			if(b[x] == null) {
				b[x] = new LinkedList<Double>();
				b[x].add(d);
			}else {
				boolean flag = false;
				for (int i = 1; i < b[x].size(); i++) {
					double dd = b[x].get(i);
					if(dd > d) {
						b[x].add(i-1, d);
						flag = true;
						break;
					}
				}
				if(!flag) {
					b[x].addLast(d);
				}
			}
		}
		
		LinkedList<Double> t = new LinkedList<Double>();
		for (LinkedList<Double> linkedList : b) {
			if(linkedList != null) {
				t.addAll(linkedList);
			}
		}
		return t.toArray();
	}

}
