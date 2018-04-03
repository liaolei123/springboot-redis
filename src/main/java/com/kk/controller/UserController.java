package com.kk.controller;

import com.kk.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    /**
     * @Cacheable 是用来声明方法是可缓存的，将结果存储到缓存中以便后续使用相同参数调用时不需执行实际的方法，直接从缓存中取值
     *  value  缓存的名称    key 缓存的id  condition 缓存的条件
     *  与 @Cacheable 不同的是使用 @CachePut 标注的方法在执行前，不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中。
     *  @CacheEvict 是用来标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。
     * @param name
     * @return
     */
    @RequestMapping("/hello")
    @Cacheable(value="helloCache")
    public String hello(String name) {
        System.out.println("没有走缓存！");
        return "hello "+name;
    }


    @RequestMapping(value = "/setSession")
    public Map<String, Object> setSession (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        request.getSession().setAttribute("message", request.getRequestURL());
        map.put("request Url", request.getRequestURL());
        return map;
    }

    @RequestMapping(value = "/getSession")
    public Object getSession (HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("message"));
        return map;
    }


    @RequestMapping(value = "/index")
    public String index (HttpServletRequest request){
        String msg="index content";
        Object user= request.getSession().getAttribute("user");
        if (user==null){
            msg="please login first！";
        }
        return msg;
    }

    @RequestMapping(value = "/login")
    public String login (HttpServletRequest request,String userName,String password){
        String msg="logon failure!";
        User user= new User();
        if (user!=null){
            request.getSession().setAttribute("user",user);
            msg="login successful!";
        }
        return msg;
    }




}
