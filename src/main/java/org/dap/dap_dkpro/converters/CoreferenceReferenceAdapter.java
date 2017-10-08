package org.dap.dap_dkpro.converters;

import java.util.Iterator;
import java.util.Map;

import org.apache.uima.cas.Type;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.dap.common.DapException;
import org.dap.dap_dkpro.annotations.coref.CoreferenceLink;
import org.dap.dap_uimafit.ReferencesAdapter;
import org.dap.data_structures.AnnotationReference;
import org.dap.data_structures.Document;

import de.tudarmstadt.ukp.dkpro.core.api.coref.type.CoreferenceChain;


/**
 * 
 *
 * <p>
 * Date: 8 Aug 2017
 * @author Asher Stern
 *
 */
public class CoreferenceReferenceAdapter implements ReferencesAdapter
{
	public static final CoreferenceReferenceAdapter INSTANCE = new CoreferenceReferenceAdapter();
	
	@SuppressWarnings("unused")
	@Override
	public void adapt(Document document, JCas jcas, Map<AnnotationReference, Annotation> mapDapToUima, Map<Annotation, AnnotationReference> mapUimaToDap)
	{
		Type coreferenceChainType = CasUtil.getType(jcas.getCas(), CoreferenceChain.class);
		Iterator<CoreferenceChain> iterator = jcas.getIndexRepository().getAllIndexedFS(coreferenceChainType);
		while (iterator.hasNext())
		{
			CoreferenceChain chain = iterator.next();
			
			de.tudarmstadt.ukp.dkpro.core.api.coref.type.CoreferenceLink uimaCoreferenceLink = chain.getFirst();
			if (uimaCoreferenceLink == null) {throw new DapException("A required UIMA annotation, referenced by a coreference chain, could not be detected.");}
			AnnotationReference dapFirst = mapUimaToDap.get(uimaCoreferenceLink);
			if (dapFirst == null) {throw new DapException("A required annotation, referenced by a coreference chain, could not be detected.");}
			
			AnnotationReference dapReferenceToCoreferenceLink = dapFirst;
			if (dapReferenceToCoreferenceLink == null) {throw new DapException("A required annotation, referenced by a coreference chain, could not be detected.");}
			
			while (uimaCoreferenceLink!=null)
			{
				CoreferenceLink dapCoreferenceLink = (CoreferenceLink) document.findAnnotation(dapReferenceToCoreferenceLink, true).getAnnotationContents();
				
				dapCoreferenceLink.setFirst(dapFirst);
				
				uimaCoreferenceLink = uimaCoreferenceLink.getNext();
				if (uimaCoreferenceLink != null)
				{
					dapReferenceToCoreferenceLink = mapUimaToDap.get(uimaCoreferenceLink);
					if (dapReferenceToCoreferenceLink == null)
					{
						throw new DapException("A required annotation, referenced by a coreference chain, could not be detected.\n"
								+ "For uimaCoreferenceLink over " + uimaCoreferenceLink.getCoveredText());
					}
					
					dapCoreferenceLink.setNext(dapReferenceToCoreferenceLink); // dapCoreferenceLink is still the previous
				}
			}
		}
	}
}
