/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginJSF;

/**
 *
 * @author benny
 */
public class StringBean {
    public static void main(String[] args)
{
    char [] charArray = {'i', 'o'}; 
    String mySentence = new String ("The Qu1ck Br0wn Fox jumps\n" +
"over,the,Lazy,D*g");
    
   mySentence = mySentence.replace("1", "i"); 
   System.out.println(mySentence); 
    

} 
}
