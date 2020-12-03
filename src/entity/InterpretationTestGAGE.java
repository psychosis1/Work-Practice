package entity;

import java.time.LocalDateTime;

public class InterpretationTestGAGE {
    private int idInterpretationGAGE;
    private int test;
    private int overall_points_risk_abuse;
    private String risk_abuse;
    private int overall_points_risk_dependency;
    private String risk_dependency;
    private LocalDateTime date;

    public int getIdInterpretationGAGE() {
        return idInterpretationGAGE;
    }

    public void setIdInterpretationGAGE(int idInterpretationGAGE) {
        this.idInterpretationGAGE = idInterpretationGAGE;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public int getOverall_points_risk_abuse() {
        return overall_points_risk_abuse;
    }

    public void setOverall_points_risk_abuse(int overall_points_risk_abuse) {
        this.overall_points_risk_abuse = overall_points_risk_abuse;
    }

    public String getRisk_abuse() {
        return risk_abuse;
    }

    public void setRisk_abuse(String risk_abuse) {
        this.risk_abuse = risk_abuse;
    }

    public int getOverall_points_risk_dependency() {
        return overall_points_risk_dependency;
    }

    public void setOverall_points_risk_dependency(int overall_points_risk_dependency) {
        this.overall_points_risk_dependency = overall_points_risk_dependency;
    }

    public String getRisk_dependency() {
        return risk_dependency;
    }

    public void setRisk_dependency(String risk_dependency) {
        this.risk_dependency = risk_dependency;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "InterpretationTestGAGE{" +
                "idInterpretationGAGE=" + idInterpretationGAGE +
                ", test=" + test +
                ", overall_points_risk_abuse=" + overall_points_risk_abuse +
                ", risk_abuse='" + risk_abuse + '\'' +
                ", overall_points_risk_dependency=" + overall_points_risk_dependency +
                ", risk_dependency='" + risk_dependency + '\'' +
                ", date=" + date +
                '}';
    }
}
