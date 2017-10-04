package org.dap.dap_dkpro.annotations.syntax.depencency;

import org.dap.dap_dkpro.annotations.Token;
import org.dap.data_structures.AnnotationContents;

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
	
	public Token getGovernor()
	{
		return governor;
	}
	public Token getDependent()
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
	
	
	private Token governor;
	private Token dependent;
	private final String dependencyType;
	private final String flavor;
}
