package org.mogong.wengcheng;

import java.io.IOException;

public class TaintRuntime {
	
	  public Process exec(String paramString)throws IOException
      {
		  if(paramString.getTaint()){
			  System.out.println("find command execution");
		  }
		  return Runtime.getRuntime().exec(paramString, null, null);
      }
	  
	  public Process exec1(String paramString)throws IOException
      {

		  return Runtime.getRuntime().exec(paramString, null, null);
      }
	  
	  public void out0(){
		  System.out.println("hello");
	  }
	  
	  public void out1(){
		  System.out.println("world");
		  System.out.println("hello");
	  }
}
