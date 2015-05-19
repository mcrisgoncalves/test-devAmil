package br.com.projeto.test;

import org.junit.Test;

import br.com.projeto.exception.FileReadException;
import br.com.projeto.file.FileHelper;

public class FileReadTest {

	@Test
	public void lerArquivo() throws FileReadException {
		
		new FileHelper().readFile();
	}
}
