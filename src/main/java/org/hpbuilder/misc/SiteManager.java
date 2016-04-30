package org.hpbuilder.misc;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that manages the metadata of all sites.
 */
public class SiteManager {

    private Chapter chapter;
    private String appUrl;
    private Map<String, Site> sites;

    public SiteManager() {
        this("sites/siteStructure.json", "http://driven-park-107519.appspot.com");
    }

    public SiteManager(String configFile, String appUrl) {
        this.appUrl = appUrl;
        this.sites = new HashMap<>();
        readSiteStructure(configFile);
        readSiteContent(chapter);
    }

    private void readSiteStructure(String configFile) {
        try {
            String siteStructureFile = Reader.getFileContentAsString(configFile);
            Gson gson = new GsonBuilder().create();

            chapter = gson.fromJson(siteStructureFile, Chapter.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readSiteContent(Chapter chapter) {
        try {
            for(Site site : chapter.getSiteList()) {
                String path = chapter.getDirectory() + site.getFile();
                String content = Reader.getFileContentAsString(path);
                String ogTitle = site.getTitle();
                String ogUrl = appUrl + "/" + site.getCssId();
                String ogImage = appUrl + site.getImage();
                String ogDescription = site.getDescription();
                String masterLayout = Reader.getFileContentAsString(site.getMasterLayout());
                String siteContent = MessageFormat.format(masterLayout, ogTitle, ogUrl,
                        ogImage, ogDescription, site.getCssId(), content);
                site.setContent(siteContent);
                sites.put(path, site);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Site getSite(String uri) {
        if(uri.startsWith("/")) {
            uri = uri.substring(1);
        }
        return sites.get(uri);
    }

    public String getStartSiteUri() {
        return chapter.getStartSite();
    }
}
