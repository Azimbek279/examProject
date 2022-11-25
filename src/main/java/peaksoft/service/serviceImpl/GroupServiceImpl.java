package peaksoft.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Group;
import peaksoft.repository.GroupRepository;
import peaksoft.service.GroupService;

import java.io.IOException;
import java.util.List;
@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAllGroupses(Long id) {
        return groupRepository.getAllGroupses(id);
    }

    @Override
    public void saveGroup(Long id,Group group) {
        groupRepository.saveGroup(id,group);
    }

    @Override
    public void updateGroup(Group group,Long id) {
        groupRepository.updateGroup(group,id);
    }

    @Override
    public List<Group> getAllGroups(Long id) {
        return groupRepository.getAllGroups(id);
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.getGroupById(id);
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteGroup(id);
    }

    @Override
    public void assigningGroup(Long courseId, Long groupId) throws IOException {
        groupRepository.assigningGroup(courseId,groupId);
    }
}
