import java.util.Scanner;

class Trunk
{
    Trunk prev;
    String str;
 
    Trunk(Trunk prev, String str)
    {
        this.prev = prev;
        this.str = str;
    }
};
 
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

class Node {
    int key, height;
    Node left, right;
 
    Node(int d) {
        key = d;
        height = 1;
    }
}
 
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
    class AVLTree {
    	 
        Node root;
     
        // A utility function to get the height of the tree
        int height(Node N) {
            if (N == null)
                return 0;
     
            return N.height;
        }
     
        // A utility function to get maximum of two integers
        int max(int a, int b) {
            return (a > b) ? a : b;
        }
     
        // A utility function to right rotate subtree rooted with y
        // See the diagram given above.
        Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;
     
            // Perform rotation
            x.right = y;
            y.left = T2;
     
            // Update heights
            y.height = max(height(y.left), height(y.right)) + 1;
            x.height = max(height(x.left), height(x.right)) + 1;
     
            // Return new root
            return x;
        }
     
        // A utility function to left rotate subtree rooted with x
        // See the diagram given above.
        Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;
     
            // Perform rotation
            y.left = x;
            x.right = T2;
     
            //  Update heights
            x.height = max(height(x.left), height(x.right)) + 1;
            y.height = max(height(y.left), height(y.right)) + 1;
     
            // Return new root
            return y;
        }
     
        // Get Balance factor of node N
        int getBalance(Node N) {
            if (N == null)
                return 0;
     
            return height(N.left) - height(N.right);
        }
     
        Node insert(Node node, int key) {
     
            /* 1.  Perform the normal BST insertion */
            if (node == null)
                return (new Node(key));
     
            if (key < node.key)
                node.left = insert(node.left, key);
            else if (key > node.key)
                node.right = insert(node.right, key);
            else // Duplicate keys not allowed
                return node;
     
            /* 2. Update height of this ancestor node */
            node.height = 1 + max(height(node.left),
                                  height(node.right));
     
            /* 3. Get the balance factor of this ancestor
                  node to check whether this node became
                  unbalanced */
            int balance = getBalance(node);
     
            // If this node becomes unbalanced, then there
            // are 4 cases Left Left Case
            if (balance > 1 && key < node.left.key)
                return rightRotate(node);
     
            // Right Right Case
            if (balance < -1 && key > node.right.key)
                return leftRotate(node);
     
            // Left Right Case
            if (balance > 1 && key > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
     
            // Right Left Case
            if (balance < -1 && key < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
     
            /* return the (unchanged) node pointer */
            return node;
        }
        Node minValueNode(Node node)
        {
            Node current = node;
     
            /* loop down to find the leftmost leaf */
            while (current.left != null)
            current = current.left;
     
            return current;
        }
     
        Node deleteNode(Node root, int key)
        {
            // STEP 1: PERFORM STANDARD BST DELETE
            if (root == null)
                return root;
     
            // If the key to be deleted is smaller than
            // the root's key, then it lies in left subtree
            if (key < root.key)
                root.left = deleteNode(root.left, key);
     
            // If the key to be deleted is greater than the
            // root's key, then it lies in right subtree
            else if (key > root.key)
                root.right = deleteNode(root.right, key);
     
            // if key is same as root's key, then this is the node
            // to be deleted
            else
            {
     
                // node with only one child or no child
                if ((root.left == null) || (root.right == null))
                {
                    Node temp = null;
                    if (temp == root.left)
                        temp = root.right;
                    else
                        temp = root.left;
     
                    // No child case
                    if (temp == null)
                    {
                        temp = root;
                        root = null;
                    }
                    else // One child case
                        root = temp; // Copy the contents of
                                    // the non-empty child
                }
                else
                {
     
                    // node with two children: Get the inorder
                    // successor (smallest in the right subtree)
                    Node temp = minValueNode(root.right);
     
                    // Copy the inorder successor's data to this node
                    root.key = temp.key;
     
                    // Delete the inorder successor
                    root.right = deleteNode(root.right, temp.key);
                }
            }
     
            // If the tree had only one node then return
            if (root == null)
                return root;
     
            // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
            root.height = max(height(root.left), height(root.right)) + 1;
     
            // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
            // this node became unbalanced)
            int balance = getBalance(root);
     
            // If this node becomes unbalanced, then there are 4 cases
            // Left Left Case
            if (balance > 1 && getBalance(root.left) >= 0)
                return rightRotate(root);
     
            // Left Right Case
            if (balance > 1 && getBalance(root.left) < 0)
            {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
     
            // Right Right Case
            if (balance < -1 && getBalance(root.right) <= 0)
                return leftRotate(root);
     
            // Right Left Case
            if (balance < -1 && getBalance(root.right) > 0)
            {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
     
            return root;
        }
        // A utility function to print preorder traversal
        // of the tree.
        // The function also prints height of every node
        void preOrder(Node node) {
            if (node != null) {
                System.out.print(node.key + " ");
                preOrder(node.left);
                preOrder(node.right);
            }
        }
    }

