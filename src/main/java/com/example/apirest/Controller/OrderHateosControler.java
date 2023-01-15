package com.example.apirest.Controller;

import com.example.apirest.Entity.OrderHateoas;
import com.example.apirest.Repository.OrderHateosRepository;
import jakarta.persistence.criteria.Order;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OrderHateosControler {

    private final OrderHateosRepository repositoryOrder;

    OrderHateosControler(OrderHateosRepository repository) {
        this.repositoryOrder = repository;
    }

    @GetMapping("/orders")
    ResponseEntity<List<OrderHateoas>> consultOrderAll() {
        long idOrder;
        Link linkUri;
        List<OrderHateoas> orderList = repositoryOrder.findAll();
        if (orderList.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        for (OrderHateoas orderHateoas:orderList){
            idOrder = orderHateoas.getId();
            linkUri = linkTo(methodOn(OrderHateosControler.class).consultOneOrder(idOrder)).withSelfRel();
            orderHateoas.add(linkUri);
            linkUri = linkTo(methodOn(OrderHateosControler.class).consultOrderAll()).withRel("Custumer order list");
            orderHateoas.add(linkUri);
        }
        return new ResponseEntity<List<OrderHateoas>>(orderList, HttpStatus.OK);

    }

    @GetMapping("/orders/{id}")
    ResponseEntity<OrderHateoas> consultOneOrder(@PathVariable Long id) {
        Optional<OrderHateoas> orderPointer = repositoryOrder.findById(id);
        if (orderPointer.isPresent()) {
            OrderHateoas order = orderPointer.get();
            order.add(linkTo(methodOn(OrderHateosControler.class).consultOrderAll()).withRel("All orders"));
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/orders")
    OrderHateoas newOrderHateos(OrderHateoas newOrder) {
        return repositoryOrder.save(newOrder);
    }
}
