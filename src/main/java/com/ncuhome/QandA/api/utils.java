package com.ncuhome.QandA.api;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class utils {
	
	protected static final Random r = new Random();

	public static int randInt() {
		return r.nextInt();
	}
	
	public static int randIntRanged(int range) {
		return r.nextInt(range);
	}
	
	public static void shuffle(List<?> list) {
		Collections.shuffle(list);
	}
	
	public static List<String> listDir(String directoryPath, String suffix) {
		List<String> res = new ArrayList<String>();
		File filePtr = new File(directoryPath);
		FileFilter filter = new FileFilter() {
			
			public boolean accept(File file) {
				if(file.isFile() && file.getName().endsWith(suffix)) {
					return true;
				}
				else {
					return false;
				}
			}
		};
		if(!filePtr.exists()) {
			return res;
		}
		File[] files = filePtr.listFiles(filter);
		for(File file: files) {
			res.add(file.getName());
		}
		return res;
	}
	
	public static List<String> listDir(String directoryPath) {
		return listDir(directoryPath, "");
	}
}
