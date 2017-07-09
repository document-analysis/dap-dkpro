package org.dap.dap_dkpro;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.dap.dap_uimafit.ConverterAnnotator;
import org.dap.data_structures.Annotation;
import org.dap.data_structures.Document;
import org.dap.data_structures.LanguageFeature;

import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter;

/**
 * 
 *
 * <p>
 * Date: 9 Jul 2017
 * @author Asher Stern
 *
 */
public class Demo1
{
	public static void main(String[] args)
	{
		try
		{
			AnalysisEngineDescription segmenterDesc = AnalysisEngineFactory.createEngineDescription(OpenNlpSegmenter.class);
			AnalysisEngineDescription posDesc = AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class);
			AnalysisEngineDescription aggDesc = AnalysisEngineFactory.createEngineDescription(segmenterDesc, posDesc);
			AnalysisEngine uimaAnnotator = AnalysisEngineFactory.createEngine(aggDesc);
			
			Document document = new Document("doc1", "Hello world. This is my first document. I hope you enjoyed.");
			LanguageFeature.setDocumentLanguage(document, "en");
			try (ConverterAnnotator dapAnnotator = new ConverterAnnotator(uimaAnnotator))
			{
				dapAnnotator.annotate(document);
			}

			for (Annotation<?> annotation : document)
			{
				System.out.println(annotation.getCoveredText());
			}
			
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

}
