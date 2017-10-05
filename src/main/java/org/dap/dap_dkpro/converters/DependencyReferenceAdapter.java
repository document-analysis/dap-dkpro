package org.dap.dap_dkpro.converters;

import java.util.Map;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.dap.dap_dkpro.annotations.syntax.depencency.Dependency;
import org.dap.dap_uimafit.ReferencesAdapter;
import org.dap.data_structures.AnnotationReference;
import org.dap.data_structures.Document;




/**
 * 
 *
 * <p>
 * Date: October 2017
 * @author Asher Stern
 *
 */
public class DependencyReferenceAdapter implements ReferencesAdapter
{
	public static final DependencyReferenceAdapter INSTANCE = new DependencyReferenceAdapter();
	
	@Override
	public void adapt(Document document, JCas jcas, Map<AnnotationReference, Annotation> mapDapToUima, Map<Annotation, AnnotationReference> mapUimaToDap)
	{
		for (Annotation uimaAnnotation : mapUimaToDap.keySet())
		{
			if (uimaAnnotation instanceof de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency)
			{
				de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency uimaDependency = (de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency) uimaAnnotation;
				@SuppressWarnings("unchecked")
				org.dap.data_structures.Annotation<? extends Dependency> dapDependency = (org.dap.data_structures.Annotation<? extends Dependency>) document.findAnnotation(mapUimaToDap.get(uimaAnnotation), true);

				AnnotationReference governorReference = mapUimaToDap.get(uimaDependency.getGovernor());
				AnnotationReference dependentReference = mapUimaToDap.get(uimaDependency.getDependent());
				dapDependency.getAnnotationContents().setGovernor(governorReference);
				dapDependency.getAnnotationContents().setDependent(dependentReference);

				
				//			org.dap.data_structures.Annotation<Token> governor = findToken(document, uimaDependency.getGovernor());
				//			org.dap.data_structures.Annotation<Token> dependent = findToken(document, uimaDependency.getDependent());
				//			
				//			dapDependency.getAnnotationContents().setGovernor(governor.getAnnotationReference());
				//			dapDependency.getAnnotationContents().setDependent(dependent.getAnnotationReference());
			}
		}
	}
	
	
//	private org.dap.data_structures.Annotation<Token> findToken(Document document, de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token uimaToken)
//	{
//		Iterator<org.dap.data_structures.Annotation<?>> iterator = document.iterator(Token.class, uimaToken.getBegin(), uimaToken.getEnd());
//		if (!iterator.hasNext()) { throw new DapException("Token could not be found."); }
//		@SuppressWarnings("unchecked")
//		org.dap.data_structures.Annotation<Token> token = (org.dap.data_structures.Annotation<Token>) iterator.next();
//		if (iterator.hasNext()) { throw new DapException("More than one Token annotation has been encountered for indexes: "+uimaToken.getBegin()+" - "+uimaToken.getEnd()); }
//		return token;
//	}
}
