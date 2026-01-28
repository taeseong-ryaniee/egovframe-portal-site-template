package egovframework.test

import lombok.RequiredArgsConstructor
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.MethodName
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.util.StopWatch
import org.springframework.web.context.WebApplicationContext

/**
 * Spring MVC 테스트
 *
 * @author 이백행
 * @since 2024-09-21
 */
@ActiveProfiles("mysql", "dummy") //@ActiveProfiles({ "oracle", "dummy" })
//@ActiveProfiles({ "altibase", "dummy" })
//@ActiveProfiles({ "tibero", "dummy" })
//@ActiveProfiles({ "cubrid", "dummy" })
//@ActiveProfiles({ "maria", "dummy" })
//@ActiveProfiles({ "postgres", "dummy" })
//@ActiveProfiles({ "goldilocks", "dummy" })
@ExtendWith(SpringExtension::class)
@TestMethodOrder(MethodName::class)
@WebAppConfiguration
@ContextConfiguration(
    "classpath*:egovframework/spring/com/context-*.xml" //     "classpath*:egovframework/spring/com/idgn/context-*.xml",
    //     "classpath*:egovframework/spring/com/scheduling/context-*.xml",

    , "file:src/main/webapp/WEB-INF/config/egovframework/springmvc/egov-com-*.xml"

)
@RequiredArgsConstructor
open class EgovTestAbstractSpringMvc {
    /**
     * Before After
     */
    private val stopWatch = StopWatch()

    /**
     * ApplicationContext
     */
    @Autowired
    private val context: WebApplicationContext? = null

    /**
     * 서버 측 Spring MVC 테스트 지원을 위한 주요 진입점입니다.
     */
    @JvmField
    protected var mockMvc: MockMvc? = null

    /**
     * setUp
     */
    @BeforeEach
    fun setUp() {
        stopWatch.start()

        log.debug("setUp start")

        if (beanDefinitionNames == null) {
            beanDefinitionNames = context!!.getBeanDefinitionNames()
            for (beanDefinitionName in beanDefinitionNames!!) {
                log.debug("beanDefinitionName={}", beanDefinitionName)
            }
            if (log.isDebugEnabled()) {
                log.debug("length={}", beanDefinitionNames!!.size)
            }

            mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
        }
    }

    /**
     * tearDown
     */
    @AfterEach
    fun tearDown() {
        stopWatch.stop()

        if (log.isDebugEnabled()) {
            log.debug("tearDown stop")

            log.debug("totalTimeMillis={}", stopWatch.getTotalTimeMillis())
            log.debug("totalTimeSeconds={}", stopWatch.getTotalTimeSeconds())
        }
    }

    companion object {
        protected val log: Logger = LoggerFactory.getLogger(EgovTestAbstractSpringMvc::class.java)

        /**
         * BeforeClass AfterClass
         */
        private val STOP_WATCH = StopWatch()

        /**
         * beanDefinitionNames
         */
        private var beanDefinitionNames: Array<String?>?

        /**
         * setUpBeforeClass
         */
        @BeforeAll
        fun setUpBeforeClass() {
            STOP_WATCH.start()

            log.debug("setUpBeforeClass start")
        }

        /**
         * tearDownAfterClass
         */
        @AfterAll
        fun tearDownAfterClass() {
            STOP_WATCH.stop()

            if (log.isDebugEnabled()) {
                log.debug("tearDownAfterClass stop")

                log.debug("totalTimeMillis={}", STOP_WATCH.getTotalTimeMillis())
                log.debug("totalTimeSeconds={}", STOP_WATCH.getTotalTimeSeconds())
            }
        }
    }
}