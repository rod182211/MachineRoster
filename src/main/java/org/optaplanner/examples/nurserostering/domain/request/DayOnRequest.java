/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.examples.nurserostering.domain.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.optaplanner.examples.common.domain.AbstractPersistable;
import org.optaplanner.examples.nurserostering.domain.Employee;
import org.optaplanner.examples.nurserostering.domain.ShiftDate;
import org.optaplanner.examples.nurserostering.domain.ShiftType;




@Entity (name = "DayOnRequest")
@XStreamAlias("DayOnRequest")
public class DayOnRequest extends AbstractPersistable {

	@ManyToOne(cascade = CascadeType.MERGE)
    private Employee employee;
	@ManyToOne(cascade = CascadeType.MERGE)
	private ShiftDate shiftDate;
    private int weight;

  
	


	public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


	public ShiftDate getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(ShiftDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public int getWeight() {
        return weight;
    }

 	public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return shiftDate + "_ON_" + employee;
    }

}