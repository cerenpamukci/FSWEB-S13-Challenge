package org.example;

public class Employee {
    private int id;                // int tipinde
    private String fullName;       // String tipinde
    private String email;          // String tipinde
    private String password;       // String tipinde
    private String[] healthplans;
    public Employee(int id, String fullName, String email, String password, int healthplanSize) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.healthplans = new String[healthplanSize]; // healthplans dizisini belirtilen boyutla başlat
    }

    public Employee(int id, String johnDoe, String mail, String number, String[] healthplans) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getHealthplans() {
        return healthplans;
    }

    public void setHealthplans(String[] healthplans) {
        this.healthplans = healthplans;
    }

    public void addHealthplan(int index, String name) {
        // Geçersiz bir index kontrolü yapılmalı
        if (index < 0 || index >= healthplans.length) {
            System.out.println("Index " + index + " is out of bounds.");
            return; // Geçersiz bir index ise metodu sonlandır
        }

        // İlgili index'te eğer healthplan mevcut değilse ekleme yap
        if (healthplans[index] == null) {
            healthplans[index] = name;
            System.out.println("Healthplan " + name + " added at index " + index);
        } else {
            System.out.println("Index " + index + " is already occupied."); // Mevcut bir değer varsa uyarı ver
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", healthplans=" + java.util.Arrays.toString(healthplans) +
                '}';
    }

    public void addHealthPlan(int i, String testSigorta) {
    }

    public String getHealthPlans() {
        return null;
    }
}
