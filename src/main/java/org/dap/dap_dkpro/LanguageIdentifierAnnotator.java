package org.dap.dap_dkpro;

import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.dap.common.DapException;
import org.dap.dap_uimafit.AbstractConverterAnnotator;
import org.dap.data_structures.Document;
import org.dap.data_structures.LanguageFeature;

/**
 * 
 *
 * <p>
 * Date: 12 Jul 2017
 * @author Asher Stern
 *
 */
public class LanguageIdentifierAnnotator extends AbstractConverterAnnotator
{
	public LanguageIdentifierAnnotator(AnalysisEngine uimaAnalysisEngine)
	{
		super(uimaAnalysisEngine);
	}

	public LanguageIdentifierAnnotator(AnalysisEngine uimaAnalysisEngine, JCas jcas)
	{
		super(uimaAnalysisEngine, jcas);
	}

	@Override
	public void annotate(Document document)
	{
		try
		{
			jcas.setDocumentText(document.getText());
			uimaAnalysisEngine.process(jcas);
			
			String language = jcas.getDocumentLanguage();
			if (language!=null)
			{
				LanguageFeature.setDocumentLanguage(document, language);
			}
		}
		catch (AnalysisEngineProcessException e)
		{
			throw new DapException(e);
		}
	}
}
