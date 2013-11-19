package lv.testtask
import com.hazelcast.core.HazelcastInstance
import lv.testtask.config.MvcConfig
import lv.testtask.validation.IpValidationData
import org.joda.time.DateTime
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

    @Autowired
    private HazelcastInstance instance



    def "check context not null"() {
        expect:
        ctx != null
    }

    def "check hazelcast server initialized"() {
        expect:
        instance != null
    }

    def "store/retrieve value with hazelcast"() {
        def map = instance.getMap("loanData")

        map.put("127.0.0.1", new IpValidationData(lastLoanTaken:new DateTime(2013,10,10,10,0), loansTakenInDay:1))

        when:
        IpValidationData result = map.get("127.0.0.1")

        then:
        result != null
        result.lastLoanTaken ==  new DateTime(2013,10,10,10,0)
        result.loansTakenInDay == 1
    }
}
