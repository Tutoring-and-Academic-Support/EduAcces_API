package com.upao.eduaccess.controller;

import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import com.upao.eduaccess.domain.Plan;
import com.upao.eduaccess.domain.Tutor;
import com.upao.eduaccess.domain.User;
import com.upao.eduaccess.dto.PlanRequest;
import com.upao.eduaccess.dto.PlanResponse;
import com.upao.eduaccess.exception.ResourceNotFoundException;
import com.upao.eduaccess.repository.TutorRepository;
import com.upao.eduaccess.service.Impl.PaypalServiceImpl;
import com.upao.eduaccess.service.PaypalService;
import com.upao.eduaccess.service.PlanService;
import com.upao.eduaccess.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.upao.eduaccess.service.Impl.PlanServiceImpl;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plan")
public class PlanController {

    private final UserService userService;
    private final PlanService planService;
    private final PaypalService paypalService;
    private final TutorRepository tutorRepository;

    @Autowired
    public PlanController(PlanService planService, PaypalService paypalService, UserService userService, TutorRepository tutorRepository) {
        this.planService = planService;
        this.paypalService = paypalService;
        this.userService = userService;
        this.tutorRepository = tutorRepository;
    }

    // Obtener todos los planes
    // Obtener todos los planes (devuelve PlanResponse si es para la API REST)
    @GetMapping
    public ResponseEntity<List<PlanResponse>> obtenerPlan() {
        List<PlanResponse> planes = planService.getAllPlan(); // Esto devuelve DTOs
        return ResponseEntity.ok(planes);
    }

    // Obtener un plan por ID (devuelve PlanResponse)
    @GetMapping("/{idPlan}")
    public ResponseEntity<PlanResponse> obtenerPlan(@PathVariable Integer idPlan) {
        PlanResponse plan = planService.getPlanById(idPlan);
        return ResponseEntity.ok(plan);
    }


    // Iniciar el proceso de pago para un plan
    @PostMapping("/{idPlan}/pagar")
    public ResponseEntity<String> pagarPlan(@PathVariable Integer idPlan) throws IOException {
        // Obtener el usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // Obtener el userId como Long
        Long userId = userService.findUserByEmail(email).getId();

        // Llamar al método pagarPlan con idPlan y userId
        String approvalUrl = paypalService.pagarPlan(idPlan, userId);
        return ResponseEntity.ok(approvalUrl);
    }


}
