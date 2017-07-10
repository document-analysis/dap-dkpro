package org.dap.dap_dkpro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.dap.dap_dkpro.converters.LemmaConverter;
import org.dap.dap_dkpro.converters.PosConverter;
import org.dap.dap_dkpro.converters.SentenceConverter;
import org.dap.dap_dkpro.converters.StemConverter;
import org.dap.dap_dkpro.converters.TokenConverter;
import org.dap.dap_dkpro.converters.TokenReferenceAdapter;
import org.dap.dap_uimafit.AnnotationConverter;
import org.dap.dap_uimafit.ConverterAnnotator;
import org.dap.data_structures.Annotation;
import org.dap.data_structures.AnnotationReference;
import org.dap.data_structures.Document;
import org.dap.data_structures.LanguageFeature;

import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Stem;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
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
			
			Map<Class<?>, AnnotationConverter<?>> converters = new LinkedHashMap<>();
			converters.put(Sentence.class, SentenceConverter.INSTANCE);
			converters.put(Token.class, TokenConverter.INSTANCE);
			converters.put(Lemma.class, LemmaConverter.INSTANCE);
			converters.put(Stem.class, StemConverter.INSTANCE);
			converters.put(POS.class, PosConverter.INSTANCE);
			
			Document document = new Document("doc1", "Hello world. This is my first document. I hope you enjoyed.");
			LanguageFeature.setDocumentLanguage(document, "en");
			try (ConverterAnnotator dapAnnotator = new ConverterAnnotator(converters, uimaAnnotator))
			{
				dapAnnotator.setReferencesAdapter(TokenReferenceAdapter.INSTANCE);
				dapAnnotator.annotate(document);
			}

			for (Annotation<?> annotation : document)
			{
				System.out.println(annotation.getAnnotationContents().getClass().getSimpleName() + ": " + annotation.getCoveredText());
				if (annotation.getAnnotationContents() instanceof org.dap.dap_dkpro.annotations.Token)
				{
					AnnotationReference posReference = ((org.dap.dap_dkpro.annotations.Token)annotation.getAnnotationContents()).getPosReference();
					String pos = document.findAnnotation(posReference).getAnnotationContents().getClass().getSimpleName();
					System.out.println("\t"+pos);
				}
			}
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

}
