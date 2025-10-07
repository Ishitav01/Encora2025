package com.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill_manager")
public class BillManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billno;  // primary key

    private Double total;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemTransaction> items;

    // getters and setters
    public Long getBillno() {
        return billno;
    }

    public void setBillno(Long billno) {
        this.billno = billno;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<ItemTransaction> getItems() {
        return items;
    }

    public void setItems(List<ItemTransaction> items) {
        this.items = items;
        if (items != null) {
            for (ItemTransaction item : items) {
                item.setBill(this); // ensure bidirectional mapping
            }
        }
    }
}
