package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "company_seq")
    @SequenceGenerator(name = "company_seq",
    sequenceName = "company_seq",
            allocationSize = 1)
    private Long id;

    @Column(length = 100000,name = "company_name")
    private String companyName;

    @Column(length = 100000,name = "located_country")
    private String locatedCountry;

    @OneToMany(cascade = {MERGE, REFRESH, DETACH, REMOVE, PERSIST},mappedBy = "company")
    private List<Course> courses;
    public void addCourse(Course course){
        if (courses == null){
            courses = new ArrayList<>();
        }
        courses.add(course);
    }

    @OneToMany(cascade = {ALL},fetch = FetchType.LAZY,mappedBy = "company")
    private List<Group> groups;



//    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY,mappedBy = "company")
//    private List<Group> groups;

//    public void addGroup(Group group){
//        if (groups==null){
//            groups=new ArrayList<>();
//        }
//        groups.add(group);
//    }

}
