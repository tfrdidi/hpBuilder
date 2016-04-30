package org.hpbuilder.misc;

/**
 * Object representing one site of this website.
 */
public class Site {
    private String title;
    private String file;
    private String image;
    private String subtitle;
    private String cssId;
    private String description;
    private String content;
    private String masterLayout;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCssId() {
        return cssId;
    }

    public void setCssId(String cssId) {
        this.cssId = cssId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.cssId = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMasterLayout() {
        return masterLayout;
    }

    public void setMasterLayout(String masterLayout) {
        this.masterLayout = masterLayout;
    }
}
