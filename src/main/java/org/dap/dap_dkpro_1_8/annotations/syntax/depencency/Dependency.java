package org.dap.dap_dkpro_1_8.annotations.syntax.depencency;

import org.dap.data_structures.AnnotationContents;
import org.dap.data_structures.AnnotationReference;

/**
 * 
 *
 * <p>
 * Date: 4 Oct 2017
 * @author Asher Stern
 *
 */
public class Dependency extends AnnotationContents
{
	private static final long serialVersionUID = -4883332938522583929L;
	
	public Dependency(String dependencyType, String flavor)
	{
		super();
		this.dependencyType = dependencyType;
		this.flavor = flavor;
	}
	
	
	public void setGovernor(AnnotationReference governor)
	{
		this.governor = governor;
	}
	public void setDependent(AnnotationReference dependent)
	{
		this.dependent = dependent;
	}

	public AnnotationReference getGovernor()
	{
		return governor;
	}
	public AnnotationReference getDependent()
	{
		return dependent;
	}
	
	public String getDependencyType()
	{
		return dependencyType;
	}
	public String getFlavor()
	{
		return flavor;
	}
	
	
	private AnnotationReference governor;
	private AnnotationReference dependent;
	private final String dependencyType;
	private final String flavor;
}
