package com.vinod.test;

import org.drools.core.event.BeforeActivationFiredEvent;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.MatchEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;

public class CustomerRuleTracker extends DefaultAgendaEventListener {

	@Override
	public void afterMatchFired(AfterMatchFiredEvent event) {
		System.out.println("Executing afterMatchFired ");
	}
	@Override
	public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
		System.out.println("Executing beforeRuleFlowGroupActivated ");

	}
	@Override
	public void beforeMatchFired(BeforeMatchFiredEvent event) {
		System.out.println("Executing beforeMatchFired ");
	}
	public void beforeActivationFired(BeforeActivationFiredEvent event) {
		System.out.println("Executing beforeActivationFired ");

	}
	@Override
	public void matchCreated(MatchCreatedEvent event) {
		System.out.println("Executing matchCreated ");
		registerActions(event);
	}
	@Override
	public void matchCancelled(MatchCancelledEvent event) {
		System.out.println("Executing matchCancelled");

	}
	private void registerActions(MatchEvent event) {
		System.out.println("Executing registerActions");
	}
}