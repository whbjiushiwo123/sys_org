package com.whb.sys.org.util.algorithms;

/**
 * B树
 * 1：每个节点都有以下性质：
 * 		a、x.n，当前存储在节点x中的关键字个数
 * 		b、x.n中的关键字本身x.key1,x.key2……以非降序存放，是的x.key1<=x.key2……
 * 		c、x.leaf,一个bool值，如果x为叶节点，则为true，如果x为内部节点则为false
 * 2，每个内部节点x还包含x.n+1个指向其孩子的指针，x.c1,x.c2……叶节点没有孩子，ci属性没有意义
 * 3、关键字x.key对存储在各子树中的关键字范围加以分割：如果ki为任意一个存储在以x.c1为根的子树中的关键字，
 * 		则k1<=x.key1<=k2<=x.key2……
 * 4、每个叶节点有相同的树高，即树的高度为h
 * 5、每个节点所包含的关键字个数有上界和下界，用一个被称为B树的最小度数(minmum degree)t>=2表示这些界
 * 		a、除了根节点，每个节点都有t-1个关键字，因此除了根节点意以外的每个内部节点至少有t个孩子，如果树非空，则至少有一个关键字
 * 		b、每个节点至多可包含t-1个关键字，每个内部节点至多可以有2t个孩子
 * @author WHB
 *
 */
public class BTree {
	private BTreeNode root;
	private BTreeNode node;
	public BTree() {

	}
	public BTree(BTreeNode root) {
		super();
		this.root = root;
	}

	public BTreeNode getRoot() {
		return root;
	}

	public void setRoot(BTreeNode root) {
		this.root = root;
	}
	
	/**
	 * BTee搜索
	 * 先找出最小下标i，是的k<x.keyi，若找不到，则置i为x.n+1
	 * @param root 指向根节点
	 * @param key 搜索的关键字
	 */
	public Object[] BTreeSearch(BTreeNode[] root,int key){
		int i = 1;
		while(i<=root[0].getN() && key>=root[0].getKey()[i-1]){
			i += 1;
		}
		if(i<=root[0].getN() && key <= root[0].getKey()[i-1]){
			return new Object[]{root,i};
		}else if(root[0].isLeaf()){
			return null;
		}else{
			return BTreeSearch(root[0].getChildren(),key);
		}
	}
	
	/**
	 * 创建一个BTree
	 */
	public static BTree  CreateBTree(){
		BTree T = new BTree();
		BTreeNode x = new BTreeNode();
		x.setLeaf(true);
		x.setN(0);
		T.setRoot(x);
		return T;
	}
	
	/**
	 * 1：把子节点分裂成为两个，并调整x，使其包含多出来的孩子
	 * 2：分裂满的根，先要让根成为一个新的空的根节点的子节点
	 * 设t=8,y是x的第i个孩子，开始时，y有2t个孩子（2t-1个关键字）
	 * 在分裂后减少至t个孩子（t-1个关键字）。节点z取走y的t个最大孩子（t-1个关键字），并且z成为x新孩子，
	 * z在x的孩子中紧在y后面,y的中间关键字上升到x中，成为分割y和z的关键字
	 * @param x 非满的内部节点x（假定在主存中）---被分裂的节点
	 * @param i 使x.c1为x的一个满子节点下标
	 */
	public static int t = 4;
	public void BTreeSplitChild(BTreeNode x,int i){
		BTreeNode z = new BTreeNode();
		BTreeNode y = x.getChildren()[i-1];
		z.setLeaf(y.isLeaf());
		z.setN(t-1); 
		for(int j=1;j<=t-1;t++){
			z.getKey()[j-1] = y.getKey()[j-1+t];
		}
		if(!y.isLeaf()){
			for(int j=1;j<=t;j++){
				z.getChildren()[j-1] = y.getChildren()[j-1+t];
			}
		}
		y.setN(t-1);
		
		for(int j=x.getN()+1 ;j>=i+1;j--){
			//需要扩充子节点数组
			x.getChildren()[j] = x.getChildren()[j-1];
		}
		
		x.getChildren()[i] = z;
		
		for(int j=x.getN();j>=i;j--){
			x.getKey()[j] = x.getKey()[j-1];
		}
		x.getKey()[i-1] = y.getKey()[t-1];
		x.setN(x.getN()+1);
	}
	
	public void BTreeInsert(BTree T,int k){
		BTreeNode r = T.getRoot();
		if(r.getN() == 2*t-1){
			BTreeNode s = new BTreeNode();
			T.setRoot(s);
			s.setLeaf(false);
			s.setN(0);
			BTreeNode[] sc = new BTreeNode[2*t-1];
			sc[0] = r;
			s.setChildren(sc);
			BTreeSplitChild(s, k);
			BTreeInsertNonFull(s,k);
		}else{
			BTreeInsertNonFull(r,k);
		}
	}

	public void BTreeInsertNonFull(BTreeNode x, int k) {
		int i = x.getN();
		if(x.isLeaf()){
			while(i>=1 && k<x.getKey()[i-1]){
				x.getKey()[i] = x.getKey()[i-1];
				i-=1;
			}
			x.getKey()[i-1] = k;
			x.setN(x.getN()+1);
		}else{
			while(i>=1 && k<x.getKey()[i-1]){
				i -= 1;
			}
			i += 1;
			if(x.getChildren()[i-1].getN() == 2*t-1){
				BTreeSplitChild(x, i);
				if(k>x.getKey()[i-1]){
					i += 1;
				}
			}
			BTreeInsertNonFull(x.getChildren()[i], k);
		}
		
	}
}
