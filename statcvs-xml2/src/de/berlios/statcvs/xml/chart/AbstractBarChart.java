/*
    StatCvs - CVS statistics generation 
    Copyright (C) 2002  Lukasz Pekacki <lukasz@pekacki.de>
    http://statcvs.sf.net/
    
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
    
	$RCSfile: AbstractBarChart.java,v $
	$Date: 2004-02-21 14:46:06 $ 
*/
package de.berlios.statcvs.xml.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.DefaultCategoryDataset;

import de.berlios.statcvs.xml.output.ReportSettings;

/**
 * AbtractBarChart
 * 
 * @author Tammo van Lessen
 */
public abstract class AbstractBarChart extends AbstractChart {

	protected DefaultCategoryDataset dataset;
	
	/**
	 * @param filename
	 * @param title
	 */
	public AbstractBarChart(ReportSettings settings, String filename, String title, 
							String domainLabel, String rangeLabel)
	{
		super(settings, filename, title);
		
		dataset = new DefaultCategoryDataset();
		setChart(ChartFactory.createBarChart3D(
			settings.getProjectName(),  // chart title
			domainLabel,    // domain axis label
			rangeLabel,       // range axis label
			dataset,       // data
			PlotOrientation.VERTICAL,
			true,          // include legend
			true,          // tooltips
			false));          // urls
	}

	/**
	 * 
	 */
	private void createChart() {
		// create the chart...
		//placeTitle();      
	}

	/**
	 * @deprecated
	 */
	public void setCategoryAxisLabel(String text) {
		CategoryPlot plot = getChart().getCategoryPlot();
		plot.getDomainAxis().setLabel(text);
	}

	/**
	 * @deprecated
	 */
	public void setValueAxisLabel(String text) {
		CategoryPlot plot = getChart().getCategoryPlot();
		plot.getRangeAxis().setLabel(text);
	}

}
