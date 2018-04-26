package cheater;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class cheaters {
	public static String filename;
	public static int seqlen;
	public static String dir;
    public static ArrayList<File> filelist;
	public static void main(String[] args) {
		
		parseArgs(args);
        File file = null;
        File fileComp = null;
        LinkedList<String> seq = new LinkedList<String>();
        LinkedList<String> seq2 = new LinkedList<String>();
        for(int i = 0; i < filelist.size();i++) {
        	file = filelist.get(i);
	        for(int j = i+1; j < filelist.size();j++) {
	        	fileComp = filelist.get(j);
	        	int similarities = 0;
	        	//System.out.println("Comparing "+ file + "against " + fileComp);
	    		try {
					Scanner scan = new Scanner(file);
		        	while(scan.hasNext()) {
		        		seq.add(scan.next().replaceAll("[^a-zA-Z]", ""));
		        		if(seq.size() == seqlen) {
							Scanner scan2 = new Scanner(fileComp);

				        	while(scan2.hasNext()) {
				        		seq2.add(scan2.next().replaceAll("[^a-zA-Z]", ""));
				        		if(seq2.size() == seqlen) {
				        			if(seq.equals(seq2)) {
				        			//	System.out.println("Similarity between" + file + "and " + fileComp);
				        				//System.out.println(seq);
				        				//System.out.println(seq2);
				        				similarities++;
				        			}
					        		seq2.poll();
				        		}
				        	}
		        			seq.poll();
				            scan2.close();
		        		}
		        	}
		        	
		        	
		            scan.close();

				} catch (FileNotFoundException e) {
					System.out.println("File not Found");
					System.exit(1);
				}
	    		System.out.println(similarities + "detected between " + file + " and " + fileComp);
	        }
		}

	}
	
    public static void parseArgs(String[] args) {
    	
        filename = null;
        seqlen = 0;
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
