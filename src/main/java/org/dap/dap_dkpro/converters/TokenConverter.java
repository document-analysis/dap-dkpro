package org.dap.dap_dkpro.converters;

import org.apache.uima.jcas.tcas.Annotation;
import org.dap.common.DapException;
import org.dap.dap_dkpro.annotations.Token;
import org.dap.dap_uimafit.AnnotationConverter;

/**
 * 
 *
 * <p>
 * Date: 10 Jul 2017
 * @author Asher Stern
 *
 */
public class TokenConverter implements AnnotationConverter<Token>
{
	public static final TokenConverter INSTANCE = new TokenConverter();
	
	@Override
	public Token convert(Annotation uimaAnnotation)
	{
		if (!(uimaAnnotation instanceof de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token))
		{
			throw new DapException("Tried to use "+TokenConverter.class.getSimpleName()+" for non-Token.");
		}
		return new Token();
	}
	
	private TokenConverter() {}
}
