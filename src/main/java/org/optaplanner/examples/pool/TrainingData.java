package org.optaplanner.examples.pool;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.optaplanner.examples.common.domain.AbstractPersistable;
import org.optaplanner.examples.nurserostering.domain.Employee;
import org.optaplanner.examples.nurserostering.domain.Shift;
@Entity(name = "TrainingData")
public class TrainingData extends AbstractPersistable{
	private int weight;

	@ManyToOne
	private Employee employee;
	private LocalDate date;
	
	private String shiftType;
	
	@ManyToOne
	private Shift shift;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	

	public String getShiftType() {
		return shiftType;
	}

	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}

	public int getWeight() {
		return weight;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}



}



