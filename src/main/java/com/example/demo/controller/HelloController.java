package com.example.demo.controller;

import com.example.demo.model.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class HelloController {

    @GetMapping(path = {"", "hello"})
    public String greeting(Model model) {
        model.addAttribute("message", "HELLO WORLD");
        model.addAttribute("date", LocalDateTime.of(2018, 4, 13, 11, 12, 13));
        List<ItemDto> items = Arrays.asList(
                ItemDto.of(1L, "うまい棒", 10, LocalDateTime.now()),
                ItemDto.of(2L, "ポテトフライ", 30, LocalDateTime.now()),
                ItemDto.of(3L, "きなこ棒", 10, LocalDateTime.now()),
                ItemDto.of(4L, "いきいきビール", 40, LocalDateTime.now())
        );
        model.addAttribute("items", items);
        return "hello";
    }

    @GetMapping(path = "checkout")
    public String checkout() {
        return "checkout";
    }
}
