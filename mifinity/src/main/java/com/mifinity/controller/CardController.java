package com.mifinity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mifinity.model.Card;
import com.mifinity.model.User;
import com.mifinity.service.CardService;
import com.mifinity.service.UserService;
import com.mifinity.validator.CardValidator;

@Controller
public class CardController {

	@Autowired
	private UserService userService;
	@Autowired
	private CardService cardService;

	/*
	 * this method is required for retrieving the current user 
	 * to display only user specific cards
	 */
	private User getLoggedInUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return userService.findByUsername(((UserDetails) principal).getUsername());
		}
		return new User();
	}

	@RequestMapping(value = "/add-card", method = RequestMethod.GET)
	public ModelAndView addCard() {
		ModelAndView model = new ModelAndView();
		Card newCard = new Card();
		model.addObject("card", newCard);
		model.setViewName("card");
		return model;
	}

	@RequestMapping("/list-cards")
	public ModelAndView getCards() {
		ModelAndView model = new ModelAndView();
		model.addObject("cards", cardService.getAllCards(getLoggedInUser().getId()));
		model.setViewName("/dashboard");
		return model;
	}

	@Autowired
	private CardValidator cardValidator;

	@PostMapping(value = "/add-card")
	public ModelAndView updateCard(@ModelAttribute("cardDetailsForm") Card cardDetails, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		cardDetails.setUser(getLoggedInUser());
		
		// send the card details to the validator
		cardValidator.validate(cardDetails, bindingResult);

		if (bindingResult.hasErrors()) {
			model.setViewName("/card");
			return model;
		}

		cardService.addCard(cardDetails);
		model.addObject("cards", cardService.getAllCards(getLoggedInUser().getId()));
		model.setViewName("/dashboard");
		return model;
	}

	@RequestMapping("/delete-card/{id}")
	public String deleteCard(@PathVariable Integer id) {
		cardService.deleteCard(id.longValue());
		return "redirect:/list-cards";
	}
	
	@GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        return "redirect:/list-cards";
    }

}
