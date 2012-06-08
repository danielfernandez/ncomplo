package org.eleventhlabs.ncomplo.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class SessionUtil {

    public static final String SESSION_ATTRIBUTE_AUTH_LOGIN = "AUTH_LOGIN";
    public static final String SESSION_ATTRIBUTE_AUTH_ADMIN = "AUTH_ADMIN";
    

    public static boolean isUserAuthenticated(final HttpServletRequest request) {
        final HttpSession session = request.getSession(true);
        return (session.getAttribute(SESSION_ATTRIBUTE_AUTH_LOGIN) != null);
    }

    
    public static boolean isAdminAuthenticated(final HttpServletRequest request) {
        final HttpSession session = request.getSession(true);
        final Boolean admin =
                (Boolean) session.getAttribute(SESSION_ATTRIBUTE_AUTH_ADMIN);
        if (admin == null) {
            return false;
        }
        return admin.booleanValue();
    }

    
    public static String getAuthenticatedUser(final HttpServletRequest request) {
        final HttpSession session = request.getSession(true);
        return (String) session.getAttribute(SESSION_ATTRIBUTE_AUTH_LOGIN);
    }

    
    public static void setAuthenticatedUser(final HttpServletRequest request, final String login, final boolean admin) {
        final HttpSession session = request.getSession(true);
        session.setAttribute(SESSION_ATTRIBUTE_AUTH_LOGIN, login);
        session.setAttribute(SESSION_ATTRIBUTE_AUTH_ADMIN, Boolean.valueOf(admin));
    }
    
}
