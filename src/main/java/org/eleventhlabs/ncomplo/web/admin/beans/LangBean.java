package org.eleventhlabs.ncomplo.web.admin.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;


public class LangBean implements Serializable {

    private static final long serialVersionUID = 5346733885599754478L;

    @NotNull
    private String lang;
    
    @NotNull
    private String value;
    
    
    public LangBean() {
        super();
    }


    public String getLang() {
        return this.lang;
    }


    public void setLang(String lang) {
        this.lang = lang;
    }


    public String getValue() {
        return this.value;
    }


    public void setValue(String value) {
        this.value = value;
    }
    
    
    
    public static List<LangBean> listFromMap(final Map<String,String> valuesByLang) {
        final List<LangBean> langBeans = new ArrayList<LangBean>();
        for (final Map.Entry<String,String> namesByLangEntry : valuesByLang.entrySet()) {
            final LangBean langBean = new LangBean();
            langBean.setLang(namesByLangEntry.getKey());
            langBean.setValue(namesByLangEntry.getValue());
            langBeans.add(langBean);
        }
        return langBeans;
    }
    
    
    
    public static Map<String,String> mapFromList(final List<LangBean> langBeans) {
        final Map<String,String> valuesByLang = new LinkedHashMap<String, String>();
        for (final LangBean langBean : langBeans) {
            valuesByLang.put(langBean.getLang(), langBean.getValue());
        }
        return valuesByLang;
    }
    
    
}
