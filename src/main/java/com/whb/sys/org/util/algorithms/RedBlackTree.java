package com.whb.sys.org.util.algorithms;

/**
 * 红黑树的性质：
 * 1、每个节点 或是红色的或是  黑色的
 * 2、根节点是黑色的
 * 3、每个叶节点（NIL）是黑色的
 * 4、如果一个节点是红色的，那么他的两个子节点是黑色的
 * 5、对每个节点，从根节点到其所有后代叶节点的简单路径上，均包含相同数目的黑色节点
 * @author WHB
 *
 */
public class RedBlackTree {
	public RedBlackTree() {
		// TODO Auto-generated constructor stub
	}
	public static void mssain(String[] args){
		Integer[] is = new Integer[]{13,1,43,6,14,12,67,32,413,133};
		getRBTree(is);
	}
	public static RedBlackNode<Integer> getRBTree(Integer[] is){
		RedBlackNode<Integer> T = null;
		for(int i:is){
			RedBlackNode<Integer> z = new RedBlackNode<Integer>();
			z.setValue(i);
			z.setColor(TreeConstant.RED);
			RB_INSERT(T, z);
		}
		return T;
	}

	public static void RB_INSERT(RedBlackNode<Integer> T,RedBlackNode<Integer> z){
		RedBlackNode<Integer> y = null;
		RedBlackNode<Integer> x = T;
		
		while(x != null){
			y=x;
			if(z.getValue()<x.getValue()){
				x = x.getLeft();
			}else x = x.getRight();
		}
		
		z.setP(y);
		
		if(y == null){
			T = z;
		}else if(z.getValue()<y.getValue()){
			y.setLeft(z);
		}else if(z.getValue()>y.getValue()){
			y.setRight(z);
		}
		
		z.setRight(null);
		z.setLeft(null);
		z.setColor(TreeConstant.RED);
		
		RB_INSERT_FIXUP(T,z);
		
	}
	
	public static void RB_INSERT_FIXUP(RedBlackNode<Integer> T, RedBlackNode<Integer> z) {
		while(z.getP().getColor() == TreeConstant.RED){
			if(z.getP() == z.getP().getP().getLeft()){//左边的情况
				RedBlackNode<Integer> y = z.getP().getP().getRight();
				if(y.getColor() == TreeConstant.RED){
					z.getP().setColor(TreeConstant.BLACK);
					y.setColor(TreeConstant.BLACK);
					z.getP().getP().setColor(TreeConstant.RED);
					z = z.getP().getP();
				}else if(z == z.getP().getRight()){
					z = z.getP();
					leftRotate(T,z);
				}
				z.getP().setColor(TreeConstant.BLACK);
				z.getP().getP().setColor(TreeConstant.RED);
				rightRotate(T,z);
			}else{//右边的情况
				
			}
			T.setColor(TreeConstant.BLACK);
		}
	}
	
	public void RB_TRANSPLANT(RedBlackNode<Integer> T, RedBlackNode<Integer> u, RedBlackNode<Integer> v){
		if(u.getP() == null){
			T = v;
		}else if(u.getP().getLeft() == u){
			u.getP().setLeft(v);
		}else if(u.getP().getRight() == u){
			u.getP().setRight(v);
		}
		v.setP(u.getP());
	}
	
	/**
	 * 最大值，可以在O(h)时间内完成（h为树高度）
	 * 所遇到的节点都是从树根向下简单路径
	 * @param x
	 * @return
	 */
	public static RedBlackNode<Integer> RB_MIMIMUM(RedBlackNode<Integer> x){
		while(x.getLeft()!=null){
			x=x.getLeft();
		}
		return x;
	}
	
