package egovframework.test

import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.MethodName
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StopWatch

@ActiveProfiles("mysql", "dummy")
@ExtendWith(SpringExtension::class)
@TestMethodOrder(MethodName::class)
@Transactional
@Configuration
@ImportResource(
    "classpath*:egovframework/spring/com/test-context-common.xml",
    "classpath*:egovframework/spring/com/test-context-egovuserdetailshelper.xml",
    "classpath*:egovframework/spring/com/context-crypto.xml",
    "classpath*:egovframework/spring/com/context-datasource.xml",
    "classpath*:egovframework/spring/com/context-mapper.xml",
    "classpath*:egovframework/spring/com/context-properties.xml",
    "classpath*:egovframework/spring/com/context-transaction.xml"
)
@RequiredArgsConstructor
@Slf4j
open class EgovTestAbstractSpring {
    private val stopWatch = StopWatch()

    @Autowired
    private val context: ApplicationContext? = null

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
        }
    }

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
        protected val log: Logger = LoggerFactory.getLogger(EgovTestAbstractSpring::class.java)
        private val STOP_WATCH = StopWatch()
        private var beanDefinitionNames: Array<String?>?

        @BeforeAll
        fun setUpBeforeClass() {
            STOP_WATCH.start()
            log.debug("setUpBeforeClass start") // ← 변경
        }

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