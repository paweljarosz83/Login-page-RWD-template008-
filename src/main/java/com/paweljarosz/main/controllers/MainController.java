package com.paweljarosz.main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	private static final String INDEX = "index";
	private static final String DETAILS = "details";
	
	Game g1 = new Game(2L,"Shadow warroir","https://img.itch.zone/aW1hZ2UvMTkxOTUxLzkxNTk2My5naWY=/347x500/MBV4a8.gif",124L,"https://pj83.itch.io/rollover","Romek","iPhone");
	Game g2 = new Game(3L,"Sword Bros","https://img.itch.zone/aW1hZ2UvMTkxOTUxLzkxNTk2My5naWY=/347x500/MBV4a8.gif",4L,"https://pj83.itch.io/rollover","Tom","Android");
	Game g3 = new Game(2L,"Lazers","https://img.itch.zone/aW1hZ2UvMTkxOTUxLzkxNTk2My5naWY=/347x500/MBV4a8.gif",435L,"https://pj83.itch.io/rollover","Janek","Web");
	Game g4 = new Game(3L,"Not for","https://img.itch.zone/aW1hZ2UvMTkxOTUxLzkxNTk2My5naWY=/347x500/MBV4a8.gif",22L,"https://pj83.itch.io/rollover","Tigerr","Windows Phone");
	Game g5 = new Game(2L,"Nightman","https://img.itch.zone/aW1hZ2UvMTkxOTUxLzkxNTk2My5naWY=/347x500/MBV4a8.gif",11L,"https://pj83.itch.io/rollover","Ziomek","PC");
	Game g6 = new Game(3L,"Car chase","https://img.itch.zone/aW1hZ2UvMTkxOTUxLzkxNTk2My5naWY=/347x500/MBV4a8.gif",1008L,"https://pj83.itch.io/rollover","Tytus","Android");
	
	
	
	@RequestMapping("/")
	public String root(Model model){
		
		List<Game>games = new ArrayList<>();
		
		games.add(g1);
		games.add(g2);
		games.add(g3);
		games.add(g4);
		games.add(g5);
		games.add(g6);

		model.addAttribute("games",games);

		return INDEX;
	}
	
	@RequestMapping("/index")
	public String index(){
		return INDEX;
	}
	
	@RequestMapping("/home")
	public String home(){
		return "home";
	}
	
	@RequestMapping("gameDetails/{id}")
	public String details(Model model){
		model.addAttribute("game",g1);
		return DETAILS;
	}
	
	
}
