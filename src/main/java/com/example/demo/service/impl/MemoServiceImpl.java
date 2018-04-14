package com.example.demo.service.impl;

import com.example.demo.entity.Memo;
import com.example.demo.repository.MemoRepository;
import com.example.demo.service.MemoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MemoServiceImpl implements MemoService {

    final private MemoRepository memoRepository;

    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public Page<Memo> findAll(Pageable page) {
        return memoRepository.findAll(page);
    }

}
