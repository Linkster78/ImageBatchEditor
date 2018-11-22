package com.tek.batch.filters;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tek.batch.main.BatchImage;
import com.tek.batch.options.FilterParameter;
import com.tek.batch.options.StringParameter;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class RenameFilter implements IFilter, Cloneable {

	private Pattern equationPattern;
	private StringParameter renamePattern;
	
	public RenameFilter() {
		equationPattern = Pattern.compile("[\\[][\\-\\+\\*\\\\\\^\\d\\si]+[\\]]");
		renamePattern = new StringParameter("Image_[i]", "Rename Pattern");
	}
	
	@Override
	public String toString() {
		return "Rename";
	}

	@Override
	public List<FilterParameter<?>> getParameters() {
		return Arrays.asList(renamePattern);
	}

	@Override
	public void apply(int i, BatchImage image) {
		Matcher m = equationPattern.matcher(renamePattern.getValue());
		StringBuilder strBuilder = new StringBuilder(renamePattern.getValue());
		
		int removed = 0;
		
		while(m.find()) {
			int start = m.start();
			int end = m.end();
			String match = m.group();
			String equation = match.substring(1, match.length() - 1);
		
			Expression e = new ExpressionBuilder(equation)
					.variable("i")
					.build();
			e.setVariable("i", i);
			
			int result = (int) e.evaluate();
			
			strBuilder.replace(start - removed, end - removed, result + "");
			
			removed += (end - start) - ("" + result).length();
		}
		
		image.setName(strBuilder.toString());
	}
	
	public Pattern getEquationPattern() {
		return equationPattern;
	}
	
	public StringParameter getRenamePattern() {
		return renamePattern;
	}

}
