package io.ykkh.calc.web;

import java.util.List;

public class ExpressionRequest {
	
	private List<ExpressionSegment> expressionSegments;

	public List<ExpressionSegment> getExpressionSegments() {
		return expressionSegments;
	}

	public void setExpressionSegments(List<ExpressionSegment> expressionSegments) {
		this.expressionSegments = expressionSegments;
	}

}
