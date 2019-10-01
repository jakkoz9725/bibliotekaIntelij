package com.company.secondImplementation;

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.ui.IStartup;

public class Schuede implements IStartup {

	private static final long STARTUP_DELAY = 1000; 
	protected static final long JOB_INTERVAL = 2000; 

	
	public void earlyStartup() {
		final Job updateJob = new CheckStatusJob("Refreshing data in the background");
		updateJob.schedule(STARTUP_DELAY);
		updateJob.addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(IJobChangeEvent event) {
				super.done(event);
				updateJob.schedule(JOB_INTERVAL);
			}
		});
	}
}