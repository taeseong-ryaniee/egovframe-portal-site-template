package egovframework.com.cmm.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.beans.PropertyEditorSupport

/**
 * atchFileId 파라미터 복호화 클래스
 *
 * @author 표준프레임워크팀 신용호
 * @since 2022.12.22
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력
</pre> */
class EgovAtchFileIdPropertyEditor : PropertyEditorSupport() {
    @Throws(IllegalArgumentException::class)
    override fun setAsText(text: String?) {
        LOGGER.debug("===>>> setText : " + text)
        var decryptText: String? = ""
        if (text != null && "" != text) {
            try {
                decryptText = EgovFileMngController.Companion.decrypt(text)
            } catch (e: Exception) {
                LOGGER.debug(e.message)
                decryptText = "FILE_ID_DECRIPT_EXCEPTION_01"
            }
        }
        this.setValue(decryptText)
    }


    override fun getAsText(): String? {
        LOGGER.debug("===>>> getText : " + getValue())
        return getValue().toString()
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(EgovAtchFileIdPropertyEditor::class.java)
    }
}
