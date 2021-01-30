package rs.ac.uns.ftn.isa.pharmacy.demo.service;

import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.LogInDto;
import rs.ac.uns.ftn.isa.pharmacy.demo.model.dto.UserTokenState;


public interface LogInService {

   UserTokenState logIn(LogInDto authenticationRequest);
   void firsLoginPasswordChange(String newPassword);

}
