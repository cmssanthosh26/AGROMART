
import java.util.Scanner;

public class FileSystem {
    private final Directory root;
    private final Scanner scanner;
    private Directory currentDirectory;
    private String currentPath;

    public FileSystem() {
        root = new Directory("~");
        currentDirectory = root;
        currentPath = "~";
        scanner = new Scanner(System.in);
    }

    public void takeCommands() {
        while(true) {
            System.out.print(currentPath + " > ");
            String command = scanner.nextLine();
            if(command.equals("exit"))
                break;
            parseCommand(command);
        }
    }

    public void parseCommand(String command) {
        String[] parsedCommand = splitPath(' ', command);
        switch (parsedCommand[0]) {
            case "md":
                if (parsedCommand.length == 1) {
                    System.out.println("Error: Directory name must not be empty");
                } else {
                    makeDirectory(parsedCommand[1]);
                }
                break;
            case "pwd":
                System.out.println(currentPath);
                break;
            case "cd":
                if (parsedCommand.length == 1) {
                    changeDirectory("~");
                } else {
                    changeDirectory(parsedCommand[1]);
                }
                break;
            case "ls":
                listDirectory();
                break;
            default:
                System.out.println(parsedCommand[0] + " command not recognized.");
                break;
        }
    }

    public Directory traverse(Directory dir, String[] dirInPath, int start, int end) {
        Directory tempDir = dir;
        for(int i=start; i<end; i++) {
            Directory temp =tempDir.getChild(dirInPath[i]);
            if(temp != null) {
                tempDir = temp;
            } else {
                System.out.println("Error: " + dirInPath[i] + " directory does not exist");
                return dir;
            }
        }
        return tempDir;
    }

    public void addChild(Directory tempDir, String directoryName) {
        if(tempDir.containsChild(directoryName)) {
            System.out.println(directoryName + " directory already exits.");
        } else {
            tempDir.addChild(directoryName);
        }
    }

    public void makeDirectory(String directoryPath) {
        String[] dirInDirectoryPath = splitPath('/', directoryPath);

        Directory tempDir = currentDirectory;

        if(dirInDirectoryPath.length > 1) {
            tempDir = traverse(tempDir, dirInDirectoryPath, 0, dirInDirectoryPath.length-1);
            if(tempDir != currentDirectory) {
                addChild(tempDir, dirInDirectoryPath[dirInDirectoryPath.length-1]);
            }
        } else {
            addChild(tempDir, dirInDirectoryPath[0]);
        }
    }

    public void changeDirectory(String path) {
        String[] dirInPath = path.split("/");

        if(dirInPath[0].equals("~")) {
            if(dirInPath.length > 1) {
                Directory tempDir = traverse(root, dirInPath, 1, dirInPath.length);
                if(tempDir != currentDirectory) {
                    currentDirectory = tempDir;
                    currentPath = String.join("/", dirInPath);
                }

            } else {
                currentDirectory = root;
                currentPath = "~";
            }
        } else if(dirInPath[0].equals("..")) {
            if(currentPath.equals("~")) {
                System.out.println("Error: You are already in root directory.");
                return;
            }
            String[] dirInCurrentPath= splitPath('/', currentPath);
            dirInCurrentPath = copyRange(  dirInCurrentPath, 0, dirInCurrentPath.length-1);
            currentDirectory = traverse(root, dirInCurrentPath, 1, dirInCurrentPath.length);
            currentPath = String.join("/", dirInCurrentPath);
        } else {
            int start = (dirInPath[0].equals(".")) ? 1 : 0;
            if(start==0 || dirInPath.length>1) {
                Directory tempDir = traverse(currentDirectory, dirInPath, start, dirInPath.length);
                if(tempDir!=currentDirectory) {
                    currentPath+= "/"+String.join("/", copyRange( dirInPath, start, dirInPath.length));
                    currentDirectory=tempDir;
                }
            }
        }
    }

    public void listDirectory() {
        currentDirectory.listChildren();
    }

    public String[] copyRange(String[] dirs, int start, int end) {
        String[] dirsCopy = new String[end-start];
        for(int i=start, j=0; i<end; i++, j++) {
            dirsCopy[j] = dirs[i];
        }
        return  dirsCopy;
    }

    public String joinString(char joiner, String[] dirs) {
        String path = "";
        for(int i=0; i<dirs.length; i++) {
            path+=dirs[i];
            if(i!= dirs.length-1)
                path+=joiner;
        }
        return path;
    }

    public int getDirCount(char delimiter, String path) {
        int dirCount=0;
        for(int i=0; i<path.length(); i++) {
            if(i!=0 && path.charAt(i)==delimiter) dirCount++;
        }
        if(path.charAt(path.length()-1) != delimiter) {
            dirCount++;
        }
        return dirCount;
    }

    public String[] splitPath(char delimiter, String path) {
        int count = getDirCount( delimiter, path);
        String[] dirInPath = new String[count];
        int dirCount=0;
        for(int i=0; i<path.length(); i++) {
            dirInPath[dirCount] = "";
            if(i==0 && path.charAt(i)==delimiter)
                continue;
            while(i<path.length() && path.charAt(i) != delimiter) {
                dirInPath[dirCount]+=path.charAt(i);
                i++;
            }
            dirCount++;
        }
        return dirInPath;
    }
}
