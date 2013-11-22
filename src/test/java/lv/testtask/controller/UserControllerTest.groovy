package lv.testtask.controller
import lv.testtask.server.EmbeddedJetty
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class UserControllerTest extends Specification {

    def "Test controller method"(){
        EmbeddedJetty embeddedJetty = new EmbeddedJetty()
        embeddedJetty.startServer();
        def entity = new RestTemplate().getForEntity("http://localhost:8888/loan/", String.class)
        when:
        entity
        then:
        entity != null
        entity.statusCode == HttpStatus.OK
        entity.body == "Hello"

        embeddedJetty.stop()
    }
}