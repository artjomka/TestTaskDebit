package lv.testtask

import lv.testtask.config.MvcConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.AnnotationConfigWebContextLoader
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification


@ContextConfiguration(classes =  [MvcConfig], loader = AnnotationConfigWebContextLoader.class)
@ActiveProfiles("dev")
@WebAppConfiguration
class ApplicationSpockTest extends Specification {

    @Autowired
    private AbstractApplicationContext ctx

    def "check context not null"() {
        expect:
        ctx != null
    }
}
