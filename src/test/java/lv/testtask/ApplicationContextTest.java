package lv.testtask;

import lv.testtask.config.MvcConfig;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MvcConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class ApplicationContextTest {


    @Autowired
    private AbstractApplicationContext ctx;

    @Test
    public void test(){
        assertThat(ctx, notNullValue());

    }
}
