package org.dap.dap_dkpro_1_8.annotations.syntax.constituency;

import java.util.List;

import org.dap.data_structures.AnnotationContents;
import org.dap.data_structures.AnnotationReference;

/**
 * 
 *
 * <p>
 * Date: 6 Oct 2017
 * @author Asher Stern
 *
 */
public class Constituent extends AnnotationContents
{
	private static final long serialVersionUID = 4034435284784665369L;
	
	public Constituent(String constituentType, String syntacticFunction)
	{
		super();
		this.constituentType = constituentType;
		this.syntacticFunction = syntacticFunction;
	}
	
	
	public void setParent(AnnotationReference parent)
	{
		this.parent = parent;
	}
	public void setChildren(List<AnnotationReference> children)
	{
		this.children = children;
	}

	public String getConstituentType()
	{
		return constituentType;
	}
	public String getSyntacticFunction()
	{
		return syntacticFunction;
	}
	public AnnotationReference getParent()
	{
		return parent;
	}
	public List<AnnotationReference> getChildren()
	{
		return children;
	}



	private final String constituentType;
	private final String syntacticFunction;
	private AnnotationReference parent;
	private List<AnnotationReference> children;
}
