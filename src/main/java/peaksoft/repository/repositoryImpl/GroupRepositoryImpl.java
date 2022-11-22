package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.repository.GroupRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class GroupRepositoryImpl implements GroupRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveGroup(Long id,Group group) {
        Course course = entityManager.find(Course.class,id);
        course.addGroups(group);
        group.addCourses(course);

        entityManager.merge(group);

    }

    @Override
    public void updateGroup(Group group,Long id) {
        Group group1 = entityManager.find(Group.class,id);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setImage(group.getImage());
        entityManager.merge(group1);
    }

    @Override
    public List<Group> getAllGroups() {
        return entityManager.createQuery("from Group",Group.class).getResultList();
    }

    @Override
    public Group getGroupById(Long id) {
        return entityManager.find(Group.class,id);
    }

    @Override
    public void deleteGroup(Long id) {
        System.out.println("deleteGroupRepository");
        entityManager.remove(entityManager.find(Group.class, id));
    }
}
