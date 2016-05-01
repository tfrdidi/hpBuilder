package org.hpbuilder.model;

/**
 * Represents a whole homepage with all necessary information to publish in GAE.
 */
public class Homepage {

    private String gaeUrl;
    private Chapter root;

    public String getGaeUrl() {
        return gaeUrl;
    }

    public void setGaeUrl(String gaeUrl) {
        this.gaeUrl = gaeUrl;
    }

    public Chapter getRoot() {
        return root;
    }

    public void setRoot(Chapter root) {
        this.root = root;
    }
}
