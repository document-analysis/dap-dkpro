package org.dap.dap_dkpro.converters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.uima.jcas.tcas.Annotation;
import org.dap.common.DapException;
import org.dap.dap_dkpro.annotations.syntax.chunk.Chunk;
import org.dap.dap_uimafit.AnnotationConverter;

/**
 * 
 *
 * <p>
 * Date: 6 Oct 2017
 * @author Asher Stern
 *
 */
public class ChunkConverter implements AnnotationConverter<Chunk>
{
	public static final ChunkConverter INSTANCE = new ChunkConverter();
	
	public static final String PACKAGE_NAME = Chunk.class.getPackage().getName();

	@Override
	public Chunk convert(Annotation uimaAnnotation)
	{
		try
		{
			de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk uimaChunk = (de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk) uimaAnnotation;
			final String clsName = PACKAGE_NAME+"."+uimaChunk.getClass().getSimpleName();
			Constructor<?> ctor = Class.forName(clsName).getConstructor(String.class);
			return (Chunk) ctor.newInstance(uimaChunk.getChunkValue());
		}
		catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
		{
			throw new DapException(e);
		}
	}
}
