package com.kangle.study.excel;

import com.teamcenter.rac.aif.AbstractAIFOperation;

/**
 * 
 * @author haozt
 * 2017��12��14��
 */
public class ExcelImportOperation extends AbstractAIFOperation{


	private ExcelImportDialog dialog;

	public ExcelImportOperation(ExcelImportDialog dialog) {
		super();
		this.dialog =dialog;
	}
	
	
	@Override
	public void executeOperation() throws Exception {
		// TODO Auto-generated method stub
		dialog.onButtonEvent();
	}

}
