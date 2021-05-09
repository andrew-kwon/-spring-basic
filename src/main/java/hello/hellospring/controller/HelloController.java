package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 웹 어플리케이션에서 "/hello"라고 치고 들어오면, 이 메서드를 실행함
    @GetMapping("hello")
    public String hello(Model model) { // model이라는 것이 넘어오는데, 스프링이 model을 만들어서 넣어줌
       model.addAttribute("data", "hello!!");
        return "hello"; // resources 폴더에 있는 hello.html으로 가서 렌더링 하라는 의미 (model을 이 화면에 넘기면서, 이 화면을 실행시키라는 의미)
    }

    @GetMapping("hello-mvc")
    // 외부에서 파라미터로 받는 경우 --> @RequestParam 사용 -->
    // model에 담으면 view에서 랜더링 할 때 사용
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        // 파라미터로 넘어온 name을 넘김 (인자 중 앞에있는 "name"이 key임)
        model.addAttribute("name", name);
        // hello-templete.html로 넘어감
        return "hello-templete";
    }

    @GetMapping("hello-string")
    @ResponseBody // html에 나오는 body가 아닌, HTTP의 body부에 이 데이터를 직접 넣겠다는 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello olive" -> 요청한 클라이언트에 이 문자 그대로 전달함
    }

    @GetMapping("hello-api")
    @ResponseBody // html에 나오는 body가 아닌, HTTP의 body부에 이 데이터를 직접 넣겠다는 의미
    // @ResponseBody - 객체가 전달되면, JSON 방식으로 데이터를 만들어서 HTTP 응답으로 반환하겠다는 것이 기본 정
    public Hello helloApi(@RequestParam("name") String name) {
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
