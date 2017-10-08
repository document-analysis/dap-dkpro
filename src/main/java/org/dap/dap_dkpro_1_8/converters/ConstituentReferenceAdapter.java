package org.dap.dap_dkpro_1_8.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.dap.common.DapException;
import org.dap.dap_uimafit.ReferencesAdapter;
import org.dap.data_structures.AnnotationReference;
import org.dap.data_structures.Document;

import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.constituent.Constituent;

/**
 * 
 *
 * <p>
 * Date: 6 Oct 2017
 * @author Asher Stern
 *
 */
public class ConstituentReferenceAdapter implements ReferencesAdapter
{
	public static final ConstituentReferenceAdapter INSTANCE = new ConstituentReferenceAdapter();

	@Override
	public void adapt(Document document, JCas jcas, Map<AnnotationReference, Annotation> mapDapToUima, Map<Annotation, AnnotationReference> mapUimaToDap)
	{
		for (Annotation uimaAnnotation : mapUimaToDap.keySet())
		{
			if (uimaAnnotation instanceof Constituent)
			{
				Constituent uimaConstituent = (Constituent) uimaAnnotation;
				org.dap.dap_dkpro_1_8.annotations.syntax.constituency.Constituent dapConstituent = (org.dap.dap_dkpro_1_8.annotations.syntax.constituency.Constituent) document.findAnnotation(mapUimaToDap.get(uimaAnnotation)).getAnnotationContents();
				dapConstituent.setParent(mapUimaToDap.get(uimaConstituent.getParent()));
				
				List<AnnotationReference> children = new ArrayList<>(uimaConstituent.getChildren().size());
				for (FeatureStructure fsChild : uimaConstituent.getChildren())
				{
					AnnotationReference childReference = mapUimaToDap.get(fsChild);
					if (null == childReference)
					{
						throw new DapException("No matching DAP constituent-child was found for a UIMA constituent-child");
					}
					children.add(childReference);
				}
				dapConstituent.setChildren(children);
			}
		}
	}
}
