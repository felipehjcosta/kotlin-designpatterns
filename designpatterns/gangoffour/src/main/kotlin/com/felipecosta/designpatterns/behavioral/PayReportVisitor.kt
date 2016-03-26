package com.felipecosta.designpatterns.behavioral

import java.math.BigDecimal

abstract class Employee {
    abstract fun accept(visitor: EmployeeVisitor)
}

class HourlyEmployee(val hoursWorked: Int) : Employee() {

    override fun accept(visitor: EmployeeVisitor) {
        visitor.visit(this)
    }
}

class SalariedEmployee(val fixedSalary: BigDecimal) : Employee() {

    override fun accept(visitor: EmployeeVisitor) {
        visitor.visit(this)
    }
}

interface EmployeeVisitor {
    fun visit(hourlyEmployee: HourlyEmployee): Unit
    fun visit(salariedEmployee: SalariedEmployee): Unit
}

class QtdHoursAndPayReport(val dollarHour: BigDecimal) : EmployeeVisitor {

    override fun visit(hourlyEmployee: HourlyEmployee) {
        System.out.println("Hours worked by this employee: ${hourlyEmployee.hoursWorked}\n" +
                "Total paid: ${dollarHour * BigDecimal(hourlyEmployee.hoursWorked)}")
    }

    override fun visit(salariedEmployee: SalariedEmployee) {
        // do nothing
    }
}

fun main(args: Array<String>) {
    val employees: Array<Employee> = arrayOf(SalariedEmployee(BigDecimal.valueOf(1000)),
            HourlyEmployee(170),
            HourlyEmployee(180))

    val employeeVisitor: EmployeeVisitor = QtdHoursAndPayReport(BigDecimal.valueOf(10.50))

    for (employee in employees) {
        employee.accept(employeeVisitor)
    }
}
