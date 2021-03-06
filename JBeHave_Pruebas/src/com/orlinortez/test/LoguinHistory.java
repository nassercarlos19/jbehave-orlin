package com.orlinortez.test;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

import java.net.URL;

import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.web.selenium.LocalFrameContextView;
import org.jbehave.web.selenium.SeleniumContext;
import com.orlinortez.steps.TestSteps;
import java.util.List;

import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.web.selenium.ContextView;
import org.jbehave.web.selenium.SeleniumConfiguration;
import org.jbehave.web.selenium.SeleniumContextOutput;
import org.jbehave.web.selenium.SeleniumStepMonitor;
import org.junit.runner.RunWith;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.web.selenium.WebDriverHtmlOutput.WEB_DRIVER_HTML;

@RunWith(JUnitReportingRunner.class)
public class LoguinHistory extends JUnitStories  {

	@Override
	public List candidateSteps() {
		InstanceStepsFactory stepsFactory = new InstanceStepsFactory(
				configuration(), new TestSteps());
		return stepsFactory.createCandidateSteps();
	}

	public Configuration configuration() {
		CrossReference crossReference = new CrossReference().withJsonOnly()
				.withOutputAfterEachStory(true)
				.excludingStoriesWithNoExecutedScenarios(true);
		ContextView contextView = new LocalFrameContextView().sized(640, 120);
		SeleniumContext seleniumContext = new SeleniumContext();
		SeleniumStepMonitor stepMonitor = new SeleniumStepMonitor(contextView,
				seleniumContext, crossReference.getStepMonitor());
		Format[] formats = new Format[] {
				new SeleniumContextOutput(seleniumContext), CONSOLE,
				WEB_DRIVER_HTML };
		StoryReporterBuilder reporterBuilder = new StoryReporterBuilder()
				.withCodeLocation(codeLocationFromClass(this.getClass()))
				.withFailureTrace(true).withFailureTraceCompression(true)
				.withDefaultFormats().withFormats(formats)
				.withCrossReference(crossReference);

		Configuration configuration = new SeleniumConfiguration()
				.useSeleniumContext(seleniumContext)
				.useFailureStrategy(new FailingUponPendingStep())
				.useStoryControls(
						new StoryControls().doResetStateBeforeScenario(false))
				.useStepMonitor(stepMonitor)
				.useStoryLoader(new LoadFromClasspath(this.getClass()))
				.useStoryReporterBuilder(reporterBuilder);
		useConfiguration(configuration);
		return configuration;
	}

	/*@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration()
		// where to find the stories
				.useStoryLoader(new LoadFromClasspath(this.getClass()))
				// CONSOLE and TXT reporting
				.useStoryReporterBuilder(
						new StoryReporterBuilder().withDefaultFormats()
								.withFormats(Format.CONSOLE, Format.TXT));
	}*/

	 
	public List<CandidateSteps> CandidateSteps() { 
		return new InstanceStepsFactory(configuration(), new TestSteps())
				.createCandidateSteps();
	}

	@Override
	protected List<String> storyPaths() {
		// TODO Auto-generated method stub
		return null;
	}
}