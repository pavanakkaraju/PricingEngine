import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Vector;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 * 
 */

/**
 * @author pavan
 *
 */
public class TopFrequentFinder {

	private final String INPUT_FILE="E:\\MySql\\sample_input.txt";
	private final String OUTPUT_FILE="E:\\MySql\\OUTPUT.properties";
	/**
	 * 
	 */
	public TopFrequentFinder() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TopFrequentFinder tf=new TopFrequentFinder();
		try {
			tf.topFrequency();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void topFrequency() throws Exception{
		File inputFile=new File(INPUT_FILE);
		File ouputFile=new File(OUTPUT_FILE);
		if(!ouputFile.exists()){
			ouputFile.createNewFile();
		}
		else{
				ouputFile.delete();
				ouputFile.createNewFile();
		}
		LineIterator it = FileUtils.lineIterator(inputFile, "UTF-8");
		StringBuilder sbuffer=new StringBuilder();
		String[] tempArray=null;		
		int i=0;
		try {
		    while (it.hasNext()) {
		    	sbuffer.delete(0, sbuffer.length());
		    	sbuffer.append(it.nextLine());
		        System.out.println(i +"\t"+sbuffer.toString());
		        tempArray=sbuffer.toString().split("\\|");
		        for(int j=0;j<tempArray.length;j++){
		        	checkAndSaveProperty(ouputFile, tempArray[j]);
		        }
		        i++;
		        if(i>=100){
		        	break;
		        }
		    }
		} finally {
		    LineIterator.closeQuietly(it);
		}
//		Properties prop = new Properties();		
//		InputStream is=new FileInputStream(ouputFile);
//		prop.load(is);
//		sortValue(prop);
	}
	
	
	public void checkAndSaveProperty(File propFile,String key) throws Exception{
		Properties prop = new Properties();	
		try(InputStream is=new FileInputStream(propFile)){
		prop.load(is);
		
		String propValue = prop.getProperty(key);
		if(propValue == null)/* if property does not exist write for the first time*/
		{
			prop.setProperty(key, 1+"");
		}
		else /* if property does exists increment the value by 1 and write*/
		{
			prop.setProperty(key, Long.parseLong(propValue)+1+"");
		} 
		prop.store(new FileOutputStream(propFile), null);
		}catch (Exception e) {
			throw e;
		}
	}
	
}

class PlainPojo{
	private Integer value;
	private String key;
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}

