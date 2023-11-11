package com.example.cruddemo;

import com.example.cruddemo.dao.StudentDAO;
import com.example.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);

    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
//            createStudent(studentDAO);
            createMultipleStudents(studentDAO);
//            readStudent(studentDAO);
//            queryForStudents(studentDAO);
//            queryForStudentsByLastName(studentDAO);
//            updateStudent(studentDAO);
//            deleteStudent(studentDAO);
//            deleteAllStudents(studentDAO);

        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        int rowsDeleted = studentDAO.deleteAll();
        System.out.println("Deleted rows count: " + rowsDeleted);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int id = 3;
        System.out.println("Deleting student id: " + id);
        studentDAO.delete(id);
    }

    private void updateStudent(StudentDAO studentDAO) {
        int studentId = 1;
        System.out.println("Getting student with id: " + studentId);
        Student myStudent = studentDAO.findById(studentId);

        System.out.println("Updating student...");
        myStudent.setFirstName("Scooby");

        studentDAO.update(myStudent);
        System.out.println("Updated student: " + myStudent);
    }

    private void queryForStudentsByLastName(StudentDAO studentDAO) {
        List<Student> students = studentDAO.findByLastName("Duck");
        students.forEach(System.out::println);
    }

    private void queryForStudents(StudentDAO studentDAO) {
        List<Student> students = studentDAO.findAll();
        students.forEach(System.out::println);
    }

    private void createMultipleStudents(StudentDAO studentDAO){

        System.out.println("Creating 3 Student objects...");
        Student tempStudent1 = new Student("John", "Doe", "john@example.com");
        Student tempStudent2 = new Student("Mary", "Public", "mary@example.com");
        Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@example.com");

        System.out.println("Saving the students...");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);
    }
    private void createStudent(StudentDAO studentDAO){
        System.out.println("Creating new Student object...");
        Student tempStudent = new Student("Paul", "Doe", "paul@example.com");

        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);

        System.out.println("Student saved. Generated ID: " + tempStudent.getId());
    }

    private void readStudent(StudentDAO studentDAO){
        System.out.println("Creating new Student object...");
        Student tempStudent = new Student("Duffy", "Duck", "duffy@example.com");

        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);

        System.out.println("Student saved. Generated ID: " + tempStudent.getId());

        System.out.println("Retrieving student with id: " + tempStudent.getId());
        Student myStudent = studentDAO.findById(tempStudent.getId());

        System.out.println("Found the student: " + myStudent);
    }
}

