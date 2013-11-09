/**
* @File Name: AppVar.java
* @Author: Ankur Upadhyay, Research Assistant, Computer Science Department, University at Buffalo.
* The instance of the class is used to represent the variable in databse or transaction
**/

public class Variable{

	protected String name;
	protected int initialValue;
    protected int finalValue;

	public Variable(String name, int initialValue, int finalValue){
		this.name = name;
		this.initialValue = initialValue;
        this.finalValue = finalValue;
	}

	public void setInitialValue(int initialValue){this.initialValue=initialValue;}
	public void setName(String name){this.name=name;}
	public int getInitialValue(){return this.initialValue;}
	public String getName(){return this.name;}
    public void setFinalValue(int finalValue){this.finalValue = finalValue;}
    public int getFinalValue(){return this.finalValue;}
}