import java.util.*;

public class TreeMain {
	
	public static void main(String[] args) {
    	 AVLTree tree = new AVLTree();
    	 List<Integer> l = new ArrayList<>();
    	    
    	 while(true) {
    		 try {
	        	 Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	
	    		 System.out.println("Enter 1 for insert, 2 for delete, 3 for stop");
	    		 	
	    		 Integer choice = myObj.nextInt();  // Read user input
	    		 if(choice == 1) {
	    			 System.out.println("What Number do You Want to Insert?");
	    			 myObj = new Scanner(System.in);
	    			 Integer number = myObj.nextInt();
	    			 if(l.contains(number)) {
	    				 System.out.println("Number Already Exists\n");
	    				 continue;
	    			 }
	    			 if(l.size() == 33) {
	    				 System.out.println("Maximum Number of Inputs Reached (33)\n");
	    				 continue;
	    			 }
	    			 l.add(number);
	    			 tree.root = tree.insert(tree.root, number);
	    			 printTree(tree.root, null, false);
	    			 System.out.println("");
	    		 } else if(choice == 2) {
	    			 System.out.println("What Number do You Want to Delete?");
	    			 myObj = new Scanner(System.in);
	    			 Integer number = myObj.nextInt();
	    			 if(!l.contains(number)) {
	    				 System.out.println("Number Doesn't Exist\n");
	    				 continue;
	    			 }
	    			 l.remove(number);
	    			 tree.root = tree.deleteNode(tree.root, number);
	    			 printTree(tree.root, null, false);
	    			 System.out.println("");
	    		 } else if(choice == 3) {
	    			 myObj.close();
	    			 break;
	    		 }
    		 } catch (Exception e) {
    			 System.out.println("Make sure to enter integer");
    		 }
    	 }
    }
	
	public static void showTrunks(Trunk p) {
        if (p == null) {
            return;
        }
 
        showTrunks(p.prev);
        System.out.print(p.str);
    }
 
    public static void printTree(Node root, Trunk prev, boolean isLeft) {
        if (root == null) {
            return;
        }
 
        String prev_str = "     ";
        Trunk trunk = new Trunk(prev, prev_str);
 
        printTree(root.right, trunk, true);
 
        if (prev == null) {
            trunk.str = "————";
        }
        else if (isLeft) {
            trunk.str = "/————";
            prev_str = "    |";
        }
        else {
            trunk.str = "\\————";
            prev.str = prev_str;
        }
 
        showTrunks(trunk);
        System.out.println(" " + root.data);
 
        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "    |";
 
        printTree(root.left, trunk, false);
    }
}
