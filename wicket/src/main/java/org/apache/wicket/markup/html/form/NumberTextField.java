/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.markup.html.form;

import org.apache.wicket.model.IModel;
import org.apache.wicket.validation.validator.RangeValidator;

/**
 * A {@link TextField} for HTML5 &lt;input&gt; with type <strong>number</strong>.
 * 
 * <p>
 * Automatically validates the input against the configured {@link #setMinimum(Double) min} and
 * {@link #setMaximum(Double) max} attributes. If any of them is <code>null</code> then
 * {@link Double#MIN_VALUE} and {@link Double#MAX_VALUE} are used respectfully.
 */
public class NumberTextField extends TextField<Double>
{
	private static final long serialVersionUID = 1L;

	private RangeValidator<Double> validator;

	private Double minimum;

	private Double maximum;

	/**
	 * Construct.
	 * 
	 * @param id
	 *            component id
	 */
	public NumberTextField(String id)
	{
		this(id, null);
	}

	/**
	 * Construct.
	 * 
	 * @param id
	 *            see Component
	 * @param model
	 *            the model
	 */
	public NumberTextField(String id, IModel<Double> model)
	{
		super(id, model, Double.class);

		validator = null;
		minimum = null;
		maximum = null;
	}

	/**
	 * @param minimum
	 *            the minimum allowed value
	 * @return this instance
	 */
	public NumberTextField setMinimum(final Double minimum)
	{
		this.minimum = minimum;
		return this;
	}

	/**
	 * @param maximum
	 *            the maximum allowed value
	 * @return this instance
	 */
	public NumberTextField setMaximum(final Double maximum)
	{
		this.maximum = maximum;
		return this;
	}

	@Override
	public void onConfigure()
	{
		super.onConfigure();

		if (validator != null)
		{
			remove(validator);
		}

		Double min = minimum;
		Double max = maximum;

		if (min == null)
		{
			min = Double.MIN_VALUE;
		}

		if (max == null)
		{
			max = Double.MAX_VALUE;
		}

		validator = new RangeValidator<Double>(min, max);
		add(validator);
	}

	@Override
	protected String getInputType()
	{
		return "number";
	}
}
