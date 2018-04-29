package cheater;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

public class cheaters {
	public static int dLimit;
	public static String filename;
	public static int seqlen;
	public static String dir;
    public static ArrayList<File> filelist;
	public static void main(String[] args) {
		
		parseArgs(args);
        LinkedList<String> seq = new LinkedList<String>();
        
        ArrayList<Set>record = new ArrayList<Set>();
        for(int i = 0;i<filelist.size();i++) {
        	Set<String>setTest = new HashSet<String>();
        	try {
        		Scanner scan = new Scanner(filelist.get(i));
        		while(scan.hasNext()) {	
        			String test = scan.next().replaceAll("[^a-zA-Z1-9]", "").toLowerCase();
        			seq.add(test);
        		}

        		for(int j = 0;j < seq.size()+1-seqlen;j++) {
        			
        			String adds = "";
        			for(int x = 0;x<seqlen;x++) {
        				adds = adds + seq.get(j+x) + " ";
        			}
        			setTest.add(adds);	//sequences in file i
        		}
        		scan.close();
        	}
        	catch(FileNotFoundException  e) {
        		System.out.println("File not Found");
        		System.exit(1);
        	}
        	record.add(setTest); //array of sequences of every file
        	seq.clear();
        }
        //prints similarities between files
        for(int i = 0;i<filelist.size();i++) {
        	for(int j = i+1;j<filelist.size();j++) {
        		 testCompare1(record.get(i),record.get(j),i,j);
        	}
        }
        System.exit(1);
	}
	/**
	 * testCompare1 compares the word phrases between files and counts if it has any matches and prints that amount
	 * @param s1 = set of phrases of file 1
	 * @param s2 = set of phrases of file 2
	 * @param a = location of file 1 in filelist
	 * @param b = location of file 2 in filelist
	 */
	public static void testCompare1(Set<String>s1, Set<String>s2,int a, int b) {
		int differences = 0;
		Iterator<String>it = s2.iterator();
		while(it.hasNext()) {
			if(s1.contains(it.next())){
				differences++;
			}
		}
		if(differences>dLimit) {
			System.out.println(differences + " TOTAL SIMILARITIES between " + (filelist.get(a).toString()).replace(dir, "").replace("/","").replace("\\", "") 
					+ " and " + (filelist.get(b).toString()).replace(dir, "").replace("/","").replace("\\", ""));
		}
		
	}
/**
 * parseArgs looks at the user input and deems whether it is a valid input form
 * [File Path] [Word Sequence Length] [Difference Limit]
 * @param args
 */
    public static void parseArgs(String[] args) {

        filename = null;
        seqlen = 0;
        filelist = new  ArrayList<File>();
        dir = null;
        if (args.length != 3) {
        	System.out.println("Invalid Use");
        	System.out.println("Invalid Use");
        	System.out.println("java cheaters [File Path] [Word Sequence Length] [Difference Limit]");
            System.exit(1);
        }
    	dLimit = Integer.parseInt(args[2]);
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
        	seqlen = Integer.parseInt(args[1]);
        	if(seqlen <= 0) {
        		throw new NumberFormatException();
        	}
        }catch(NumberFormatException e) {
        	System.out.println("Not a valid word sequence length");
            System.exit(1);
        }

    }
}