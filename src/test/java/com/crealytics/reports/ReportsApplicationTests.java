package com.crealytics.reports;

import com.crealytics.reports.util.Month;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"com.crealytics.reports.service", "com.crealytics.reports.controller",
		"com.crealytics.reports.listener"})
public class ReportsApplicationTests {

	public static final String SITE_DESKTOP_WEB = "desktop web";
	public static final String SITE_MOBILE_WEB = "mobile web";
	public static final String SITE_ANDROID = "android";
	public static final String SITE_IOS = "iOS";

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
		String month = Month.JANUARY.getValue();
		this.mockMvc.perform(get("/reports")
				.param("site", site)
				.param("month", month)
				.accept(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", equalTo(1)))
				.andExpect(jsonPath("$[0].site", equalTo(SITE_IOS)))
				.andExpect(jsonPath("$[0].month", equalTo(Month.JANUARY.getValue())))
				.andExpect(jsonPath("$[0].requests", equalTo(2550165)))
				.andExpect(jsonPath("$[0].impressions", equalTo(2419733)))
				.andExpect(jsonPath("$[0].clicks", equalTo(6331)))
				.andExpect(jsonPath("$[0].conversions", equalTo(1564)))
				.andExpect(jsonPath("$[0].revenue", closeTo(4692.28, 0.02)))

				.andExpect(jsonPath("$[0].ctr", closeTo(0.261, 0.002))) // (clicks ÷ impressions) × 100%
				.andExpect(jsonPath("$[0].cr", closeTo(0.064, 0.002))) // (conversions ÷ impressions) × 100%
				.andExpect(jsonPath("$[0].fill_rate", closeTo(94.885, 0.002))) // (impressions ÷ requests) × 100%
				.andExpect(jsonPath("$[0].eCPM", closeTo(1.939, 0.002))); // (revenue × 1000); ÷ impressions
	}

	@Test
	public void testMonthThreeLetters() throws Exception{
		String site = "iOS";
		String month = "Jan";
		this.mockMvc.perform(get("/reports")
				.param("site", site)
				.param("month", month)
				.accept(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", equalTo(1)))
				.andExpect(jsonPath("$[0].site", equalTo(SITE_IOS)))
				.andExpect(jsonPath("$[0].month", equalTo(Month.JANUARY.getValue())))
				.andExpect(jsonPath("$[0].requests", equalTo(2550165)))
				.andExpect(jsonPath("$[0].impressions", equalTo(2419733)))
				.andExpect(jsonPath("$[0].clicks", equalTo(6331)))
				.andExpect(jsonPath("$[0].conversions", equalTo(1564)))
				.andExpect(jsonPath("$[0].revenue", closeTo(4692.28, 0.02)))

				.andExpect(jsonPath("$[0].ctr", closeTo(0.261, 0.002))) // (clicks ÷ impressions) × 100%
				.andExpect(jsonPath("$[0].cr", closeTo(0.064, 0.002))) // (conversions ÷ impressions) × 100%
				.andExpect(jsonPath("$[0].fill_rate", closeTo(94.885, 0.002))) // (impressions ÷ requests) × 100%
				.andExpect(jsonPath("$[0].eCPM", closeTo(1.939, 0.002))); // (revenue × 1000); ÷ impressions
		;
	}

	@Test
	public void testMonthNumber() throws Exception{
		String site = "iOS";
		String month = "1";
		this.mockMvc.perform(get("/reports")
				.param("site", site)
				.param("month", month)
				.accept(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()", equalTo(1)))
				.andExpect(jsonPath("$[0].site", equalTo(SITE_IOS)))
				.andExpect(jsonPath("$[0].month", equalTo(Month.JANUARY.getValue())))
				.andExpect(jsonPath("$[0].requests", equalTo(2550165)))
				.andExpect(jsonPath("$[0].impressions", equalTo(2419733)))
				.andExpect(jsonPath("$[0].clicks", equalTo(6331)))
				.andExpect(jsonPath("$[0].conversions", equalTo(1564)))
				.andExpect(jsonPath("$[0].revenue", closeTo(4692.28, 0.02)))

				.andExpect(jsonPath("$[0].ctr", closeTo(0.261, 0.002))) // (clicks ÷ impressions) × 100%
				.andExpect(jsonPath("$[0].cr", closeTo(0.064, 0.002))) // (conversions ÷ impressions) × 100%
				.andExpect(jsonPath("$[0].fill_rate", closeTo(94.885, 0.002))) // (impressions ÷ requests) × 100%
				.andExpect(jsonPath("$[0].eCPM", closeTo(1.939, 0.002))); // (revenue × 1000); ÷ impressions
		;
	}

	@Test
	public void testEmptyParams() throws Exception {
		String site = "";
		String month = "";
		this.mockMvc.perform(get("/reports")
				.param("site", site)
				.param("month", month)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

}
