package setup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import util.PropertyReader;

public class RecuperarSenha {

	PropertyReader reader = new PropertyReader();

	/**
	 * MÉTODO PARA VALIDAR SE O S.O É WINDOWS OU LINUX, RECUPERANDO A SENHA RADOMICAMENTE
	 * 
	 * @return senha do arquivo de logs
	 * @throws IOException
	 */
	public String recuperarSenha(String tipoSenha) throws IOException {
		String senha = null;
		if (System.getProperty("os.name").contains("Windows")) {
			if (tipoSenha.equals(Senhas.APLICACAO.toString())) {
				senha = this.recuperaArquivoConfiguracao(Senhas.APLICACAO.toString(),
						reader.readProperty("senha.path.windows"), reader.readProperty("senha.nome.arquivo"));
			} else if (tipoSenha.equals(Senhas.DATABASE.toString())) {
				senha = this.recuperaArquivoConfiguracao(Senhas.DATABASE.toString(),
						reader.readProperty("senha.path.windows"), reader.readProperty("senha.nome.arquivo"));
			}
		} else {
			if (tipoSenha.equals(Senhas.APLICACAO.toString())) {
				senha = this.recuperaArquivoConfiguracao(Senhas.APLICACAO.toString(),
						reader.readProperty("senha.path.linux"), reader.readProperty("senha.nome.arquivo"));
				
			} else if (tipoSenha.equals(Senhas.DATABASE.toString())) {
				senha = this.recuperaArquivoConfiguracao(Senhas.DATABASE.toString(),
						reader.readProperty("senha.path.linux"), reader.readProperty("senha.nome.arquivo"));
			}
		}
		return senha.replaceAll(" ", "");
	}

	/**
	 * 
	 * @param tipoSenha:
	 *            Valores do Enhum "Senhas" que podem ser APLICACAO/DATABASE
	 * @param path:
	 *            Caminho onde está o log gerado pela instalação da aplicação
	 * @param nomeArquivo:
	 *            Nome do arquivo gerado com os logs e senhas da aplicação
	 * @return a senha
	 * @throws IOException
	 */
	public String recuperaArquivoConfiguracao(String tipoSenha, String path, String nomeArquivo) throws IOException {
		String resultado = null;
		Scanner in = null;
		File baseFolder = new File(path);
		File[] files = baseFolder.listFiles();
		try {
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				if (file.getPath().endsWith(nomeArquivo)) {
					in = new Scanner(new FileReader(file));
					while (in.hasNextLine()) {
						String line = in.nextLine();
						
						if (tipoSenha.equals(Senhas.APLICACAO.toString())) {
							if (line.contains("Application password:")) {
								resultado = line.replace("** Application password:      ", "").replace("  **", "");
							}
						}
						if (tipoSenha.equals(Senhas.DATABASE.toString())) {
							if (line.contains("Database root password:")) {
								resultado = line.replace("** Database root password:    ", "").replace("  **", "");
							}
						}
					}
				}
			}
		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
