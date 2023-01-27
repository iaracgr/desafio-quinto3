package com.gonzalez.desafioquintoimpacto.config;


public enum RoleType {

    ADMIN,STUDENT,PROFESSOR;
    private static final String ROLE_PREFIX = "ROLE_";

    public String getFullRoleName(){return ROLE_PREFIX + this.name();}

}
