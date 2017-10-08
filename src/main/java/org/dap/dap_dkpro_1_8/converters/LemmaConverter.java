package org.dap.dap_dkpro_1_8.converters;

import org.apache.uima.jcas.tcas.Annotation;
import org.dap.common.DapException;
import org.dap.dap_dkpro_1_8.annotations.Lemma;
import org.dap.dap_uimafit.AnnotationConverter;

/**
 * 
 *
 * <p>
 * Date: 10 Jul 2017
 * @author Asher Stern
 *
 */
public class LemmaConverter implements AnnotationConverter<Lemma>
{
	public static final LemmaConverter INSTANCE = new LemmaConverter();
	
	@Override
	public Lemma convert(Annotation uimaAnnotation)
	{
		if (uimaAnnotation instanceof de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma)
		{
			throw new DapException("Tried to use "+LemmaConverter.class.getSimpleName()+" for non-Lemma.");
		}
		de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma uimaLemma = (de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma) uimaAnnotation;
		return new Lemma(uimaLemma.getValue());
	}
	
	private LemmaConverter() {}
}
