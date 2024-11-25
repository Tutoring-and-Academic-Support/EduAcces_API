package com.upao.eduaccess.service.Impl;

import com.paypal.orders.*;
import com.paypal.http.HttpResponse;
import com.paypal.http.exceptions.HttpException;
import com.paypal.core.PayPalHttpClient;
import com.upao.eduaccess.config.PaypalConfig;
import com.upao.eduaccess.domain.Plan;
import com.upao.eduaccess.repository.PlanRepository;
import com.upao.eduaccess.service.PaypalService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
@RequiredArgsConstructor

public class PaypalServiceImpl implements PaypalService {

    public static class OrderDetails {
        private Long userId;
        private Integer idPlan;

        public OrderDetails(Long userId, Integer idPlan) {
            this.userId = userId;
            this.idPlan = idPlan;
        }

        public Long getUserId() {
            return userId;
        }

        public Integer getIdPlan() {
            return idPlan;
        }
    }

    private final PlanRepository planRepository;
    private final PayPalHttpClient payPalHttpClient;

    private final ConcurrentMap<String, OrderDetails> orderDetailsMap = new ConcurrentHashMap<>();


    public String createOrder(double cost, String returnUrl, String cancelUrl, Long userId, Integer idPlan) throws IOException {
        String currency = "USD";
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");
        List<PurchaseUnitRequest> purchaseUnits = new ArrayList<>();

        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode(currency).value(String.format("%.2f", cost)));

        purchaseUnits.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnits);

        orderRequest.applicationContext(new ApplicationContext()
                .returnUrl(returnUrl)
                .cancelUrl(cancelUrl));

        OrdersCreateRequest request = new OrdersCreateRequest().requestBody(orderRequest);

        try {
            HttpResponse<Order> response = payPalHttpClient.execute(request);
            Order order = response.result();

            // Asociar orderId con userId e idPlan
            orderDetailsMap.put(order.id(), new OrderDetails(userId, idPlan));

            return order.id();
        } catch (HttpException e) {
            System.err.println("Error de PayPal: " + e.getMessage());
            return null;
        }
    }


    public HttpResponse<Order> captureOrder(String orderId) throws IOException {
        OrdersCaptureRequest request = new OrdersCaptureRequest(orderId);
        try {
            return payPalHttpClient.execute(request);
        } catch (HttpException e) {
            System.err.println("Error al capturar el pago: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String pagarPlan(Integer idPlan, Long userId) throws IOException {
        Plan plan = planRepository.findById(idPlan).orElse(null);
        if (plan == null) {
            throw new NotFoundException("No se encontró el plan");
        }

        String returnUrl = "http://localhost:4200/payment-success"; // Frontend
        String cancelUrl = "http://localhost:4200/dashboard";   // Frontend

        String orderId = createOrder(plan.getPrecio(), returnUrl, cancelUrl, userId, idPlan);
        if (orderId == null) {
            throw new IOException("No se pudo crear la orden de PayPal");
        }

        String approvalUrl = "https://www.sandbox.paypal.com/checkoutnow?token=" + orderId;
        return approvalUrl;
    }

    public OrderDetails getOrderDetails(String orderId) {
        return orderDetailsMap.get(orderId);
    }

    public void removeOrderId(String orderId) {
        orderDetailsMap.remove(orderId);
    }
}
