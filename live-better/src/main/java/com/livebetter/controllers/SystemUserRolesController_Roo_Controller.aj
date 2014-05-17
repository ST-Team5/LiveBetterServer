// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livebetter.controllers;

import com.livebetter.controllers.SystemUserRolesController;
import com.livebetter.domain.SystemUserRoles;
import com.livebetter.services.SystemUserRolesService;
import com.livebetter.services.SystemUsersService;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect SystemUserRolesController_Roo_Controller {
    
    @Autowired
    SystemUserRolesService SystemUserRolesController.systemUserRolesService;
    
    @Autowired
    SystemUsersService SystemUserRolesController.systemUsersService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String SystemUserRolesController.create(@Valid SystemUserRoles systemUserRoles, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, systemUserRoles);
            return "systemuserroleses/create";
        }
        uiModel.asMap().clear();
        systemUserRolesService.saveSystemUserRoles(systemUserRoles);
        return "redirect:/systemuserroleses/" + encodeUrlPathSegment(systemUserRoles.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String SystemUserRolesController.createForm(Model uiModel) {
        populateEditForm(uiModel, new SystemUserRoles());
        return "systemuserroleses/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String SystemUserRolesController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("systemuserroles", systemUserRolesService.findSystemUserRoles(id));
        uiModel.addAttribute("itemId", id);
        return "systemuserroleses/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String SystemUserRolesController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("systemuserroleses", SystemUserRoles.findSystemUserRolesEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) systemUserRolesService.countAllSystemUserRoleses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("systemuserroleses", SystemUserRoles.findAllSystemUserRoleses(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "systemuserroleses/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String SystemUserRolesController.update(@Valid SystemUserRoles systemUserRoles, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, systemUserRoles);
            return "systemuserroleses/update";
        }
        uiModel.asMap().clear();
        systemUserRolesService.updateSystemUserRoles(systemUserRoles);
        return "redirect:/systemuserroleses/" + encodeUrlPathSegment(systemUserRoles.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String SystemUserRolesController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, systemUserRolesService.findSystemUserRoles(id));
        return "systemuserroleses/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String SystemUserRolesController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        SystemUserRoles systemUserRoles = systemUserRolesService.findSystemUserRoles(id);
        systemUserRolesService.deleteSystemUserRoles(systemUserRoles);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/systemuserroleses";
    }
    
    void SystemUserRolesController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("systemUserRoles_createddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("systemUserRoles_modifieddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
    }
    
    void SystemUserRolesController.populateEditForm(Model uiModel, SystemUserRoles systemUserRoles) {
        uiModel.addAttribute("systemUserRoles", systemUserRoles);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("systemuserses", systemUsersService.findAllSystemUserses());
    }
    
    String SystemUserRolesController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
