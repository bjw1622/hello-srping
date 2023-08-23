package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "asdasdd");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    // ResponseBody => html에 나오는 body태그가 아닌, http response body부분에 직접 넣어주겠다
    // view가 존재하지 않고 데이터를 그대로 내려준다
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    // 이러한 형식의 json방식이 채택 됨
    // 기본이 json 방식으로 반환(xml 쓰이지 않음)
    // @ResponseBody 어노테이션이 있을때는 뷰 리졸버에게 넘기는 것이 아니라 HttpMessageConverter에게 넘기게 된다
    // 이 때 객체의 경우 JsonConverter에게
    // 문자열인 경우 StringConverter에게
    public Hello helloApi(@RequestParam("name") String name) {
        // command + shift + enter => 자동완성
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
