package com.example.orderservice.service;

import com.example.orderservice.client.CatalogServiceClient;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.jpa.OrderEntity;
import com.example.orderservice.jpa.OrderRepository;
import com.example.orderservice.vo.ResponseCatalog;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;
    CatalogServiceClient catalogServiceClient;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            CatalogServiceClient catalogServiceClient) {
        this.orderRepository = orderRepository;
        this.catalogServiceClient = catalogServiceClient;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) throws Exception {
        ResponseCatalog responseCatalog = catalogServiceClient.getCatalog(orderDto.getProductId());
        if (responseCatalog.getStock() < orderDto.getQty()) {
            throw new Exception("You are ordering more than stock");
        }

        orderDto.setOrderId(UUID.randomUUID().toString());
        orderDto.setUnitPrice(responseCatalog.getUnitPrice());
        orderDto.setTotalPrice(orderDto.getQty() * responseCatalog.getUnitPrice());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        OrderEntity orderEntity = mapper.map(orderDto, OrderEntity.class);

        orderRepository.save(orderEntity);

        OrderDto returnValue = mapper.map(orderEntity, OrderDto.class);

        return returnValue;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        OrderDto orderDto = new ModelMapper().map(orderEntity, OrderDto.class);

        return orderDto;
    }

    @Override
    public Iterable<OrderEntity> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
