package org.example;

public class Company {
    private int id;                // int tipinde
    private String name;           // String tipinde
    private double giro;           // double tipinde
    private String[] developerNames;

    public Company(int id, String name, double giro, int developerCount) {
        this.id = id;
        this.name = name;
        setGiro(giro); // Negatif olmamasını sağlamak için setGiro metodu kullanılır
        this.developerNames = new String[developerCount]; // Dizi boyutunu belirleyin
    }

    public Company(int id, String workintech, int giro, String[] employees) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGiro() {
        return giro;
    }

    public void setGiro(double giro) {
        if (giro >= 0) {
            this.giro = giro;
        } else {
            System.out.println("Giro cannot be negative.");
            this.giro = 0;
        }
    }

    public String[] getDeveloperNames() {
        return developerNames;
    }

    public void setDeveloperNames(String[] developerNames) {
        this.developerNames = developerNames;
    }

    public void addEmployee(int index, String name) {
        if (index >= 0 && index < developerNames.length) {
            if (developerNames[index] == null) {
                developerNames[index] = name;
                System.out.println("Employee " + name + " added at index " + index);
            } else {
                System.out.println("Index " + index + " is already occupied.");
            }
        } else {
            System.out.println("Index " + index + " is out of bounds.");
        }
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", giro=" + giro +
                ", developerNames=" + java.util.Arrays.toString(developerNames) +
                '}';
    }
}