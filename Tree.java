
class Tree
{
    private Node root;
    private int size;
    private Node previous;
    public static final int MAX_SIZE = 30;

    // Constructor. An empty tree has a size of 0.
    public Tree()
    {
        root = null;
        size = 0;
    }

    // Copy constructor. Clones Tree t (i.e. its nodes, and its size)
    public Tree(Tree t)
    {
        root = mycopy(t.root);
        size = t.size;
    }

    // Private copy function that recursively copies Node a (along with
    // all its links, and returns that copy.
    private Node mycopy(Node a)
    {
        if (a == null)
        {
            return null;
        }
        else
        {
            Node root_copy = new Node();
            root_copy.setData(a.getData());
            root_copy.setLeft(mycopy(a.getLeft()));
            root_copy.setRight(mycopy(a.getRight()));
            return root_copy;
        }
        
    }

    // function that takes the key and calls the deleteKey function.
    // Should only work if the tree is not empty.
    public void Delete(int key)
    {
        if (!IsEmpty())
        {
            root = DeleteKey(root, key);
            
        }
    }

    // private recursive function that takes a node, and the key to delete from
    // the subtrees attached to that node. It returns a copy of the tree
    // with the required node having been removed from the appropriate subtrees.
    private Node DeleteKey(Node a, int key)
    {
        Node copy = mycopy(a);

        if (copy == null)
        {
            return null;
        }
        // find key within copy
        if(key > copy.getData())
        {
            copy.setRight(DeleteKey(copy.getRight(), key));
        }
        else if(key < copy.getData())
        {
            copy.setLeft(DeleteKey(copy.getLeft(), key));
        }
        else
        {
            // case 1: deleting a leaf
            if(copy.getLeft() == null && copy.getRight() == null)
            {
                copy = null;
                size--;
            }
            

            //case 2: 1 child (child becomes parent)
                else if (copy.getLeft() == null)
                {
                    copy = copy.getRight();
                    size--;
                }
                else if(copy.getRight() == null)
                {
                    copy = copy.getLeft();
                    size--;
                }
            // case 3: 2 children (successor)
            else
            {
                Node successor = Successor(copy);
                copy.setData(successor.getData());
                copy.setRight(DeleteKey(copy.getRight(), successor.getData()));
            }
            
        }
        return copy;
    }

    // Private function to find the successor to a node. The successor
    // of a node in a binary tree is the node immediately larger than
    // the required node.
    private Node Successor(Node a)
    {
        a = a.getRight();
        Node temp = a;
        Node successor = null;
        while(temp!=null)
        {
            successor = temp;
            temp = temp.getLeft();
        }
        return successor;
    }


    // Function to insert data into the tree in its appropriate location
    // by using the Add() recursive function. This should not be
    // possible for a tree that is already full. If the tree is empty,
    // then it does the insertion itself.
    public void Insert(int data)
    {
        if(size!= MAX_SIZE)
        {
            if(IsEmpty())
            {
                root = new Node(data);
                size++;
            }
            else
            {
                Add(data,root);
                size++;
            }
        }
        
    }

    // Private recursive function that takes a Node attached to its own 
    // subtrees, and attaches the data to the tree in the proper location.
    private void Add(int data, Node a)
    {

        if (a == null)
        {
            a = new Node(data);
            
            if(a.getData() > previous.getData())
            {
                previous.setRight(a);
            }
            else
            {
                previous.setLeft(a);
            }
        }

        else if (data <= a.getData())
        {
            previous = a;
           Add(data, a.getLeft());
        }
        else if (data > a.getData())
        {
            previous = a;
            Add(data, a.getRight());
        }
        else
        {
            return;
        }

    }

    // function to return the size of the tree (i.e. the number of nodes
    // in the tree).
    public int Size()
    {
        return size;
    }

    // Function to tell whether the tree is empty or not.
    public boolean IsEmpty()
    {
        if(root == null)
        {
            return true;
        }
        return false;
    }

    // Function to tell whether the tree is full or not.
    public boolean IsFull()
    {
        if(size == MAX_SIZE)
        {
            return true;
        }
        return false;
    }

    // Function to return the InOrder traversal of the tree. It takes a
    // string as its argument, updates the string with the node
    // information, and then returns the updated string that should 
    // contain the inorder traversal of the tree.
    private String InOrder(Node a, String s)
    {
        if(a!=null)
        {
            s=InOrder(a.getLeft(), s);
            s+= a.getData() + " ";
            s=InOrder(a.getRight(), s);
        }
        return s;
    }

    // Function to return the PreOrder traversal of the tree. It takes a
    // string as its argument, updates the string with the node
    // information, and then returns the updated string that should 
    // contain the preorder traversal of the tree.
    private String PreOrder(Node a, String s)
    {
        if(a!=null)
        {
            s+= a.getData() + " ";
            s=PreOrder(a.getLeft(), s);
            s=PreOrder(a.getRight(), s);
        }
        return s;
    }

    // Function to return the PostOrder traversal of the tree. It takes a
    // string as its argument, updates the string with the node
    // information, and then returns the updated string that should 
    // contain the postorder traversal of the tree.
    private String PostOrder(Node a, String s)
    {
        if(a!=null)
        {
            s=PostOrder(a.getLeft(), s);
            s=PostOrder(a.getRight(), s);
            s+= a.getData() + " ";
        }
        return s;
    }

    // A function that returns the maximum value in the tree. That value
    // is -1 for an empty tree.
    public int getMax()
    {
        if(!IsEmpty())
        {
            int max_val = 0;
            Node temp = root;
            while(temp !=null)
            {
                max_val = temp.getData();
                temp = temp.getRight();
            }
            return max_val;
        }
        return -1;
    }

    // A function that returns the minimum value in the tree. That value
    // is -1 for an empty tree.
    public int getMin()
    {
        if(!IsEmpty())
        {
            int min_val = 0;
            Node temp = root;
            while(temp !=null)
            {
                min_val = temp.getData();
                temp = temp.getLeft();
            }
            return min_val;
        }
        return -1;
    }

    // A toString function that returns "NULL" if the tree is empty.
    // Otherwise, it returns the InOrder traversal of the tree.
    public String toString()
    {
        if(IsEmpty())
        {
            return "NULL";
        }
        return InOrder(root, "");
    }

    // A Print function that prints out the InOrder, PreOrder, and
    // PostOrder traversals of the tree (each one preceeded by the word
    // identifying what kind of traversal it is). It also calls the
    // private Print() function which prints out the tree sideways.
    public void Print()
    {
        System.out.println("InOrder: " + InOrder(root, ""));
        System.out.println("PreOrder: " + PreOrder(root, ""));
        System.out.println("PostOrder: " + PostOrder(root, ""));
        Print(root,0);
    }

    // A Print function that takes a node and an int to recursively print
    // out the tree sideways. The int "lev" determines how far away from
    // the root that particular node will be printed. (Refer to notes for 
    // details of this function).
    private void Print(Node n, int lev)
    {
        if (n!=null)
        {
            Print(n.getRight(), lev+1);

            for(int i = 0; i<lev; i++)
            {
                System.out.print("\t");
            }
            System.out.println(n.getData());

            Print(n.getLeft(), lev+1);
        }
    }

    // A function that returns if two trees are equal by value.
    public boolean Equals(Tree t)
    {
        String tree_1 = PreOrder(this.root, "");
        String tree_2 = PreOrder(t.root, "");
        if(tree_1.equals(tree_2))
        {
            return true;
        }
        return false;

    }
}
