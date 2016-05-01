package org.hpbuilder.misc;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hpbuilder.model.Chapter;
import org.hpbuilder.model.Homepage;
import org.hpbuilder.model.Site;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that manages the metadata of all sites.
 */
public class SiteManager {

    private Homepage homepage;
    private String startSiteUri;
    private Map<String, Site> sites;

    public SiteManager() {
        this("sites/siteStructure.json");
    }

    public SiteManager(String configFile) {
        this.sites = new HashMap<>();
        readSiteStructure(configFile);
        readSiteContent(homepage);
    }

    private void readSiteStructure(String configFile) {
        try {
            String siteStructureFile = Reader.getFileContentAsString(configFile);
            Gson gson = new GsonBuilder().create();

            homepage = gson.fromJson(siteStructureFile, Homepage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readSiteContent(Homepage homepage) {
        try {
            Chapter chapter = homepage.getRoot();
            String appUrl = homepage.getGaeUrl();

            startSiteUri = chapter.getDirectory() + chapter.getStartSite();

            // create links to all pages
            String siteLinkTemplate = Reader.getFileContentAsString(
                    chapter.getDirectory() + chapter.getPathOfLinkTemplate());

            for(Site site : chapter.getSiteList()) {
                String path = chapter.getDirectory() + site.getFile();
                String listOfContents = "";
                for(Site _site : chapter.getSiteList()) {
                    if(!_site.getFile().equals(site.getFile())) {
                        String link = MessageFormat.format(siteLinkTemplate,
                                _site.getCssId(), _site.getSubtitle(), _site.getTitle());
                        listOfContents += link;
                    }
                }
                String content = Reader.getFileContentAsString(path) + listOfContents;
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
        return startSiteUri;
    }
}
