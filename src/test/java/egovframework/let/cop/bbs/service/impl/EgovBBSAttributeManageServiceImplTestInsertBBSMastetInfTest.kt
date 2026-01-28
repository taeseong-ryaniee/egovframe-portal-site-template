package egovframework.let.cop.bbs.service.impl

import egovframework.com.cmm.LoginVO
import egovframework.com.cmm.util.EgovUserDetailsHelper.Companion.authenticatedUser
import egovframework.let.cop.bbs.service.BoardMaster
import egovframework.let.cop.bbs.service.EgovBBSAttributeManageService
import egovframework.let.cop.com.service.EgovUserInfManageService
import egovframework.let.cop.com.service.impl.BBSUseInfoManageDAO
import egovframework.let.cop.com.service.impl.EgovUserInfManageDAO
import egovframework.test.EgovTestAbstractSpring
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import org.springframework.context.annotation.ImportResource
import org.springframework.test.context.ContextConfiguration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * [게시판생성관리][EgovBBSAttributeManageServiceImpl.insertBBSMastetInf] ServiceImpl
 * 단위 테스트
 *
 * @author 이백행
 * @since 2024-09-21
 */
@ContextConfiguration(
    classes = [EgovBBSAttributeManageServiceImplTestInsertBBSMastetInfTest::class, EgovTestAbstractSpring::class]
)
@Configuration
@ImportResource("classpath*:egovframework/spring/com/context-idgen.xml")
@ComponentScan(
    useDefaultFilters = false,
    basePackages = ["egovframework.let.cop.bbs.service.impl", "egovframework.let.cop.com.service.impl"],
    includeFilters = [ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = [EgovBBSAttributeManageServiceImpl::class, BBSAttributeManageDAO::class, BBSUseInfoManageDAO::class, EgovUserInfManageService::class, EgovUserInfManageDAO::class, BBSAddedOptionsDAO::class]
    )]
)
@RequiredArgsConstructor
@Slf4j
internal class EgovBBSAttributeManageServiceImplTestInsertBBSMastetInfTest : EgovTestAbstractSpring() {
    /**
     * 게시판 속성관리를 위한 서비스 인터페이스 클래스
     */
    @Autowired
    private val egovBBSAttributeManageService: EgovBBSAttributeManageService? = null

    /**
     * 신규 게시판 속성정보를 생성한다.
     *
     * @throws Exception
     */
    @Test
    @Throws(Exception::class)
    fun test() {
        // given
        val boardMaster = BoardMaster()

        // 게시판ID
        val now = LocalDateTime.now()
        val now2 = now.format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss"))

        //		final String now3 = now.format(DateTimeFormatter.ofPattern("uuuuMMddHHmmssS"));
//		boardMaster.setBbsId("TEST_" + now3);

        // 게시판유형코드
        // SELECT A.* FROM LETTCCMMNDETAILCODE AS A WHERE A.CODE_ID = 'COM004';
        boardMaster.bbsTyCode = "BBST01"

        // SELECT A.* FROM LETTCCMMNDETAILCODE AS A WHERE A.CODE_ID = 'COM009';
        boardMaster.bbsAttrbCode = "BBSA03"

        // 게시판명
        boardMaster.bbsNm = "test 이백행 게시판명 " + now2

        // 게시판소개
        boardMaster.bbsIntrcn = "test 이백행 게시판소개 " + now2

        // 답장가능여부
        boardMaster.replyPosblAt = "N"

        // 파일첨부가능여부
        boardMaster.fileAtchPosblAt = "Y"

        // 첨부가능파일숫자
        boardMaster.posblAtchFileNumber = 3

        // 첨부가능파일사이즈
        boardMaster.posblAtchFileSize = "0"

        // 템플릿ID
        boardMaster.tmplatId = "TMPLAT_BOARD_DEFAULT"

        // 사용여부
        boardMaster.useAt = "Y"

        val loginVO = authenticatedUser as LoginVO?
        if (loginVO != null) {
            // 최초등록자ID
            boardMaster.frstRegisterId = loginVO.uniqId
        }

        // when
        val result = egovBBSAttributeManageService!!.insertBBSMastetInf(boardMaster)

        // then
        if (EgovBBSAttributeManageServiceImplTestInsertBBSMastetInfTest.log.isDebugEnabled()) {
            EgovBBSAttributeManageServiceImplTestInsertBBSMastetInfTest.log.debug("boardMaster={}", boardMaster)
            EgovBBSAttributeManageServiceImplTestInsertBBSMastetInfTest.log.debug(
                "getBbsId={}, {}",
                boardMaster.bbsId,
                result
            )
        }

        Assertions.assertEquals(boardMaster.bbsId, result, "신규 게시판 속성정보를 생성한다.")
    }
}