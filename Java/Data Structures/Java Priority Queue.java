// https://www.hackerrank.com/challenges/java-priority-queue/problem
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Create the Student and Priorities classes here.
 */

import java.util.Comparator;
import java.util.PriorityQueue;

class Student {
    private int id;
    private String name;
    private double cgpa;

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCGPA() {
        return cgpa;
    }
}

class Priorities {
    public List<Student> getStudents(List<String> events) {
        PriorityQueue<Student> priorityQueue = new PriorityQueue<>(new customSort());

        for (String event : events) {
            String[] eventFields = event.split(" ");
            switch (eventFields[0]) {
                case "ENTER":
                    Student student = new Student(Integer.parseInt(eventFields[3]), eventFields[1], Double.parseDouble(eventFields[2]));
                    priorityQueue.add(student);
                    break;
                case "SERVED":
                    priorityQueue.poll();
                    break;
                default:
                    System.out.printf("Unexpected event type '%s' encountered.", eventFields[0]);
                    System.exit(1);
            }
        }

        List<Student> output = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            Student student = priorityQueue.poll();
            output.add(student);
        }
        return output;
    }
}

class customSort implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getCGPA() < o2.getCGPA()) {
            return 1;
        } else if (o1.getCGPA() > o2.getCGPA()) {
            return -1;
        } else {
            if (o1.getName().equals(o2.getName())) {
                if (o1.getID() < o2.getID()) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return o1.getName().compareTo(o2.getName());
            }
        }
    }
}

public class Solution {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();

    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());
        List<String> events = new ArrayList<>();

        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }

        List<Student> students = priorities.getStudents(events);

        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}