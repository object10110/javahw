package org.samarskii.controllers;

import org.samarskii.utils.Calculator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class CalculatorController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model, @RequestParam String a, @RequestParam String b,
                        @RequestParam String op)//что бы передать + в url браузера нужно писать %2B
    {
        BigDecimal _a = BigDecimal.valueOf(Float.parseFloat(a));
        BigDecimal _b = BigDecimal.valueOf(Float.parseFloat(b));

        //в строке браузера + воспринимается как пробел
        if(op.equals(" "))
        {
            op = "+";
        }

        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("op", op);
        model.addAttribute("result", Calculator.Calc(_a,_b,op));

        return "/calculator/index";
    }
}
