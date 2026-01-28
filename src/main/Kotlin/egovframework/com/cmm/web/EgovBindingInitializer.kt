package egovframework.com.cmm.web

import org.springframework.beans.propertyeditors.CustomDateEditor
import org.springframework.beans.propertyeditors.StringTrimmerEditor
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.support.WebBindingInitializer
import java.text.SimpleDateFormat
import java.util.*

class EgovBindingInitializer : WebBindingInitializer {
    override fun initBinder(binder: WebDataBinder) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        dateFormat.setLenient(false)
        binder.registerCustomEditor(Date::class.java, CustomDateEditor(dateFormat, false))
        binder.registerCustomEditor(String::class.java, StringTrimmerEditor(false))

        binder.registerCustomEditor(String::class.java, "atchFileId", EgovAtchFileIdPropertyEditor())
    }
}
