package com.akokko.controller;

import com.akokko.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping("/quick1")
    @ResponseBody
    public User quick1() {
        User user = new User();
        user.setName("zhangsan");
        user.setAge(18);
        return user;
    }

    @RequestMapping("/quick2")
    @ResponseBody
    public void quick2(@RequestBody List<User> userList) {
        System.out.println(userList);
    }

    @RequestMapping("/quick3")
    @ResponseBody
    public void quick3(String username, MultipartFile[] fileUpload) throws IOException {
        System.out.println(username);
        for (MultipartFile multipartFile : fileUpload) {
            String originalFilename = multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File("D:\\test\\" + originalFilename));
        }
    }
}
