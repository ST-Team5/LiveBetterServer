package com.livebetter.controllers;
import com.livebetter.domain.SystemUser;
import com.livebetter.services.SystemUserRoleService;
import com.livebetter.services.SystemUserService;
import com.livebetter.services.UserToPersonService;
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

@RequestMapping("/systemuserses")
@Controller
public class SystemUserController {

	@Autowired
    SystemUserService systemUserService;

	@Autowired
    SystemUserRoleService systemUserRoleService;

	@Autowired
    UserToPersonService userToPersonService;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid SystemUser systemUser, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, systemUser);
            return "systemuserses/create";
        }
        uiModel.asMap().clear();
        systemUserService.saveSystemUsers(systemUser);
        return "redirect:/systemuserses/" + encodeUrlPathSegment(systemUser.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new SystemUser());
        return "systemuserses/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("systemusers", systemUserService.findSystemUsers(id));
        uiModel.addAttribute("itemId", id);
        return "systemuserses/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("systemuserses", SystemUser.findSystemUsersEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) systemUserService.countAllSystemUserses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("systemuserses", SystemUser.findAllSystemUserses(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "systemuserses/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid SystemUser systemUser, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, systemUser);
            return "systemuserses/update";
        }
        uiModel.asMap().clear();
        systemUserService.updateSystemUsers(systemUser);
        return "redirect:/systemuserses/" + encodeUrlPathSegment(systemUser.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, systemUserService.findSystemUsers(id));
        return "systemuserses/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        SystemUser systemUser = systemUserService.findSystemUsers(id);
        systemUserService.deleteSystemUsers(systemUser);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/systemuserses";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("systemUsers_createddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("systemUsers_modifieddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, SystemUser systemUser) {
        uiModel.addAttribute("systemUsers", systemUser);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("systemuserroleses", systemUserRoleService.findAllSystemUserRoleses());
        uiModel.addAttribute("userstopersonses", userToPersonService.findAllUsersToPersonses());
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
