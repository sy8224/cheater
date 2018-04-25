package cheater;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class cheaters {
	public static String filename;
	public static String dir;
    public static ArrayList<File> filelist;
	public static void main(String[] args) {
		
		parseArgs(args);
        System.out.println(filelist);
        File file = null;
        File fileComp = null;
        LinkedList<String> seq = new LinkedList<String>();
        
        for(int i = 0; i < filelist.size();i++) {
        	file = filelist.get(i);
	        for(int j = 1; j < filelist.size();j++) {
	        	fileComp = filelist.get(j);
	    		try {
					Scanner scan = new Scanner(file);
		        	while(scan.hasNext()) {
		        		seq.add(scan.next().replaceAll("[^a-zA-Z]", ""));
		        			System.out.println(seq);
		        			seq.poll();
		        		}
		        	}
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
        		throw new NumberFormatException();
        	}
        }catch(NumberFormatException e) {
        	System.out.println("Not a valid word sequence length");
            System.exit(1);
        }

    }
}
