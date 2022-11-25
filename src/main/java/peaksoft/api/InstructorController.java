package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Instructor;
import peaksoft.service.CourseService;
import peaksoft.service.InstructorService;

import java.io.IOException;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    private final CourseService courseService;

    @Autowired
    public InstructorController(InstructorService instructorService, CourseService courseService) {
        this.instructorService = instructorService;
        this.courseService = courseService;
    }

    @GetMapping("/instructors/{id}")
    public String getAllInstructors(@PathVariable Long id, Model model){
        model.addAttribute("instructors",instructorService.getAllInstructors(id));
        model.addAttribute("companyId", courseService.getCourseById(id).getCompany().getId());
        model.addAttribute("courseId", id);
        return "/instructor/instructors";
    }

    @GetMapping("/{id}/addInstructor")
    private String addInstructor(@PathVariable Long id, Model model) {
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("courseId", id);
        return "instructor/addInstructor";
    }

    @PostMapping("/{id}/saveInstructor")
    private String saveInstructor(@ModelAttribute("newInstructor") Instructor instructor,
                              @PathVariable Long id) throws IOException {
        instructorService.saveInstructor(id, instructor);
        return "redirect:/instructors/instructors/" + id;
    }

    @GetMapping("/updateInstructor/{id}")
    public String updateInstructor(@PathVariable("id") Long id, Model model) {
        Instructor instructor = instructorService.getInstructorById(id);
        model.addAttribute("instructor", instructor);
        model.addAttribute("courseId", instructor.getCourse().getId());
        return "/instructor/updateInstructor";
    }

    @PostMapping("/{courseId}/{id}/saveUpdateInstructor")
    public String saveUpdateInstructor(@PathVariable("courseId") Long courseId,
                                       @PathVariable("id") Long id,
                                       @ModelAttribute("instructor") Instructor instructor) {
        instructorService.updateInstructor(instructor,id);
        return "redirect:/instructors/instructors/" +courseId;
    }
    @GetMapping("/{courseId}/{id}/deleteInstructor")
    public String deleteInstructor(@PathVariable("id") Long id, @PathVariable("courseId") Long courseId) {
        instructorService.deleteInstructor(id);
        return "redirect:/instructors/instructors/" + courseId;
    }

    @PostMapping("/{courseId}/{companyId}/assignInstructor")
    public String assignInstructor(@PathVariable("courseId") Long courseId,
                                   @PathVariable("companyId") Long companyId,
                                   @ModelAttribute("instructor") Instructor instructor){
        instructorService.assignedInstructorToCourse(courseId, instructor.getId());
        return "redirect:/instructors/" + companyId;
    }
}
