package com.linkage.rainbow.util.context;

/**
 * <p>把Context对象的实例保存为当前线程的变量，在当前线程里的其他功能模块可以获取到Context变量</p>
 *
 * @version 	0.0.1
 */
public class ContextHolder {
	/**
	 * 线程变量
	 */
    private static ThreadLocal contextHolder = new ThreadLocal();

    /**
     * 将context对象实例设置线程
     * @param context
     */
    public static void setContext(Context context) {
        contextHolder.set(context);
    }

    /**
     * 获取线程中的context对象实例
     * @return
     */
    public static Context getContext() {
        return (Context) contextHolder.get();
    }
}
