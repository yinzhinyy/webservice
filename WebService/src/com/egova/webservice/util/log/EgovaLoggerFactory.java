/*
 * Created on 2004-10-8
 *
 * add by zzm@egova.com.cn
 */
package com.egova.webservice.util.log;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

/**
 * @author zzm
 */
class EgovaLoggerFactory implements LoggerFactory {

    /* (non-Javadoc)
     * @see org.apache.log4j.spi.LoggerFactory#makeNewLoggerInstance(java.lang.String)
     */
    public Logger makeNewLoggerInstance(String name) {
        return new ServerLogger(name);
    }

}
