package org.dap.dap_dkpro_1_8.annotations.ne;

import org.dap.data_structures.AnnotationContents;

/**
 * 
 *
 * <p>
 * Date: 12 Jul 2017
 * @author Asher Stern
 *
 */
public class NamedEntity extends AnnotationContents
{
	private static final long serialVersionUID = 6602903439754392414L;
	
	public NamedEntity(String value, String id, String type)
	{
		super();
		this.value = value;
		this.id = id;
		this.type = type;
	}
	
	
	
	public String getValue()
	{
		return value;
	}
	public String getId()
	{
		return id;
	}
	public String getType()
	{
		return type;
	}
	
	



	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		NamedEntity other = (NamedEntity) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (type == null)
		{
			if (other.type != null)
				return false;
		}
		else if (!type.equals(other.type))
			return false;
		if (value == null)
		{
			if (other.value != null)
				return false;
		}
		else if (!value.equals(other.value))
			return false;
		return true;
	}


	@Override
	public String toString()
	{
		return "NamedEntity [value=" + value + ", id=" + id + ", type=" + type + "]";
	}


	private final String value;
	private final String id;
	private final String type;
}
