package egovframework.let.cop.com.service

/**
 * 템플릿 관리를 위한 서비스 인터페이스 클래스
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.03.17
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovTemplateManageService {
    /**
     * 템플릿 정보를 삭제한다.
     *
     * @param tmplatInf
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun deleteTemplateInf(tmplatInf: TemplateInf?)

    /**
     * 템플릿 정보를 등록한다.
     *
     * @param tmplatInf
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun insertTemplateInf(tmplatInf: TemplateInf?)

    /**
     * 템플릿에 대한 상세정보를 조회한다.
     * @return
     *
     * @param tmplatInfVO
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun selectTemplateInf(tmplatInfVO: TemplateInfVO?): TemplateInfVO?

    /**
     * 템플릿에 대한 목록를 조회한다.
     * @return
     *
     * @param tmplatInfVO
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun selectTemplateInfs(tmplatInfVO: TemplateInfVO?): MutableMap<String?, Any?>?

    /**
     * 템플릿 구분에 따른 목록을 조회한다.
     * @return
     *
     * @param tmplatInfVO
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun selectTemplateInfsByCode(tmplatInfVO: TemplateInfVO?): MutableList<TemplateInfVO?>?

    /**
     * 템플릿에 대한 미리보기 정보를 조회한다.
     * @return
     *
     * @param tmplatInfVO
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun selectTemplatePreview(tmplatInfVO: TemplateInfVO?): TemplateInfVO?

    /**
     * 템플릿 정보를 수정한다.
     *
     * @param tmplatInf
     * @exception Exception Exception
     */
    @Throws(Exception::class)
    fun updateTemplateInf(tmplatInf: TemplateInf?)
}