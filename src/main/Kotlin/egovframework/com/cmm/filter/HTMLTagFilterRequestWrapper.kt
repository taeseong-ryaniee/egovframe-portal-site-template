/*
 * Copyright 2008-2009 MOPAS(Ministry of Public Administration and Security).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.com.cmm.filter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper

class HTMLTagFilterRequestWrapper(request: HttpServletRequest) : HttpServletRequestWrapper(request) {
    override fun getParameterValues(parameter: String?): Array<String?>? {
        val values = super.getParameterValues(parameter)

        if (values == null) {
            return null
        }

        for (i in values.indices) {
            if (values[i] != null) {
                values[i] = getSafeParamData(values[i]!!)
                //System.out.println( "[HTMLTagFilter getParameterValues] "+ parameter + "===>>>"+values[i] );
            } else {
                values[i] = null
            }
        }

        return values
    }

    override fun getParameter(parameter: String?): String? {
        var value = super.getParameter(parameter)

        if (value == null) {
            return null
        }

        value = getSafeParamData(value)
        //System.out.println( "[HTMLTagFilter getParameter] "+ parameter + "===>>>"+value );
        return value
    }

    /**
     * Map으로 바인딩된 경우를 처리한다.
     *
     * @return  Map - String Type Key / String배열타입 값
     */
    override fun getParameterMap(): MutableMap<String?, Array<String?>?> {
        val valueMap = super.getParameterMap()

        var values: Array<String?>
        for (key in valueMap.keys) {
            values = valueMap.get(key)!!

            for (i in values.indices) {
                if (values[i] != null) {
                    values[i] = getSafeParamData(values[i]!!)
                    //System.out.println( "[HTMLTagFilter getParameterMap] "+ key + "===>>>"+values[i] );
                } else {
                    values[i] = null
                }
            }


            //System.out.println( String.format("키 : %s, 값 : %s", key, valueMap.get(key)) );
        }

        return valueMap
    }

    private fun getSafeParamData(value: String): String {
        var value = value
        val strBuff = StringBuffer()

        for (i in 0..<value.length) {
            val c = value.get(i)
            when (c) {
                '<' -> if (checkNextWhiteListTag(i, value) == false) strBuff.append("&lt;")
                else strBuff.append(c)

                '>' -> if (checkPrevWhiteListTag(i, value) == false) strBuff.append("&gt;")
                else strBuff.append(c)

                '"' -> strBuff.append("&quot;")
                '\'' -> strBuff.append("&apos;")
                '(' -> strBuff.append("&#40;")
                ')' -> strBuff.append("&#41;")
                else -> strBuff.append(c)
            }
        }

        value = strBuff.toString()
        return value
    }

    private fun checkNextWhiteListTag(index: Int, data: String): Boolean {
        var extractData = ""
        //int beginIndex = 0;
        var endIndex = 0
        for (whiteListData in whiteListTag) {
            //System.out.println("===>>> whiteListData="+whiteListData);
            endIndex = index + whiteListData!!.length
            if (data.length > endIndex) extractData = data.substring(index, endIndex)
            else extractData = ""
            //System.out.println("extractData="+extractData);
            if (whiteListData == extractData) return true // whiteList 대상으로 판정
        }

        return false
    }

    private fun checkPrevWhiteListTag(index: Int, data: String): Boolean {
        var extractData = ""
        var beginIndex = 0
        var endIndex = 0
        for (whiteListData in whiteListTag) {
            //System.out.println("===>>> whiteListData="+whiteListData);
            beginIndex = index - whiteListData!!.length + 1
            endIndex = index + 1
            //System.out.println("  range ["+beginIndex+" ~ "+endIndex+"]");
            if (beginIndex >= 0) extractData = data.substring(beginIndex, endIndex)
            else extractData = ""
            //System.out.println("extractData="+extractData);
            if (whiteListData == extractData) return true // whiteList 대상으로 판정
        }

        return false
    }

    companion object {
        // Tag 화이트 리스트 ( 허용할 태그 등록 )
        private val whiteListTag = arrayOf<String?>("<p>", "</p>", "<br />")
    }
}