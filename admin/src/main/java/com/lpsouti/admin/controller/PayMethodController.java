package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.pay_method.PayMethodAddDTO;
import com.lpsouti.admin.dto.pay_method.PayMethodEditDTO;
import com.lpsouti.admin.service.PayMethodService;
import com.lpsouti.common.entity.PayMethod;
import com.lpsouti.common.vo.ResultVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pay_method")
@Slf4j
@RequiredArgsConstructor
public class PayMethodController {

    private final PayMethodService payMethodService;

    // 新增支付方式
    @PostMapping
    public ResultVO<Void> add(@RequestBody @Valid PayMethodAddDTO dto) {
        log.info("pay method add dto = {}", dto);
        payMethodService.add(dto);
        return ResultVO.success();
    }

    // 修改支付方式
    @PutMapping
    public ResultVO<Void> edit(@RequestBody @Valid PayMethodEditDTO dto) {
        log.info("pay method edit dto = {}", dto);
        payMethodService.edit(dto);
        return ResultVO.success();
    }

    // 查询所有支付方式
    @GetMapping
    public ResultVO<List<PayMethod>> queryAll() {
        List<PayMethod> vos = payMethodService.queryAll();
        return ResultVO.success(vos);
    }
}
