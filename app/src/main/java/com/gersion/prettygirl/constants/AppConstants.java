package com.gersion.prettygirl.constants;

/**
 * Created by aa326 on 2017/10/17.
 */

public interface AppConstants {
    String BASE_URL = "http://120.79.49.134:8080/";
    String BASE_IMAGE_URL = "http://120.79.49.134/image/";
    String SP_FILE = "config";
    String QQ_APP_ID = "";
    String WEIXIN_APP_ID = "";
    String WEIXIN_APP_SECURITID = "";

    interface URLS {
        String GET_CATEGORY_LIST = BASE_URL + "image/getCategoryListByGirlType";//获取图片分类列表
        String GET_ALL_IMAGE_LIST = BASE_URL + "image/getAllImageCategory";//获取单个图片类别下的列表
        String GET_IMAGE_LIST = BASE_URL + "image/getImageListByCategoryId?categoryId=";//获取每个人的图片列表
        String GET_MAX_CATEGORY_ID = BASE_URL + "image/getMaxCategoryId";//获取CategoryId最大值
        String GET_MIN_CATEGORY_ID = BASE_URL + "image/getMinCategoryId";//获取CategoryId最小值
        String GET_CATEGORY_BY_IDS = BASE_URL + "image/getCategoryByIds";//获取CategoryIds对应的列表
    }
}
