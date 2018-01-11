package com.pts3.sport.dao;

/**
 * Created by Ragnulf on 10/12/2017.
 */

public class Criteres {

    private int id;
    private int competence;
    private int type;
    private String choix1;
    private String choix2;
    private String choix3;
    private String choix4;
    private String choix5;
    private String choix6;
    private float point1;
    private float point2;
    private float point3;
    private float point4;
    private float  point5;
    private float point6;
    private int idsport;

    public Criteres(int id, int competence, int type, int idsport) {
        this.id = id;
        this.competence = competence;
        this.type = type;
        this.idsport = idsport;
    }

    public void setChoix1(String choix1) {
        this.choix1 = choix1;
    }

    public void setChoix2(String choix2) {
        this.choix2 = choix2;
    }

    public void setChoix3(String choix3) {
        this.choix3 = choix3;
    }

    public void setChoix4(String choix4) {
        this.choix4 = choix4;
    }

    public void setChoix5(String choix5) {
        this.choix5 = choix5;
    }

    public void setChoix6(String choix6) {
        this.choix6 = choix6;
    }

    public void setPoint1(float point1) {
        this.point1 = point1;
    }

    public void setPoint2(float point2) {
        this.point2 = point2;
    }

    public void setPoint3(float point3) {
        this.point3 = point3;
    }

    public void setPoint4(float point4) {
        this.point4 = point4;
    }

    public void setPoint5(float point5) {
        this.point5 = point5;
    }

    public void setPoint6(float point6) {
        this.point6 = point6;
    }


    public int getCompetence() {
        return competence;
    }

    public int getType() {
        return type;
    }

    public String getChoix1() {
        return choix1;
    }

    public String getChoix2() {
        return choix2;
    }

    public String getChoix3() {
        return choix3;
    }

    public String getChoix4() {
        return choix4;
    }

    public String getChoix5() {
        return choix5;
    }

    public String getChoix6() {
        return choix6;
    }

    public float getPoint1() {
        return point1;
    }

    public float getPoint2() {
        return point2;
    }

    public float getPoint3() {
        return point3;
    }

    public float getPoint4() {
        return point4;
    }

    public float getPoint5() {
        return point5;
    }

    public float getPoint6() {
        return point6;
    }

    public int getId() {
        return id;
    }

    public int getIdsport() {
        return idsport;
    }
}
