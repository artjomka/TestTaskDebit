package lv.testtask.controller

import lv.testtask.server.EmbeddedJetty
import org.apache.http.HttpHeaders
import spock.lang.Specification

import static com.jayway.restassured.RestAssured.given

class LoanControllerTest extends Specification {

    def "Unathorized request"(){
        EmbeddedJetty embeddedJetty = new EmbeddedJetty()
        embeddedJetty.startServer();
        def variables = [:]
        def entity = given().header(HttpHeaders.ACCEPT, "application/json").post("http://localhost:8888/loan/", variables)
        when:
        entity
        then:
        entity != null
        entity.statusCode == 405

        embeddedJetty.stop()
    }

    def "Registered user authorized request "(){
        EmbeddedJetty embeddedJetty = new EmbeddedJetty()
        embeddedJetty.startServer();
        def userString = "{\"username\":\"23232323\",\"email\":\"artjom.kalita@gmail.com\",\"password\":\"mypass\"}"
        given().header(HttpHeaders.ACCEPT, "application/json").formParam("userJson", userString).post("http://localhost:8888/user/register")
        def loanData = "{\"taken\":\"2013-10-10 13:40\",\"returnTill\":\"2013-12-10 08:52\",\"amount\":\"100.2\",\"interest\":\"10.1\",\"currency\":\"EUR\"}"
        def entity = given().header(HttpHeaders.ACCEPT, "application/json").auth().basic("23232323", "mypass").formParam("loanJson", loanData)
                .post("http://localhost:8888/loan/takeLoan/")
        when:
        entity
        then:
        entity != null
        entity.statusCode == 200
    }

}