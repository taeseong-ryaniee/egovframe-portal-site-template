package egovframework.let.cop.com.service.impl

import egovframework.let.cop.com.service.EgovTemplateManageService
import egovframework.let.cop.com.service.TemplateInf
import egovframework.let.cop.com.service.TemplateInfVO
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 템플릿 정보관리를 위한 서비스 구현 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.3.17
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("EgovTemplateManageService")
class EgovTemplateManageServiceImpl : EgovAbstractServiceImpl(), EgovTemplateManageService {
    @Resource(name = "TemplateManageDAO")
    private val tmplatDAO: TemplateManageDAO? = null

    @Resource(name = "egovTmplatIdGnrService")
    private val idgenService: EgovIdGnrService? = null

    /**
     * 템플릿 정보를 삭제한다.
     *
     * @see egovframework.let.cop.bbs.com.service.EgovTemplateManageService.deleteTemplateInf
     */
    @Throws(Exception::class)
    override fun deleteTemplateInf(tmplatInf: TemplateInf?) {
        tmplatDAO!!.deleteTemplateInf(tmplatInf)
    }

    /**
     * 템플릿 정보를 등록한다.
     *
     * @see egovframework.let.cop.bbs.com.service.EgovTemplateManageService.insertTemplateInf
     */
    @Throws(Exception::class)
    override fun insertTemplateInf(tmplatInf: TemplateInf) {
        tmplatInf.tmplatId = idgenService!!.getNextStringId()

        tmplatDAO!!.insertTemplateInf(tmplatInf)
    }

    /**
     * 템플릿에 대한 상세정보를 조회한다.
     *
     * @see egovframework.let.cop.bbs.com.service.EgovTemplateManageService.selectTemplateInf
     */
    @Throws(Exception::class)
    override fun selectTemplateInf(tmplatInfVO: TemplateInfVO?): TemplateInfVO? {
        var vo: TemplateInfVO? = TemplateInfVO()
        vo = tmplatDAO!!.selectTemplateInf(tmplatInfVO)
        return vo
    }

    /**
     * 템플릿에 대한 목록를 조회한다.
     *
     * @see egovframework.let.cop.bbs.com.service.EgovTemplateManageService.selectTemplateInfs
     */
    @Throws(Exception::class)
    override fun selectTemplateInfs(tmplatInfVO: TemplateInfVO?): MutableMap<String?, Any?> {
        val result = tmplatDAO!!.selectTemplateInfs(tmplatInfVO)
        val cnt = tmplatDAO.selectTemplateInfsCnt(tmplatInfVO)

        val map: MutableMap<String?, Any?> = HashMap<String?, Any?>()

        map.put("resultList", result)
        map.put("resultCnt", cnt.toString())

        return map
    }

    /**
     * 템플릿에 대한 미리보기 정보를 조회한다.
     *
     * @see egovframework.let.cop.bbs.com.service.EgovTemplateManageService.selectTemplatePreview
     */
    @Throws(Exception::class)
    override fun selectTemplatePreview(tmplatInfVO: TemplateInfVO?): TemplateInfVO? {
        var vo: TemplateInfVO? = TemplateInfVO()

        vo = tmplatDAO!!.selectTemplatePreview(tmplatInfVO)

        return vo
    }

    /**
     * 템플릿 정보를 수정한다.
     *
     * @see egovframework.let.cop.bbs.com.service.EgovTemplateManageService.updateTemplateInf
     */
    @Throws(Exception::class)
    override fun updateTemplateInf(tmplatInf: TemplateInf?) {
        tmplatDAO!!.updateTemplateInf(tmplatInf)
    }

    /**
     * 템플릿 구분에 따른 목록을 조회한다.
     *
     * @see egovframework.let.cop.bbs.com.service.EgovTemplateManageService.selectAllTemplateInfs
     */
    @Throws(Exception::class)
    override fun selectTemplateInfsByCode(tmplatInfVO: TemplateInfVO?): MutableList<TemplateInfVO?>? {
        return tmplatDAO!!.selectTemplateInfsByCode(tmplatInfVO)
    }
}
