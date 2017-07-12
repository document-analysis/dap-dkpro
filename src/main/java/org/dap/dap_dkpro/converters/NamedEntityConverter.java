package org.dap.dap_dkpro.converters;

import org.apache.uima.jcas.tcas.Annotation;
import org.dap.common.DapException;
import org.dap.dap_dkpro.annotations.ne.NamedEntity;
import org.dap.dap_uimafit.AnnotationConverter;

/**
 * 
 *
 * <p>
 * Date: 12 Jul 2017
 * @author Asher Stern
 *
 */
public class NamedEntityConverter implements AnnotationConverter<NamedEntity>
{
	public static final NamedEntityConverter INSTANCE = new NamedEntityConverter();
	
	@Override
	public NamedEntity convert(Annotation uimaAnnotation)
	{
		if (uimaAnnotation instanceof de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity)
		{
			de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity uimaNE = (de.tudarmstadt.ukp.dkpro.core.api.ner.type.NamedEntity) uimaAnnotation;
			return new NamedEntity(uimaNE.getValue(), null , uimaNE.getClass().getSimpleName());
		}
		else
		{
			throw new DapException("Tried to convert a non-named-entity-annotation into a named-entity annotation.");
		}
	}
	
	private NamedEntityConverter() {}
}
