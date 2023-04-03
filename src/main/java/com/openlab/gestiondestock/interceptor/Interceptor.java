package com.openlab.gestiondestock.interceptor;

import org.hibernate.EmptyInterceptor;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

public class Interceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql){
        if(StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")){
            final String entityName = sql.substring(7, sql.indexOf("."));
            final String idEntreprise = MDC.get("idEntreprise");
            if(StringUtils.hasLength(entityName) && !entityName.toLowerCase().contains("entreprise")
                                                 && !entityName.toLowerCase().contains("roles")
                                                 && StringUtils.hasLength(idEntreprise)){

                if(sql.contains("where")){
                    sql += sql + "and" + entityName + ".idEntreprise = " +idEntreprise ;
                }else{
                    sql += sql + "where" + entityName + ".idEntreprise = " +idEntreprise ;
                }

            }

        }
        return super.onPrepareStatement(sql);
    }
}
