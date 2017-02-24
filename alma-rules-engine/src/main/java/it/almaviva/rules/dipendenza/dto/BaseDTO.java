/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.almaviva.rules.dipendenza.dto;

import it.almaviva.rules.dipendenza.utils.Trace;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;


public abstract class BaseDTO extends Object implements Serializable {


	
    public Map parametersMap() {
        Map map = null;
        try {
            map = BeanUtilsBean.getInstance().getPropertyUtils().describe(this);
        } catch (Exception e) {
           Trace.error(this, "parameterMaps error:", e);
        }
        return map;
    }
}
