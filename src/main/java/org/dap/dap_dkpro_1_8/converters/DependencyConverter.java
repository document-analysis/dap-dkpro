package org.dap.dap_dkpro_1_8.converters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.uima.jcas.tcas.Annotation;
import org.dap.common.DapException;
import org.dap.dap_dkpro_1_8.annotations.syntax.depencency.Dependency;
import org.dap.dap_uimafit.AnnotationConverter;

/**
 * 
 *
 * <p>
 * Date: October 2017
 * @author Asher Stern
 *
 */
public class DependencyConverter implements AnnotationConverter<Dependency>
{
	public static final DependencyConverter INSTANCE = new DependencyConverter();
	
	public static final String DEPENDENCY_PACKAGE = Dependency.class.getPackage().getName();
	
	@Override
	public Dependency convert(Annotation uimaAnnotation)
	{
		if (!(uimaAnnotation instanceof de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency))
		{
			throw new DapException("The given annotation class is " + uimaAnnotation.getClass() + " which is not a subclass of Dependency.");
		}
		de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency uimaDependency = (de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.Dependency) uimaAnnotation;
		
		try
		{
			Class<?> cls = Class.forName(DEPENDENCY_PACKAGE+"."+uimaDependency.getClass().getSimpleName());
			Constructor<?> ctor = cls.getConstructor(String.class, String.class);
			Dependency dapDependency = (Dependency) ctor.newInstance(uimaDependency.getDependencyType(), uimaDependency.getFlavor());
			return dapDependency;
		}
		catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			throw new DapException(e);
		}
	}
}
