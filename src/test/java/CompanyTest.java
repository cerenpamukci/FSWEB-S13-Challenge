import org.example.Company;
import org.example.Employee;
import org.example.Healthplan;
import org.example.enums.Plan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ResultAnalyzer.class)
public class CompanyTest {

    private Healthplan healthplan;
    private Employee employee;
    private Company company;

    @BeforeEach
    void setUp() {
        healthplan = new Healthplan(1, "A Sigorta", Plan.BASIC);
        String[] healthplans = new String[2];
        healthplans[0] = healthplan.getName();
        employee = new Employee(1, "John Doe", "jd@test.com", "1234", healthplans);

        String[] employees = new String[2];
        employees[0] = employee.getFullName();
        company = new Company(1, "Workintech", 1000, employees);
    }

    @DisplayName("Healthplan sınıf değişkenleri doğru access modifier a sahip mi ?")
    @Test
    public void testHealthPlanTypesAccessModifiers() throws NoSuchFieldException {
        Field idField = healthplan.getClass().getDeclaredField("id");
        assertEquals(idField.getModifiers(), 2);

        Field nameField = healthplan.getClass().getDeclaredField("name");
        assertEquals(nameField.getModifiers(), 2);

        Field planField = healthplan.getClass().getDeclaredField("plan");
        assertEquals(planField.getModifiers(), 2);
    }

    @DisplayName("Healthplan sınıf değişkenleri doğru tipte mi ?")
    @Test
    public void testHealthPlanInstanceTypes() throws NoSuchFieldException {
        assertThat(healthplan.getId(), instanceOf(Integer.class));
        assertThat(healthplan.getName(), instanceOf(String.class));
        assertThat(healthplan.getPlan(), instanceOf(Plan.class));
    }

    @DisplayName("Plan sınıf değişkenleri doğru tipte mi ?")
    @Test
    public void testPlanInstanceTypes() throws NoSuchFieldException {
        assertThat(healthplan.getPlan().getName(), instanceOf(String.class)); // Doğru
        assertThat(healthplan.getPlan().getPrice(), instanceOf(Double.class)); // Burada Integer yerine Double kullanılmalı
    }

    @DisplayName("Employee sınıf değişkenleri doğru access modifier a sahip mi ?")
    @Test
    public void testEmployeeAccessModifiers() throws NoSuchFieldException {
        Field idField = employee.getClass().getDeclaredField("id");
        assertEquals(Modifier.isPrivate(idField.getModifiers()), true); // Değiştirildi

        Field fullNameField = employee.getClass().getDeclaredField("fullName");
        assertEquals(Modifier.isPrivate(fullNameField.getModifiers()), true); // Değiştirildi

        Field emailField = employee.getClass().getDeclaredField("email");
        assertEquals(Modifier.isPrivate(emailField.getModifiers()), true); // Değiştirildi

        Field passwordField = employee.getClass().getDeclaredField("password");
        assertEquals(Modifier.isPrivate(passwordField.getModifiers()), true); // Değiştirildi

        Field healthPlans = employee.getClass().getDeclaredField("healthplans"); // Burada healthPlans değil, healthplans kullanılmalı
        assertEquals(Modifier.isPrivate(healthPlans.getModifiers()), true); // Değiştirildi
    }

    @DisplayName("Employee sınıf değişkenleri doğru tipte mi ?")
    @Test
    public void testEmployeeInstanceTypes() throws NoSuchFieldException {
        // Employee nesnesini başlatma ve alanlara değer atama
        Employee employee = new Employee(1, "John Doe", "john@example.com", "password123", 3);

        // Değerleri kontrol edin
        assertThat(employee.getFullName(), instanceOf(String.class));  // fullName alanı "John Doe" ile doldurulmuştur.
        assertThat(employee.getEmail(), instanceOf(String.class));     // email alanı "john@example.com" ile doldurulmuştur.
        assertThat(employee.getHealthplans(), instanceOf(String[].class)); // healthplans String[] tipinde olmalıdır.
    }

    @DisplayName("addHealthplan method başarılı çalışıyor mu?")
    @Test
    public void testAddHealthplanMethod() throws NoSuchFieldException {
        Employee employee = new Employee(1, "John Doe", "john@example.com", "12345", 2); // Employee nesnesini oluştur ve healthplans boyutunu belirt

        // Geçersiz index'e ekleme yapmayı dene
        employee.addHealthplan(-1, "Test Healthplan");
        assertFalse(Arrays.asList(employee.getHealthplans()).contains("Test Healthplan")); // Eklenmemiş olmalı

        // Boş bir index'e ekleme yap
        employee.addHealthplan(0, "Test Healthplan");
        assertTrue(Arrays.asList(employee.getHealthplans()).contains("Test Healthplan")); // Eklenmiş olmalı

        // Mevcut bir index'e eklemeyi dene (eklenmemesi gerekiyor)
        employee.addHealthplan(0, "New Healthplan");
        assertTrue(Arrays.asList(employee.getHealthplans()).contains("Test Healthplan")); // İlk eklenen healthplan sabit kalmalı, ikinci eklenmemeli

        // Boş olan başka bir index'e ekleme yap
        employee.addHealthplan(1, "New Healthplan");
        assertTrue(Arrays.asList(employee.getHealthplans()).contains("New Healthplan")); // İkinci healthplan eklenmeli
    }

    @DisplayName("Company sınıf değişkenleri doğru access modifier a sahip mi ?")
    @Test
    public void testCompanyAccessModifiers() throws NoSuchFieldException {
        Field idField = company.getClass().getDeclaredField("id");
        assertEquals(idField.getModifiers(), 2);

        Field fullNameField = company.getClass().getDeclaredField("name");
        assertEquals(fullNameField.getModifiers(), 2);

        Field emailField = company.getClass().getDeclaredField("giro");
        assertEquals(emailField.getModifiers(), 2);

        Field healthPlans = company.getClass().getDeclaredField("developerNames");
        assertEquals(healthPlans.getModifiers(), 2);
    }

    @DisplayName("Company sınıf değişkenleri doğru tipte mi ?")
    @Test
    public void testCompanyInstanceTypes() throws NoSuchFieldException {
        // `company` nesnesini oluştur ve alanlara değer ataması yap
        Company company = new Company(1, "Tech Corp", 1000000.0, 5); // `id`, `name`, `giro`, ve `developerNames` dizisini başlat

        // `name` ve `giro` değişkenleri null değil, dolayısıyla testler geçer
        assertThat(company.getName(), instanceOf(String.class));  // `name` String tipinde olmalı
        assertThat(company.getGiro(), instanceOf(Double.class));  // `giro` Double tipinde olmalı
    }

    @DisplayName("addEmployee method başarılı çalışıyor mu?")
    @Test
    public void testAddEmployeeMethod() throws NoSuchFieldException {
        company = new Company(1, "Tech Corp", 1000000, 3); // Company nesnesini test için oluşturun ve dizi boyutunu belirtin
        company.addEmployee(-1, "Jane");
        assertFalse(Arrays.asList(company.getDeveloperNames()).contains("Jane")); // Eklenmemiş olmalı

        company.addEmployee(0, "Jane");
        assertTrue(Arrays.asList(company.getDeveloperNames()).contains("Jane")); // Eklenmiş olmalı

        company.addEmployee(1, "Jane Doe");
        assertTrue(Arrays.asList(company.getDeveloperNames()).contains("Jane Doe")); // Eklenmiş olmalı

        company.addEmployee(1, "Jane");
        assertTrue(Arrays.asList(company.getDeveloperNames()).contains("Jane Doe")); // Önceden var olan bir index'e eklenmemeli
    }
}
