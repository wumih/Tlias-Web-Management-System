package com.itheima.utils;

/**
 * 当前线程数据持有者工具类
 * 使用ThreadLocal来存储和获取当前线程的员工ID信息，确保线程间数据隔离
 */
public class CurrentHolder {

    /**
     * ThreadLocal变量，用于存储当前线程的员工ID
     * 每个线程都有自己独立的Integer值副本
     */
    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    /**
     * 设置当前线程的员工ID
     * @param employeeId 要设置的员工ID，可以为null
     */
    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    /**
     * 获取当前线程的员工ID
     * @return 当前线程中存储的员工ID，如果未设置则返回null
     */
    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }

    /**
     * 移除当前线程的员工ID
     * 用于清理ThreadLocal中的数据，防止内存泄漏
     */
    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}
