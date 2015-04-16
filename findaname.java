 import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

 import opennlp.tools.namefind.NameFinderME;
 import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;


  public class findaname{
	 public findaname() throws IOException 
	{
		FileInputStream is = new FileInputStream("C://en-ner-person.train");
        TokenNameFinderModel model = new TokenNameFinderModel(is);
		is.close();
        NameFinderME nameFinder = new NameFinderME(model);
	    FileInputStream modelIn = new FileInputStream("C://en-token.bin");
        TokenizerModel model1 = new TokenizerModel(modelIn);
	    Tokenizer tokenizer = new TokenizerME(model1);//USED TO CREATE TOKENS
	    ArrayList<String> token=new ArrayList<String>();//ARRAY OF TOKENS	
	    String sCurrentLine;
	    BufferedReader br = new BufferedReader(new FileReader("C://lath.txt"));
		while ((sCurrentLine = br.readLine()) != null) 
		{
	      token.addAll(Arrays.asList(tokenizer.tokenize(sCurrentLine)));  
		}
		br.close();  
	    String [] strArray = new String[token.size()];
	    Span nameSpans[] = nameFinder.find(token.toArray(strArray));
	    for(Span s: nameSpans)
		System.out.println(token.get(s.getStart()));
   }
}