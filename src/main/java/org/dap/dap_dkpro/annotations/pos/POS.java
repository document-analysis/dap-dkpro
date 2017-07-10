package org.dap.dap_dkpro.annotations.pos;

import org.dap.data_structures.AnnotationContents;

/**
 * 
 *
 * <p>
 * Date: 9 Jul 2017
 * @author Asher Stern
 *
 */
public class POS extends AnnotationContents
{
	private static final long serialVersionUID = 5327869218541085719L;
	
	public POS(String posValue, String coarseValue)
	{
		super();
		this.posValue = posValue;
		this.coarseValue = coarseValue;
	}
	
	
	
	public String getPosValue()
	{
		return posValue;
	}
	public String getCoarseValue()
	{
		return coarseValue;
	}
	
	
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coarseValue == null) ? 0 : coarseValue.hashCode());
		result = prime * result + ((posValue == null) ? 0 : posValue.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		POS other = (POS) obj;
		if (coarseValue == null)
		{
			if (other.coarseValue != null)
				return false;
		}
		else if (!coarseValue.equals(other.coarseValue))
			return false;
		if (posValue == null)
		{
			if (other.posValue != null)
				return false;
		}
		else if (!posValue.equals(other.posValue))
			return false;
		return true;
	}
	
	
	







	@Override
	public String toString()
	{
		return "POS [posValue=" + posValue + ", coarseValue=" + coarseValue + "]";
	}



	private final String posValue;
	private final String coarseValue;
}
