package io.ykkh.calc.web;

public class ExpressionSegment {
 private Object num;
 private String nextOp;
 
 public ExpressionSegment() {
	 
 }
 
 public ExpressionSegment(double num, String nextChar) {
		this.num = num;
		this.nextOp = nextChar;
	}
 

public Object getNum() {
	return num;
}

public void setNum(Object num) {
	this.num = num;
}

public String getNextOp() {
	return nextOp;
}

public void setNextOp(String nextOp) {
	this.nextOp = nextOp;
}

}
