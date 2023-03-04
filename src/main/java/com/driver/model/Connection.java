package com.driver.model;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;

@Entity
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

    @ManyToOne
    @JoinColumn
    private ServiceProvider serviceProvider;

    //setting the parent user wrt to child
    @ManyToOne
    @JoinColumn
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public Connection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
