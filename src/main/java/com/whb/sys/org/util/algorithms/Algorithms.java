package com.whb.sys.org.util.algorithms;

import java.util.Random;

import com.whb.sys.org.model.SysException;

public class Algorithms {
	
	
   private static void show(int[] a){
	   for(int i:a){
		   System.out.print("、"+i+"、");
	   }
	   System.out.println("############");
   }
   
   /**
    * 获取父节点，左右子节点的下标
    * @param i
    * @return
    */
   public static int parent(int i){
	   return i>>1;
   }
   public static int left(int i){
	   return i<<1;
   }
   public static int right(int i){
	   return (i<<1)+1;
   }
   
   private static int heapSize;
   /**
    * 维护堆得性质，父节点的值>左右子节点的值
    * @param a
    * @param i
    */
   public static void maxHeap(int[]a,int i){
	   int l=left(i);
	   int r=right(i);
	   int largest=i;
	   if(l<=heapSize&&a[l-1]>a[i-1]){
		   largest=l;
	   }else largest=i;
	   
	   if(r<=heapSize&&a[r-1]>a[largest-1]){
		   largest=r;
	   }
	   if(largest!=i){
		   exChange(a,i,largest);
		   maxHeap(a,largest);
	   }
   }
   

   /**
    * 建堆
    * 利用过程MAX_HEAPIFY将大小为n=A.length的数组A[1……n]转换为最大堆
    * 子数组A((n/2)+1^n)中的元素都是树的叶节点
    * 每个叶节点看成是包含一个元素的堆
    * @param a
    */
   public static void buildMaxHeap(int []a){
	   heapSize=a.length;
	   //循环迭代前,i=(n/2)+1,(n/2)+2……n是树的叶节点
	   //调用maxHeap保持节点i为最大堆
	   //i=1时，为树的根节点
	   for(int i=(a.length)/2;i>=1;i--){
		   maxHeap(a, i);
	   }
   }
   
   /**
    * 堆排序算法
    * @param a
    */
   public static void heapSort(int []a){
	   //建立最大堆
	   buildMaxHeap(a);
	   for(int i=a.length;i>=2;i--){
		   exChange(a, 1, i);
		   heapSize=heapSize-1;
		   maxHeap(a, 1);
	   }
   }
   
   /**
    * 返回a中具有最大键值的元素
    * @param a
    * @return
    */
   private  static int heapMaximum(int[]a){
	   return a[a.length-1];
   }
   
   /**
    * 去掉并返回S中最大键值的元素
    * @param a
    * @return
    */
   private static int  heapExtractMax(int []a){
	   if(heapSize<1){
		   throw new SysException("heap underflow");
	   }
	   int max=a[1-1];
	   a[1-1]=a[heapSize];
	   heapSize=heapSize-1;
	   maxHeap(a, 1);
	   return max;
   }
   /**
    * 增加关键字的优先队列元素由对应的数组下标i来标识
    * 现将a[i]的关键字更新为新值
    * 所以需要不断与父节点比较大小，如果大于父节点就需要交换，直到小于父节点
    * @param a
    * @param i
    * @param key
    */
   public static  void heapIncreaseKeys(int[]a,int i,int key){
	   if(key<a[i-1]){
		   throw new SysException("new key is smaller than key");
	   }
	   a[i-1]=key;
	   while(i>1&&a[parent(i)-1]<a[i-1]){
		   exChange(a, i, parent(i)-1);
		   i=parent(i);
	   }
   }
   
   private static int [] maxHeapInset(int[]a,int key){
	   int []b=new int[a.length+1];
	   System.arraycopy(a, 0, b, 0, a.length);
	   //扩展最大堆
	   heapSize=b.length;
	   //最大堆值
	   b[heapSize-1]=Integer.MIN_VALUE;
	   heapIncreaseKeys(b,heapSize,key);
	   heapSort(b);
	   return b;
   }
   /**
    * 快速排序
    * 将数组分解
    * 然后递归调用，对子数组进行排序
    * @param a
    * @param p
    * @param r
    */
   public static void quickSort(int []a,int p,int r){
	   int q=Integer.MIN_VALUE;
	   if(p<r){
		   q = partitions(a,p,r);
		   quickSort(a,p,q-1);
		   quickSort(a,q+1,r);
	   }
	   
   }
   public static void randomized_Partition(int []a,int p,int r){
	   int q=Integer.MIN_VALUE;
	   if(p<r){
		   q = randomizedPartition(a,p,r);
		   quickSort(a,p,q-1);
		   quickSort(a,q+1,r);
	   }
	   
   }
   
   /**
    * 实现对数组的原址重排
    * 选择一个x=A[r]作为主元，围绕他来划分数组：a[p……r]
    * 
    * @param a
    * @param p
    * @param r
    * @return
    */
	private static int partitions(int[] a, int p, int r) {
		int x =a[r-1];
		int i=p-1;
		for(int j=p;j<=r-1;j++){
			if(a[j-1]<x){
				if(a[j-1]<x){
					i++;
					System.out.println(i+"----------");
					exChanges(a, i, j);
				}
			}
		}
		exChanges(a, i+1, r);
		return i+1;
	}
	
	private static int randomizedPartition(int []a,int p,int r){
		int i=new Random().nextInt(r);
		exChanges(a, r, i);
		return partitions(a, p, r);
	}
	
	public  static void mddain(String[]args){
		   int[]a=new int[1000000];
		   //heapSize=a.length;
		   for(int i=0;i<a.length;i++){
			   a[i]=new Random().nextInt(400000)+10;
		   }
		  // heapSort(a);
		   //System.out.println(0<<1);
		  // show(a);
		   //System.out.println("------ss-------");
		  // a = maxHeapInset(a,1540);
		   System.out.println("-----d--------");
		   //System.out.print(heapMaximum(a));
		   
		   quickSort(a,1,a.length);
		   show(a);
	}
	
	   /**
	    * 交换最大值作为子树的父节点
	    * @param a
	    * @param i
	    * @param largest
	    */
	   public static void exChanges(int[]a,int i,int largest){
		   System.out.println(i);
			   int temp=a[i-1];
			   a[i-1]=a[largest-1];
			   a[largest-1]=temp;
	   }
	   public static void exChange(int[]a,int i,int largest){
		   int temp=a[i-1];
		   a[i-1]=a[largest-1];
		   a[largest-1]=temp;
   }
}
