package io.ykkh.calc.common;

import java.util.LinkedList;
import java.util.List;

import io.ykkh.calc.web.ExpressionSegment;

public class Utils {

	public static boolean isNumeric(Object str) {
		try {
			String s = String.valueOf(str);
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static List<ExpressionSegment> stringToObject(String expr) {

		expr = expr.replaceAll("\\s+", "");

		char[] tokens = expr.toCharArray();

		List<ExpressionSegment> exprList = new LinkedList<>();

		for (int i = 0; i < tokens.length; i++) {
			ExpressionSegment exprObj = new ExpressionSegment();

			if (isNumeric(tokens[i])) {
				String num = String.valueOf(tokens[i]);
				while (i + 1 < tokens.length && isNumeric(tokens[i + 1])) {
					num = num + tokens[++i];
				}
				exprObj.setNum(num);
			}
			if (i + 1 < tokens.length && tokens[i] != '(' && tokens[i] != ')') {
				exprObj.setNextOp(String.valueOf(tokens[++i]));
			} else if (tokens[i] == '(') {

				StringBuilder wString = new StringBuilder();

				while (i < expr.lastIndexOf(')') - 1) {
					wString.append(tokens[++i]);
					// i++;
				}
				i = expr.lastIndexOf(')');
				exprObj.setNum(stringToObject(wString.toString())); // recursive
				exprObj.setNextOp(String.valueOf(tokens[++i]));
			}

			exprList.add(exprObj);

		}

		return exprList;
	}
}
