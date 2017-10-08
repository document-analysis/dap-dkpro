package org.dap.dap_dkpro;

import java.util.Arrays;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.dap.annotators.AggregateAnnotator;
import org.dap.dap_dkpro.converters.ChunkConverter;
import org.dap.dap_dkpro.converters.ConstituentConverter;
import org.dap.dap_dkpro.converters.ConstituentReferenceAdapter;
import org.dap.dap_dkpro.converters.CoreferenceLinkConverter;
import org.dap.dap_dkpro.converters.CoreferenceReferenceAdapter;
import org.dap.dap_dkpro.converters.DependencyConverter;
import org.dap.dap_dkpro.converters.DependencyReferenceAdapter;
import org.dap.dap_dkpro.converters.LemmaConverter;
import org.dap.dap_dkpro.converters.NamedEntityConverter;
import org.dap.dap_dkpro.converters.PosConverter;
import org.dap.dap_dkpro.converters.SentenceConverter;
import org.dap.dap_dkpro.converters.StemConverter;
import org.dap.dap_dkpro.converters.TokenConverter;
import org.dap.dap_dkpro.converters.TokenReferenceAdapter;
import org.dap.dap_uimafit.AggregateReferencesAdapter;
import org.dap.dap_uimafit.AnnotationConverter;
import org.dap.dap_uimafit.ConverterAnnotator;
import org.dap.data_structures.Annotation;
import org.dap.data_structures.AnnotationReference;
import org.dap.data_structures.Document;
import org.dap.data_structures.LanguageFeature;

