public class Directory {
    private String name;
    private Directory children;
    private Directory next;

    public Directory(String name) {
        this.name = name;
        this.children = null;
        this.next = null;
    }

    public String getName() {
        return name;
    }

    public boolean containsChild(String directoryName) {
        Directory currentNode = children;
        while(currentNode!=null) {
            if(directoryName.equals(currentNode.name)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public Directory getChild(String directoryName) {
        Directory currentNode = children;
        while(currentNode!=null) {
            if(directoryName.equals(currentNode.name)) {
                return currentNode;
            }
            currentNode=currentNode.next;
        }
        return null;
    }

    public void addChild(String directoryName) {
        if(children == null) {
            this.children = new Directory(directoryName);

        } else {
            Directory currentNode = children;
            while(currentNode.next!=null) {
                currentNode = currentNode.next;
            }
            currentNode.next = new Directory(directoryName);
        }
    }

    public void listChildren() {
        if(childrenSize() > 0) {
            Directory currentNode = children;
            while(currentNode!=null) {
                System.out.println(currentNode.getName());
                currentNode = currentNode.next;
            }
        }
    }

    public int childrenSize() {
        int count=0;
        Directory currentNode = children;
        while(currentNode!=null) {
            currentNode = currentNode.next;
            count++;
        }
        return count;
    }

    public void add(Directory dir) {

    }
}
