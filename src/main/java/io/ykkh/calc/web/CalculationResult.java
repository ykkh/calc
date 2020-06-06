package io.ykkh.calc.web;

public class CalculationResult {
  private double a;
  private double b;
  private String op;
  private double result;
  
  
public CalculationResult(double a, double b, String op, double result) {
	super();
	this.a = a;
	this.b = b;
	this.op = op;
	this.result = result;
}
public double getA() {
	return a;
}
public void setA(double a) {
	this.a = a;
}
public double getB() {
	return b;
}
public void setB(double b) {
	this.b = b;
}
public String getOp() {
	return op;
}
public void setOp(String op) {
	this.op = op;
}
public double getResult() {
	return result;
}
public void setResult(double result) {
	this.result = result;
}
  
  
  
  
}
