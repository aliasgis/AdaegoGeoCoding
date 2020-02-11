package com.jygis.geocoding.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
public class FileUtil {

	public static void makeDir(String path) {

        File dir = new File(path);

        if(!dir.isDirectory()){
        	if(!dir.mkdirs()){
        		log.error("{}", path + " 디렉토리 생성 실패. 퍼미션 체크가 필요합니다.");
        	}
        }
	}
}