import java.io.File;
import java.io.IOException;

//import opennlp.maxent.GISModel;
import opennlp.maxent.io.PooledGISModelReader;
import opennlp.model.AbstractModel;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.util.Span;


public class TrainedName {

  public TrainedName() throws IOException
  {
	  AbstractModel model =  new PooledGISModelReader(new File("C:\\modelFile.bin")).getModel();
	  @SuppressWarnings("deprecation")
	  NameFinderME nameFinder= new NameFinderME(model);
	  String []sentence = new String[]{
			    "John",
			    "has",
			    "an",
			    "amount",
			    "of",
			    "30",
			    "%",
			    };

			Span nameSpans[] = nameFinder.find(sentence);

		for(Span s: nameSpans)
		  System.out.println(s.getStart()+""+s.getEnd()+s.getType());
  }

}
