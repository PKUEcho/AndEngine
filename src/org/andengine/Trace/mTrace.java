package org.andengine.Trace;

import java.lang.reflect.Method;

import android.os.Trace;

public final class mTrace {
	/**
     * Writes trace message to indicate the value of a given counter.
     *
     * @param traceTag The trace tag.
     * @param counterName The counter name to appear in the trace.
     * @param counterValue The counter value.
     *
     */
    public static void traceCounter(long traceTag, String counterName, int counterValue) {
    	try {
        	Method m = Class.forName("android.os.Trace").getMethod("traceCounter", new Class[] {long.class, String.class, int.class});
			m.invoke(null, new Object[] {traceTag, counterName, counterValue});
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
     * Writes a trace message to indicate that a given section of code has
     * begun. Must be followed by a call to {@link #asyncTraceEnd} using the same
     * tag. Unlike {@link #traceBegin(long, String)} and {@link #traceEnd(long)},
     * asynchronous events do not need to be nested. The name and cookie used to
     * begin an event must be used to end it.
     *
     * @param traceTag The trace tag.
     * @param methodName The method name to appear in the trace.
     * @param cookie Unique identifier for distinguishing simultaneous events
     *
     */
    public static void asyncTraceBegin(long traceTag, String methodName, int cookie) {
        try {
        	Method m = Class.forName("android.os.Trace").getMethod("asyncTraceBegin", new Class[] {long.class, String.class, int.class});
			m.invoke(null, new Object[] {traceTag, methodName, cookie});
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    /**
     * Writes a trace message to indicate that the current method has ended.
     * Must be called exactly once for each call to {@link #asyncTraceBegin(long, String, int)}
     * using the same tag, name and cookie.
     *
     * @param traceTag The trace tag.
     * @param methodName The method name to appear in the trace.
     * @param cookie Unique identifier for distinguishing simultaneous events
     *
     */
    public static void asyncTraceEnd(long traceTag, String methodName, int cookie) {
        try {
        	Method m = Class.forName("android.os.Trace").getMethod("asyncTraceEnd", new Class[] {long.class, String.class, int.class});
			m.invoke(null, new Object[] {traceTag, methodName, cookie});
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
     * Writes a trace message to indicate that a given section of code has begun. This call must
     * be followed by a corresponding call to {@link #endSection()} on the same thread.
     *
     * <p class="note"> At this time the vertical bar character '|', newline character '\n', and
     * null character '\0' are used internally by the tracing mechanism.  If sectionName contains
     * these characters they will be replaced with a space character in the trace.
     *
     * @param sectionName The name of the code section to appear in the trace.  This may be at
     * most 127 Unicode code units long.
     */
    public static void beginSection(String sectionName) {
        Trace.beginSection(sectionName);
    }

    /**
     * Writes a trace message to indicate that a given section of code has ended. This call must
     * be preceeded by a corresponding call to {@link #beginSection(String)}. Calling this method
     * will mark the end of the most recently begun section of code, so care must be taken to
     * ensure that beginSection / endSection pairs are properly nested and called from the same
     * thread.
     */
    public static void endSection() {
        Trace.endSection();
    }
}