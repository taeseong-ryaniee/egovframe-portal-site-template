package egovframework.let.cop.bbs.web

import egovframework.test.EgovTestAbstractSpringMvc
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

/**
 * [게시판생성관리][EgovBBSAdminManageController.insertBBSMasterInf] Controller 단위 테스트
 *
 * @author 이백행
 * @since 2024-09-21
 */
@RequiredArgsConstructor
@Slf4j
internal class EgovBBSAttributeManageControllerTestInsertBBSMasterInfTest : EgovTestAbstractSpringMvc() {
    /**
     * 신규 게시판 마스터 정보를 등록한다.
     *
     * @throws Exception
     */
    @Test
    @Throws(Exception::class)
    fun test() {
        // given

        // when

        mockMvc!!.perform(MockMvcRequestBuilders.post("/cop/bbs/insertBBSMasterInf.do"))
            .andExpect(MockMvcResultMatchers.status().isOk())

        // then
        if (EgovBBSAttributeManageControllerTestInsertBBSMasterInfTest.log.isDebugEnabled()) {
            EgovBBSAttributeManageControllerTestInsertBBSMasterInfTest.log.debug("test")
        }

        Assertions.assertEquals("", "", "신규 게시판 속성정보를 생성한다.")
    }
}