package egovframework.let.uss.umt.service

/**
 * 일반회원관리에 관한 인터페이스클래스를 정의한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
interface EgovMberManageService {
    /**
     * 사용자의 기본정보를 화면에서 입력하여 항목의 정합성을 체크하고 데이터베이스에 저장
     * @param mberManageVO 일반회원 등록정보
     * @return 등록결과
     * @throws Exception
     */
    @Throws(Exception::class)
    fun insertMber(mberManageVO: MberManageVO?): Int

    /**
     * 기 등록된 사용자 중 검색조건에 맞는 일반회원의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param mberId 상세조회대상 일반회원아이디
     * @return mberManageVO 일반회원상세정보
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectMber(mberId: String?): MberManageVO?

    /**
     * 기 등록된 회원 중 검색조건에 맞는 회원들의 정보를 데이터베이스에서 읽어와 화면에 출력
     * @param userSearchVO 검색조건
     * @return List<MberManageVO> 일반회원목록정보
     * @throws Exception
    </MberManageVO> */
    @Throws(Exception::class)
    fun selectMberList(userSearchVO: UserDefaultVO?): MutableList<MberManageVO?>?

    /**
     * 일반회원 총 갯수를 조회한다.
     * @param userSearchVO 검색조건
     * @return 일반회원총갯수(int)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectMberListTotCnt(userSearchVO: UserDefaultVO?): Int

    /**
     * 화면에 조회된 일반회원의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * @param mberManageVO 일반회원수정정보
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateMber(mberManageVO: MberManageVO?)

    /**
     * 화면에 조회된 사용자의 정보를 데이터베이스에서 삭제
     * @param checkedIdForDel 삭제대상 일반회원아이디
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteMber(checkedIdForDel: String?)

    /**
     * 일반회원 약관확인
     * @param stplatId 일반회원약관아이디
     * @return 일반회원약관정보(List)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectStplat(stplatId: String?): MutableList<*>?

    /**
     * 일반회원암호수정
     * @param mberManageVO 일반회원수정정보(비밀번호)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updatePassword(mberManageVO: MberManageVO?)

    /**
     * 일반회원이 비밀번호를 기억하지 못할 때 비밀번호를 찾을 수 있도록 함
     * @param passVO 일반회원암호 조회조건정보
     * @return mberManageVO 일반회원암호정보
     * @throws Exception
     */
    @Throws(Exception::class)
    fun selectPassword(passVO: MberManageVO?): MberManageVO?

    /**
     * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
     * @param checkId 중복여부 확인대상 아이디
     * @return 사용가능여부(아이디 사용회수 int)
     * @throws Exception
     */
    @Throws(Exception::class)
    fun checkIdDplct(checkId: String?): Int
}