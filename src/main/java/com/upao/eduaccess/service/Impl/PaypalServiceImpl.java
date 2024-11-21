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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaypalServiceImpl implements PaypalService {

    private final PlanRepository planRepository;
    private final PayPalHttpClient payPalHttpClient;


    public String createOrder(double cost  ,String returnUrl, String cancelUrl) throws IOException {
        String currency = "USD";
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");
        List<PurchaseUnitRequest> purchaseUnits = new ArrayList<>();

        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().amountWithBreakdown(new AmountWithBreakdown().currencyCode(currency).value(String.format("%.2f", cost)));

        purchaseUnits.add(purchaseUnitRequest);

        orderRequest.purchaseUnits(purchaseUnits);

        orderRequest.applicationContext(new ApplicationContext()
                .returnUrl(returnUrl)  // Configura la URL de retorno
                .cancelUrl(cancelUrl)); // Configura la URL de cancelación

        OrdersCreateRequest request = new OrdersCreateRequest().requestBody(orderRequest);
        try {
            // Realiza la solicitud a PayPal
            HttpResponse<Order> response = payPalHttpClient.execute(request);
            Order order = response.result();

            // Retorna el id de la orden
            return order.id();
        }
        catch (HttpException e) {
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
    public String pagarPlan(Integer idPlan) throws IOException {


        Plan plan = planRepository.findById(idPlan).orElse(null);
        if (plan == null) {
            throw new NotFoundException("No se encontro el plan");
        }
        String returnUrl = "https://www.youtube.com/watch?v=hDml3GNFZKc";
        String cancelUrl = "https://upao-grupo7-transaccional.atlassian.net/jira/software/projects/SCRUM/boards/1/backlog?selectedIssue=SCRUM-18";
        String approvalUrl = "https://www.sandbox.paypal.com/checkoutnow?token=" + createOrder(plan.getPrecio(), returnUrl, cancelUrl);

        return (approvalUrl);


    }
}
