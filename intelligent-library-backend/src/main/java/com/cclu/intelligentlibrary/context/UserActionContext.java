package com.cclu.intelligentlibrary.context;

/**
 * @author ChangCheng Lu
 * @date 2023/11/12 18:47
 * @description
 * @copyright ChangChengLu
 */
public class UserActionContext {

    private static ThreadLocal<Integer> userRole;

    private static ThreadLocal<Integer> actionMode;

    static {
        userRole = new ThreadLocal<>();
        actionMode = new ThreadLocal<>();
    }

    public static Integer getUserRole() {
        return userRole.get();
    }

    public static void setUserRole(Integer uRole) {
        userRole.set(uRole);
    }

    public static Integer getActionMode() {
        return actionMode.get();
    }

    public static void setActionMode(Integer aMode) {
        actionMode.set(aMode);
    }

    public static void clearUserRole() {
        userRole.remove();
    }

    public static void clearActionMode() {
        actionMode.remove();
    }
}
