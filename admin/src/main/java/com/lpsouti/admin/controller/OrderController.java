package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.order.OrderAddDTO;
import com.lpsouti.admin.dto.order.OrderEditDTO;
import com.lpsouti.admin.service.OrderService;
import com.lpsouti.common.vo.ResultVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    // 修改订单
    @PutMapping
    public ResultVO<Void> edit(@RequestBody @Valid OrderEditDTO dto) {
        log.info("order edit dto = {}", dto);
        orderService.edit(dto);
        return ResultVO.success();
    }
}
