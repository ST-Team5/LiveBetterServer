package com.livebetter.controllers;
import com.livebetter.domain.SystemUserRole;
import com.livebetter.services.SystemUserRoleService;
import com.livebetter.services.SystemUserService;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/systemuserroleses")
@Controller
public class SystemUserRoleController {

	@Autowired
    SystemUserRoleService systemUserRoleService;

	@Autowired
    SystemUserService systemUserService;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid SystemUserRole systemUserRole, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, systemUserRole);
            return "systemuserroleses/create";
        }
        uiModel.asMap().clear();
        systemUserRoleService.saveSystemUserRoles(systemUserRole);
        return "redirect:/systemuserroleses/" + encodeUrlPathSegment(systemUserRole.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new SystemUserRole());
        return "systemuserroleses/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("systemuserroles", systemUserRoleService.findSystemUserRoles(id));
        uiModel.addAttribute("itemId", id);
        return "systemuserroleses/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("systemuserroleses", SystemUserRole.findSystemUserRolesEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) systemUserRoleService.countAllSystemUserRoleses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("systemuserroleses", SystemUserRole.findAllSystemUserRoleses(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "systemuserroleses/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid SystemUserRole systemUserRole, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, systemUserRole);
            return "systemuserroleses/update";
        }
        uiModel.asMap().clear();
        systemUserRoleService.updateSystemUserRoles(systemUserRole);
        return "redirect:/systemuserroleses/" + encodeUrlPathSegment(systemUserRole.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, systemUserRoleService.findSystemUserRoles(id));
        return "systemuserroleses/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        SystemUserRole systemUserRole = systemUserRoleService.findSystemUserRoles(id);
        systemUserRoleService.deleteSystemUserRoles(systemUserRole);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/systemuserroleses";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("systemUserRoles_createddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("systemUserRoles_modifieddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, SystemUserRole systemUserRole) {
        uiModel.addAttribute("systemUserRoles", systemUserRole);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("systemuserses", systemUserService.findAllSystemUserses());
    }

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
