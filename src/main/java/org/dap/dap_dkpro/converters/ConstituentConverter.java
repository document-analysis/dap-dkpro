package org.dap.dap_dkpro.converters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.uima.jcas.tcas.Annotation;
import org.dap.common.DapException;
import org.dap.dap_dkpro.annotations.syntax.constituency.Constituent;
import org.dap.dap_uimafit.AnnotationConverter;

/**
 * 
 *
 * <p>
 * Date: 6 Oct 2017
 * @author Asher Stern
 *
 */
public class ConstituentConverter implements AnnotationConverter<Constituent>
{
	public static final ConstituentConverter INSTANCE = new ConstituentConverter();
	
	public static final String PACKAGE_NAME = Constituent.class.getPackage().getName();
	
	@Override
	public Constituent convert(Annotation uimaAnnotation)
	{
		if (!(uimaAnnotation instanceof de.tudarmstadt.ukp.dkpro.core.api.syntax.type.constituent.Constituent))
		{
			throw new DapException("The given annotation is not a constituent.");
		}
		
		try
		{
			de.tudarmstadt.ukp.dkpro.core.api.syntax.type.constituent.Constituent uimaConstituent = (de.tudarmstadt.ukp.dkpro.core.api.syntax.type.constituent.Constituent) uimaAnnotation;
			String simpleName = uimaConstituent.getClass().getSimpleName();
			if ("PRN".equals(simpleName)) { simpleName = "PARN"; }
			final String clsName = PACKAGE_NAME+"."+simpleName;
			Constructor<?> ctor = Class.forName(clsName).getConstructor(String.class, String.class);
			return (Constituent) ctor.newInstance(uimaConstituent.getConstituentType(), uimaConstituent.getSyntacticFunction());
		}
		catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			throw new DapException(e);
		}
	}
}
