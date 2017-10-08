package org.dap.dap_dkpro_1_8.converters;

import java.util.Map;

import org.apache.uima.jcas.JCas;
import org.dap.dap_dkpro_1_8.annotations.Token;
import org.dap.dap_uimafit.ReferencesAdapter;
import org.dap.data_structures.Annotation;
import org.dap.data_structures.AnnotationReference;
import org.dap.data_structures.Document;

/**
 * 
 *
 * <p>
 * Date: 10 Jul 2017
 * @author Asher Stern
 *
 */
public class TokenReferenceAdapter implements ReferencesAdapter
{
	public static final TokenReferenceAdapter INSTANCE = new TokenReferenceAdapter();
	
	@Override
	public void adapt(Document document, JCas jcas, Map<AnnotationReference, org.apache.uima.jcas.tcas.Annotation> mapDapToUima, Map<org.apache.uima.jcas.tcas.Annotation, AnnotationReference> mapUimaToDap)
	{
		for (org.apache.uima.jcas.tcas.Annotation uimaAnnotation : mapUimaToDap.keySet())
		{
			if (uimaAnnotation instanceof de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token)
			{
				de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token uimaToken = (de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token) uimaAnnotation;
				
				Annotation<?> dapAnnotation = document.findAnnotation(mapUimaToDap.get(uimaAnnotation));
				Token dapToken = (Token) dapAnnotation.getAnnotationContents();
				
				de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS uimaPos = uimaToken.getPos();
				if (uimaPos != null)
				{
					AnnotationReference posReference = mapUimaToDap.get(uimaPos);
					if (posReference!=null)
					{
						dapToken.setPosReference(posReference);
					}
				}
				
				de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Lemma uimaLemma = uimaToken.getLemma();
				if (uimaLemma!=null)
				{
					AnnotationReference lemmaReference = mapUimaToDap.get(uimaLemma);
					if (lemmaReference!=null)
					{
						dapToken.setLemmaReference(lemmaReference);
					}
				}
				
				de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Stem uimaStem = uimaToken.getStem();
				if (uimaStem != null)
				{
					AnnotationReference stemReference = mapUimaToDap.get(uimaStem);
					if (stemReference != null)
					{
						dapToken.setStemReference(stemReference);
					}
				}
			}
		}
	}
	
	
	private TokenReferenceAdapter() {}
}
