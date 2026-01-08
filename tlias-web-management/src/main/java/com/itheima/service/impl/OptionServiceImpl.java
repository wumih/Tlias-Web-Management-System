package com.itheima.service.impl;

import com.itheima.mapper.OptionMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.Option;
import com.itheima.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionMapper optionMapper;

    @Override
    public List<Emp> list(Integer job) {
        return optionMapper.list(job);
    }
}

