package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "course_seq")
    @SequenceGenerator(name = "course_seq",
    sequenceName = "course_seq",
    allocationSize = 1)
    private Long id;
    @Column(length = 100000,name = "course_name")
    private String courseName;

    private String duration;
    @Column(length = 100000)
    private String description;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
    private Company company;

    @ManyToMany(cascade = {MERGE,DETACH,PERSIST,REFRESH,REMOVE},fetch = FetchType.LAZY,mappedBy = "courses")
    private List<Group> groups;
    //
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "courses")
    private List<Instructor> instructors;

    @OneToMany(cascade = {ALL}, fetch = FetchType.LAZY,mappedBy = "course")
    private List<Lesson> lessons;

    public void addGroups(Group group){
        if (groups == null){
            groups = new ArrayList<>();
        }
        groups.add(group);
    }

    public void addInstructor(Instructor instructor){
        if (instructors == null){
            instructors = new ArrayList<>();
        }
        instructors.add(instructor);
    }



    //
    public void addLesson(Lesson lesson){
        if (lessons == null){
            lessons = new ArrayList<>();
        }
        lessons.add(lesson);
    }





}
