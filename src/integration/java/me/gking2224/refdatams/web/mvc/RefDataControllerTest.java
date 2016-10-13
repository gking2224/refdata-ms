package me.gking2224.refdatams.web.mvc;

import static me.gking2224.common.utils.test.JsonMvcTestHelper.doGet;
import static me.gking2224.refdatams.web.mvc.RefDataController.LOCATIONS;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import me.gking2224.common.utils.test.JsonMvcTestHelper;
import me.gking2224.refdatams.mvc.RefDataWebAppTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration()
@ActiveProfiles({"embedded","web"})
@ContextConfiguration(classes=RefDataWebAppTestConfiguration.class)
@Transactional
@Rollback
@Sql("../../db/jpa/LocationRepositoryIT.sql")
public class RefDataControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetAllLocations() throws Exception {
        doGet(this.mockMvc, LOCATIONS, JsonMvcTestHelper::expectOK)
            .andDo(JsonMvcTestHelper::responseContent)
            .andExpect(jsonPath("$.length()").value(greaterThan(0)));
    }
}
