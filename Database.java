/**
* @File Name: Database.java
* @Author: Ankur Upadhyay, Research Assistant, Computer Science Department, University at Buffalo.
* The instance of the class is used to represent the session of dastabse
* Method  details:
 -----------------
 * beginTransaction :- The method is used to begin the transaction. The time complexity of the method is O(1).
 * rollbackTransaction :- The method is used to rollback the last live transaction. The time complexity of the method is O(n).
 * set() :- The method is used to set the value of some particular. The time complexity of the method is O(1).
 * get() :- The method is used to get the value of the variable. The time complxity of the method is O(1).
 * commitTransaction() :- The method is used to commit all the live transactions in the database. The time complexity of the method is O(1).
 * numEqual() :- The method is used to return the number of variables whose value is equal to particular number. The time complexity of the method is O(n).
*			      While this compleity can be reduced to constant time (O(1)) by using an extra space such as Hash Map or Tree Map which would keep the mapping *			       of the values to the number of variables, but I preffered to reduce the space optimization by increasing the time complexity to linear.
 * unset () :- The method is used to unset the value of the variable. The time complexity of the method is O(1).
**/

import java.util.*;

public class Database{

	protected ArrayList<Variable> operationTracker; //tracker for the operations performed in the database session
	protected ArrayList<Integer> transactionTracker; //tracker for all the live transactions in the database session.
    	protected HashMap<String, ArrayList<Integer>> variableTracker; //tracker for all the variables
    	protected int tracker; //tracker for tracking the last oepration performed in database session.

	
	public Database(){
		this.operationTracker = new ArrayList<Variable>();
		this.transactionTracker = new ArrayList<Integer>();
        	this.variableTracker = new HashMap<String, ArrayList<Integer>>();
        	this.tracker = 0;
	}

	public void beginTransaction(){
		this.transactionTracker.add(this.operationTracker.size());
	}

	public void rollbackTransaction(){
        	if(this.transactionTracker.size() == 0){
            	    System.out.println("NO TRANSACTION");
        	}else{
        	    int last_transaction_start = this.transactionTracker.get(this.transactionTracker.size()-1);
        	    Variable variable = null;
        	    ArrayList<Integer> vIndexes = null;
        	    int counter = this.tracker;
        	    while(counter >= last_transaction_start){
        	        variable = this.operationTracker.get(this.tracker);
        	        vIndexes = this.variableTracker.get(variable.getName());
        	        vIndexes.remove(vIndexes.size()-1);
        	        this.variableTracker.put(variable.getName(), vIndexes);
        	        this.operationTracker.remove(counter);
        	        this.transactionTracker.remove(this.transactionTracker.size()-1);
        	        counter--;
        	    }
        	    this.tracker = this.operationTracker.size()-1;
        	}
	}

    
	public void set(String name, int value){
        	ArrayList<Integer> vIndexes = this.variableTracker.get(name);
        	if(vIndexes == null || (vIndexes.size() == 0)){
        	    //element is not present in the database
        	    Variable new_variable = new Variable(name, 0, value);
        	    this.operationTracker.add(new_variable);
        	    this.tracker=this.operationTracker.size()-1;
        	    vIndexes = new ArrayList<Integer>();
        	    vIndexes.add(this.tracker);
        	    this.variableTracker.put(name, vIndexes);
        	}else{
        	    //element is already present in the database
        	    int last_index = vIndexes.get(vIndexes.size()-1);
        	    Variable last_variable = this.operationTracker.get(last_index);
        	    Variable new_variable = new Variable(name, last_variable.getFinalValue(), value);
        	    this.operationTracker.add(new_variable);
        	    this.tracker = this.operationTracker.size()-1;
        	    vIndexes.add(this.tracker);
        	    this.variableTracker.put(name, vIndexes);
        	}
	}

	public Variable get(String name){
		ArrayList<Integer> vIndexes = this.variableTracker.get(name);
        	if(vIndexes == null || (vIndexes.size() == 0)){
        	    //element is not present in the database
        	    return null;
        	}else{
        	    Variable last_variable = this.operationTracker.get(vIndexes.get(vIndexes.size()-1));
        	    return last_variable;
        	}
	}
	
	public void commitTransaction(){
        	if(this.transactionTracker.size() == 0)
        	    System.out.println("NO TRANSACTION");
        	else{
		   this.transactionTracker = new ArrayList<Integer>();
        	}
    	}

	public int numEqual(int value){
		int numberOfVars = 0;
        	ArrayList<Integer> vIndexes = null;
        	for(Map.Entry<String, ArrayList<Integer>> variable : this.variableTracker.entrySet()){
        	    vIndexes = variable.getValue();
        	    int last_index = vIndexes.get(vIndexes.size()-1);
        	    int number = (this.operationTracker.get(last_index)).getFinalValue();
        	    if(number == value)
        	        numberOfVars++;
        	}
        	return numberOfVars;
	}

	public void unset(String name){
		ArrayList<Integer> vIndexes = this.variableTracker.get(name);
        	if(vIndexes == null || (vIndexes.size() == 0)){
        	    System.out.println("The variable is not present in the database");
        	}else{
            	     int last_index = vIndexes.get(vIndexes.size()-1);
            	     Variable last_variable = this.operationTracker.get(last_index);
                     Variable new_variable = new Variable(name, true);
                     this.operationTracker.add(new_variable);
                     this.tracker = this.operationTracker.size()-1;
                     vIndexes.add(this.tracker);
                     this.variableTracker.put(name, vIndexes);
        	}
	}
}
