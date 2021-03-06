/*
 * $Id$
 *
 * All Rights Reserved 2013 China OPS Information Technology Co.,Ltd.
 */
package com.chinaops.web.edesktop.test;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Harley Ren  
 */
@Controller
public class TestController {
    // ========================== Attributes ============================

    // ========================= Constructors ===========================

    // ======================= Getters & Setters ========================

    // ======================== Public methods ==========================
    @RequestMapping(value = "/test.do")
    public void doTest(@RequestParam String agent,HttpServletResponse response) {
        log.debug("Receive request from Agent:" + agent);
        try {
            response.getWriter().write("Receive request from Agent:" + agent);
        } catch (IOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
    }
    // ==================== Private utility methods =====================

    // ========================== main method ===========================
    private final Log    log = LogFactory.getLog(this.getClass());
}
