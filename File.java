package filesystem;
import java.util.Scanner;
public class File {
    private final Directory root;
    private Directory currentDirectory;
    private final Scanner scanner;
    private String currentPath;
    public File(){
        this.root=new Directory("~");
        this.currentDirectory=root;
        this.scanner=new Scanner(System.in);
        this.currentPath="~";
    }
    public void takeCommands(){
        while(true){
            System.out.print(this.currentPath+">");
            String command=this.scanner.nextLine();
            if(command.equalsIgnoreCase("exit")){
                System.out.println("Thanks");
                break;
            }
            takeActionOnCommand(command); 
        }
    }
    public void takeActionOnCommand(String command){
        command=command.trim();
        String[] commandsArray=split(' ',command);
        /*for(int i=0;i<commandsArray.length;i++){
            System.out.println(commandsArray[i]);
        }*/
        switch(commandsArray[0]){
            case "md":
                if(commandsArray.length==1)
                    System.out.println("Enter the correct command");
                else
                    makeDirectory(commandsArray[1]);
                break;
            case "cd":
                if(commandsArray.length==1)
                    changeDirectory("~");
                else
                    changeDirectory(commandsArray[1]);
                break;
            case "ls":
                listChildDirectory();
                break;
            case "pwd":
                listPath();
                break;
            default:
                System.out.println("Enter the correct command");
        }
    }
    public void listChildDirectory(){
        this.currentDirectory.listChildDirectories();
    }
    public void listPath(){
        System.out.println();
        System.out.println(this.currentPath);
    }
    public void makeDirectory(String command){
        String[] dirArray=split('/',command);
        /*for(int i=0;i<dirArray.length;i++){
            System.out.println(dirArray[i]);
        }*/
        Directory dir=this.currentDirectory;
        if(dirArray.length>1){
            Directory tempDir=traverse(dir,dirArray,0,dirArray.length-1);
            if(tempDir!=null){
                addChild(tempDir,dirArray[dirArray.length-1]);
            }
        }else{
            addChild(dir,dirArray[0]);
        }
        //display(dir);
    }
    public void changeDirectory(String commandPath)
    {
        String[] dirArray=split('/',commandPath);
        if(dirArray[0].equals("~")){
            if(dirArray.length>1){
                Directory temp=traverse(root,dirArray,1,dirArray.length);
                if(temp==null){
                    return; 
                }
                this.currentDirectory=temp;
                this.currentPath=join(dirArray,0,dirArray.length-1,'/');
            }else{
             this.currentDirectory=root;
             this.currentPath="~";
            }
        }
        else if(dirArray[0].equals("..")){
            String[] currentPathArray=split('/',this.currentPath);
            this.currentPath=join(currentPathArray,0,currentPathArray.length-2,'/');
            this.currentDirectory=this.currentDirectory.parent;
        }
        else{
            int start=dirArray[0].equals(".")?1:0;
            Directory tempDir=traverse(this.currentDirectory,dirArray,start,dirArray.length);
            if(tempDir!=null){
                this.currentPath+="/"+join(dirArray,start,dirArray.length-1,'/');
                this.currentDirectory=tempDir;
            }
        }
    }
    public String join(String[] dirArray,int start,int end,char delimiter){
        String tempPath="";
        for(int i=start;i<end;i++){
            tempPath=tempPath+dirArray[i]+delimiter;
        }
        tempPath=tempPath+dirArray[end];
        return tempPath;
    }
    public Directory traverse(Directory dir,String[] dirArray,int start,int end)
    {
        
        for(int i=start;i<end;i++){
          dir=dir.getChild(dirArray[i]);
          if(dir==null){
              System.out.println("Directory "+dirArray[i]+" doesn't exist");
              return null;
          }
        }
        return dir;
        //return null;
    }
    /*public void display(Directory tempdir){
        tempdir.display();
    }*/
    public void addChild(Directory dir,String directoryName){
        int c=1;
        if(dir.containsChild(directoryName)){
            System.out.println(directoryName+" Directory already exist");
        }
        else{
            System.out.println(c);
            dir.addChild(directoryName);
        }
    }
    public String[] split(char delimiter,String command){
        String[] splitString=new String[count(delimiter,command)];
        String str="";
        int count=0;
        for(int i=0;i<command.length();i++){
            if(command.charAt(i)==delimiter){
                splitString[count++]=str;
                str="";
            }
            else
                str=str+command.charAt(i);
        }
        splitString[count]=str;
        return splitString;
    }
    public int count(char delimiter,String command){
        int countVal=0;
        for(int i=0;i<command.length();i++){
            if(command.charAt(i)==delimiter){
                countVal++;
            }
        }
        ++countVal;
        return countVal;
    }
}
