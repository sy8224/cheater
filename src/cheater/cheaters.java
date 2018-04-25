package cheater;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class cheaters {
	public static String filename;
	public static int numFiles;
	public static String dir;
	public static void main(String[] args) {
		
		parseArgs(args);
		Scanner scan = new Scanner("File");
	}
	
    public static void parseArgs(String[] args) {
    	
        filename = null;
        numFiles = 0;
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
        ArrayList<File> filelist = new ArrayList<File>();
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
