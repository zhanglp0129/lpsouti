package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.order.OrderAddDTO;
import com.lpsouti.admin.service.OrderService;
import com.lpsouti.common.vo.ResultVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 创建订单
    @PostMapping
    public ResultVO<Void> add(@RequestBody @Valid OrderAddDTO dto) {
        log.info("order add dto = {}", dto);
        orderService.add(dto);
        return ResultVO.success();
    }
}
