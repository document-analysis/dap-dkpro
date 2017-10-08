package org.dap.dap_dkpro_1_8.converters;

import org.apache.uima.jcas.tcas.Annotation;
import org.dap.common.DapException;
import org.dap.dap_dkpro_1_8.annotations.Stem;
import org.dap.dap_uimafit.AnnotationConverter;

/**
 * 
 *
 * <p>
 * Date: 10 Jul 2017
 * @author Asher Stern
 *
 */
public class StemConverter implements AnnotationConverter<Stem>
{
	public static final StemConverter INSTANCE = new StemConverter();
	
	@Override
	public Stem convert(Annotation uimaAnnotation)
	{
		if (uimaAnnotation instanceof de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Stem)
		{
			de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Stem uimaStem = (de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Stem) uimaAnnotation;
			return new Stem(uimaStem.getValue());
		}
		else
		{
			throw new DapException("Tried to use "+StemConverter.class.getSimpleName()+" for non-Stem.");
		}
	}
	
	private StemConverter() {}
}