import de.tudarmstadt.ukp.dkpro.core.api.coref.type.CoreferenceLink;
import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Stem;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.constituent.Constituent;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency;
import de.tudarmstadt.ukp.dkpro.core.maltparser.MaltParser;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpChunker;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpNamedEntityRecognizer;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpParser;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpPosTagger;
import de.tudarmstadt.ukp.dkpro.core.opennlp.OpenNlpSegmenter;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordCoreferenceResolver;
import de.tudarmstadt.ukp.dkpro.core.textcat.LanguageIdentifier;

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
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		try
		{
			AnalysisEngineDescription segmenterDesc = AnalysisEngineFactory.createEngineDescription(OpenNlpSegmenter.class);
			AnalysisEngineDescription posDesc = AnalysisEngineFactory.createEngineDescription(OpenNlpPosTagger.class);
			AnalysisEngineDescription chunkDesc = AnalysisEngineFactory.createEngineDescription(OpenNlpChunker.class);
			AnalysisEngineDescription nerDesc = AnalysisEngineFactory.createEngineDescription(OpenNlpNamedEntityRecognizer.class);
//			AnalysisEngineDescription nerDesc = AnalysisEngineFactory.createEngineDescription(StanfordNamedEntityRecognizer.class);
			
			AnalysisEngineDescription corefDesc = AnalysisEngineFactory.createEngineDescription(StanfordCoreferenceResolver.class);
			
			AnalysisEngineDescription dependencyParserDesc = AnalysisEngineFactory.createEngineDescription(MaltParser.class);
			AnalysisEngineDescription constituencyParserDesc = AnalysisEngineFactory.createEngineDescription(OpenNlpParser.class);
			
			AnalysisEngineDescription aggDesc = AnalysisEngineFactory.createEngineDescription(segmenterDesc, posDesc, chunkDesc, nerDesc, dependencyParserDesc, constituencyParserDesc
					, corefDesc
					);
			AnalysisEngine uimaAnnotator = AnalysisEngineFactory.createEngine(aggDesc);
			
			AnalysisEngine langUimaAnnotator = AnalysisEngineFactory.createEngine(LanguageIdentifier.class);
			
			Map<Class<?>, AnnotationConverter<?>> converters = new LinkedHashMap<>();
			converters.put(Sentence.class, SentenceConverter.INSTANCE);
			converters.put(Token.class, TokenConverter.INSTANCE);
			converters.put(Lemma.class, LemmaConverter.INSTANCE);
			converters.put(Stem.class, StemConverter.INSTANCE);
			converters.put(POS.class, PosConverter.INSTANCE);
			converters.put(Chunk.class, ChunkConverter.INSTANCE);
			converters.put(NamedEntity.class, NamedEntityConverter.INSTANCE);
			converters.put(Dependency.class, DependencyConverter.INSTANCE);
			converters.put(Constituent.class, ConstituentConverter.INSTANCE);
			converters.put(CoreferenceLink.class, CoreferenceLinkConverter.INSTANCE);
			
			AggregateReferencesAdapter aggregateReferencesAdapter = new AggregateReferencesAdapter(TokenReferenceAdapter.INSTANCE, CoreferenceReferenceAdapter.INSTANCE, DependencyReferenceAdapter.INSTANCE, ConstituentReferenceAdapter.INSTANCE);
			
			Document document = new Document("doc1", "Hello world.\n"
					+ "This is my first document. John loves Marry. John is happy. She loves him too. This is an apple. It is nice. It is tasty. I hope you enjoyed. Microsoft is a large company, founded Bill Gates."
					);
			
			LanguageFeature.setDocumentLanguage(document, "en");
			try (ConverterAnnotator dapAnnotator = new ConverterAnnotator(converters, uimaAnnotator))
			{
				dapAnnotator.setReferencesAdapter(aggregateReferencesAdapter);
				
				try (LanguageIdentifierAnnotator langAnnotator = new LanguageIdentifierAnnotator(langUimaAnnotator))
				{
					try (AggregateAnnotator aggregateAnnotator = new AggregateAnnotator(Arrays.asList(
							//langAnnotator, // A problem with DKPro... Always detects Hungarian...
							dapAnnotator)))
					{
						aggregateAnnotator.annotate(document);
					}
				}
			}
			
			System.out.println("document.getName() = "+document.getName());

			for (Annotation<?> annotation : document)
			{
				System.out.println(annotation.getAnnotationContents().getClass().getName() + ": " + annotation.getCoveredText());
				if (annotation.getAnnotationContents() instanceof org.dap.dap_dkpro.annotations.Token)
				{
					AnnotationReference posReference = ((org.dap.dap_dkpro.annotations.Token)annotation.getAnnotationContents()).getPosReference();
					String pos = document.findAnnotation(posReference).getAnnotationContents().getClass().getSimpleName();
					System.out.println("\t"+pos);
				}
				if (annotation.getAnnotationContents() instanceof org.dap.dap_dkpro.annotations.ne.NamedEntity)
				{
					org.dap.dap_dkpro.annotations.ne.NamedEntity ne = (org.dap.dap_dkpro.annotations.ne.NamedEntity)annotation.getAnnotationContents();
					System.out.println("\t"+ne.getType()+"\t"+ne.getValue());
				}
				if (annotation.getAnnotationContents() instanceof org.dap.dap_dkpro.annotations.syntax.depencency.Dependency)
				{
					org.dap.dap_dkpro.annotations.syntax.depencency.Dependency dependency = (org.dap.dap_dkpro.annotations.syntax.depencency.Dependency) annotation.getAnnotationContents();
					System.out.println("\tDependency type = " + dependency.getDependencyType()); 
					System.out.println("\tGovernor: " + document.findAnnotation(dependency.getGovernor()).getCoveredText());
					System.out.println("\tDependent: " + document.findAnnotation(dependency.getDependent()).getCoveredText());
				}
				if (annotation.getAnnotationContents() instanceof org.dap.dap_dkpro.annotations.syntax.constituency.Constituent)
				{
					org.dap.dap_dkpro.annotations.syntax.constituency.Constituent constituent = (org.dap.dap_dkpro.annotations.syntax.constituency.Constituent) annotation.getAnnotationContents();
					if (constituent.getParent()!=null)
					{
						Annotation<org.dap.dap_dkpro.annotations.syntax.constituency.Constituent> parentAnnotation = (Annotation<org.dap.dap_dkpro.annotations.syntax.constituency.Constituent>) document.findAnnotation(constituent.getParent(), true);
						
						System.out.println("\t^"+parentAnnotation.getAnnotationContents().getClass().getSimpleName()+": "+parentAnnotation.getCoveredText());
					}
					for (AnnotationReference child : constituent.getChildren())
					{
						System.out.println("\t---"+document.findAnnotation(child, true).getCoveredText());
					}
				}
				if (annotation.getAnnotationContents() instanceof org.dap.dap_dkpro.annotations.coref.CoreferenceLink)
				{
					System.out.println("\tFirst = "+document.findAnnotation(((org.dap.dap_dkpro.annotations.coref.CoreferenceLink)annotation.getAnnotationContents()).getFirst()).getCoveredText());
				}
			}
			
			System.out.println("Coreference:");
			List<Annotation<org.dap.dap_dkpro.annotations.coref.CoreferenceLink>> firstLinks = new LinkedList<>();
			for (Annotation<?> annotation : document.iterable(org.dap.dap_dkpro.annotations.coref.CoreferenceLink.class))
			{
				Annotation<org.dap.dap_dkpro.annotations.coref.CoreferenceLink> corefLink = (Annotation<org.dap.dap_dkpro.annotations.coref.CoreferenceLink>) annotation;
				if (corefLink.getAnnotationContents().getFirst().equals(corefLink.getAnnotationReference()))
				{
					firstLinks.add(corefLink);
				}
			}
			for (Annotation<org.dap.dap_dkpro.annotations.coref.CoreferenceLink> firstLink : firstLinks)
			{
				Annotation<org.dap.dap_dkpro.annotations.coref.CoreferenceLink> link = firstLink;
				while (link != null)
				{
					System.out.print(link.getCoveredText()+" -> ");
					AnnotationReference next = link.getAnnotationContents().getNext();
					if (next!=null)
					{
						link = (Annotation<org.dap.dap_dkpro.annotations.coref.CoreferenceLink>) document.findAnnotation(next);
					}
					else
					{
						link = null;
					}
				}
				System.out.println();
			}
		}
		catch (Throwable t)
		{
			t.printStackTrace();
		}
	}

}
