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
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    } //http://localhost:8080/hello-mvc?name=spring!!

    @GetMapping("hello-string")
    @ResponseBody // html 의 body부에 이 데이터를 직접 넣어주겠다는 뜻임
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //spring을 입력하면 "hello spring"이 갈 것임
        //탬플릿 엔진과의 파이는 view 이런게 없이 문자열이 그대로 내려감
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        hello.setArr(name);
        return hello;
    }

    static class Hello {
        private String name;
        private String[] arr = new String[2];

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String[] getArr() {
            return arr;
        }

        public void setArr(String str) {
            this.arr[0] = str;
            this.arr[1] = str;
        }
    }


}
