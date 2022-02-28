import java.util.Scanner;

//class Trunk
//{
//    Trunk prev;
//    String str;
// 
//    Trunk(Trunk prev, String str)
//    {
//        this.prev = prev;
//        this.str = str;
//    }
//};
 
// A Binary Tree Node
//class Node
//{
//    int data;
//    Node left, right;
// 
//    Node() {}
//    Node(int data)
//    {
//        this.data = data;
//        this.left = this.right = null;
//    }
//}

//class Node {
//    int key, height;
//    Node left, right;
// 
//    Node(int d) {
//        key = d;
//        height = 1;
//    }
//}
 
class GUI
{
    public static void showTrunks(Trunk p)
    {
        if (p == null) {
            return;
        }
 
        showTrunks(p.prev);
        System.out.print(p.str);
    }
 
    public static void printTree(Node root, Trunk prev, boolean isLeft)
    {
        if (root == null) {
            return;
        }
 
        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);
 
        printTree(root.right, trunk, true);
 
        if (prev == null) {
            trunk.str = "———";
        }
        else if (isLeft) {
            trunk.str = ".———";
            prev_str = "   |";
        }
        else {
            trunk.str = "`———";
            prev.str = prev_str;
        }
 
        showTrunks(trunk);
        System.out.println(" " + root.key);
 
        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";
 
        printTree(root.left, trunk, false);
    }
 
    public static void main(String[] args)
    {
        // Construct above tree
//        Node root = new Node(1);
//        root.left = new Node(2);
//        root.right = new Node(3);
//        root.left.left = new Node(4);
//        root.left.right = new Node(5);
//        root.right.left = new Node(6);
//        root.right.right = new Node(7);
//        root.left.left.left = new Node(8);
//        root.left.left.right = new Node(9);
//        root.left.right.left = new Node(10);
//        root.left.right.right = new Node(11);
//        root.right.left.left = new Node(12);
//        root.right.left.right = new Node(13);
//        root.right.right.left = new Node(14);
//        root.right.right.right = new Node(15);
//        root.left.left.left.left = new Node(16);
//        root.left.left.left.right = new Node(16);
//        root.left.left.right.left = new Node(16);
//        root.left.left.right.right = new Node(16);
        // print constructed binary tree
    	 AVLTree tree = new AVLTree();
    	    
    	 while(true) {
    		 try {
	        	 Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	
	    		 System.out.println("Enter 1 for insert, 2 for delete, 3 for stop");
	
	    		 Integer choice = myObj.nextInt();  // Read user input
	    		 if(choice == 1) {
	    			 System.out.println("What Number do You Want to Insert?");
	    			 myObj = new Scanner(System.in);
	    			 Integer number = myObj.nextInt();
	    			 tree.root = tree.insert(tree.root, number);
	    			 printTree(tree.root, null, false);
	    			 System.out.println("");
	    		 } else if(choice == 2) {
	    			 System.out.println("What Number do You Want to Delete?");
	    			 myObj = new Scanner(System.in);
	    			 Integer number = myObj.nextInt();
	    			 tree.root = tree.deleteNode(tree.root, number);
	    			 printTree(tree.root, null, false);
	    			 System.out.println("");
	    		 } else if(choice == 3) {
	    			 break;
	    		 }
    		 } catch (Exception e) {
    			 System.out.println("Make sure to enter integer");
    		 }
    	 }
         /* Constructing tree given in the above figure */
//         tree.root = tree.insert(tree.root, 10);
//         tree.root = tree.insert(tree.root, 45);
//         tree.root = tree.insert(tree.root, 20);
//         tree.root = tree.insert(tree.root, 40);
//         tree.root = tree.insert(tree.root, 5);
//         tree.root = tree.insert(tree.root, 25);
//         tree.root = tree.insert(tree.root, 1);
//         tree.root = tree.insert(tree.root, 4);
//         tree.root = tree.insert(tree.root, 2);
//         tree.root = tree.insert(tree.root, 9);
//        printTree(tree.root, null, false);
//        tree.root = tree.deleteNode(tree.root, 10);
//        printTree(tree.root, null, false);

    }
}

