package peaksoft.service;

import peaksoft.model.Group;

import java.io.IOException;
import java.util.List;

public interface GroupService {
    List<Group> getAllGroupses(Long id);//
    void saveGroup(Long id,Group group);//

    void updateGroup(Group group,Long id);//

    List<Group> getAllGroups(Long id);//

    Group getGroupById(Long id);//

    void deleteGroup(Long id);//

    void assigningGroup(Long courseId,Long groupId) throws IOException;
}
