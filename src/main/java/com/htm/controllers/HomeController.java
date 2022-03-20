/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htm.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.htm.pojo.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Manax
 */
@Controller

public class HomeController {
    @Autowired
    private Cloudinary cloudinary;
    
    @RequestMapping("/")
    public String index(Model model,
            @RequestParam(value = "first_name", required = false) String firstName,
            @RequestParam(value = "last_name", required = false) String lastName){
            List<String>cates = new ArrayList<>();
            cates.add("Mobile");
            cates.add("Laptop");
            cates.add("Desktop"); 
            cates.add("Tablet");
            
          if (firstName != null && lastName !=null)
                model.addAttribute("name", String.format("%s %s", firstName, lastName));
          else
              model.addAttribute("name", "Man");
          
              model.addAttribute("categories", cates);
              model.addAttribute("user", new User());
          return "index";
    }
    @PostMapping("/upload")
    public String upload(@ModelAttribute(value = "user")User user){
        try {
            Map res = this.cloudinary.uploader() .upload(user.getAvatar().getBytes(),  ObjectUtils.asMap("resource_type","auto"));
            System.out.println(res);// secure_url
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/login?path=test";
    }
    
    
    @RequestMapping("/hello/{name}")
    public String test(Model model,
            @PathVariable(name = "name") String name){
        model.addAttribute("name", name);
        return  "index";
    }
    
    @GetMapping("/login")
    public String loginView(Model model){
        model.addAttribute("user", new User());
        return "login";
    }
    
    @PostMapping("/login")
    public String loginHandler(Model model,
        @ModelAttribute(value = "user")User user){
        
      if(user.getUsername().equals("admin")&& user.getPassword().equals("123"))
          model.addAttribute("msg", "SUCCESSFUL");
      else
          model.addAttribute("msg", "FAILED");
      
      return  "login";
    }
}
