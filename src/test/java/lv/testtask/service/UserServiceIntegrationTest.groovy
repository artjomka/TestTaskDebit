package lv.testtask.service

import lv.testtask.config.MvcConfig
import lv.testtask.repository.MainRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.AnnotationConfigWebContextLoader
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@ContextConfiguration(classes =  [MvcConfig], loader = AnnotationConfigWebContextLoader.class)
@ActiveProfiles("dev")
@WebAppConfiguration
@Transactional
class UserServiceIntegrationTest extends Specification {

    @Autowired
    private UserService userService

    @Autowired
    private MainRepository loanRepository

    def "Register user"(){
        def userString = "{\"username\":\"23232323\",\"email\":\"artjom.kalita@gmail.com\",\"password\":\"mypass\"}"
        def result = userService.registerUser(userString)
        when:
        result
        then :
        result != null
        result == "{\"status\":\"SUCCESS\",\"errorData\":[]}"

        def user = loanRepository.getUserByUsernameAndMail("23232323", "artjom.kalita@gmail.com")
        when:
        user

        then:
        user != null
        user.password == "mypass"
        user.username == "23232323"
        user.email == "artjom.kalita@gmail.com"

        def authorities =  loanRepository.getAuthoritiesByUsername(user.username)
        when:
        authorities
        then:
        authorities != null
        authorities.authority == "USER"
    }


}