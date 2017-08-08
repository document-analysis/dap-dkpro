package org.dap.dap_dkpro.annotations.coref;

import org.dap.data_structures.AnnotationContents;
import org.dap.data_structures.AnnotationReference;

/**
 * 
 *
 * <p>
 * Date: 8 Aug 2017
 * @author Asher Stern
 *
 */
public class CoreferenceLink extends AnnotationContents
{
	private static final long serialVersionUID = 2544101098062789499L;

	public CoreferenceLink(String referenceType, String referenceRelation)
	{
		this(null, null, referenceType, referenceRelation);
	}

	public CoreferenceLink(AnnotationReference next, String referenceType, String referenceRelation)
	{
		this(null, next, referenceType, referenceRelation);
	}
	
	public CoreferenceLink(AnnotationReference first, AnnotationReference next, String referenceType, String referenceRelation)
	{
		super();
		this.first = first;
		this.next = next;
		this.referenceType = referenceType;
		this.referenceRelation = referenceRelation;
	}


	public AnnotationReference getFirst()
	{
		return first;
	}

	public void setFirst(AnnotationReference first)
	{
		this.first = first;
	}

	public AnnotationReference getNext()
	{
		return next;
	}

	public void setNext(AnnotationReference next)
	{
		this.next = next;
	}

	public String getReferenceType()
	{
		return referenceType;
	}

	public void setReferenceType(String referenceType)
	{
		this.referenceType = referenceType;
	}

	public String getReferenceRelation()
	{
		return referenceRelation;
	}

	public void setReferenceRelation(String referenceRelation)
	{
		this.referenceRelation = referenceRelation;
	}




	private AnnotationReference first = null;

	private AnnotationReference next = null;
	
	/**
	 * The role or type which the covered text has in the coreference chain.
	 */
	private String referenceType = null;
	
	/**
	 * The type of relation between this link and the next link in the chain.
	 */
	private String referenceRelation = null;
}
