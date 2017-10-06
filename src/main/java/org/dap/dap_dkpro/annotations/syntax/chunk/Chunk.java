package org.dap.dap_dkpro.annotations.syntax.chunk;

import org.dap.data_structures.AnnotationContents;

/**
 * 
 *
 * <p>
 * Date: 6 Oct 2017
 * @author Asher Stern
 *
 */
public class Chunk extends AnnotationContents
{
	private static final long serialVersionUID = -4367587318461207785L;

	public Chunk(String chunkValue)
	{
		super();
		this.chunkValue = chunkValue;
	}
	
	public String getChunkValue()
	{
		return chunkValue;
	}

	
	private final String chunkValue;
}
