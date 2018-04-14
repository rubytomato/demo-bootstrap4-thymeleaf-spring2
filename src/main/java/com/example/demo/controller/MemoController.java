package com.example.demo.controller;

import com.example.demo.entity.Memo;
import com.example.demo.service.MemoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("memo")
public class MemoController {

    final private MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping
    public String list(Pageable page, Model model) {
        Page<Memo> memos = memoService.findAll(page);
        model.addAttribute("memos", memos);
        return "memo";
    }

}
