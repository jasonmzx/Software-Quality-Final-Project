package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

// Model Imports:
import com.ontariotechu.sofe3980U.core.MemoryStore;

@Controller
public class BookingViewController {

    @GetMapping("/")
    public String home(Model model) {

        MemoryStore memoryStore = MemoryStore.getInstance(); // get singleton inst

        model.addAttribute("airports", memoryStore.getAirportList()); 

        return "booking_view";
    }

    // Basically just a view Router, all the logic (data sharing) is in the API controller

    @GetMapping("/bookings")
    public String bookings(Model model) {
        return "tickets_view";
    }


}