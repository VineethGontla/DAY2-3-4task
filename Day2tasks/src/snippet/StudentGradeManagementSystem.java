package snippet;

import java.util.*;

public class StudentGradeManagementSystem {

    static Map<String, Student> students = new HashMap<>();
    static Map<String, Course> courses = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Student Grade Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Assign Grade to Student");
            System.out.println("4. Calculate GPA of Student");
            System.out.println("5. Display Students");
            System.out.println("6. Display Courses");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    addCourse(scanner);
                    break;
                case 3:
                    assignGrade(scanner);
                    break;
                case 4:
                    calculateGPA(scanner);
                    break;
                case 5:
                    displayStudents();
                    break;
                case 6:
                    displayCourses();
                    break;
                case 7:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void addStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();
        
        // Check if student ID already exists
        if (students.containsKey(studentId)) {
            System.out.println("Student with this ID already exists.");
        } else {
            students.put(studentId, new Student(studentId, studentName));
            System.out.println("Student added successfully.");
        }
    }

    public static void addCourse(Scanner scanner) {
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();

        // Check if course code already exists
        if (courses.containsKey(courseCode)) {
            System.out.println("Course with this code already exists.");
        } else {
            courses.put(courseCode, new Course(courseCode, courseName));
            System.out.println("Course added successfully.");
        }
    }

    public static void assignGrade(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter Grade (0-100): ");
        double grade = scanner.nextDouble();
        scanner.nextLine();  // Consume newline left-over

        // Validate the grade
        if (grade < 0 || grade > 100) {
            System.out.println("Invalid grade. Please enter a grade between 0 and 100.");
            return;
        }

        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if (student != null && course != null) {
            Grade gradeObj = new Grade(course, grade);
            student.addGrade(gradeObj);
            System.out.println("Grade assigned successfully.");
        } else {
            System.out.println("Invalid Student ID or Course Code.");
        }
    }

    public static void calculateGPA(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        Student student = students.get(studentId);
        if (student != null) {
            double gpa = student.calculateGPA();
            System.out.println("GPA of " + student.getName() + ": " + gpa);
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void displayStudents() {
        System.out.println("Students:");
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student student : students.values()) {
                System.out.println(student.getId() + ": " + student.getName());
            }
        }
    }

    public static void displayCourses() {
        System.out.println("Courses:");
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            for (Course course : courses.values()) {
                System.out.println(course.getCourseCode() + ": " + course.getCourseName());
            }
        }
    }
}

class Student {
    private String id;
    private String name;
    private List<Grade> grades = new ArrayList<>();

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public double calculateGPA() {
        if (grades.isEmpty()) {
            return 0;  // No grades assigned
        }

        double totalPoints = 0;
        for (Grade grade : grades) {
            totalPoints += grade.getGrade();
        }
        return totalPoints / grades.size();
    }
}

class Course {
    private String courseCode;
    private String courseName;

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }
}

class Grade {
    private Course course;
    private double grade;

    public Grade(Course course, double grade) {
        this.course = course;
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public double getGrade() {
        return grade;
    }
}
