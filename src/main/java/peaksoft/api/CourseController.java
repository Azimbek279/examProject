package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.model.Student;
import peaksoft.service.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final InstructorService instructorService;
    private final StudentService studentService;
    private final CompanyService companyService;

    private final GroupService groupService;

    @Autowired
    public CourseController(CourseService courseService, InstructorService instructorService, StudentService studentService, CompanyService companyService, GroupService groupService) {
        this.courseService = courseService;
        this.instructorService = instructorService;
        this.studentService = studentService;
        this.companyService = companyService;
        this.groupService = groupService;
    }

    @GetMapping("/courses/{id}")
    private String getAllCourses(@PathVariable Long id, Model model,
                                 @ModelAttribute("instructor") Instructor instructor,
                                 @ModelAttribute("student") Student student,
                                 @ModelAttribute("group") Group group ) {
        model.addAttribute("courses", courseService.getAllCourses(id));
        model.addAttribute("companyId", id);
        model.addAttribute("groups",groupService.getAllGroupses(id));
        List<Instructor> instructors = instructorService.getAllInstructors(id);
        model.addAttribute("instructors", instructors);
        model.addAttribute("students", studentService.getStudentById(id));

        return "course/courses";
    }

    @GetMapping("/{id}/addCourse")
    private String addCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("companyId", id);
        return "course/addCourse";
    }

    @PostMapping("/{id}/saveCourse")
    private String saveCourse(@ModelAttribute("course") Course course,
                              @PathVariable Long id) throws IOException {
        courseService.saveCourse(course, id);
        System.out.println(course);
        return "redirect:/courses/courses/ " + id;
    }

    @GetMapping("/edit/{id}")
    private String updateCourse(@PathVariable("id") Long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("companyId", course.getCompany().getId());
        return "/course/updateCourse";
    }

    @PostMapping("{id}/{courseId}/update")
    private String saveUpdateCourse(@PathVariable("courseId") Long courseId,
                                    @PathVariable("id") Long id,
                                    @ModelAttribute("course") Course course) {
        courseService.updateCourse(courseId, course);
        return "redirect:/courses/courses/ " + id;
    }

    @PostMapping("/{id}/{compId}")
    private String deleteCourse(@PathVariable("id") Long id,
                                @PathVariable("compId") Long compId) {
        courseService.deleteCourse(id);
        return "redirect:/courses/courses/ " + compId;
    }

    @PostMapping("{companyId}/{courseId}/assignInstructor")
    private String assignInstructor(@PathVariable("companyId") Long comId,
                                    @PathVariable("courseId") Long courseId,
                                    @ModelAttribute("instructor") Instructor instructor) {
        instructorService.assignedInstructorToCourse(courseId,instructor.getId());
        return "redirect:/courses/courses/ " + comId;
    }

    @PostMapping("/{courseId}/assignGroup")
    private String assignGroup(@PathVariable("courseId")Long courseId,
                               @ModelAttribute("group") Group group) throws IOException {
        System.out.println(group);
        Long id = group.getId();
        groupService.assigningGroup(courseId,id);
        return "redirect:/groups/"+courseId;
    }

}
