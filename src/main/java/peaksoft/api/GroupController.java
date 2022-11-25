package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Group;
import peaksoft.model.Student;
import peaksoft.service.CompanyService;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;

@Controller
//@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;
//    private final CompanyService companyService;
//    private final StudentService studentService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
//        this.companyService = companyService;
//        this.studentService = studentService;
    }

    @GetMapping("/groups/{id}")
    public String  getAlGroups(@PathVariable Long id, Model model) {
        model.addAttribute("groups", groupService.getAllGroups(id));
        model.addAttribute("courseId",id);
        return "/group/groups";
    }


    @GetMapping("/{id}/addGroup")
    public String addGroup(@PathVariable Long id, Model model) {
        model.addAttribute("group", new Group());
        model.addAttribute("courseId", id);
        return "/group/addGroup";
    }


    @PostMapping("/{id}/saveGroup")
    public String saveGroup(@ModelAttribute("group") Group group,
                            @PathVariable Long id){
        groupService.saveGroup(id,group);
        return "redirect:/groups/{id}";
    }


    @GetMapping("/updateGroup/{id}/{courseId}")
    public String updateGroup(@PathVariable("id")Long id , Model model,@PathVariable("courseId")Long courseId){
        Group group = groupService.getGroupById(id);
        model.addAttribute("group",group);
        model.addAttribute("courseId",courseId);
        return "/group/updateGroup";
    }


    @PostMapping("/{courseId}/{id}/saveUpdateGroup")
    public String saveUpdateGroup(@PathVariable("courseId") Long courseId,
                                  @PathVariable("id") Long id,
                                  @ModelAttribute("group") Group group) {
        groupService.updateGroup(group, id);
        return "redirect:/groups/" + courseId;
    }

    @GetMapping("/{courseId}/{id}/deleteGroup")
    public String deleteGroup(@PathVariable("id")Long id ,
                              @PathVariable("courseId")Long courseId){
        groupService.deleteGroup(id);
        return "redirect:/groups/"+courseId;
    }


}
