package org.dap.dap_dkpro_1_8.converters;

import org.apache.uima.jcas.tcas.Annotation;
import org.dap.dap_dkpro_1_8.annotations.Sentence;
import org.dap.dap_uimafit.AnnotationConverter;

/**
 * 
 *
 * <p>
 * Date: 10 Jul 2017
 * @author Asher Stern
 *
 */
public class SentenceConverter implements AnnotationConverter<Sentence>
{
	public static final SentenceConverter INSTANCE = new SentenceConverter();
	
	@Override
	public Sentence convert(Annotation uimaAnnotation)
	{
		return new Sentence();
	}
	
	
	private SentenceConverter() {}
}
