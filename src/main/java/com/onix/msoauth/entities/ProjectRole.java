package com.onix.msoauth.entities;

public enum ProjectRole {
    PM("Project manager"),
    TL("Team Lead"),
    Arch("Architect"),
    Dev("Developer"),
    DevOps("DevOps"),
    QA("Quality Automation");
    private String label;

    ProjectRole(String label) {
        this.label = label;
    }

    public static ProjectRole getByLabel(String label){
        for (ProjectRole pr : ProjectRole.values()) {
            if (label.equalsIgnoreCase(pr.label)) return pr;
        }
        return null;
    }
}
