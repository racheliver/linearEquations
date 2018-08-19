package com.example.racheli.equationsolver;

/*
  The class called LinearFragment that represent an element in a linear equation.
  An element in the equation is an expression of type a * x where a is any actual number
 (different from zero) called the coefficient.
 */

public class LinearFragment {
	private double a; //contain one double attribute that represents the promoter.


	/*
	The constructor  receive a variable to represent the coefficient and place it in the attribute. If a is zero,
	the constructor will throw an InvalidCoefficientException exception (write the exception class).
    Add the getCoefficient method to the class. The method returns the value of the coefficient.
    Use the following methods in the department:
	 */
	public LinearFragment(double a)throws InvalidCoefficientException
	{
		if (a==0.0)
			throw new InvalidCoefficientException("The coefficient can not be zero");
		setCoefficient(a);
	}


	public double getCoefficient()
	{
		return this.a;
	}


	/*
	The method  receive the coefficient value as a parameter and place it in the attribute.
	The method will throw an exception in the InvalidCoefficientException type if a is zero.
 	*/

	public void setCoefficient(double a) throws InvalidCoefficientException
	{
		if (a==0.0)
			throw new InvalidCoefficientException("The coefficient can not be zero");
		this.a = a;
	}


	/*The method returns the object in the form of a string*/
	@Override
	public String toString()
	{
		if	(getCoefficient()==-1)
			return "-"+"x";
		else if	(getCoefficient()==1)
			return "x";
		else
			return a+"x";
	}


	/*
	  The method will be given as a string parameter that represents an element in a linear equation,
	  and returns a LinearFragment object that represents this element.
	  If the string does not represent an element in the linear equation,
	  the method will throw an IllegalFormatException exception .
	 */

	/*
	Line 67-77 First, the method checks whether the equation format entered is correct.
    After that the values are correct I used a switch case to add numbers if it was only sign.
    Line 94 - Additional Format Check.
    96-108 Distribution to private cases where there is no coefficient.
    Then I pulled the promoter value out of the string until LinearFragment was returned
	 */
	public static LinearFragment parseFragment(String s) throws IllegalFormatException
	{
		if (s.isEmpty() || s.equals(null))
			throw new IllegalFormatException("the string is empty");
		if ((!Character.isDigit(s.charAt(0)) && s.charAt(0)!='x' && s.charAt(0) !=45) || !s.endsWith("x") || s.contains(" "))
			throw new IllegalFormatException("IllegalFormat  nessery x, - , or a number (only lower case of x without space )");
		int countX=0,countMinus=0,countMult=0,countDout=0;
		for (int i = 0; i < s.length(); i++)
		{
			if ((!Character.isDigit(s.charAt(i))) && s.charAt(i)!='x' && s.charAt(i)!='*'  &&s.charAt(i)!='.'&&s.charAt(i)!='-')
			{
				throw new IllegalFormatException("you can use only numbers and  * ,  x  , -  (Without profits)" );
			}
			switch (s.charAt(i))
			{
			case 'x':
				countX++;
				break;
			case '-':
				countMinus++;
				break;
			case '*':
				countMult++;
				break;
			case '.':
				countDout++;
				break;
			}
		}
		if (countX>1 || countMinus>1|| countMult>1 || countDout>1)
			throw new IllegalFormatException("you should use one opreator or sign in linearFragment");
		if (s.equals("-x"))
			try {
				return new LinearFragment(-1);
			} catch (InvalidCoefficientException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if (s.equals("x"))
			try {
				return new LinearFragment(1);
			} catch (InvalidCoefficientException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		int i=0;
		String prase="";
		double doublePrase=1, minus=1;
		while (s.charAt(i)!='x')
		{
			if (Character.isDigit(s.charAt(i)))
				prase+=s.charAt(i);
			if (s.charAt(i)=='-')
				minus=-1;
			if (s.charAt(i)=='*' &&i+1<s.length() && s.charAt(i+1)=='x')
			{
				doublePrase=Double.parseDouble(prase);
				try {
					if (minus==-1)
					return new LinearFragment(doublePrase*-1);
					else
						return new LinearFragment(doublePrase);
				} catch (InvalidCoefficientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (s.charAt(i+1)=='x')
			{
				doublePrase=Double.parseDouble(prase);
				try {
					if (minus==-1)
						return new LinearFragment(doublePrase*-1);
						else
							return new LinearFragment(doublePrase);
				} catch (InvalidCoefficientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			i++;
		}
		try {
			return new LinearFragment(doublePrase);
		} catch (InvalidCoefficientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

}
