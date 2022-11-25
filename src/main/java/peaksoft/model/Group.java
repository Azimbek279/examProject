package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity

@Getter
@Setter
@NoArgsConstructor
@Table(name = "groups")
public class Group {

    @Id
    @SequenceGenerator(name = "group_seq", sequenceName = "group_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "group_seq")
    private Long id;

    @Column(length = 100000,name = "group_Name")
    private String groupName;

    @Column(name = "date_of_start")
    private String dateOfStart;

    private String image;

    @ManyToMany(cascade = {MERGE,DETACH,REFRESH},fetch = FetchType.LAZY)
    private List<Course> courses;
     //
    public void addCourses(Course course){
        if (courses == null){
            courses = new ArrayList<>();
        }
        courses.add(course);
    }

    @OneToMany(cascade = {ALL},fetch = FetchType.EAGER,mappedBy = "group")
    private List<Student> students;
    //
    public void addStudent(Student student){
        if (students == null){
            students = new ArrayList<>();
        }
        students.add(student);
    }

    @ManyToOne(cascade = {MERGE, DETACH,PERSIST, REFRESH},fetch = FetchType.EAGER)
    private Company company;


}
