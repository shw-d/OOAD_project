package StudentManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import StudentManagement.dao.*;
import StudentManagement.daoimpl.*;
import StudentManagement.dto.*;

public class StudentManagementUI extends JFrame {

    private JTextField studentIdField, studentNameField, studentAgeField, courseIdField, courseNameField, courseFeeField;
    private JButton addStudentButton, updateStudentButton, deleteStudentButton, addCourseButton, updateCourseButton, deleteCourseButton;
    private JTextArea studentTextArea, courseTextArea;
    private StudentDao studentDao;
    private CourseDao courseDao;

    public StudentManagementUI() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        studentDao = new StudentDaoImpl();
        courseDao = new CourseDaoImpl();

        // Student panel
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BoxLayout(studentPanel, BoxLayout.Y_AXIS));
        studentPanel.setBorder(BorderFactory.createTitledBorder("Student"));

        studentIdField = new JTextField(10);
        studentNameField = new JTextField(10);
        studentAgeField = new JTextField(10);
        addStudentButton = new JButton("Add Student");
        updateStudentButton = new JButton("Update Student");
        deleteStudentButton = new JButton("Delete Student");
        studentTextArea = new JTextArea(10, 30);
        studentTextArea.setEditable(false);

        studentPanel.add(new JLabel("ID:"));
        studentPanel.add(studentIdField);
        studentPanel.add(new JLabel("Name:"));
        studentPanel.add(studentNameField);
        studentPanel.add(new JLabel("Age:"));
        studentPanel.add(studentAgeField);
        studentPanel.add(addStudentButton);
        studentPanel.add(updateStudentButton);
        studentPanel.add(deleteStudentButton);
        studentPanel.add(new JScrollPane(studentTextArea));

        // Course panel
        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        coursePanel.setBorder(BorderFactory.createTitledBorder("Course"));

        courseIdField = new JTextField(10);
        courseNameField = new JTextField(10);
        courseFeeField = new JTextField(10);
        addCourseButton = new JButton("Add Course");
        updateCourseButton = new JButton("Update Course");
        deleteCourseButton = new JButton("Delete Course");
        courseTextArea = new JTextArea(10, 30);
        courseTextArea.setEditable(false);

        coursePanel.add(new JLabel("ID:"));
        coursePanel.add(courseIdField);
        coursePanel.add(new JLabel("Name:"));
        coursePanel.add(courseNameField);
        coursePanel.add(new JLabel("Fee:"));
        coursePanel.add(courseFeeField);
        coursePanel.add(addCourseButton);
        coursePanel.add(updateCourseButton);
        coursePanel.add(deleteCourseButton);
        coursePanel.add(new JScrollPane(courseTextArea));

        // Add panels to frame
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.add(studentPanel);
        mainPanel.add(coursePanel);
        add(mainPanel);

        // Action listeners
        addStudentButton.addActionListener(e -> addStudent());
        updateStudentButton.addActionListener(e -> updateStudent());
        deleteStudentButton.addActionListener(e -> deleteStudent());
        addCourseButton.addActionListener(e -> addCourse());
        updateCourseButton.addActionListener(e -> updateCourse());
        deleteCourseButton.addActionListener(e -> deleteCourse());
    }

    private void addStudent() {
        try {
            int id = Integer.parseInt(studentIdField.getText());
            String name = studentNameField.getText();
            int age = Integer.parseInt(studentAgeField.getText());

            studentDao.add_student(new Student(id, name, age, null));
            studentTextArea.setText("Student added:\nID: " + id + "\nName: " + name + "\nAge: " + age);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values.");
        }
    }

    private void updateStudent() {
        try {
            int id = Integer.parseInt(studentIdField.getText());
            String name = studentNameField.getText();
            int age = Integer.parseInt(studentAgeField.getText());

            studentDao.update_student(new Student(id, name, age, null));
            studentTextArea.setText("Student updated:\nID: " + id + "\nName: " + name + "\nAge: " + age);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values.");
        }
    }

    private void deleteStudent() {
        try {
            int id = Integer.parseInt(studentIdField.getText());
            studentDao.delete_student(id);
            studentTextArea.setText("Student deleted:\nID: " + id);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values.");
        }
    }

    private void addCourse() {
        try {
            int id = Integer.parseInt(courseIdField.getText());
            String name = courseNameField.getText();
            double fee = Double.parseDouble(courseFeeField.getText());

            courseDao.add_course(new Course(id, name, fee));
            courseTextArea.setText("Course added:\nID: " + id + "\nName: " + name + "\nFee: " + fee);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values.");
        }
    }

    private void updateCourse() {
        try {
            int id = Integer.parseInt(courseIdField.getText());
            String name = courseNameField.getText();
            double fee = Double.parseDouble(courseFeeField.getText());

            courseDao.update_course(new Course(id, name, fee));
            courseTextArea.setText("Course updated:\nID: " + id + "\nName: " + name + "\nFee: " + fee);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values.");
        }
    }

    private void deleteCourse() {
        try {
            int id = Integer.parseInt(courseIdField.getText());
            courseDao.delete_course(id);
            courseTextArea.setText("Course deleted:\nID: " + id);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementUI ui = new StudentManagementUI();
            ui.setVisible(true);
        });
    }
}

