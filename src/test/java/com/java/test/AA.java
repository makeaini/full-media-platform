package com.java.test;

import java.util.ArrayList;
import java.util.List;

public class AA {
	public static void main(String[] args) {
	   List<String> la=new ArrayList<String>();
	   la.add("1");
	   la.add("2");
	   la.add("3");
	   la.add("4");
	   la.add("5");
	   for (String string : la) {
		if(string.equals("1")){
			string="dfkfkfk";
			la.set(0, string);
			break;
		}
	}
	   
	   System.out.println(la);
	   
	}

}
