package com.jygis.geocoding;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Handles requests for the application home page.
 */
@Controller
public class GeoCodeController {

    @Autowired
    private GeoCoding geoCoding;

    @RequestMapping(value = "/Geocoding.do", method = RequestMethod.GET)
    public String Addrposition(Model model, HttpServletRequest request) {

        //	Double x = Double.parseDouble(request.getParameter("x"));
        //	Double y = Double.parseDouble(request.getParameter("y"));
        String addr = request.getParameter("addr");
        //String bonbu = request.getParameter("bonbu");

        //String s = new GeoCoding().AddrToXY(addr);

        //System.out.println("s ==>" + s);

        model.addAttribute("result", geoCoding.AddrToXY(addr));

        return "postion";
    }

    @Cacheable(cacheNames = "addrCacheData")
    @RequestMapping(value = "/Geocoding-json.do", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map json(HttpServletRequest request, @RequestParam("addr") String Addr) {
        Map resultMap = new HashMap<String, String>();
        resultMap.put("result", geoCoding.AddrToXY(Addr));
        return resultMap;
    }

    @Cacheable(cacheNames = "RerverseCacheData")
    @RequestMapping(value = "/reverse-json.do", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map rerversejson(HttpServletRequest request) {
        Double x = Double.parseDouble(request.getParameter("x"));
        Double y = Double.parseDouble(request.getParameter("y"));

        System.out.println("x : " + x);
        System.out.println("y : " + y);

        Map resultMap = new HashMap<String, String>();
        resultMap.put("result", geoCoding.XYToADDR(x, y));

        return resultMap;
    }

}
