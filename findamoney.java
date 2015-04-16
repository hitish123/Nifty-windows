
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
 import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;


 import opennlp.tools.namefind.NameFinderME;
 import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;
import opennlp.tools.util.featuregen.AdaptiveFeatureGenerator;
import opennlp.tools.util.featuregen.CachedFeatureGenerator;
import opennlp.tools.util.featuregen.OutcomePriorFeatureGenerator;
import opennlp.tools.util.featuregen.PreviousMapFeatureGenerator;
import opennlp.tools.util.featuregen.SentenceFeatureGenerator;
import opennlp.tools.util.featuregen.TokenClassFeatureGenerator;
import opennlp.tools.util.featuregen.TokenFeatureGenerator;
import opennlp.tools.util.featuregen.WindowFeatureGenerator;


  public class findamoney{
	 public findamoney() throws IOException 
	{
		FileInputStream is = new FileInputStream("C://modelFile.bin");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C://sree4.txt")));
		BufferedWriter br2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C://test1.txt")));
		TokenNameFinderModel model = new TokenNameFinderModel(is);
		ArrayList <String> wordList = new ArrayList <String>();
		String line = null;
		while( (line = br.readLine())!= null )
		{
	      String [] tokens = line.split("\\s+");
	        wordList.addAll( Arrays.asList(tokens)); 
	   }
		br.close();
		String []sentence=wordList.toArray(new String[wordList.size()]);
		 AdaptiveFeatureGenerator featureGenerator = new CachedFeatureGenerator(
    	         new AdaptiveFeatureGenerator[]
    	         {
    	           new WindowFeatureGenerator(new TokenFeatureGenerator(), 0, 0),
    	           new WindowFeatureGenerator(new TokenClassFeatureGenerator(true), 0, 0),
    	           new OutcomePriorFeatureGenerator(),
    	           new PreviousMapFeatureGenerator(),
    	           new SentenceFeatureGenerator(false, false)
    	           });
		     NameFinderME nameFinder = new NameFinderME(model,featureGenerator,NameFinderME.DEFAULT_BEAM_SIZE);
				Span nameSpans[] = nameFinder.find(sentence);
			for(Span s: nameSpans)
			{
			  int i=s.getStart();
			  while(i!=s.getEnd()&&s.getType().equalsIgnoreCase("item"))
			  {
				  br2.write(sentence[i]+" ");
				  i++;
			  }
			    br2.write("\n");
			}
			br2.close();
			is.close();
	}
}