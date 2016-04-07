package com.aspsine.irecyclerview.demo;

/**
 * Created by aspsine on 16/4/6.
 */
public class Constants {
    private static final String GITHUB = "https://raw.githubusercontent.com/";
    private static final String AUTHOR = "Aspsine";
    private static final String BRANCH = "master";
    private static final String REPOSITORY = "IRecyclerView";
    private static final String ASSETS = "/app/src/main/assets";
    private static final String API = "api/superman_vs_batman/gallery";
    private static final String ENDPOINT = GITHUB + AUTHOR + "/" + REPOSITORY + "/" + BRANCH + ASSETS + "/" + API;

    public static final String BannerAPI = ENDPOINT + "/banner.json";

    public static String ImagesAPI(int page) {
        return ENDPOINT + "/" + page + ".json";
    }

}
