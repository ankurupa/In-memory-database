/**
* @File Name: AppVar.java
* @Author: Ankur Upadhyay, Research Assistant, Computer Science Department, University at Buffalo.
* The instance of the class is used to represent the session of dastabse
**/

import java.util.*;

public class Database{

	protected ArrayList<Variable> variables;
	protected ArrayList<Integer> transactions;	
	
	public Database(){
		this.variables = new ArrayList<Variable>();
		this.transactions = new ArrayList<Integer>();
	}

	public void beginTransaction(){
		this.transactions.add(this.variables.size());
	}

	public void rollbackTransaction(){
		int start = this.transactions.get(this.transactions.size()-1);
		int counter = this.variables.size()-1;
		while(counter >= start){
			this.variables.remove(counter);
			counter--;
		}
		this.transactions.remove(this.transactions.size()-1);
	}

	public void set(String name, int value){
		Variable variable = new Variable(name, value);
		this.variables.add(variable);
	}

	public Variable get(String name){
		if(this.variables.size() == 0)
			return null;
		int counter = this.variables.size()-1;
		Variable variable = null;		
		boolean found = false;		
		while(counter >= 0){
			variable = this.variables.get(counter);
			if(variable.getName().equals(name))				
				return variable;
			counter--;
		}
		return null;
	}
	
	public void commitTransaction(){}

	public int numEqual(int value){
		Set<String> vars = new HashSet<String>();
		int counter = this.variables.size()-1;
		int number = 0;
		Variable variable = null;		
		while(counter >= 0){
			variable = this.variables.get(counter);
			if(!vars.contains(variable.getName())){
				if(variable.getValue() == value)
					number++;
				vars.add(variable.getName());
			}
			counter--;	
		}
		return number;
	}

	public void unset(String name){
		int number=0;
		int counter = this.variables.size()-1;
		Variable variable=null;		
		while(counter >= 0){
			variable=this.variables.get(counter);
			if(variable.getName().equals(name))
				number++;
			if(number == 2) 
				break;
			counter--;	
		}
		Variable new_variable = null;
		if(number == 2)
			new_variable = new Variable(name, variable.getValue());
		else
			new_variable = new Variable(name, 0);
		this.variables.add(new_variable);
	}
}
