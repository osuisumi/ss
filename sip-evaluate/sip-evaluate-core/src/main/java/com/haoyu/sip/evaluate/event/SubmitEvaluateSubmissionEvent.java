package com.haoyu.sip.evaluate.event;

import org.springframework.context.ApplicationEvent;

public class SubmitEvaluateSubmissionEvent extends ApplicationEvent {

	private static final long serialVersionUID = -9067220462166338148L;

	public SubmitEvaluateSubmissionEvent(Object source) {
		super(source);
	}

}
