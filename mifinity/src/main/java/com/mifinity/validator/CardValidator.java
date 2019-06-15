package com.mifinity.validator;

import com.mifinity.model.Card;
import com.mifinity.model.User;
import com.mifinity.service.CardService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CardValidator implements Validator {
	
	// checking - card number should be digits only & of length 16
    Pattern pattern = Pattern.compile("\\d{16}");

    @Autowired
	private CardService cardService;
    
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Card card = (Card) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ccNumber", "NotEmpty");
        Matcher matcher = pattern.matcher(card.getCcNumber());
        if (!matcher.matches()) {
            errors.rejectValue("ccNumber", "Size.cardDetailsForm.ccNumber");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");

        Date expiryDate;
		try {
			expiryDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/" + card.getExpMonthYear());
			if (!isValid(expiryDate)) {
				errors.rejectValue("expMonthYear", "Date.cardDetailsForm.expMonthYear");
			}
		} catch (ParseException e) {
			errors.rejectValue("expMonthYear", "Date.cardDetailsForm.expMonthYear");
		}
		
		if (isCcNumberDuplicate(card)) {
			errors.rejectValue("ccNumber", "Duplicate.cardDetailsForm.ccNumber");
		}
        
    }
    
    /*
     * validate if the same card with ccNumber does not already exists
     */
    private boolean isCcNumberDuplicate(Card card) {
		boolean isDuplicate = false;
		
		List<Card> allUserCards = cardService.getAllCards(card.getUser().getId());
		try {
			Card userCard = allUserCards.stream().filter(c -> c.getCcNumber().equals(card.getCcNumber())).findFirst().get();
			if(userCard != null ) {
				isDuplicate = true;
			}
		}catch(Exception e) {
			// no cards exists for this user... so go ahead adding this card
		}
		return isDuplicate;
	}

    /*
     * check if the expiry date in format mm/yyyy is valid and not less than current date
     */
    public boolean isValid(Date value) {

        if (value == null) {
          return false;
        }

        Calendar calendarExpiry = Calendar.getInstance();
        Calendar calendarNow = Calendar.getInstance();

        calendarExpiry.setTime(value);

        if (calendarExpiry.get(Calendar.YEAR) > calendarNow.get(Calendar.YEAR)) {
          return true;
        } else {
          if (calendarExpiry.get(Calendar.YEAR) == calendarNow.get(Calendar.YEAR)
              && calendarExpiry.get(Calendar.MONTH) >= calendarNow.get(Calendar.MONTH)) {
            return true;
          }
        }

        return false;
      }
}