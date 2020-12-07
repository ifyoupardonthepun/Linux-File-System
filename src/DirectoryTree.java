// Shrey Shah
// ID: 112693183
// Shrey.Shah@stonybrook.edu
// Homework #5
// CSE214
// R.04 James Finn

import org.jetbrains.annotations.NotNull;

public class DirectoryTree {
    public DirectoryNode root;
    public DirectoryNode cursor;

    /**
     * Initializes a DirectoryTree object with a single DirectoryNode named "root".
     */
    public DirectoryTree() {
        root = new DirectoryNode("root", false, null);
        cursor = root;
    }

    /**
     * Moves the cursor to the root node of the tree.
     */
    public void resetCursor() {
        cursor = root;
    }

    /**
     * Moves the cursor to the directory with the name indicated by name.
     *
     * @param name
     * references a valid directory ('name' cannot reference a file).
     *
     * @throws NotADirectoryException
     * The node with the indicated name is a file, as files cannot be selected by the cursor, or cannot be found.
     *
     * @throws NoSuchDirectoryException
     * If directory is not one of the children
     */
    public void changeDirectory(String name) throws NotADirectoryException, NoSuchDirectoryException{
        if(cursor.getLeft() != null) {
            if (cursor.getLeft().getName().equals(name)) {
                if (!cursor.getLeft().isFile()) {
                    cursor = cursor.getLeft();
                    return;
                } else {
                    throw new NotADirectoryException();
                }
            }
        }
        if(cursor.getMiddle() != null) {
            if (cursor.getMiddle().getName().equals(name)) {
                if (!cursor.getMiddle().isFile()) {
                    cursor = cursor.getMiddle();
                    return;
                } else {
                    throw new NotADirectoryException();
                }
            }
        }
        if(cursor.getRight() != null) {
            if (cursor.getRight().getName().equals(name)) {
                if (!cursor.getRight().isFile()) {
                    cursor = cursor.getRight();
                    return;
                } else {
                    throw new NotADirectoryException();
                }
            }
        }
        throw new NoSuchDirectoryException();
    }

    /**
     * A String containing the path of directory names from the root node of the tree to the cursor, with each name separated by a forward slash "/".
     *
     * @return
     *  String containing the path of directory names from the root node of the tree to the cursor
     *
     */
    public String presentWorkingDirectory() {
        DirectoryNode ptr = cursor;
        StringBuilder pwd = new StringBuilder(ptr.getName());
        while(ptr.getParent() != null) {
            pwd.insert(0, ptr.getParent().getName() + "/");
            ptr = ptr.getParent();
        }
        return pwd.toString();
    }

    /**
     * a String containing a space-separated list of names of all the child directories or files of the cursor.
     *
     * @return
     * A formatted String of DirectoryNode names.
     */
    public String listDirectory() {
        String listDir = "";
        if(cursor.getLeft() != null) {
            listDir += cursor.getLeft().getName()+ " ";
        }
        if(cursor.getMiddle() != null) {
            listDir += cursor.getMiddle().getName()+ " ";
        }
        if(cursor.getRight() != null) {
            listDir += cursor.getRight().getName();
        }
        return listDir;
    }

    /**
     * Prints a formatted nested list of names of all the nodes in the directory tree, starting from the cursor.
     */
    public void printDirectoryTree() {
        helper(cursor, 0);
    }

    /**
     * Helper method for printDirectoryTree().
     * Prints a formatted nested list of names of all the nodes in the directory tree, starting from the cursor.
     *
     * @param node
     * Temp node that remains at the same DirectoryNode.
     *
     * @param tabs
     * number of tabs for nesting
     */
    public void helper(DirectoryNode node, int tabs) {
        if(node == null) {
            return;
        }
        for(int i = 0; i < tabs; i++) {
            System.out.print("   ");
        }
        if(node.isFile()) {
            System.out.printf("- " + node.getName() + "%n");
        } else {
            System.out.printf("|- " + node.getName() + "%n");
            helper(node.getLeft(), tabs + 1);
            helper(node.getMiddle(), tabs + 1);
            helper(node.getRight(), tabs + 1);
        }
    }

    /**
     * Creates a directory with the indicated name and adds it to the children of the cursor node
     *
     * @param name
     * The name of the directory to add.
     *
     * @throws IllegalArgumentException
     * The 'name' argument is invalid.
     *
     * @throws FullDirectoryException
     * If all child references of this directory are occupied.
     */
    public void makeDirectory(@NotNull String name) throws IllegalArgumentException, FullDirectoryException{
        if(name.contains(" ") || name.contains("/")) {
            throw new IllegalArgumentException("Directory already contains a file with the same name.");
        }
        try {
            DirectoryNode newChild = new DirectoryNode(name, false, cursor);
            cursor.addChild(newChild);
        } catch(FullDirectoryException e) {
            throw new FullDirectoryException();
        } catch (NotADirectoryException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creates a file with the indicated name and adds it to the children of the cursor node.
     *
     * @param name
     * The name of the file to add.
     *
     * @throws IllegalArgumentException
     *  The 'name' argument is invalid.
     *
     * @throws FullDirectoryException
     * If all child references of this directory are occupied.
     */
    public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException{
        if(name.contains(" ") || name.contains("/")) {
            throw new IllegalArgumentException("File names should not contain spaces or slashes.");
        }
        try
        {
            DirectoryNode newChild = new DirectoryNode(name, true, cursor);
            cursor.addChild(newChild);
    } catch(FullDirectoryException e) {
            throw new FullDirectoryException();
        } catch (NotADirectoryException e) {
            System.out.println(e.getMessage());
        }
    }
}