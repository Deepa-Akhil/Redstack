package com.enterprise.common.pojo.quote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStepable implements Serializable, IAbstractStepable {
	private static final long serialVersionUID = 8586026779080287810L;
	private List<IStepable> steps = new ArrayList<IStepable>();
	private int activeStep = 0;

	public int getActiveStep() {
		return activeStep;
	}
	
	void setActiveStep(int activeStep) {
		this.activeStep = activeStep;
	}

	public void next() {
		activeStep += 1;
	}
	
	public void back() {
		activeStep -= 1;
	}

	public List<IStepable> getSteps() {
		return steps;
	}
	
	public IStepable getStepAtActiveIndex() {
		return steps.get(activeStep);
	}
}