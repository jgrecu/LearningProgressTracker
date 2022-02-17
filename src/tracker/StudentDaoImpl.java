package tracker;

import java.util.HashMap;
import java.util.Map;

public class StudentDaoImpl implements StudentDao {
    private int studentId = 10000;
    private final Map<Integer, Student> students;

    public StudentDaoImpl() {
        this.students = new HashMap<>();
    }

    @Override
    public Map<Integer, Student> getAllStudents() {
        return new HashMap<>(students);
    }

    @Override
    public Student getStudent(int studentID) {
        return students.get(studentID);
    }

    @Override
    public void addStudent(Student student) {
        // todo: check student is not already in list
        if (!students.containsKey(student.getId())) {
            students.put(student.getId(), student);
        }
    }

    @Override
    public void updateStudent(Student student) {
        students.put(student.getId(), student);
    }

    public int getNextStudentId() {
        return studentId++;
    }
}
