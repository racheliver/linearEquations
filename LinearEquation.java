package com.example.racheli.equationsolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
/*
The class called LinearEquation that  represent a linear equation.
The class  have two properties of the double type - the coefficient of x and the free organ
(can be zero).

 */
public class LinearEquation {
	private static double a;
	private static double b;
	/*
	The constructor  receive as parameters the value of the coefficient (a) and the free number (b)
	and set them to the attributes. If a is zero,
 	the constructor will throw out an InvalidException exception.
 	*/
	public LinearEquation(double a, double b) throws InvalidCoefficientException
	{
		if (a==0)
			throw new InvalidCoefficientException("The coefficient can not be zero");
		this.a=a;
		this.b=b;
	}


	public double getCoefficient()
	{
		return this.a;
	}
	public double getConstant()
	{
		return this.b;
	}

		/*
		The method will get the coefficient value and place it in the attribute.
	 	The method will throw an exception of InvalidCoefficientException if a is 0.
		 */
	public void setCoefficient(double a) throws InvalidCoefficientException
	{
		LinearFragment linearFragment = new LinearFragment(this.a);
		linearFragment.setCoefficient(a);
	}
	public void setConstant(double b)
	{
		this.b=b;
	}

	public String toString()
	{
		try {
			LinearFragment linearFragment = new LinearFragment(this.a);
			return linearFragment.toString() + " =  "+b; 
		} catch (InvalidCoefficientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	/*
	The method  be given as a string parameter that represents a linear equation
	 and returns a LinearEquation object. The method will throw an IllegalFormatException exception if the string does not represent a valid equation.
	A valid equation can consist of a number of fragments for example
	 */
	public static LinearEquation parseEquation(String s) throws IllegalFormatException
	{
		String arrSplitEqual[]	=s.split("=",s.length());
		String []	arrSplitx2=null;
		String leftEquation=arrSplitEqual[0];
		String arrSplitx[]	=leftEquation.split("x",leftEquation.length());
		double doublePrase=0;
		int countEqual=0;
		for (int i = 0; i < s.length(); i++)
		{
			if (s.contains(" "))
				throw new IllegalFormatException("you cant use space in the Equation");
			if ((!Character.isDigit(s.charAt(0)) && s.charAt(0)!='x' && s.charAt(0) !=45) || !Character.isDigit(s.charAt(s.length()-1))|| (s.charAt(i)=='x' && Character.isDigit(s.charAt(i+1))||(s.charAt(i)=='x' && (s.charAt(i+1)=='x'))))
				throw new IllegalFormatException("check if x apper more then one time \n x cant be befor a number or in the right side of the Equation \n (should start with a number or x or minus only) ");
			if(s.charAt(i)=='=')
				countEqual++;
		}
		if(s.equals(null)|| s.equals("") ||countEqual!=1 || arrSplitEqual.length!=2)
		throw new IllegalFormatException("The Equation cant be empty and you should use = only one time");
		for (int i = 0; i < arrSplitx.length-1; i++) 
		{
			switch (arrSplitx[i])
			{
			case "":
				arrSplitx[i]="1";
				break;
			case "-":
				arrSplitx[i]="-1";
				break;
			case "+":
				arrSplitx[i]="1";
				break;
			}
			if(arrSplitx[i].contains("*"))
				arrSplitx[i]=arrSplitx[i].substring(0,arrSplitx[i].length()-1);
			if(arrSplitx[i].contains("+"))
				arrSplitx[i]=arrSplitx[i].substring(1);
			arrSplitx2=arrSplitx;
		}
			for (int i = 0; i < arrSplitx.length-1; i++) 
			{
			if (!isNumeric(arrSplitx[i]))
				throw new IllegalFormatException("");
			else
			{
				doublePrase+= Double.parseDouble(arrSplitx[i]);
				arrSplitx2[i]+="x";
				
			}
		}
		LinearFragment linearFragment = null;
		try {
			linearFragment = new LinearFragment(a);
		} catch (InvalidCoefficientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Vector<LinearFragment> vector= new Vector<LinearFragment>();
		for (int j= 0; j < arrSplitx2.length-1; j++) 
		{
			vector.add(linearFragment.parseFragment(arrSplitx2[j]));
		}
		//System.out.println(doublePrase);
		try {
			return new LinearEquation(doublePrase,Double.parseDouble(arrSplitEqual[1]));
		} catch (NumberFormatException | InvalidCoefficientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	


//A private method that checks if STRING is a number
	private static  boolean isNumeric(String str)  
	{  
		try  
		{  
			double d = Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}
	//The method will return the solution of the equation.
		public double solve()
		{
			return this.b/this.a;
		}
}

