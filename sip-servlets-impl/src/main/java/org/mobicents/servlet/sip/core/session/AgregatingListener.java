package org.mobicents.servlet.sip.core.session;

import javax.servlet.sip.ServletTimer;
import javax.servlet.sip.SipApplicationSessionEvent;
import javax.servlet.sip.SipApplicationSessionListener;
import javax.servlet.sip.TimerListener;

public class AgregatingListener implements TimerListener {

	
	SipApplicationSessionImpl _app=null;
	
	
	
	public AgregatingListener(SipApplicationSessionImpl _app) {
		super();
		this._app = _app;
	}



	public void timeout(ServletTimer timer) {
		
		if(timer.getInfo().equals(this._app.getEndObject()))
		{
			SipApplicationSessionEvent ev=new SipApplicationSessionEvent(this._app);
			for(SipApplicationSessionListener l: this._app.getListeners().getSipApplicationSessionListeners())
				l.sessionExpired(ev);
			this._app.expirationTimerFired();
		}else
		{
			
			for(TimerListener l:this._app.getListeners().getTimerListeners())
			{
				l.timeout(timer);
			}
			
		}

	}

}
