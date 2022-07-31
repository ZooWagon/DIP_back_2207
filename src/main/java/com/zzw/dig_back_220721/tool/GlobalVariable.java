package com.zzw.dig_back_220721.tool;


import java.util.HashMap;

public class GlobalVariable {
    public static String inputImgPath="/soft/dip2207_back/input/";
    public static String outputImgPath="/soft/dip2207_back/output/";
    public static String pyProPath="/soft/dip2207_back/py/";
    public static String filePath="/soft/dip2207_back/file/";
    public static HashMap<String,String> tcode2py = new HashMap<String, String>(){{
        put("1","style_transfer.py");
        put("2","clock_identify.py");
        put("3011","B3011-three-channel.py");
        put("3012","B3012-BGR2GRAY.py");
        put("3013","B3013-binarization.py");
        put("3021","B3021-resize.py");
        put("3022","B3022-translation.py");
        put("3023","B3023-mirror-flip.py");
        put("3024","B3024-zoom.py");
        put("3025","B3025-rotate.py");
        put("3026","B3026-warp-affine.py");
        put("3031","B3031-region-grow.py");
        put("3032","B3032-split-and-merge.py");
        put("3040","B3040-edge-detect.py");
        put("3046","B3046-hough-line.py");
        put("3051","B3051-add-noise.py");
        put("3052","B3052-remove-noise.py");
        put("3061","B3061-highpass-filter.py");
        put("3062","B3062-differentiation.py");
        put("3063","B3063-antishaped-mask.py");
    }};
}
