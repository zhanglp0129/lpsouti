package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.pay_method.PayMethodAddDTO;
import com.lpsouti.admin.service.PayMethodService;
import com.lpsouti.common.vo.ResultVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
