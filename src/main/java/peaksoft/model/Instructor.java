package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_seq")
    @SequenceGenerator(name = "instructor_seq",
            sequenceName = "instructor_seq",
            allocationSize = 1)
    private Long id;
    @Column(length = 100000, name = "first_Name")
    private String firstName;
    @Column(length = 100000, name = "last_Name")
    private String lastName;
    @Column(length = 100000, name = "phone_number")
    private String phoneNumber;

    private String email;

    private String specialization;

    private int count = 0;

    public void plus(){
        count++;
    }

    public void minus(){
        count--;
    }

    @ManyToOne(cascade = {MERGE, DETACH, PERSIST, REFRESH}, fetch = FetchType.EAGER)
    private Course courses;

    public Course getCourse() {
        return courses;
    }

    public void setCourse(Course course) {
        this.courses = course;
    }



}


