/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.almaviva.rules.dipendenza.dto;


/**
 *
 * @author Bitmedia_Vizzarro
 */
public class DataFieldsPagePrecVersionDTO extends DataFieldPageDTO {
  
    private String valuePreviousVersion; 
    
    //Per metriche
	private String metr1;
	private String metr2;
	private String metr3;
  
    public DataFieldsPagePrecVersionDTO() {
    	
    }

	public String getValuePreviousVersion() {
		return valuePreviousVersion;
	}

	public void setValuePreviousVersion(String valuePreviousVersion) {
		this.valuePreviousVersion = valuePreviousVersion;
	}

}
