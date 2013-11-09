
/**
* @File Name: Variable.java
* @Author: Ankur Upadhyay, Research Assistant, Computer Science Department, University at Buffalo.
* The instance of the class is used to represent the variable in databse or transaction
**/

public class Variable{

	protected String name; //name of the variable
	protected int initialValue; //initial value of the variable before the operation
    	protected int finalValue; //final value of the variable after the operation
	protected boolean unset; //the variable used to denote whether the variable has been unset by the operation.
	

	/**
	* Constructor Definitions
	**/
	public Variable(){}
	public Variable(String name, boolean unset){
		this.name = name;		
		this.unset = unset;
	}

	public Variable(String name, int initialValue, int finalValue){
		this.name = name;
		this.initialValue = initialValue;
        	this.finalValue = finalValue;
		this.unset=false;
	}

	/**
	* getter and setter methods for the protected member variables
	**/
	public void setInitialValue(int initialValue){this.initialValue=initialValue;}
	public void setName(String name){this.name=name;}
	public int getInitialValue(){return this.initialValue;}
	public String getName(){return this.name;}
    	public void setFinalValue(int finalValue){this.finalValue = finalValue;}
    	public int getFinalValue(){return this.finalValue;}
	public boolean isUnset(){return this.unset;}
}
