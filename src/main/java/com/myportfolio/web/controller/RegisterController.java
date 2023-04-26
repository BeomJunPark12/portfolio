package com.myportfolio.web.controller;

import com.myportfolio.web.domain.UserDto;
import com.myportfolio.web.service.UserService;
import com.myportfolio.web.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserService userService;

    @InitBinder
    public void validate(WebDataBinder binder) {
//        binder.setValidator(new UserValidator()); // UserValidator를 WebDataBinder의 로컬 validator로 등록
//        binder.addValidators(new UserValidator()); // UserValidator를 WebDataBinder의 글로벌 validator로 등록
        List<Validator> validators = binder.getValidators();
        System.out.println("validators = " + validators);
    }

    @GetMapping("/add")
    public String register() {
        return "register/registerForm";
    }

    @PostMapping("/add")
    public String save(@Valid UserDto userDto, BindingResult result, Model model) throws Exception {
        // 수동 검증 - validator를 직접 생성하고 validate()를 직접 호출
//        UserValidator userValidator = new UserValidator();
//        userValidator.validate(userDto, errors);
        System.out.println("result = " + result);
        // user객체를 검증한 결과 에러가 있으면 registerForm을 이용해서 에러를 보여주기
        if (result.hasErrors()) {
            return "register/registerForm";
        }


//        //1. 유효성 검사
//        if (!isValid(userDto)) {
//            String msg = URLEncoder.encode("id를 잘못 입력하셨습니다.", "utf-8");
//            model.addAttribute("msg", msg);
//
//            return "redirect:/register/add";
//        }
//          "register/registerInfo";

        userService.saveUser(userDto);

        return "login/loginForm";
    }

    private boolean isValid(UserDto userDto) {
        return true;
    }
}
