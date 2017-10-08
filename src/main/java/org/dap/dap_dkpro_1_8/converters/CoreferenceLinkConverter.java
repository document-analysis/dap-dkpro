package org.dap.dap_dkpro_1_8.converters;

import org.apache.uima.jcas.tcas.Annotation;
import org.dap.dap_dkpro_1_8.annotations.coref.CoreferenceLink;
import org.dap.dap_uimafit.AnnotationConverter;

/**
 * 
 *
 * <p>
 * Date: 8 Aug 2017
 * @author Asher Stern
 *
 */
public class CoreferenceLinkConverter implements AnnotationConverter<CoreferenceLink>
{
	public static final CoreferenceLinkConverter INSTANCE = new CoreferenceLinkConverter();
	
	@Override
	public CoreferenceLink convert(Annotation uimaAnnotation)
	{
		de.tudarmstadt.ukp.dkpro.core.api.coref.type.CoreferenceLink uimaCoreferenceLink = (de.tudarmstadt.ukp.dkpro.core.api.coref.type.CoreferenceLink) uimaAnnotation;
		return new CoreferenceLink(uimaCoreferenceLink.getReferenceType(), uimaCoreferenceLink.getReferenceRelation());
	}
}
