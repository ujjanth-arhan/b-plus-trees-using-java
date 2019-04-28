import java.util.Scanner;

//typedef struct node * NODE;

public class BPlusTrees {

	public static void main(String args[]) {
		btree  b = new btree();	
		int ch, key;
		boolean flag;
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("\n\nMain Menu\n-------------\n1. to insert an element\n2. to Search for an element\n3. to Display the Tree\n4. to Exit\nEnter Choice: ");
			ch = sc.nextInt();
			switch(ch) {
				case 1:	System.out.println("Enter the integer to be inserted: ");
					key = sc.nextInt();
					b.insert(key);
					break;
				case 2:	System.out.println("Enter the integer to be found: ");
					key = sc.nextInt();
					flag=b.search(key);
					if(flag)
						System.out.println("Element is present\n");
					else
						System.out.println("Element not found\n");
					break;
				case 3: b.display();
					break;
				default:System.exit(0);
			}
		}
		
	}
}