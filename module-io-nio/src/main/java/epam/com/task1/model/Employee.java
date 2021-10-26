package epam.com.task1.model;

import java.io.Serializable;

public class Employee extends Person implements Serializable {

    private transient int salary;
    private String companyName;

    public Employee(String name, int age, int salary, String companyName) {
        super(name, age);
        this.salary = salary;
        this.companyName = companyName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
