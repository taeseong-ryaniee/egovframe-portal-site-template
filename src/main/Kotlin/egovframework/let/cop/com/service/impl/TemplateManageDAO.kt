package egovframework.let.cop.com.service.impl

import egovframework.let.cop.com.service.TemplateInf
import egovframework.let.cop.com.service.TemplateInfVO
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper
import org.springframework.stereotype.Repository

/**
 * 템플릿 정보관리를 위한 데이터 접근 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.03.17
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Repository("TemplateManageDAO")
class TemplateManageDAO : EgovAbstractMapper() {
    /**
     * 템플릿 정보를 삭제한다.
     *
     * @param tmplatInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteTemplateInf(tmplatInf: TemplateInf?) {
        update("TemplateManageDAO.deleteTemplateInf", tmplatInf)
    }

    /**
     * 템플릿 정보를 등록한다.
     *
     * @param tmplatInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertTemplateInf(tmplatInf: TemplateInf?) {
        insert("TemplateManageDAO.insertTemplateInf", tmplatInf)
    }

    /**
     * 템플릿 정보를 수정한다.
     *
     * @param tmplatInf
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateTemplateInf(tmplatInf: TemplateInf?) {
        update("TemplateManageDAO.updateTemplateInf", tmplatInf)
    }

    /**
     * 템플릿에 대한 목록를 조회한다.
     *
     * @param tmplatInfVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectTemplateInfs(tmplatInfVO: TemplateInfVO?): MutableList<TemplateInfVO?>? {
        return selectList<TemplateInfVO?>("TemplateManageDAO.selectTemplateInfs", tmplatInfVO)
    }

    /**
     * 템플릿에 대한 목록 전체 건수를 조회한다.
     *
     * @param tmplatInfVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectTemplateInfsCnt(tmplatInfVO: TemplateInfVO?): Int {
        return ((Integer)<Object> selectOne < kotlin . Any ? > ("TemplateManageDAO.selectTemplateInfsCnt", tmplatInfVO)).toInt()
    }

    /**
     * 템플릿에 대한 상세정보를 조회한다.
     *
     * @param tmplatInfVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectTemplateInf(tmplatInfVO: TemplateInfVO?): TemplateInfVO {
        return (TemplateInfVO)<Object> selectOne < kotlin . Any ? > ("TemplateManageDAO.selectTemplateInf", tmplatInfVO)
    }

    /**
     * 템플릿에 대한 미리보기 정보를 조회한다.
     *
     * @param tmplatInfVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectTemplatePreview(tmplatInfVO: TemplateInfVO?): TemplateInfVO? {
        return null
    }

    /**
     * 템플릿 구분에 따른 목록을 조회한다.
     *
     * @param tmplatInfVO
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectTemplateInfsByCode(tmplatInfVO: TemplateInfVO?): MutableList<TemplateInfVO?>? {
        return selectList<TemplateInfVO?>("TemplateManageDAO.selectTemplateInfsByCode", tmplatInfVO)
    }
}
