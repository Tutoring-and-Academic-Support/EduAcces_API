package com.upao.eduaccess.service;

import java.io.IOException;

import com.paypal.http.HttpResponse;
import com.paypal.orders.*;

public interface PaypalService {
    public String createOrder(double cost, String returnUrl, String cancelUrl, Long userId, Integer idPlan)throws IOException;
    String pagarPlan(Integer idPlan, Long userId) throws IOException;

    public HttpResponse<Order> captureOrder(String orderId) throws IOException;
    // tipo de retorno de captureORder "com.paypal.http.HttpResponse<Order>"

}
