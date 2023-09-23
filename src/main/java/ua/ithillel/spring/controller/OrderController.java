package ua.ithillel.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ithillel.spring.exception.OrderNotFoundException;
import ua.ithillel.spring.model.dto.OrderDTO;
import ua.ithillel.spring.service.OrderService;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

//    @GetMapping
//    ResponseEntity<List<Order>> getAll(){
//        return ResponseEntity.ok(orderRepository.getAll());
//    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<OrderDTO>> findById(@PathVariable("id") Integer id)
            throws OrderNotFoundException, IllegalArgumentException {

        Optional<OrderDTO> order = orderService.findById(id);
//        if(order == null){
//            throw new OrderNotFoundException(id);
//        }
        return ResponseEntity.ok(order);
    }

//    @PostMapping
//    public ResponseEntity<Order> addOrder(@RequestBody Order order){
//        Order savedorder = orderService.addOrder(order);
//        return ResponseEntity.ok(savedorder);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Order> removeOrder(@PathVariable("id") Integer id) throws OrderNotFoundException, IllegalArgumentException {
//        Order removedOrder = orderService.removeOrder(id);
//        return ResponseEntity.ok(removedOrder);
//    }
}
