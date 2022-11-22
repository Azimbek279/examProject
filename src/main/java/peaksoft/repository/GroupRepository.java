package peaksoft.repository;

import peaksoft.model.Company;
import peaksoft.model.Course;
import peaksoft.model.Group;

import java.util.List;

public interface GroupRepository {
    void saveGroup(Long id,Group group);//

    void updateGroup(Group group,Long id);//

    List<Group> getAllGroups();//

    Group getGroupById(Long id);//

    void deleteGroup(Long id);//
}
