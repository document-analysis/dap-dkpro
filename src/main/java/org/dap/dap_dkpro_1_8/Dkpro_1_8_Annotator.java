package org.dap.dap_dkpro_1_8;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.dap.annotators.Annotator;
import org.dap.common.DapException;
import org.dap.dap_dkpro_1_8.converters.ChunkConverter;
import org.dap.dap_dkpro_1_8.converters.ConstituentConverter;
import org.dap.dap_dkpro_1_8.converters.ConstituentReferenceAdapter;
import org.dap.dap_dkpro_1_8.converters.CoreferenceLinkConverter;
import org.dap.dap_dkpro_1_8.converters.CoreferenceReferenceAdapter;
import org.dap.dap_dkpro_1_8.converters.DependencyConverter;
import org.dap.dap_dkpro_1_8.converters.DependencyReferenceAdapter;
import org.dap.dap_dkpro_1_8.converters.LemmaConverter;
import org.dap.dap_dkpro_1_8.converters.NamedEntityConverter;
import org.dap.dap_dkpro_1_8.converters.PosConverter;
import org.dap.dap_dkpro_1_8.converters.SentenceConverter;
import org.dap.dap_dkpro_1_8.converters.StemConverter;
import org.dap.dap_dkpro_1_8.converters.TokenConverter;
import org.dap.dap_dkpro_1_8.converters.TokenReferenceAdapter;
import org.dap.dap_uimafit.AggregateReferencesAdapter;
import org.dap.dap_uimafit.AnnotationConverter;
import org.dap.dap_uimafit.ConverterAnnotator;
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

/**
 * 
 *
 * <p>
 * Date: 8 Oct 2017
 * @author Asher Stern
 *
 */
public class Dkpro_1_8_Annotator extends Annotator
{
	public Dkpro_1_8_Annotator(AnalysisEngine aggregateDkproAnnotator)
	{
		constructUnderlyingAnnotator(aggregateDkproAnnotator);
	}
	
	@Override
	public void annotate(Document document)
	{
		if (null == LanguageFeature.getDocumentLanguage(document))
		{
			throw new DapException("Document language has not been set.");
		}
		underlyingAnnotator.annotate(document);
	}

	@Override
	public void close()
	{
		underlyingAnnotator.close();
	}
	
	
	private void constructUnderlyingAnnotator(AnalysisEngine aggregateDkproAnnotator)
	{
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
		
		underlyingAnnotator = new ConverterAnnotator(converters, aggregateDkproAnnotator);
		underlyingAnnotator.setReferencesAdapter(aggregateReferencesAdapter);
	}

	
	private ConverterAnnotator underlyingAnnotator = null;
}
