import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public void decrementCapacity() {
        if (capacity > 0) {
            capacity--;
        }
    }

    public void incrementCapacity() {
        capacity++;
    }

    @Override
    public String toString() {
        return "Course: " + code + " - " + title + "\nDescription: " + description + "\nCapacity: "
                + capacity + "\nSchedule: " + schedule + "\n";
    }
}

class Student {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        if (!registeredCourses.contains(course) && course.getCapacity() > 0) {
            registeredCourses.add(course);
            course.decrementCapacity();
            System.out.println(name + " has successfully registered for " + course.getTitle());
        } else if (registeredCourses.contains(course)) {
            System.out.println(name + " is already registered for " + course.getTitle());
        } else {
            System.out.println("Sorry, " + course.getTitle() + " is already full.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.incrementCapacity();
            System.out.println(name + " has dropped " + course.getTitle());
        } else {
            System.out.println(name + " is not registered for " + course.getTitle());
        }
    }

    @Override
    public String toString() {
        StringBuilder coursesInfo = new StringBuilder("Registered Courses:\n");
        for (Course course : registeredCourses) {
            coursesInfo.append(course.toString()).append("\n");
        }
        return "Student ID: " + studentID + "\nName: " + name + "\n" + coursesInfo;
    }
}

public class UniversitySystem {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CSE101", "Introduction to Computer Science", "Basic computer science concepts", 50, "Mon, Wed 9:00 AM - 10:30 AM"));
        courses.add(new Course("MAT201", "Calculus I", "Limits, derivatives, and integrals", 40, "Tue, Thu 11:00 AM - 12:30 PM"));
        courses.add(new Course("ENG301", "Advanced English Composition", "Developing advanced writing skills", 30, "Mon, Wed 1:30 PM - 3:00 PM"));

        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "John Doe"));
        students.add(new Student(2, "Jane Smith"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nUniversity Management System");
            System.out.println("1. Display Course Listing");
            System.out.println("2. Display Student Information");
            System.out.println("3. Register Student for Course");
            System.out.println("4. Drop Course for Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayCourseListing(courses);
                    break;
                case 2:
                    displayStudentInformation(students);
                    break;
                case 3:
                    registerStudentForCourse(students, courses);
                    break;
                case 4:
                    dropCourseForStudent(students, courses);
                    break;
                case 5:
                    System.out.println("Exiting University Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    private static void displayCourseListing(List<Course> courses) {
        System.out.println("Available Courses:\n");
        for (Course course : courses) {
            System.out.println(course.toString());
        }
    }

    private static void displayStudentInformation(List<Student> students) {
        System.out.println("Student Information:\n");
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    private static void registerStudentForCourse(List<Student> students, List<Course> courses) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Student ID:");
        int studentID = scanner.nextInt();

        System.out.println("Enter Course Code:");
        String courseCode = scanner.next();

        Student student = findStudentByID(students, studentID);
        Course course = findCourseByCode(courses, courseCode);

        if (student != null && course != null) {
            student.registerCourse(course);
        } else {
            System.out.println("Invalid Student ID or Course Code. Please check and try again.");
        }
    }

    private static void dropCourseForStudent(List<Student> students, List<Course> courses) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Student ID:");
        int studentID = scanner.nextInt();

        System.out.println("Enter Course Code:");
        String courseCode = scanner.next();

        Student student = findStudentByID(students, studentID);
        Course course = findCourseByCode(courses, courseCode);

        if (student != null && course != null) {
            student.dropCourse(course);
        } else {
            System.out.println("Invalid Student ID or Course Code. Please check and try again.");
        }
    }

    private static Student findStudentByID(List<Student> students, int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(List<Course> courses, String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
}