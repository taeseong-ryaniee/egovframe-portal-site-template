package egovframework.let.uss.umt.service.impl

import egovframework.let.uss.umt.service.EgovMberManageService
import egovframework.let.uss.umt.service.MberManageVO
import egovframework.let.uss.umt.service.UserDefaultVO
import egovframework.let.utl.sim.service.EgovFileScrty
import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 일반회원관리에 관한비지니스클래스를 정의한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
@Service("mberManageService")
class EgovMberManageServiceImpl : EgovAbstractServiceImpl(), EgovMberManageService {
    /** mberManageDAO  */
    @Resource(name = "mberManageDAO")
    private val mberManageDAO: MberManageDAO? = null

    /** egovUsrCnfrmIdGnrService  */
    @Resource(name = "egovUsrCnfrmIdGnrService")
    private val idgenService: EgovIdGnrService? = null

    /**
     * 사용자의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param mberManageVO 일반회원 등록정보
     * @return result 등록결과
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun insertMber(mberManageVO: MberManageVO): Int {
        //고유아이디 셋팅
        val uniqId = idgenService!!.getNextStringId()
        mberManageVO.setUniqId(uniqId)
        //패스워드 암호화
        val pass = EgovFileScrty.encryptPassword(mberManageVO.getPassword(), mberManageVO.getMberId())
        mberManageVO.setPassword(pass)

        val result = mberManageDAO!!.insertMber(mberManageVO)
        return result
    }

    /**
     * 기 등록된 사용자 중 검색조건에 맞는 일반회원의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param uniqId 상세조회대상 일반회원아이디
     * @return mberManageVO 일반회원상세정보
     * @throws Exception
     */
    override fun selectMber(uniqId: String?): MberManageVO? {
        val mberManageVO = mberManageDAO!!.selectMber(uniqId)
        return mberManageVO
    }

    /**
     * 기 등록된 회원 중 검색조건에 맞는 회원들의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param userSearchVO 검색조건
     * @return List<MberManageVO> 일반회원목록정보
    </MberManageVO> */
    override fun selectMberList(userSearchVO: UserDefaultVO?): MutableList<MberManageVO?>? {
        return mberManageDAO!!.selectMberList(userSearchVO)
    }

    /**
     * 일반회원 총 갯수를 조회한다.
     * @param userSearchVO 검색조건
     * @return 일반회원총갯수(int)
     */
    override fun selectMberListTotCnt(userSearchVO: UserDefaultVO?): Int {
        return mberManageDAO!!.selectMberListTotCnt(userSearchVO)
    }

    /**
     * 화면에 조회된 일반회원의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param mberManageVO 일반회원수정정보
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun updateMber(mberManageVO: MberManageVO) {
        //패스워드 암호화
        val pass = EgovFileScrty.encryptPassword(mberManageVO.getPassword(), mberManageVO.getMberId())
        mberManageVO.setPassword(pass)
        mberManageDAO!!.updateMber(mberManageVO)
    }

    /**
     * 화면에 조회된 사용자의 정보를 데이터베이스에서 삭제
     * @param checkedIdForDel 삭제대상 일반회원아이디
     * @throws Exception
     */
    override fun deleteMber(checkedIdForDel: String) {
        val delId: Array<String?> = checkedIdForDel.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (i in delId.indices) {
            val id: Array<String?> = delId[i]!!.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (id[0] == "USR03") {
                //업무사용자(직원)삭제
            } else if (id[0] == "USR01") {
                //일반회원삭제
                mberManageDAO!!.deleteMber(id[1])
            } else if (id[0] == "USR02") {
                //기업회원삭제
            }
        }
    }

    /**
     * 일반회원 약관확인
     * @param stplatId 일반회원약관아이디
     * @return 일반회원약관정보(List)
     * @throws Exception
     */
    override fun selectStplat(stplatId: String?): MutableList<*>? {
        return mberManageDAO!!.selectStplat(stplatId)
    }

    /**
     * 일반회원암호수정
     * @param mberManageVO 일반회원수정정보(비밀번호)
     * @throws Exception
     */
    override fun updatePassword(mberManageVO: MberManageVO?) {
        mberManageDAO!!.updatePassword(mberManageVO)
    }

    /**
     * 일반회원이 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
     * @param passVO 일반회원암호 조회조건정보
     * @return mberManageVO 일반회원암호정보
     * @throws Exception
     */
    override fun selectPassword(passVO: MberManageVO?): MberManageVO? {
        val mberManageVO = mberManageDAO!!.selectPassword(passVO)
        return mberManageVO
    }

    /**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     * @param checkId 중복여부 확인대상 아이디
     * @return 사용가능여부(아이디 사용회수 int)
     * @throws Exception
     */
    override fun checkIdDplct(checkId: String?): Int {
        return mberManageDAO!!.checkIdDplct(checkId)
    }
}