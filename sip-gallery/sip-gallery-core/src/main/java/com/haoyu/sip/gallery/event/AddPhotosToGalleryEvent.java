package com.haoyu.sip.gallery.event;

import org.springframework.context.ApplicationEvent;

public class AddPhotosToGalleryEvent extends ApplicationEvent{
	private static final long serialVersionUID = 513708221171204016L;
	public AddPhotosToGalleryEvent(Object source) {
		super(source);
	}
}
