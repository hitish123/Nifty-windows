 import java.io.BufferedOutputStream;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.nio.charset.Charset;
 import java.util.Collections;
import opennlp.tools.namefind.NameFinderME;
 import opennlp.tools.namefind.NameSample;
 import opennlp.tools.namefind.NameSampleDataStream;
 import opennlp.tools.namefind.TokenNameFinderModel;
 import opennlp.tools.util.ObjectStream;
 import opennlp.tools.util.PlainTextByLineStream;
 import opennlp.tools.util.featuregen.AdaptiveFeatureGenerator; 
 import opennlp.tools.util.featuregen.CachedFeatureGenerator;
 import opennlp.tools.util.featuregen.OutcomePriorFeatureGenerator;
 import opennlp.tools.util.featuregen.PreviousMapFeatureGenerator;
 import opennlp.tools.util.featuregen.SentenceFeatureGenerator;
 import opennlp.tools.util.featuregen.TokenClassFeatureGenerator;
 import opennlp.tools.util.featuregen.TokenFeatureGenerator;
 import opennlp.tools.util.featuregen.WindowFeatureGenerator;

  public class SentenceDetectTrain
 {
	
	public SentenceDetectTrain () throws IOException
	 {
      Charset charset = Charset.forName("UTF-8");
      ObjectStream<String> lineStream =
      new PlainTextByLineStream(new FileInputStream("C://latha.txt"), charset);
      ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);
       TokenNameFinderModel model;
       AdaptiveFeatureGenerator featureGenerator = new CachedFeatureGenerator(
    	         new AdaptiveFeatureGenerator[]{
    	           new WindowFeatureGenerator(new TokenFeatureGenerator(), 0, 0),
    	           new WindowFeatureGenerator(new TokenClassFeatureGenerator(true), 0, 0),
    	           new OutcomePriorFeatureGenerator(),
    	           new PreviousMapFeatureGenerator(),
    	           new SentenceFeatureGenerator(false, false)
    	           });
   try {
         model = NameFinderME.train("en", "person", sampleStream,
        		 featureGenerator,Collections.<String, Object>emptyMap(), 1000, 1);
       }
   finally
    {
       sampleStream.close();
    }
    OutputStream modelOut = null;
  try {
       modelOut = new BufferedOutputStream(new FileOutputStream("C://modelFile.bin"));
       model.serialize(modelOut);
      } 
  finally 
    {
     if (modelOut!= null) 
       modelOut.close();
    }
  }
}
 