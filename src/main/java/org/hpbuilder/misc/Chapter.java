package org.hpbuilder.misc;

/**
 * Represents a part of the whole homepage with several sizes.
 * All sites of the Chapter get links on each other.
 */
public class Chapter {
    private String pathOfLinkTemplate;
    private Site[] siteList;
    private String startSite;
    private String directory;

    public String getPathOfLinkTemplate() {
        return pathOfLinkTemplate;
    }

    public void setPathOfLinkTemplate(String pathOfLinkTemplate) {
        this.pathOfLinkTemplate = pathOfLinkTemplate;
    }

    public Site[] getSiteList() {
        return siteList;
    }

    public void setSiteList(Site[] siteList) {
        this.siteList = siteList;
    }

    public String getStartSite() {
        return startSite;
    }

    public void setStartSite(String startSite) {
        this.startSite = startSite;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }
}
