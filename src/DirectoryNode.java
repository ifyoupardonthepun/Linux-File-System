// Shrey Shah
// ID: 112693183
// Shrey.Shah@stonybrook.edu
// Homework #5
// CSE214
// R.04 James Finn

public class DirectoryNode {
    public String name;
    public DirectoryNode left;
    public DirectoryNode middle;
    public DirectoryNode right;
    public DirectoryNode parent;
    public boolean isFile;

    /**
     *
     * @param n
     * The name of the node
     *
     * @param f
     * Whether the node is a file or directory.
     *
     * @param p
     * The parent of the node.
     */
    public DirectoryNode(String n, boolean f, DirectoryNode p) {
        name = n;
        isFile = f;
        parent = p;
    }

    /**
     *
     * @return
     * The name of the DirectoryNode
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     * The left child of the node.
     */
    public DirectoryNode getLeft() {
        return left;
    }

    /**
     *
     * @return
     * The middle child of the node.
     */
    public DirectoryNode getMiddle() {
        return middle;
    }

    /**
     *
     * @return
     * The right child of the node.
     */
    public DirectoryNode getRight() {
        return right;
    }

    /**
     *
     * @return
     * The parent of the node.
     */
    public DirectoryNode getParent() {
        return parent;
    }

    /**
     *
     * @return
     * Whether the node is a file.
     */
    public boolean isFile() {
        return isFile;
    }

    /**
     *
     * @param newChild
     * Adds newChild to any of the open child positions of this node (left, middle, or right).
     *
     * @throws NotADirectoryException
     * If the current node is a file, as files cannot contain DirectoryNode references (i.e. all files are leaves).
     *
     * @throws FullDirectoryException
     * If all child references of this directory are occupied.
     */
    public void addChild(DirectoryNode newChild) throws NotADirectoryException, FullDirectoryException {
        if (isFile) {
            throw new NotADirectoryException();
        }
        if (left != null) {
            if (middle != null) {
                if (right != null) {
                    throw new FullDirectoryException();
                } else
                    right = newChild;
                } else
                    middle = newChild;
                } else
                    left = newChild;
    }
}