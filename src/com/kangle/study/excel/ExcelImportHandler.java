package com.kangle.study.excel;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * 
 * @author haozt
 * 2017��12��14��
 */
public class ExcelImportHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		// TODO Auto-generated method stub
		ExcelImportDialog dialog = new ExcelImportDialog();
		new Thread(dialog).start();
		return null;
	}

}
