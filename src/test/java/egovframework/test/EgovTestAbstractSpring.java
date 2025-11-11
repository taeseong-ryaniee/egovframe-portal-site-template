package egovframework.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ActiveProfiles({ "mysql", "dummy" })
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
@Transactional
@Configuration
@ImportResource({
        "classpath*:egovframework/spring/com/test-context-common.xml",
        "classpath*:egovframework/spring/com/test-context-egovuserdetailshelper.xml",
        "classpath*:egovframework/spring/com/context-crypto.xml",
        "classpath*:egovframework/spring/com/context-datasource.xml",
        "classpath*:egovframework/spring/com/context-mapper.xml",
        "classpath*:egovframework/spring/com/context-properties.xml",
        "classpath*:egovframework/spring/com/context-transaction.xml",
})
@RequiredArgsConstructor
@Slf4j
public class EgovTestAbstractSpring {

    protected static final Logger log = LoggerFactory.getLogger(EgovTestAbstractSpring.class);
    private static final StopWatch STOP_WATCH = new StopWatch();
    private final StopWatch stopWatch = new StopWatch();
    private static String[] beanDefinitionNames;

    @Autowired
    private ApplicationContext context;

    @BeforeAll
    static void setUpBeforeClass() {
        STOP_WATCH.start();
        log.debug("setUpBeforeClass start");  // ← 변경
    }

    @AfterAll
    static void tearDownAfterClass() {
        STOP_WATCH.stop();
        if (log.isDebugEnabled()) {
            log.debug("tearDownAfterClass stop");
            log.debug("totalTimeMillis={}", STOP_WATCH.getTotalTimeMillis());
            log.debug("totalTimeSeconds={}", STOP_WATCH.getTotalTimeSeconds());
        }
    }

    @BeforeEach
    void setUp() {
        stopWatch.start();
        log.debug("setUp start");
        if (beanDefinitionNames == null) {
            beanDefinitionNames = context.getBeanDefinitionNames();
            for (final String beanDefinitionName : beanDefinitionNames) {
                log.debug("beanDefinitionName={}", beanDefinitionName);
            }
            if (log.isDebugEnabled()) {
                log.debug("length={}", beanDefinitionNames.length);
            }
        }
    }

    @AfterEach
    void tearDown() {
        stopWatch.stop();
        if (log.isDebugEnabled()) {
            log.debug("tearDown stop");
            log.debug("totalTimeMillis={}", stopWatch.getTotalTimeMillis());
            log.debug("totalTimeSeconds={}", stopWatch.getTotalTimeSeconds());
        }
    }
}