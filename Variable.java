/**
* @File Name: AppVar.java
* @Author: Ankur Upadhyay, Research Assistant, Computer Science Department, University at Buffalo.
* The instance of the class is used to represent the variable in databse or transaction
**/

public class Variable{

	protected String name;
	protected int value;

	public Variable(String name, int value){
		this.name = name;
		this.value = value;
	}

	public void setValue(int value){this.value=value;}
	public void setName(String name){this.name=name;}
	public int getValue(){return this.value;}
	public String getName(){return this.name;}
}
