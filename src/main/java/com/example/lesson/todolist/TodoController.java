package com.example.lesson.todolist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TodoController {

    @GetMapping("/todosSample")
    public String getTodoSample(){
        return "walk the dog";
    }

    @GetMapping("/todos")
    public ModelAndView getTodo(){
        return new ModelAndView("todos/index");
    }

}
