package egovframework.com.cmm

import org.egovframe.rte.psl.orm.ibatis.support.AbstractLobTypeHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.support.lob.LobCreator
import org.springframework.jdbc.support.lob.LobHandler
import java.io.IOException
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

/*
* Copyright 2002-2005 the original author or authors.
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

/**
 * iBATIS TypeHandler implementation for Strings that get mapped to CLOBs.
 * Retrieves the LobHandler to use from SqlMapClientFactoryBean at config time.
 *
 *
 * Particularly useful for storing Strings with more than 4000 characters in an
 * Oracle database (only possible via CLOBs), in combination with OracleLobHandler.
 *
 *
 * Can also be defined in generic iBATIS mappings, as DefaultLobCreator will
 * work with most JDBC-compliant database drivers. In this case, the field type
 * does not have to be BLOB: For databases like MySQL and MS SQL Server, any
 * large enough binary type will work.
 *
 * @author Juergen Hoeller
 * @since 1.1.5
 * @see org.springframework.orm.ibatis.SqlMapClientFactoryBean.setLobHandler
 */
@Suppress("deprecation")
class AltibaseClobStringTypeHandler : AbstractLobTypeHandler {
    /**
     * Constructor used by iBATIS: fetches config-time LobHandler from
     * SqlMapClientFactoryBean.
     * @see org.springframework.orm.ibatis.SqlMapClientFactoryBean.getConfigTimeLobHandler
     */
    constructor() : super()

    /**
     * Constructor used for testing: takes an explicit LobHandler.
     */
    protected constructor(lobHandler: LobHandler) : super(lobHandler)

    @Throws(SQLException::class)
    override fun setParameterInternal(
        ps: PreparedStatement,
        index: Int,
        value: Any?,
        jdbcType: String?,
        lobCreator: LobCreator
    ) {
        lobCreator.setClobAsString(ps, index, value as String?)
    }

    @Throws(SQLException::class)
    override fun getResultInternal(rs: ResultSet, index: Int, lobHandler: LobHandler): Any {
        val read_data = StringBuffer("")
        var read_length: Int

        val buf = CharArray(1024)

        val rd = lobHandler.getClobAsCharacterStream(rs, index)
        try {
            while ((rd.read(buf).also { read_length = it }) != -1) {
                read_data.append(buf, 0, read_length)
            }
        } catch (ie: IOException) {
            LOGGER.debug("ie: {}", ie)
        } finally {
            if (rd != null) {
                try {
                    rd.close()
                } catch (ignore: IOException) {
                    LOGGER.debug("IGNORE: {}", ignore.message)
                }
            }
        }

        return read_data.toString()
    }

    override fun valueOf(s: String?): Any? {
        return s
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(AltibaseClobStringTypeHandler::class.java)
    }
}
