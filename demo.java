 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.io.OutputStreamWriter;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import java.util.ArrayList;
 import java.util.Scanner;
 import com.google.common.base.CharMatcher;
 public class demo
  {
    private static char ch;
	private static String s2;
	private Scanner x;
	private int cn=0;
	private BufferedWriter br1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C://sree.txt")));
    private BufferedWriter br2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C://latha.txt",true)));
	 public demo() throws IOException 
	{
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C://sree4.txt")));
    	BufferedReader br3=new BufferedReader(new InputStreamReader(new FileInputStream("C://test1.txt")));
		 x=new Scanner(System.in);
		 String [] str =new String [3];
	    	str[0] = "[$£€]\\s*[\\d,]*[\\.]?[pP]?\\d*\\d";
		    str[1] ="Rs[.]\\s*[\\d,]*[.]?\\d*\\d";
		    str[2]="\\d[\\d,]*[\\.]?[pP]?\\d*";
		    String p = String.format("(%s)|(%s)|(%s)",str[0],str[1],str[2]);
		    Pattern a = Pattern.compile(p);
			      String s1,s3,s4="";
				  StringBuilder s=new StringBuilder();
	              while((s1=br.readLine())!=null)
					  {
	            	     s1=CharMatcher.WHITESPACE.trimAndCollapseFrom(s1,' ');
						if(s1.length()!=0&&(Character.isDigit(s1.charAt(0))))//REMOVING THE INTIAL SIDE HEADINGS
						{
						   int k=0;
						     while(k<s1.length()&&s1.charAt(k)!=' ')
						    	 {
							    	 k++;
						    	 }
						     if(s1.substring(0,k).matches("\\d+"))
						     {
						    	 s4=validate(s1,k);
						    	 s.append(s4,0,s4.length());
						    	 s.append(" ");
						     }
						    if(k==s1.length())
								  s.append(s1);
							 else
								s.append(s1,k+1,s1.length());
						   s.append("\n");
							  continue;
						}
					  if(s1.length()!=0)
						{
							s.append(s1);
				            s.append("\n");
						}
				     }
					 s1=s.toString();//THE INPUT TEXT
					 br.close();
				     s=new StringBuilder();
                    while((s2=br3.readLine())!=null)
                    {
                	   s.append(s2);
                	   s.append(" ");
                    }
                  s2=s.toString();
				  br3.close();
				  Matcher b,b1;
				  int l,count,index=0,z=0,tmp=0,cnt=0,lb=0,it=0;
				 ArrayList <Integer> vec =new  ArrayList <Integer> ();
		          String [] arr1=s1.split("\n");
		          String [] arr2=new String [0];
				  for(int c=0;c<arr1.length||lb==1;c++)
				  {
   	                if(lb==1)
   	                  s4="str";
   	                else
   	                	s4=arr1[c];
			        b=a.matcher(s4);
			       count=0;
		            while (b.find()) 
                    {
			         count++;
                    }
		           b=a.matcher(s4); 
	             String [] arr=null;
	           if(b.find())
		         {
			          if(b.end()-b.start()==s4.length())
			            {
				             vec.add(b.start()+index);
				             cnt++;
				              if(c==arr1.length-1)
				               lb=1;
				              index+=s4.length()+1;
				              continue;	
			             }
		                  int j=b.start()+index;
                          int m;
                           do
                            {
                            	m=j;
                                   do{
            	                        j--;
            	                      }while(j>=0&&!(s1.charAt(j)=='\n'));
		                       s3=s1.substring(j+1,m);
                               l=0;
                               s=new StringBuilder();
                               for(int k=0;k<m-j-1;k++)
                                {
            	                 ch=s3.charAt(k);
            	                 it=0;
            	                  if(Character.isLetter(ch)||ch==' '||ch=='/'||ch==','||ch=='-'||ch=='_'||ch=='('||ch==')'||ch=='&')
                                     {
            	                      l=1;
            	                      if(s3.charAt(k)=='-')
           	                          {
           	                    	    tmp=it;
           	                    	    lb++;
           	                          }
            	                       s.append(ch);
          	                           it++; 
                                     }
                                  }
                                }while(count==1&&l!=1);
                           s3=s.toString().trim();
                           if(s3.length()==0)
                           {
                         	 index+=s4.length()+1;
            	             continue;
                           }
                           arr=s3.split("/");
                           if(lb==1&&arr.length==2)
                           {
                        	   s=new StringBuilder();
                        	   s.append(arr[0],0,tmp);
                        	   s.append(" ");
                        	   s.append(arr[1],0,arr[1].length());
                        	   arr[1]=s.toString();
                           }
                           lb=0;
                        z=0;
			  if(arr.length>count)
              {
				  ch=s4.charAt(b.start());
 		           write(s3,b.group());
				 /* if(arr[0].contains("(")&&arr[arr.length-1].contains(")"))
				  {
					   ch=s4.charAt(b.start());
      		           write(s3,b.group());
	              }
				  else
				  {
            	   for(int iter=0;iter<arr.length;iter++) 
            	    {
            		    ch=s4.charAt(b.start());
        		        write(arr[z],b.group());
	                     z++;
            	     }
				  }*/
            	index+=s4.length()+1;
            	 continue;
               }	
			 if(arr.length<count)
             {
			
				    Pattern a1 = Pattern.compile(String.format("(%s)|(%s)",str[0],str[1]));
				    b1=a1.matcher(s4);
				     it=0;
				   while (b1.find()) 
		            {
					  it++;
		            }
				   if(it<count&&it>=1)
				   {
					   it=b.end();
					   s=new StringBuilder();
					   s.append(s3,0,s3.length());
					   s.append(s4,b.start(),b.end());
					    if(b.find())
					    {
						  while(it!=b.start())
						  {
						      ch=s4.charAt(it);
			            	  if(Character.isLetter(ch)||ch==' '||ch=='&'||ch=='/'||ch==','||ch=='-'||ch=='_'||ch==')'||ch=='(')
			                  {
			            	    s.append(s4.charAt(it));
			                  }
			            	  it++;
			              }
						    s3=s.toString();
						    ch=s4.charAt(b.start());
						   write(s3,b.group());
					       index+=s4.length()+1;
		            	  continue;   
				      }
				   }
                   int hit=b.end();
            		 while(hit<s4.length()&&!Character.isLetter(s4.charAt(hit))&&s4.charAt(hit)!=')')
            		 {
            			 hit++;
            		 }
            		 if(hit==s4.length())
            		  {
            		   ch=s4.charAt(b.start());
        		       if(!Contains(arr[0],s2)&&(ch=='$'||ch=='£'||ch=='€'||ch=='R'))
        		        {
        		    	  for(it=0;it<5;it++)
        		    	  {
 	                       br2.write(" <START:item> "+arr[0]+" <END> \n");
        		    	  }
        		        }
                  	  if(it!=5&&!(Contains(arr[0],s2)))
	                   {
                  		  int hi=0;
                  		 if(arr[0].trim().length()>=4)
                  		 {
            		        System.out.println("DO YOU WANT TO ADD THE ITEM "+arr[0]);
	                        hi=x.nextInt();
                  		 }
	                      if(hi!=0)
	                      {
	                	  for(int itr=0;itr<5;itr++)
	                        br2.write(" <START:item> "+arr[0]+" <END> \n");
	                     }
	                    }
	                    else
	                    {
	                    	int t=1;
	                    	if(arr2.length<count)
	                    	{
	                    	  do
	                    	  {
	                    	    arr2=arr1[c-t].split("\\s+");
	                    	    t++;
	                    	   }while(!(arr2.length>=count&&arr2.length%count==0));
	                    	 String [] arr3=new String[count];
	                    	 for(int ls=0;ls!=count;ls++)
	                    	 {
	                    		 s=new StringBuilder();
	                    	   for(int sl=0;sl!=arr2.length/count;sl++)
	                    	   {
	                             s.append(arr2[ls*(arr2.length/count)+sl]+" ");   
	                    	   }
	                    	    arr3[ls]=s.toString();
	                    	 }
	                    	   arr2=arr3;
	                    	 }
	                    	for(int il=1;il<=count;il++)
	                    	{
	                    	  br1.write("item= "+arr[0]+" "+arr2[il-1]+" "+" value=  "+b.group()+"\n");
	                         if(b.find())
	                        	 ;
	                    	}
	                   }
            		 }
            	  else 
                     {
            		  if(!s.append(s4,0,b.start()).toString().contains("("))
            		  {
            		     ch=s4.charAt(b.start());
					     write(s3,b.group());
						 s=new StringBuilder();
            		  } 
            		  else
            		  {
            			   s=new StringBuilder();
            			   s.append(s3,0,s3.length());
            			   s.append(b.group());
            		  }
            		  hit=b.end();
                	    while(b.find())
                	     {
                		   while(hit<s4.length()&&!Character.isDigit(s4.charAt(hit)))
                    	    {
                            	 ch=s4.charAt(hit);
   			            	  if(Character.isLetter(ch)||ch==' '||ch=='&'||ch=='/'||ch==','||ch=='-'||ch=='_'||ch==')'||ch=='(')
   			                  {
   			            	    s.append(s4.charAt(hit));
   			                  }
                    		  hit++;
                    	    }
                    	    ch=s4.charAt(b.start());
            		         write(s.toString(),b.group());
            			     s=new StringBuilder();
            			     hit=b.end();
                    	 }
                     }
			      index+=s4.length()+1;
                   continue;
		     }
		       do
			     { 
		    	   ch=s4.charAt(b.start());
    		       write(arr[z],b.group());
                   z++;
			     }while(b.find());
		       arr2=new String [0];
	         }
	           else if(cnt>0)
			   {
			      int temp1=vec.get(0)-1;
				    while(s1.charAt(temp1)==' '||s1.charAt(temp1)=='\n')
					   temp1--;
				    for(int v=cnt-1;v>-1;v--)
				     {
					   s=new StringBuilder();
					   while(temp1>-1&&s1.charAt(temp1)!='\n')
					   {
					     s.append(s1.charAt(temp1));
					     temp1--;
					    }
					     String st=s.reverse().toString();
					     tmp=vec.get(v);
					     s=new StringBuilder();
					     while(s1.charAt(tmp)!='\n')
					      tmp++;
				     	 s.append(s1,vec.get(v),tmp);
					     ch=s1.charAt(vec.get(v));
					    write(st,s.toString());
	    		        temp1--;
				    }
				 cnt=0;
				 vec =new  ArrayList <Integer> ();
			 } 
	    index+=s4.length()+1;
	    lb=0;	
	    }
		         br1.close();   
		         br2.close();
		         System.out.println(cn);
}
	 public  boolean Contains (String s,String s1)
		{
		  int max=0;
		  s=s.trim();
		  if(s.length()<=3)
			  return false;
		  else if(s.length()<5)
			return containsString(s,s1,false);
		  for(int i=0;i<s.length();i++)
		  {
			for(int j=i+3;j<=s.length();j++)
			{
			  StringBuilder strb= new StringBuilder();
			   strb.append(s,i,j);
			   String s2=strb.toString();
			   if(containsString(s2,s1,false))
			   {
				   if(max<s2.length())
				   {
					   max=s2.length();
				   }
			   }
			  }
		    }
		  if(s.length()>=5&&max>0.60*s.length())
			  return true;
		 String [] arr=s.split("\\s+");
		  max=arr.length-1;
		  for(String st:arr)
		  {
			  if(st.length()>=3&&containsString(s2,st,false))
			   {
				  max+=st.length();  
			   }
		  }
		  if(s.length()>=5&&max>0.55*s.length())
			  return true;
		  return false;
	}
   public static boolean containsString(String original, String tobeChecked, boolean caseSensitive)
   {
      if (caseSensitive)
      {
          return original.contains(tobeChecked)||tobeChecked.contains(original);

      }
      else
      {
          return original.toLowerCase().contains(tobeChecked.toLowerCase())||tobeChecked.toLowerCase().contains(original.toLowerCase());
      }

   }
 public void write(String a,String b) throws IOException
 {
   int it=0;
              if(!Contains(a,s2)&&(ch=='$'||ch=='£'||ch=='€'||ch=='R')&&a.trim().length()>=4)
    		     {
    		    	for(it=0;it<5;it++)
    		    	{
	                    br2.write(" <START:item> "+a+" <END> \n");
    		    	}
    		     }
				 if(it!=5&&!(Contains(a,s2)))
                  {
					 cn++;
					/*int hi=0;
              		if(a.trim().length()>=4)
              		 {
        		        System.out.println("DO YOU WANT TO ADD THE ITEM "+a.trim());
                        hi=x.nextInt();
              		  }
                    if(hi!=0)
                    {
                	 for(int k=0;k<5;k++)
                       br2.write(" <START:item> "+a+" <END> \n");
                    }*/
                  }
                 else 
                 {
                    br1.write(a.trim()+" "+b+"\n");
                 }
   }
 String validate(String s1,int k)
 {
		 if((k+2<s1.length()&&s1.substring(k+1,k+2).toLowerCase().contains("l"))||(k+3<=s1.length()&&s1.substring(k+1,k+3).toLowerCase().contains("ml")))
		       return NERDemo.convert(Integer.parseInt(s1.substring(0,k)));
		 if((k+4<s1.length()&&s1.substring(k+1,k+4).toLowerCase().contains("pcs"))||(k+6<=s1.length()&&s1.substring(k+1,k+6).toLowerCase().contains("piece")))
		       return NERDemo.convert(Integer.parseInt(s1.substring(0,k)));
	  return "";
 }
}