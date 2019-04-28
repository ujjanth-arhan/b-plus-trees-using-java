public class btree
{
	private static final int MAX = 4;
	private static final int MIN = 2;
	Node root;
	
	btree()	{	
			root = create_newnode();	
	}	
	
	void insert(int key) {
		
			int i,j, t, max = 0;			
			Node temp = find_leaf(root,key);	
			if(temp == null) {
				System.out.println("Error!: Cannot insert: This integer is already present in the tree\n");
				return;		
			}
			if(!isFull(temp))			
			{
				System.out.println("Element inserted successfully\n");
				for(i=0;i<MAX;i++) {	
					if(temp.ele[i]==-1) {	
						temp.ele[i]=key;
						break;
					}
					max=temp.ele[i];
				}
				sort_node(temp);
				if(key>max && temp.parent!=null)		
					update_parent(temp.parent,key,max);
				return;
			}	
			if(isFull(temp))			
			{
				split(temp);
				insert(key);			
			}
		}
	void split(Node x) {
		int i;
		Node temp;
		if(x.parent==null)				
		{
			temp = create_newnode();
			root = create_newnode();
			
			root.ele[0] = x.ele[MIN-1];		
			root.child[0] = x;
			root.ele[1] = x.ele[MAX-1];
			root.child[1] = temp;

			x.parent = root;
			temp.parent = x.parent;					
		}
		else {
			if(isFull(x.parent))				
				split(x.parent);
		
			temp = create_newnode();			
			temp.parent = x.parent;
			for(i=0;i<MAX;i++)				
			{
				if(x.parent.ele[i]==x.ele[MAX-1])
					x.parent.child[i]=temp;
				if(x.parent.ele[i]==-1)	{
					x.parent.ele[i]=x.ele[MIN-1];
					x.parent.child[i]=x;
					break;
				}	
				
			}
		}
		temp.next_child=x.next_child;
		x.next_child=temp;
		for(i=0;i<MIN;i++) {
			temp.ele[i]=x.ele[MIN+i];
			x.ele[MIN+i]=-1;
			temp.child[i]=x.child[MIN+i];
			if(temp.child[i]!=null)
				temp.child[i].parent = temp;
			x.child[MIN+i]=null;
		}
		sort_node(x.parent); 
	}

	boolean search(int key)	{
		Node temp = find_leaf(root, key);	
		if(temp==null)
			return true;
		return false;
	}
	
	boolean isFull(Node x)
	{
		return (x.ele[MAX-1]==-1)?false:true;		
	}
	
	Node find_leaf(Node x, int key)
	{
		int i;	
		if(x.child[0]==null)			
		{						
			for(i=0;i<MAX;i++)
				if(key==x.ele[i])
					return null;	
			return x;
		}
		for(i=0;i<MAX;i++)				
		{
			if(x.ele[i] >= key)
				return find_leaf(x.child[i],key);
			if(x.ele[i]==-1)			
				return find_leaf(x.child[i-1],key);
		}
		return find_leaf(x.child[MAX-1],key);
	}
	
	Node create_newnode() {						
		int i;	
		Node temp = new Node();
		if(temp == null)
		{
			System.out.println("Fatal ERROR: Out of Memory!\n");
			System.exit(0);
		}
		for(i=0;i<MAX;i++) {
			temp.ele[i]=-1;
			temp.child[i]=null;
		}
		temp.parent=null;
		temp.next_child=null;
		return temp;
	}
	
	void sort_node(Node x)			
	{
		int i,j;
		int temp_ele;
		Node temp_child;
		for(i=0;i<MAX && x.ele[i]!=-1;i++)
		{
			for(j=i+1;j<MAX && x.ele[j]!=-1;j++)
			{
				if(x.ele[i]>x.ele[j])
				{
					temp_ele = x.ele[i];
					temp_child = x.child[i];
			
					x.ele[i] = x.ele[j];
					x.child[i] = x.child[j];
			
					x.ele[j] = temp_ele;
					x.child[j]= temp_child;
				}
			}
		}
	}
	
	void update_parent(Node x, int key, int max) {
		int i, new_max = 0;
		for(i=0;i<MAX&&(x.ele[i]!=-1);i++)
		{
			new_max=x.ele[i];
			if(x.ele[i]==max)
				x.ele[i]=key;
		}
		if(key>new_max && x.parent!=null)
			update_parent(x.parent,key,max);
	}
	void display() {
		Node queue[] = new Node[50];
		int f=0, r=-1, lvl = -1, i;
		queue[++r]=null;
		queue[++r]=root;
		while(f<r) {
			if(queue[f]==null) {	
				System.out.println("\n\nLevel --> "+(++lvl));
				if(queue[r]!=null)
					queue[++r]=null;
				f++;		
			}
			else {
				for(i=0;i<MAX;i++) {	
					if(queue[f].ele[i]!=-1)				
						System.out.print(queue[f].ele[i]+"   ");
					if(queue[f].child[i]!=null)	
						queue[++r]=queue[f].child[i];
				}				
				System.out.print("       ");
				f++;
			}
		}
	}
	
};