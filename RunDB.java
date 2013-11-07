/**
* @File Name: AppVar.java
* @Author: Ankur Upadhyay, Research Assistant, Computer Science Department, University at Buffalo.
* The class contains the main method
**/

import java.io.*;
public class RunDB{
	public static void main(String[] args){
		Database db = new Database();
		String inputFile = null;
		for(int i=0; i<args.length; i++){
			if(args[i].equals("-i"))
				inputFile = new String(args[i+1]);
		}
		
		try{
		FileInputStream fStream = new FileInputStream(inputFile);
            	DataInputStream in = new DataInputStream(fStream);
	    	BufferedReader br = new BufferedReader(new InputStreamReader(in));
            	String strLine;	
		while ((strLine = br.readLine()) != null){
			if(strLine.contains("BEGIN")){
				db.beginTransaction();
			}else if(strLine.contains("GET")){
				String[] parameters = strLine.split(" ");
				Variable variable = db.get(parameters[1]);
				if(variable != null)
					System.out.println(variable.getValue());
				else
					System.out.println("NULL");			
			}else if(strLine.contains("SET") && !(strLine.contains("UNSET"))){
				String[] parameters = strLine.split(" ");
				db.set(parameters[1], Integer.parseInt(parameters[2]));
			}else if(strLine.contains("COMMIT")){
				db.commitTransaction();
			}else if(strLine.contains("ROLLBACK")){
				db.rollbackTransaction();
			}else if(strLine.contains("END")){
				
			}else if(strLine.contains("NUMEQUALTO")){
				String[] parameters = strLine.split(" ");
				int number = db.numEqual(Integer.parseInt(parameters[1]));
				System.out.println(number);
			}else if(strLine.contains("UNSET")){
				String[] parameters = strLine.split(" ");
				db.unset(parameters[1]);
			}else{

			}
		}
	
		}catch(Exception exception){
			exception.printStackTrace();		
		}
	}
}
