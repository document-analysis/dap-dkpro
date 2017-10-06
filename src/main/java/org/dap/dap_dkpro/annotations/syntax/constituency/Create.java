package org.dap.dap_dkpro.annotations.syntax.constituency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class Create
{

	public static void main(String[] args) throws IOException
	{
		final File dir = new File("/home/asher/main/data/code/git/dap/dap-dkpro/src/main/java/org/dap/dap_dkpro/annotations/syntax/constituency/");
		final File source = new File("/home/asher/main/data/code/git/dap/dap-dkpro/src/main/java/org/dap/dap_dkpro/annotations/syntax/constituency/ADJP.java");
		final String contents = readFile(source);
		final File listFile = new File("/home/asher/main/data/code/git/dap/dap-dkpro/src/main/java/org/dap/dap_dkpro/annotations/syntax/constituency/list.txt");
		final List<String> list = readList(listFile);
		
		for (String name : list)
		{
			final String newContents = contents.replace("ADJP", name);
			try (PrintWriter writer = new PrintWriter(new File(dir, name+".java")))
			{
				writer.println(newContents);
			}
		}
	}
	
	
	private static String readFile(File file) throws IOException
	{
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			String line;
			while ( (line=reader.readLine())!=null )
			{
				sb.append(line).append("\n");
			}
		}
		return sb.toString();
	}
	
	private static List<String> readList(File file) throws IOException
	{
		List<String> ret = new LinkedList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(file)))
		{
			String line;
			while ( (line=reader.readLine())!=null )
			{
				line = line.trim();
				if (line.length()>0)
				{
					line = line.substring(64);
					line = line.substring(0, line.indexOf('<'));
					ret.add(line);
				}
			}
		}
		return ret;
		
	}

}
