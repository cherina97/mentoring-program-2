package epam.com.task1.model;

import java.io.Serializable;

public class Programmer extends Employee implements Serializable {

    private String programingLanguage;
    private transient double experienceYears;

    public Programmer(String name, int age, int salary, String companyName, String programingLanguage, double experienceYears) {
        super(name, age, salary, companyName);
        this.programingLanguage = programingLanguage;
        this.experienceYears = experienceYears;
    }

    public String getProgramingLanguage() {
        return programingLanguage;
    }

    public void setProgramingLanguage(String programingLanguage) {
        this.programingLanguage = programingLanguage;
    }

    public double getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(double experienceYears) {
        this.experienceYears = experienceYears;
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "programingLanguage='" + programingLanguage + '\'' +
                ", experienceYears=" + experienceYears +
                '}';
    }
}
