package org.andengine.Trace;

import java.util.ArrayList;

import android.util.Log;

public class EventTracer {
	private static ArrayList<EventObject> ObjectList;
	private static ArrayList<EventTimestamp> stampList;
	private static int cur_event_id;
	public static ArrayList<EventObject> getObjectList() {return ObjectList;}
	static {
		ObjectList = new ArrayList<EventObject>();
		stampList = new ArrayList<EventTimestamp>();
		cur_event_id = 0;
	}
	public static void addEvent(Object item, String _name) {
		// Log.i("EventTracer", "New Event: " + _name);
		EventTimestamp et = new EventTimestamp(
				Thread.currentThread().getId(),
				cur_event_id,
				System.currentTimeMillis(),
				Thread.currentThread().getName(),
				"null",
				_name,
				0);
		et.print();
		stampList.add(et);
		ObjectList.add(new EventObject(cur_event_id ++, item, _name));
	}
	public static void rmEvent(int event_id) {
		
	}
	public static void addEventObject(Object item) {
		
	}
	public static void rmEventObject(Object item) {
		int len = ObjectList.size();
		for (int i = 0; i < len; ++ i) {
			EventObject obj = ObjectList.get(i);
			if (obj.getObject() == item) {
				// Log.i("EventTracer", "EventObject Removed: " + obj.get_name());
				EventTimestamp et = new EventTimestamp(
						Thread.currentThread().getId(),
						obj.getEventID(),
						System.currentTimeMillis(),
						Thread.currentThread().getName(),
						"null",
						obj.get_name(),
						1);
				et.print();
				stampList.add(et);
				ObjectList.remove(i);
				break;
			}
		}
	}
	public static void proEventObject(Object old_object, Object new_object, String new_name) {
		int len = ObjectList.size();
		for (int i = 0; i < len; ++ i) {
			EventObject obj = ObjectList.get(i);
			if (obj.getObject() == old_object) {
				// Log.i("EventTracer", "EventObject Created: " + new_name + " by " + obj.get_name());
				EventTimestamp et = new EventTimestamp(
						Thread.currentThread().getId(),
						obj.getEventID(),
						System.currentTimeMillis(),
						Thread.currentThread().getName(),
						"null",
						new_name,
						2);
				et.print();
				stampList.add(et);
				ObjectList.add(new EventObject(obj.getEventID(), new_object, new_name));
				break;
			}
		}
	}
	public static void addObjectTimestamp(Object item, String desc) {
		int len = ObjectList.size();
		for (int i = 0; i < len; ++ i) {
			EventObject obj = ObjectList.get(i);
			if (obj.getObject() == item) {
				EventTimestamp et = new EventTimestamp(
						Thread.currentThread().getId(),
						obj.getEventID(),
						System.currentTimeMillis(),
						Thread.currentThread().getName(),
						desc,
						obj.get_name(),
						3);
				et.print();
				stampList.add(et);
				break;
			}
		}
	}
}

/*class EventItem {
	int EventID;
	ArrayList<EventObject> ObjectItems;
	EventItem() {
		
	}
	void addObject(Object o) {
		
	}
	void rmObject(Object o) {
		
	}
	void sftEventObject(Object old_o, Object new_o) {
		
	}
}*/

class EventObject {
	private Object o;
	private int event_id;
	private String object_name;
	public EventObject(int _tid, Object _o, String _name) {
		object_name = _name;
		event_id = _tid;
		o = _o;
	}
	public String get_name() {return object_name;}
	public void setObject(Object _o) {
		o = _o;
	}
	public Object getObject() {
		return o;
	}
	public int getEventID() {
		return event_id;
	}
}

class EventTimestamp {
	long thread_id;
	int event_id;
	int event_type; // 0: new event; 1: remove event object; 2: pro event object; 3: timestamp
	long time_stamp;
	String thread_name;
	String event_info;
	String object_name;
	public EventTimestamp(long tid, int eid, long ts, String tn, String ei, String on, int _et) {
		thread_id = tid;
		event_id = eid;
		time_stamp = ts;
		thread_name = tn;
		event_info = ei;
		object_name = on;
		event_type = _et;
	}
	public void print() {
		Log.i("EventTracer", thread_id + "-" + thread_name + " " + event_id + " " + event_type +  " " + time_stamp + " " + object_name + " " + event_info + "\n");
	}
}