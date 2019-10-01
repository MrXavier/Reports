package com.crealytics.reports;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportsApplicationTests {

	public static final String SITE_DESKTOP_WEB = "desktop web";
	public static final String SITE_MOBILE_WEB = "mobile web";
	public static final String SITE_ANDROID = "android";
	public static final String SITE_IOS = "iOS";

	public static final String JANUARY = "January";
	public static final String FEBRUARY = "February";

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testMonthFullName() throws Exception{
		String site = "iOS";
		String month = "January";
		this.mockMvc.perform(get("/reports")
				.requestAttr("site", site)
				.requestAttr("month", month)
				.accept(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk())
				.andExpect(jsonPath("site", equalTo(SITE_IOS)))
				.andExpect(jsonPath("month", equalTo("January")))
				.andExpect(jsonPath("request", equalTo(2550165)))
				.andExpect(jsonPath("impressions", equalTo(2419733)))
				.andExpect(jsonPath("clicks", equalTo(6331)))
				.andExpect(jsonPath("conversions", equalTo(1564)))
				.andExpect(jsonPath("revenue", closeTo(4692.28, 0.02)))

				.andExpect(jsonPath("ctr", closeTo(0.26, 0.02))) // (clicks ÷ impressions) × 100%
				.andExpect(jsonPath("cr", closeTo(0.064, 0.02))) // (conversions ÷ impressions) × 100%
				.andExpect(jsonPath("fill_rate", closeTo(94.88, 0.02))) // (impressions ÷ requests) × 100%
				.andExpect(jsonPath("eCPM", closeTo(1.939, 0.02))) // (revenue × 1000) ÷ impressions
				;
	}

	@Test
	public void testMonthThreeLetters() throws Exception{
		String site = "iOS";
		String month = "Jan";
		this.mockMvc.perform(get("/reports")
				.requestAttr("site", site)
				.requestAttr("month", month)
				.accept(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk())
				.andExpect(jsonPath("site", equalTo(SITE_IOS)))
				.andExpect(jsonPath("month", equalTo("January")))
				.andExpect(jsonPath("request", equalTo(2550165)))
				.andExpect(jsonPath("impressions", equalTo(2419733)))
				.andExpect(jsonPath("clicks", equalTo(6331)))
				.andExpect(jsonPath("conversions", equalTo(1564)))
				.andExpect(jsonPath("revenue", closeTo(4692.28, 0.02)))

				.andExpect(jsonPath("ctr", closeTo(0.26, 0.02))) // (clicks ÷ impressions) × 100%
				.andExpect(jsonPath("cr", closeTo(0.064, 0.02))) // (conversions ÷ impressions) × 100%
				.andExpect(jsonPath("fill_rate", closeTo(94.88, 0.02))) // (impressions ÷ requests) × 100%
				.andExpect(jsonPath("eCPM", closeTo(1.939, 0.02))) // (revenue × 1000) ÷ impressions
		;
	}

	@Test
	public void testMonthNumber() throws Exception{
		String site = "iOS";
		String month = "1";
		this.mockMvc.perform(get("/reports")
				.requestAttr("site", site)
				.requestAttr("month", month)
				.accept(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk())
				.andExpect(jsonPath("site", equalTo(SITE_IOS)))
				.andExpect(jsonPath("month", equalTo("January")))
				.andExpect(jsonPath("request", equalTo(2550165)))
				.andExpect(jsonPath("impressions", equalTo(2419733)))
				.andExpect(jsonPath("clicks", equalTo(6331)))
				.andExpect(jsonPath("conversions", equalTo(1564)))
				.andExpect(jsonPath("revenue", closeTo(4692.28, 0.02)))

				.andExpect(jsonPath("ctr", closeTo(0.26, 0.02))) // (clicks ÷ impressions) × 100%
				.andExpect(jsonPath("cr", closeTo(0.064, 0.02))) // (conversions ÷ impressions) × 100%
				.andExpect(jsonPath("fill_rate", closeTo(94.88, 0.02))) // (impressions ÷ requests) × 100%
				.andExpect(jsonPath("eCPM", closeTo(1.939, 0.02))) // (revenue × 1000) ÷ impressions
		;
	}

	@Test
	public void testEmptyParams() throws Exception {
		String site = "";
		String month = "";
		this.mockMvc.perform(get("/reports")
				.requestAttr("site", site)
				.requestAttr("month", month)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

}
