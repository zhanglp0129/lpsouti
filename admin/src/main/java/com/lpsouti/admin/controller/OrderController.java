package com.lpsouti.admin.controller;

import com.lpsouti.admin.dto.order.OrderAddDTO;
import com.lpsouti.admin.dto.order.OrderEditDTO;
import com.lpsouti.admin.dto.order.OrderPageDTO;
import com.lpsouti.admin.service.OrderService;
import com.lpsouti.common.entity.Order;
import com.lpsouti.common.vo.PageVO;
import com.lpsouti.common.vo.ResultVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    // 订单分页查询
    @GetMapping("/page")
    public ResultVO<PageVO<Order>> pageQuery(@Valid OrderPageDTO dto) {
        log.info("order page dto = {}", dto);
        PageVO<Order> vo = orderService.pageQuery(dto);
        return ResultVO.success(vo);
    }

    // 根据id查询订单
    @GetMapping("/{id}")
    public ResultVO<Order> queryById(@PathVariable Long id) {
        log.info("order id = {}", id);
        Order order = orderService.queryById(id);
        return ResultVO.success(order);
    }

    // 删除订单
    @DeleteMapping("/{id}")
    public ResultVO<Void> delete(@PathVariable Long id) {
        log.info("order id = {}", id);
        orderService.delete(id);
        return ResultVO.success();
    }

    // 修改订单状态
    @PatchMapping("/status/{id}")
    public ResultVO<Void> editStatus(
            @PathVariable Long id,  // 订单id
            @NotNull @Min(1) @Max(3) Byte status,   // 修改后的订单状态
            @RequestParam(required = false, defaultValue = "true") Boolean syncBalance, // 是否同步修改余额
            @RequestParam(required = false, defaultValue = "true") Boolean syncPayTime  // 是否同步修改余额
    ) {
        log.info("order id = {}, status = {}, syncBalance = {}, syncPayTime = {}", id, status, syncBalance, syncPayTime);
        orderService.editStatus(id, status, syncBalance, syncPayTime);
        return ResultVO.success();
    }
}
