package filesystem;
public class Directory {
    String name;
    Directory children;
    Directory parent;
    Directory next;
    public Directory(String name){
        this.name=name;
        this.children=null;
        this.parent=null;
        this.next=null;
    }
    public boolean containsChild(String name){
        Directory childrens=this.children;
        while(childrens!=null)
        {
            if(name.equals(childrens.name))
                return true;
            childrens=childrens.next;
        }
        return false;
    }
    public Directory getChild(String name){
        Directory childrens=this.children;
        while(childrens!=null)
        {
            if(name.equals(childrens.name))
                return childrens;
            childrens=childrens.next;
        }
        return null;
    }
    public void listChildDirectories(){
        Directory childrens=this.children;
        while(childrens!=null)
        {
            System.out.println(childrens.name);
            childrens=childrens.next;
        }
    }
    public void addChild(String name){
        Directory d=this;
        Directory temp=new Directory(name);
        if(this.children==null){
            this.children=temp;
            //temp.parent=d;
        }
        else
        {
            Directory child=this.children;
            while(child.next!=null){
                child=child.next;
            }
            child.next=temp;
        }
        temp.parent=d;
    }
    /*public void display()
    {
        Directory child=this.children;
        while(child!=null){
            System.out.print(child.name);
            child=child.next;
        }
    }*/
}
