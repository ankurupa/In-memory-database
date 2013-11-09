/**
* @File Name: RunDB.java
* @Author: Ankur Upadhyay, Research Assistant, Computer Science Department, University at Buffalo.
* The class contains the main method. The main method, reads the commands from the input file. 
* The command for running the database is "java RunDB -i <input-file-name>", where input files are located in test folder.
**/

import java.io.*;
public class RunDB{

	/**
	* main method
	**/
	public static void main(String[] args){
		Database db = new Database();
		String inputFile = null;
		for(int i=0; i<args.length; i++){
			if(args[i].equals("-i"))
				inputFile = new String(args[i+1]);
		}
		
		/**
		* The main method reads the command from the file.
		**/
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
					if(variable != null){
						if(variable.isUnset())
							System.out.println("NULL");
						else
							System.out.println(variable.getFinalValue());
					}else
						System.out.println("NULL");			
				}else if(strLine.contains("SET") && !(strLine.contains("UNSET"))){
					String[] parameters = strLine.split(" ");
					db.set(parameters[1], Integer.parseInt(parameters[2]));
				}else if(strLine.contains("COMMIT")){
					db.commitTransaction();
				}else if(strLine.contains("ROLLBACK")){
					db.rollbackTransaction();
				}else if(strLine.contains("END")){
					System.exit(0);
				}else if(strLine.contains("NUMEQUALTO")){
					String[] parameters = strLine.split(" ");
					int number = db.numEqual(Integer.parseInt(parameters[1]));
					System.out.println(number);
				}else if(strLine.contains("UNSET")){
					String[] parameters = strLine.split(" ");
					db.unset(parameters[1]);
				}else{
					System.out.println("The database does not support the command. All commands should be in upper case.")
				}
			}
		}catch(Exception exception){
			exception.printStackTrace();		
		}
	}
}
