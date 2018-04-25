package cheater;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class cheaters {
	public static String filename;
	public static int numFiles;
	public static String dir;
    public static ArrayList<File> filelist;
	public static void main(String[] args) {
		
		parseArgs(args);
        System.out.println(filelist);
        File file = null;
        File fileComp = null;
        for(int i = 0; i < filelist.size();i++) {
        	file = filelist.get(i);
	        for(int j = 1; j < filelist.size();j++) {
	        	fileComp = filelist.get(j);
	    		try {
					Scanner scan = new Scanner(file);
					Scanner scan2 = new Scanner(fileComp);
				} catch (FileNotFoundException e) {
					System.out.println("File not Found");
					System.exit(1);
				}
	
	        }
		}
	}
	
    public static void parseArgs(String[] args) {
    	
        filename = null;
        numFiles = 0;
        filelist = new  ArrayList<File>();
        dir = null;
        if (args.length != 2) {
        	System.out.println("Invalid Use");
        	System.out.println("Invalid Use");
        	System.out.println("java cheaters [File Path] [Word Sequence Length]");
            System.exit(1);
        }
        dir = args[0];
        File folder = new File(dir);
        File[] files = folder.listFiles();
        if(folder.isDirectory()) {
        	if(folder.list().length == 0) {
        		System.out.println("Directory has no files");
        		System.exit(1);
        	}
        }else {
        	System.out.println("This is not a Directory");
        	System.exit(1);
        }

        for(File file : files) {
        	filelist.add(file);
        }

        try {
        	numFiles = Integer.parseInt(args[1]);
        	if(numFiles <= 0) {
        		throw new NumberFormatException();
        	}
        }catch(NumberFormatException e) {
        	System.out.println("Not a valid word sequence length");
            System.exit(1);
        }

    }
}
