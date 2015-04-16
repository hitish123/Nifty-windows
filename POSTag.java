 import java.io.File;
 import java.io.IOException;
 import java.io.StringReader;


 import opennlp.tools.cmdline.postag.POSModelLoader;
 import opennlp.tools.postag.POSModel;
 import opennlp.tools.postag.POSSample;
 import opennlp.tools.postag.POSTaggerME;
 import opennlp.tools.tokenize.WhitespaceTokenizer;
 import opennlp.tools.util.ObjectStream;
 import opennlp.tools.util.PlainTextByLineStream;

 
    public class  POSTag
    {
      POSTag() throws IOException
      {
		POSModel model = new POSModelLoader()
				.load(new File("C://en-pos-maxent.bin"));
		//PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
		POSTaggerME tagger = new POSTaggerME(model);
        String input = "They refuse to permit us to obtain the refuse permit. They refuse to permit us to obtain the refuse permit.";
		ObjectStream<String> lineStream = new PlainTextByLineStream(
				new StringReader(input));
 
		//perfMon.start();
		String line;
		while ((line = lineStream.read()) != null) {
 
			String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
					.tokenize(line);
			String[] tags = tagger.tag(whitespaceTokenizerLine);
 
			POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
			System.out.println(sample.toString());
            // perfMon.incrementCounter();
		}
	//	perfMon.stopAndPrintFinalResult();
	}
 }