package tn.banque.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tn.banque.entities.Compte;

import tn.banque.metier.IBanqueMetier;

@Controller
public class BanqueController {
	@Autowired
	private IBanqueMetier banqueMetier;
	@RequestMapping(value = "/operations")
    public String index(){
        return "comptes";
    }


    @RequestMapping(value = "/consulterCompte" , method = RequestMethod.GET)
    public String consulter(Model model , String codeCompte,
    		@RequestParam(name = "page",defaultValue = "0") int page ,
            @RequestParam(name = "size",defaultValue = "4") int size){
    	model.addAttribute("codeCompte",codeCompte);
        try{
            Compte cp = banqueMetier.consulterCompte(codeCompte);
            Page listOperations = banqueMetier.listOperation(codeCompte,page,size);
            model.addAttribute("listOperations",listOperations.getContent());
            int[] pages = new int[listOperations.getTotalPages()];
            model.addAttribute("pages",pages);
            model.addAttribute("compte",cp);
        }catch (Exception e){
            model.addAttribute("exception","Compte introuvable");
        }

        return "comptes";
    }
    @RequestMapping(value="/save" ,method = RequestMethod.POST )
    public String saveOperation(Model model ,  String typeOperation , String codeCompte , double montant , String codeCompte2){
      try{
          if(typeOperation.equals("VERS")){
        	  banqueMetier.verser(codeCompte,montant);
          }        if(typeOperation.equals("RETR")){
        	  banqueMetier.retirer(codeCompte,montant);
          }  if(typeOperation.equals("VIR")){
        	  banqueMetier.virement(codeCompte,codeCompte2,montant);
          }
      }catch (Exception e){
          model.addAttribute("exception",e);
          return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
      }

        return "redirect:/consulterCompte?codeCompte="+codeCompte;
    }

}