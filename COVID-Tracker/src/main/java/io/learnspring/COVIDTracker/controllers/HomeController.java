package io.learnspring.COVIDTracker.controllers;

import io.learnspring.COVIDTracker.models.LocationStats;
import io.learnspring.COVIDTracker.services.COVIDDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    COVIDDataService covidDataService;


    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = covidDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);

        return "home";
    }
}
