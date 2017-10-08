package org.dap.dap_dkpro_1_8.converters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.uima.jcas.tcas.Annotation;
import org.dap.common.DapException;
import org.dap.dap_dkpro_1_8.annotations.pos.POS;
import org.dap.dap_uimafit.AnnotationConverter;

/**
 * 
 *
 * <p>
 * Date: 10 Jul 2017
 * @author Asher Stern
 *
 */
public class PosConverter implements AnnotationConverter<POS>
{
	public static final PosConverter INSTANCE = new PosConverter();
	
	@Override
	public POS convert(Annotation uimaAnnotation)
	{
		if (!(uimaAnnotation instanceof de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS))
		{
			throw new DapException(PosConverter.class.getSimpleName()+" has been given a non POS annotation.");
		}
		boolean tweet = uimaAnnotation.getClass().getPackage().getName().endsWith(".tweet");
		String className = POS.class.getPackage().getName()
				+(tweet?"tweet.":"")
				+"."+uimaAnnotation.getClass().getSimpleName();
		
		try
		{
			Constructor<?> constructor = Class.forName(className).getConstructor(String.class, String.class);
			de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS uimaPos = (de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS) uimaAnnotation;
			return (POS) constructor.newInstance(uimaPos.getPosValue(), null);
		}
		catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassCastException e)
		{
			throw new DapException(e);
		}
	}
	
	private PosConverter() {}
}
