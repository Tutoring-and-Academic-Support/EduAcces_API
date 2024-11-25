package com.upao.eduaccess.controller;

import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import com.upao.eduaccess.domain.Plan;
import com.upao.eduaccess.domain.Tutor;
import com.upao.eduaccess.exception.ResourceNotFoundException;
import com.upao.eduaccess.repository.TutorRepository;
import com.upao.eduaccess.service.Impl.PaypalServiceImpl;
import com.upao.eduaccess.service.PaypalService;
import com.upao.eduaccess.service.PlanService;
import com.upao.eduaccess.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/paypal")
public class PaypalController {

    @Autowired
    private PaypalService paypalService;
    @Autowired
    private UserService userService;

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private PlanService planService;

    @PostMapping("/create-order")
    public String  createOrder(@RequestParam double totalAmount, Long userId, Integer idPlan) {
        //String returnUrl = "https://govench-api.onrender.com/api/v1/admin/payments/payment";
        String returnUrl = "http://localhost:4200/payment-success";
        String cancelUrl = "https://blog.fluidui.com/top-404-error-page-examples/";
        try {
            String orderId = paypalService.createOrder(totalAmount, returnUrl, cancelUrl,  userId,  idPlan);
            if (orderId == null) {
                return "/error?status=error";
            }

            String approvalUrl = "https://www.sandbox.paypal.com/checkoutnow?token=" + orderId;
            return approvalUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return "/error?status=error";
        }
    }

@PostMapping("/pagar-plan/{idPlan}")
public String pagarPlan(@PathVariable Integer idPlan) throws IOException {
    // Obtener el usuario autenticado
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    Long userId = Long.valueOf(userService.findUserByEmail(email).getId().toString());

    // Configurar URLs de retorno al frontend
    String returnUrl = "http://localhost:4200/payment-success";
    String cancelUrl = "http://localhost:4200/dashboard";

    // Llamar al método pagarPlan en lugar de createOrder
    String orderId = paypalService.pagarPlan(idPlan, userId);
    if (orderId == null) {
        throw new IOException("No se pudo crear la orden de PayPal");
    }

    String approvalUrl = "https://www.sandbox.paypal.com/checkoutnow?token=" + orderId;
    return approvalUrl;
}


    @PostMapping("/capture-order")
    public ResponseEntity<String> captureOrder(@RequestBody Map<String, Object> data) {
        String token = (String) data.get("token");
        Integer idPlan = (Integer) data.get("idPlan");
        try {
            HttpResponse<Order> response = paypalService.captureOrder(token);
            if (response != null && response.statusCode() == 201) { // Pago exitoso
                // Obtener los detalles de la orden
                PaypalServiceImpl.OrderDetails details = ((PaypalServiceImpl) paypalService).getOrderDetails(token);
                if (details == null) {
                    throw new ResourceNotFoundException("No se encontró la orden asociada");
                }
                Long userId = details.getUserId();
                Integer capturedIdPlan = details.getIdPlan();

                // Obtener el tutor por userId
                Tutor tutor = tutorRepository.findByUserId(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("No se encontró el tutor"));

                // Obtener el plan
                Plan plan = planService.getPlanEntityById(capturedIdPlan);

                // Asociar el plan al tutor
                tutor.setPlan(plan);
                tutorRepository.save(tutor);

                // Eliminar la asociación en el mapa
                ((PaypalServiceImpl) paypalService).removeOrderId(token);

                return ResponseEntity.ok("Pago capturado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al capturar el pago");
            }
        } catch (IOException | ResourceNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al capturar el pago");
        }
    }

}
