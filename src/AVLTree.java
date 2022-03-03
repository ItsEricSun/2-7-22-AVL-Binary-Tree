
public class AVLTree {
    Node root;
    
    int height(Node x) {
        if (x == null)
            return 0;
 
        return x.height;
    }
    
    Node rightRotate(Node x) {
        Node y = x.left;
        Node z = y.right;
        y.right = x;
        x.left = z;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }
 
    Node leftRotate(Node x) {
        Node y = x.right;
        Node z = y.left;
        y.left = x;
        x.right = z;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }
 
    int getBalance(Node x) {
        if (x == null)
            return 0;
        return height(x.left) - height(x.right);
    }
 
    Node insert(Node node, int data) {
        if (node == null)
            return (new Node(data));
        if (data < node.data)
            node.left = insert(node.left, data);
        else if (data > node.data)
            node.right = insert(node.right, data);
        else 
            return node;
        node.height = 1 + Math.max(height(node.left),
                              height(node.right));
        int balance = getBalance(node);
        if (balance > 1 && data < node.left.data)
            return rightRotate(node);
        if (balance < -1 && data > node.right.data)
            return leftRotate(node);
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }
    
    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }
 
    Node delete(Node root, int data) {
        if (root == null) return root;
        if (data < root.data) root.left = delete(root.left, data);
        else if (data > root.data) root.right = delete(root.right, data);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) temp = root.right;
                else temp = root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                }
                else root = temp;                 
            } else {
                Node temp = minValueNode(root.right);
                root.data = temp.data;
                root.right = delete(root.right, temp.data);
            }
        }
        if (root == null) return root;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);
        if (balance > 1 && getBalance(root.left) >= 0) return rightRotate(root);
        if (balance < -1 && getBalance(root.right) <= 0) return leftRotate(root);
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }
}