	public void RB_DELETE(RedBlackNode<Integer> T,RedBlackNode<Integer> z){
		RedBlackNode<Integer> y = z;
		Integer y_original_color = y.getColor();//记录原始颜色
		RedBlackNode<Integer> x = new RedBlackNode<Integer> ();
		if (z.getLeft() == null){
			x = z.getRight();
			RB_TRANSPLANT(T, y, y.getRight());
		}else if(z.getRight() == null){
			x = z.getLeft();
			RB_TRANSPLANT(T, y, y.getLeft());
		}else{
			/**
			 * 如果待删除节点有两个子节点，则取其后继
			 */
			y = RB_MIMIMUM(y.getRight());
			y_original_color = y.getColor();
			x = y.getRight();
			if(y.getP() == z){
				x.setP(y);
			}else{
				RB_TRANSPLANT(T, y,y.getRight());
				y.setRight(z.getRight());
				y.getRight().setP(y);
			}
			RB_TRANSPLANT(T, z, y);
			y.setLeft(z.getLeft());
			y.getLeft().setP(y);
			y.setColor(y.getColor());
			if(y_original_color == TreeConstant.BLACK){
				RB_DELETE_FIXUP(T,x);
			}
		}
		
		
	}
	public void RB_DELETE_FIXUP(RedBlackNode<Integer> T, RedBlackNode<Integer> x) {
		while(x == T && x.getColor() == TreeConstant.BLACK){
			if(x == x.getP().getLeft()){
				RedBlackNode<Integer> w = x.getP().getRight();
				/**
				 * 情况1:x的兄弟节点w为红色
				 * 因为w必须有黑色子节点，所以可以改变w和x.p的颜色，然后对x.p做一次左旋转而不违反红黑树的性质
				 * x的兄弟节点为旋转之前w某个子节点，其颜色为黑色
				 * 将情况1转换为情况2，3，4处理
				 * 
				 * 情况2:x兄弟节点w是黑色的，且w的两个子节点都是黑色的
				 * 因为w也是黑色，所以从x和w上除去一重黑色使得x只有一重黑色而w为红色
				 * 为了补偿从x和w中去掉的一重黑色，在原来为红色或黑色的x.p上新增一重额外的黑色
				 * 通过将x.p作为新节点x来重复while循环
				 * 
				 * 情况3:x的兄弟节点w是黑色的，w的左孩子红色，右孩子为黑色
				 * 
				 * 情况4：x的兄弟节点w是黑色的，且右孩子为红色的
				 * 
				 */
				if(w.getColor() == TreeConstant.RED){//情况1
					w.setColor(TreeConstant.BLACK);
					x.getP().setColor(TreeConstant.RED);
					leftRotate(T, x.getP());
					
				}else if(w.getLeft().getColor() == TreeConstant.BLACK//情况2
						&&w.getLeft().getColor() == TreeConstant.BLACK){
					w.setColor(TreeConstant.RED);
					x = x.getP();
				}else if(w.getRight().getColor() == TreeConstant.BLACK
						&&w.getLeft().getColor() == TreeConstant.RED){//情况3
					w.getLeft().setColor(TreeConstant.BLACK);
					w.setColor(TreeConstant.BLACK);
					rightRotate(T, w);
					w = x.getP().getRight();
				}else if(w.getRight().getColor() == TreeConstant.RED){
					w.setColor(x.getP().getColor());
					x.getP().setColor(TreeConstant.BLACK);
					leftRotate(T, x.getP());
					x = T;
				}
			}else{
				
			}
			x.setColor(TreeConstant.BLACK);
			
		}
	}
	/**
	 * 节点左旋转
	 * @param x
	 */
	public static void leftRotate(RedBlackNode<Integer> T,RedBlackNode<Integer> x){
		RedBlackNode<Integer>  y = x.getRight();//SET y
		x.setRight(y.getLeft()); //turn y's left subtree into x's right subtree
		if(y.getLeft() != null){
			y.getLeft().setP(x);
		}
		y.setP(x.getP()); //link x's parent to y
		if(x.getP() == null){
			T = y;//T.root = y
		}else if(x == x.getP().getLeft()){
			x.getP().setLeft(y);
		}else {
			x.getP().setRight(y);
		}
		y.setLeft(x);//put x on y's left
		x.setP(y);
		//左旋转的时候，节点的size失效，故需要维护子树的size
		y.setSize(x.getSize());
		x.setSize(x.getLeft().getSize()+x.getRight().getSize()+1);
		
	}
	/**
	 * 右节点旋转
	 * @param x
	 */
	public static void rightRotate(RedBlackNode<Integer> T,RedBlackNode<Integer> x){
		RedBlackNode<Integer>  y = x.getP(); //set y
		y.setLeft(x.getRight()); //turn x's right subtree into y's left subtree
		if(x.getRight() != T){
			y.getLeft().setP(y);
		}
		x.setP(y.getP());//like y's parent to x
		if(y.getP() == T){
			y = x;//T.root=x;
		}else if(y == y.getP().getLeft()){
			y.getP().setLeft(x);
		}else {
			y.getP().setRight(x);
		}
		x.setRight(y);
		y.setP(x);
	}
	
	/**
	 * 找出顺序统计树T中的第i小关键字
	 * @param x
	 * @param i
	 * @return
	 */
	public RedBlackNode<Integer> OS_SELECT(RedBlackNode<Integer> x,Integer i){
		//计算以x为根的子树节点的秩r
		Integer r = x.getLeft().getSize()+1;
		//如果相等，那么x就是第i小的节点
		if(i == r){
			return x;
		}else if(i<r){
				return OS_SELECT(x.getLeft(), i);
		}else return OS_SELECT(x.getRight(), i);
	}
	
	/**
	 * 确定一个元素的秩
	 * @param T
	 * @param x
	 * @return
	 */
	public Integer OS_RANK(RedBlackNode<Integer> T,RedBlackNode<Integer> x){
		Integer r = x.getLeft().getSize()+1;
		RedBlackNode<Integer> y = x;
		while(y != T){
			if(y==y.getP().getRight()){
				r += y.getP().getLeft().getSize() + 1;
			}
			y = y.getP();
		}
		return r;
	}
}
