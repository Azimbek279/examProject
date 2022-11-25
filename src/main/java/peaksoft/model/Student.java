package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.model.enums.StudyFormat;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "student_seq")
    @SequenceGenerator(name = "student_seq",
    sequenceName = "student_seq",
    allocationSize = 1)
    private Long id;
    @Column(length = 100000,name = "first_Name")
    private String firstName;
    @Column(length = 100000,name = "last_Name")
    private String lastName;
    @Column(length = 100000,name = "phone_number")
    private String phoneNumber;

    private String email;
    @Column(length = 100000,name = "study_format")
    private String studyFormat;

    @ManyToOne(cascade = {MERGE,DETACH,PERSIST,REFRESH},fetch = FetchType.EAGER)
    private Group group;
}
