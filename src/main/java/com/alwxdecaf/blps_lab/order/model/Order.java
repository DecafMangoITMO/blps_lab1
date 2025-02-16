package com.alwxdecaf.blps_lab.order.model;

import com.alwxdecaf.blps_lab.product.model.Product;
import com.alwxdecaf.blps_lab.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "u_order")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customerId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

}
