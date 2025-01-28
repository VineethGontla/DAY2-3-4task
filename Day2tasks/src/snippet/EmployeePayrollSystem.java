package snippet;

import java.util.*;

public class EmployeePayrollSystem {

    public static void main(String[] args) {
        // Create departments and employees
        Department department = new Department("IT");

        PermanentEmployee emp1 = new PermanentEmployee("E040", "vineeth", 50000, 5000);
        ContractualEmployee emp2 = new ContractualEmployee("E045", "Chiru", 40000);

        department.addEmployee(emp1);
        department.addEmployee(emp2);

        Payroll payroll = new Payroll();
        payroll.generatePayroll(department);
        payroll.generateReport(department);
    }
}

class Employee {
    private String employeeId;
    private String name;
    private double baseSalary;

    public Employee(String employeeId, String name, double baseSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    // Default salary calculation, can be overridden by subclasses
    public double calculateSalary() {
        return baseSalary; // Base salary is the same for all employees, unless overridden.
    }
}

class PermanentEmployee extends Employee {
    private double bonus;

    public PermanentEmployee(String employeeId, String name, double baseSalary, double bonus) {
        super(employeeId, name, baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + bonus; // Permanent employees get a bonus.
    }
}

class ContractualEmployee extends Employee {
    public ContractualEmployee(String employeeId, String name, double baseSalary) {
        super(employeeId, name, baseSalary);
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary(); // No bonus for contractual employees.
    }
}

class Payroll {

    // Method to generate payroll for the department
    public void generatePayroll(Department department) {
        System.out.println("Payroll for " + department.getDepartmentName() + " Department:");
        for (Employee employee : department.getEmployees()) {
            System.out.println("Employee: " + employee.getName() + ", Salary: " + employee.calculateSalary());
        }
        System.out.println("-----------------------------------------------------");
    }

    // Method to generate a detailed report for the department
    public void generateReport(Department department) {
        System.out.println("Employee Report for " + department.getDepartmentName() + " Department:");
        for (Employee employee : department.getEmployees()) {
            System.out.println("Employee ID: " + employee.getEmployeeId() + ", Name: " + employee.getName() +
                    ", Base Salary: " + employee.getBaseSalary() + ", Total Salary: " + employee.calculateSalary());
        }
        System.out.println("-----------------------------------------------------");
    }
}

class Department {
    private String departmentName;
    private List<Employee> employees;

    // Constructor to initialize department and employee list
    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.employees = new ArrayList<>();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    // Method to add an employee to the department
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}

//output:
//
//	Payroll for IT Department:
//		Employee: Anirudh, Salary: 55000.0
//		Employee: Chiru, Salary: 40000.0
//		-----------------------------------------------------
//		Employee Report for IT Department:
//		Employee ID: E040, Name: Anirudh, Base Salary: 50000.0, Total Salary: 55000.0
//		Employee ID: E045, Name: Chiru, Base Salary: 40000.0, Total Salary: 40000.0
//		-----------------------------------------------------


