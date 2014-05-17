// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livebetter.controllers;

import com.livebetter.controllers.UsersToPersonsController;
import com.livebetter.domain.UsersToPersons;
import com.livebetter.services.PersonsService;
import com.livebetter.services.SystemUsersService;
import com.livebetter.services.UsersToPersonsService;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect UsersToPersonsController_Roo_Controller {
    
    @Autowired
    UsersToPersonsService UsersToPersonsController.usersToPersonsService;
    
    @Autowired
    PersonsService UsersToPersonsController.personsService;
    
    @Autowired
    SystemUsersService UsersToPersonsController.systemUsersService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String UsersToPersonsController.create(@Valid UsersToPersons usersToPersons, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, usersToPersons);
            return "userstopersonses/create";
        }
        uiModel.asMap().clear();
        usersToPersonsService.saveUsersToPersons(usersToPersons);
        return "redirect:/userstopersonses/" + encodeUrlPathSegment(usersToPersons.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String UsersToPersonsController.createForm(Model uiModel) {
        populateEditForm(uiModel, new UsersToPersons());
        return "userstopersonses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String UsersToPersonsController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("userstopersons", usersToPersonsService.findUsersToPersons(id));
        uiModel.addAttribute("itemId", id);
        return "userstopersonses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String UsersToPersonsController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("userstopersonses", UsersToPersons.findUsersToPersonsEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) usersToPersonsService.countAllUsersToPersonses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("userstopersonses", UsersToPersons.findAllUsersToPersonses(sortFieldName, sortOrder));
        }
        return "userstopersonses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String UsersToPersonsController.update(@Valid UsersToPersons usersToPersons, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, usersToPersons);
            return "userstopersonses/update";
        }
        uiModel.asMap().clear();
        usersToPersonsService.updateUsersToPersons(usersToPersons);
        return "redirect:/userstopersonses/" + encodeUrlPathSegment(usersToPersons.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String UsersToPersonsController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, usersToPersonsService.findUsersToPersons(id));
        return "userstopersonses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String UsersToPersonsController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        UsersToPersons usersToPersons = usersToPersonsService.findUsersToPersons(id);
        usersToPersonsService.deleteUsersToPersons(usersToPersons);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/userstopersonses";
    }
    
    void UsersToPersonsController.populateEditForm(Model uiModel, UsersToPersons usersToPersons) {
        uiModel.addAttribute("usersToPersons", usersToPersons);
        uiModel.addAttribute("personses", personsService.findAllPersonses());
        uiModel.addAttribute("systemuserses", systemUsersService.findAllSystemUserses());
    }
    
    String UsersToPersonsController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
