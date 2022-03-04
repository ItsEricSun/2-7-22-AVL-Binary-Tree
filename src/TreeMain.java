import java.util.*;

public class TreeMain {
	
	public static void main(String[] args) {
    	 AVLTree tree = new AVLTree();
    	 List<Integer> l = new ArrayList<>();
    	 while(true) {
    		 try {
	        	 Scanner sc = new Scanner(System.in);  
	    		 System.out.println("Enter 1 for insert, 2 for delete, 3 for stop");
	    		 Integer choice = sc.nextInt();
	    		 if(choice == 1) {
	    			 System.out.println("What Number do You Want to Insert?");
	    			 sc = new Scanner(System.in);
	    			 Integer number = sc.nextInt();
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
	    			 sc = new Scanner(System.in);
	    			 Integer number = sc.nextInt();
	    			 if(!l.contains(number)) {
	    				 System.out.println("Number Doesn't Exist\n");
	    				 continue;
	    			 }
	    			 l.remove(number);
	    			 tree.root = tree.delete(tree.root, number);
	    			 printTree(tree.root, null, false);
	    			 System.out.println("");
	    		 } else if(choice == 3) {
	    			 sc.close();
	    			 break;
	    		 }
    		 } catch (Exception e) {
    			 System.out.println("Make sure to enter integer");
    		 }
    	 }
    }
	
    public static void showSection(Section s) {
        if (s == null) {
            return;
        }
        showSection(s.prev);
        System.out.print(s.str);
    }
 
    public static void printTree(Node root, Section prev, boolean isRight) {
        if (root == null) {
            return;
        }
        String prev_str = "     ";
        Section section = new Section(prev, prev_str);
        printTree(root.right, section, true);
        if (prev == null) {
            section.str = "————";
        } else if (isRight) {
            section.str = "/————";
            prev_str = "    |";
        } else {
            section.str = "\\————";
            prev.str = prev_str;
        }
        showSection(section);
        System.out.println(" " + root.data);
        if (prev != null) {
            prev.str = prev_str;
        }
        section.str = "    |";
        printTree(root.left, section, false);
    }
}
