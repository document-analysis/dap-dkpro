package org.dap.dap_dkpro.annotations;

import org.dap.data_structures.AnnotationContents;
import org.dap.data_structures.AnnotationReference;

/**
 * 
 *
 * <p>
 * Date: 9 Jul 2017
 * @author Asher Stern
 *
 */
public class Token extends AnnotationContents
{
	private static final long serialVersionUID = -6982320414100938266L;
	
	public Token()
	{
		super();
	}
	public Token(AnnotationReference lemmaReference, AnnotationReference stemReference, AnnotationReference posReference)
	{
		super();
		this.lemmaReference = lemmaReference;
		this.stemReference = stemReference;
		this.posReference = posReference;
	}
	
	public AnnotationReference getLemmaReference()
	{
		return lemmaReference;
	}
	public void setLemmaReference(AnnotationReference lemmaReference)
	{
		this.lemmaReference = lemmaReference;
	}
	public AnnotationReference getStemReference()
	{
		return stemReference;
	}
	public void setStemReference(AnnotationReference stemReference)
	{
		this.stemReference = stemReference;
	}
	public AnnotationReference getPosReference()
	{
		return posReference;
	}
	public void setPosReference(AnnotationReference posReference)
	{
		this.posReference = posReference;
	}
	

	private AnnotationReference lemmaReference = null;
	private AnnotationReference stemReference = null;
	private AnnotationReference posReference = null;
}
