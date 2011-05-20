/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.dto;


import java.util.Date;

/**
 *
 * @author abdelkafi_s
 */


public class CustomerOrder {

    public CustomerOrder() {
        super();
    }

    public CustomerOrder(Long orderId, Customer customer, Date datePlaced, Date datePromised, String terms, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.datePlaced = datePlaced;
        this.datePromised = datePromised;
        this.terms = terms;
        this.status = status;
    }
    

    private Long orderId;


    private Customer customer;


    private Date datePlaced;

    private Date datePromised;

    private String terms;

    private String status;

   
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(Date datePlaced) {
        this.datePlaced = datePlaced;
    }

    public Date getDatePromised() {
        return datePromised;
    }

    public void setDatePromised(Date datePromised) {
        this.datePromised = datePromised;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }
    
    
}
