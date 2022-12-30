package bmi;

public class BMI_VO {
	private double bmi,cm,kg;
	
	public BMI_VO() { }
	
	public BMI_VO(double cm, double kg) {
		this.cm = cm;
		this.kg = kg;
	}
	public double getCm() {
		return cm;
	}
	public void setCm(double cm) {
		this.cm = cm;
	}
	public double getBmi() {
		return bmi;
	}
	public void setBmi() {
		this.bmi = kg/Math.pow(cm/100, 2);
	}
	public double getKg() {
		return kg;
	}
	public void setKg(double kg) {
		this.kg = kg;
	}
	
}
