package egovframework.let.uss.umt.service

/**
 * 일반회원VO클래스로서 일반회원관리 비지니스로직 처리용 항목을 구성한다.
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class MberManageVO : UserDefaultVO() {
    /**
     * oldPassword attribute 값을  리턴한다.
     * @return String
     */
    /**
     * oldPassword attribute 값을 설정한다.
     * @param oldPassword String
     */
    /** 이전비밀번호 - 비밀번호 변경시 사용 */
    var oldPassword: String? = ""

    /**
     * uniqId attribute 값을  리턴한다.
     * @return String
     */
    /**
     * uniqId attribute 값을 설정한다.
     * @param uniqId String
     */
    /**
     * 사용자고유아이디
     */
    var uniqId: String? = ""
    /**
     * userTy attribute 값을  리턴한다.
     * @return String
     */
    /**
     * userTy attribute 값을 설정한다.
     * @param userTy String
     */
    /**
     * 사용자 유형
     */
    var userTy: String? = null
    /**
     * adres attribute 값을  리턴한다.
     * @return String
     */
    /**
     * adres attribute 값을 설정한다.
     * @param adres String
     */
    /**
     * 주소
     */
    var adres: String? = null
    /**
     * detailAdres attribute 값을  리턴한다.
     * @return String
     */
    /**
     * detailAdres attribute 값을 설정한다.
     * @param detailAdres String
     */
    /**
     * 상세주소
     */
    var detailAdres: String? = null
    /**
     * endTelno attribute 값을  리턴한다.
     * @return String
     */
    /**
     * endTelno attribute 값을 설정한다.
     * @param endTelno String
     */
    /**
     * 끝전화번호
     */
    var endTelno: String? = null
    /**
     * mberFxnum attribute 값을  리턴한다.
     * @return String
     */
    /**
     * mberFxnum attribute 값을 설정한다.
     * @param mberFxnum String
     */
    /**
     * 팩스번호
     */
    var mberFxnum: String? = null
    /**
     * groupId attribute 값을  리턴한다.
     * @return String
     */
    /**
     * groupId attribute 값을 설정한다.
     * @param groupId String
     */
    /**
     * 그룹 ID
     */
    var groupId: String? = null
    /**
     * ihidnum attribute 값을  리턴한다.
     * @return String
     */
    /**
     * ihidnum attribute 값을 설정한다.
     * @param ihidnum String
     */
    /**
     * 주민등록번호
     */
    var ihidnum: String? = null
    /**
     * sexdstnCode attribute 값을  리턴한다.
     * @return String
     */
    /**
     * sexdstnCode attribute 값을 설정한다.
     * @param sexdstnCode String
     */
    /**
     * 성별코드
     */
    var sexdstnCode: String? = null
    /**
     * mberId attribute 값을  리턴한다.
     * @return String
     */
    /**
     * mberId attribute 값을 설정한다.
     * @param mberId String
     */
    /**
     * 회원 ID
     */
    var mberId: String? = null
    /**
     * mberNm attribute 값을  리턴한다.
     * @return String
     */
    /**
     * mberNm attribute 값을 설정한다.
     * @param mberNm String
     */
    /**
     * 회원명
     */
    var mberNm: String? = null
    /**
     * mberSttus attribute 값을  리턴한다.
     * @return String
     */
    /**
     * mberSttus attribute 값을 설정한다.
     * @param mberSttus String
     */
    /**
     * 회원상태
     */
    var mberSttus: String? = null
    /**
     * areaNo attribute 값을  리턴한다.
     * @return String
     */
    /**
     * areaNo attribute 값을 설정한다.
     * @param areaNo String
     */
    /**
     * 지역번호
     */
    var areaNo: String? = null
    /**
     * middleTelno attribute 값을  리턴한다.
     * @return String
     */
    /**
     * middleTelno attribute 값을 설정한다.
     * @param middleTelno String
     */
    /**
     * 중간전화번호
     */
    var middleTelno: String? = null
    /**
     * moblphonNo attribute 값을  리턴한다.
     * @return String
     */
    /**
     * moblphonNo attribute 값을 설정한다.
     * @param moblphonNo String
     */
    /**
     * 핸드폰번호
     */
    var moblphonNo: String? = null
    /**
     * password attribute 값을  리턴한다.
     * @return String
     */
    /**
     * password attribute 값을 설정한다.
     * @param password String
     */
    /**
     * 비밀번호
     */
    var password: String? = null
    /**
     * passwordCnsr attribute 값을  리턴한다.
     * @return String
     */
    /**
     * passwordCnsr attribute 값을 설정한다.
     * @param passwordCnsr String
     */
    /**
     * 비밀번호 정답
     */
    var passwordCnsr: String? = null
    /**
     * passwordHint attribute 값을  리턴한다.
     * @return String
     */
    /**
     * passwordHint attribute 값을 설정한다.
     * @param passwordHint String
     */
    /**
     * 비밀번호 힌트
     */
    var passwordHint: String? = null
    /**
     * sbscrbDe attribute 값을  리턴한다.
     * @return String
     */
    /**
     * sbscrbDe attribute 값을 설정한다.
     * @param sbscrbDe String
     */
    /**
     * 가입 일자
     */
    var sbscrbDe: String? = null
    /**
     * zip attribute 값을  리턴한다.
     * @return String
     */
    /**
     * zip attribute 값을 설정한다.
     * @param zip String
     */
    /**
     * 우편번호
     */
    var zip: String? = null
    /**
     * mberEmailAdres attribute 값을  리턴한다.
     * @return String
     */
    /**
     * mberEmailAdres attribute 값을 설정한다.
     * @param mberEmailAdres String
     */
    /**
     * 이메일주소
     */
    var mberEmailAdres: String? = null

    companion object {
        /**
         * serialVersionUID
         */
        private const val serialVersionUID = 1L
    }
}