package tracker;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    public Map<Integer, Student> getAllStudents();
    public Student getStudent(int studentID);
    public void addStudent(Student student);
    public void updateStudent(Student student);
}
