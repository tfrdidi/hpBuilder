package org.hpbuilder.misc;


import com.google.appengine.repackaged.com.google.gson.Gson;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

/**
 * Class that manages the metadata of all sites.
 */
public class SiteManager {

    private Map<String, Site> sites;
    private String startSiteUri;
    private String appUrl;
    private String contentSubDir;

    public SiteManager() {
        this("sites/content/", "sites/siteStructure.json", "http://driven-park-107519.appspot.com");
    }

    public SiteManager(String contentSubDir, String configFile, String appUrl) {
        this.appUrl = appUrl;
        this.contentSubDir = contentSubDir;
        this.sites = new HashMap<>();
        readSiteStructure(configFile);
        readSiteContent();
    }

    private void readSiteStructure(String configFile) {
        Gson gson = new Gson();
        try {
            String siteStructureFile = Reader.getFileContentAsString(configFile);

            Site[] siteStructure = gson.fromJson(siteStructureFile, Site[].class);
            boolean firstSite = true;
            for (Site s : siteStructure) {
                s.setFile(contentSubDir + s.getFile());
                sites.put(s.getFile(), s);
                if(firstSite) {
                    startSiteUri = s.getFile();
                    firstSite = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readSiteContent() {
        try {
            List<Site> siteList = new ArrayList<>(sites.values());
            for(Site site : siteList) {
                String content = Reader.getFileContentAsString(site.getFile());
                String ogTitle = site.getTitle();
                String ogUrl = appUrl + "/" + site.getCssId();
                String ogImage = appUrl + site.getImage();
                String ogDescription = site.getDescription();
                String masterLayout = Reader.getFileContentAsString(site.getMasterLayout());
                String siteContent = MessageFormat.format(masterLayout, ogTitle, ogUrl,
                        ogImage, ogDescription, site.getCssId(), content);
                site.setContent(siteContent);
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
        return startSiteUri;
    }
}
